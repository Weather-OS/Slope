package com.slope.ui;

import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.text.Text;
import java.util.function.DoubleUnaryOperator;

public class Plotter
{
    Canvas canvas;
    static double boundary;
    static double graphSize;

    private double toScreenX( double x )
    {
        return ( (x + boundary) / (2.0 * boundary) ) * canvas.getWidth();
    }

    private double toScreenY( double y )
    {
        return canvas.getHeight() - ( (y + boundary) / (2.0 * boundary) ) * canvas.getHeight();
    }

    private void drawCenteredText( double x, double y, String s )
    {
        var context = canvas.getGraphicsContext2D();

        Text textNode = new Text( s );
        textNode.setFont( context.getFont() );

        context.fillText( s, x - textNode.getLayoutBounds().getWidth()/2, y - textNode.getLayoutBounds().getHeight()/2 );
    }

    public void collisionDot( double atX, double atY )
    {
        var context = canvas.getGraphicsContext2D();
        Paint prevColor = context.getStroke();

        // Drawing a circle starts at it's most corner position.
        // So we have to remove 2.5 from each axis.
        context.fillOval( toScreenX( atX ) - 2.5, toScreenY( atY ) - 2.5, 5, 5 );

        // Draw the positions
        context.setStroke( Color.GRAY );
        context.strokeLine( toScreenX( atX ), toScreenY( atY ), toScreenX( atX ), canvas.getHeight() / 2 ); // X axis
        context.strokeLine( toScreenX( atX ), toScreenY( atY ), canvas.getWidth() / 2 , toScreenY( atY ) ); // Y axis

        drawCenteredText( toScreenX( atX ), (canvas.getHeight() / 2) + 20, String.format("%.3f", atX ) );
        drawCenteredText( (canvas.getWidth() / 2) - 20, toScreenY( atY ) + 12, String.format("%.3f", atY ) );
        context.setStroke( prevColor );
    }

    public Plotter( DoubleUnaryOperator function, Color color )
    {
        canvas = new Canvas( graphSize, graphSize );
        var context = canvas.getGraphicsContext2D();

        Paint prevColor = context.getStroke();
        context.setStroke( color );

        double xMin = -boundary;
        double xMax = boundary;
        int width = (int) canvas.getWidth();

        double prevX = xMin;
        double prevY = function.applyAsDouble(prevX);

        for (int pix = 1; pix <= width; pix++) {
            double x = xMin + pix * (xMax - xMin) / width;
            double y = function.applyAsDouble(x);

            // skip non-finite values to handle discontinuities
            if ( Double.isFinite( prevY ) && Double.isFinite( y ) )
            {
                context.strokeLine(toScreenX(prevX), toScreenY(prevY), toScreenX(x), toScreenY(y));
            }

            prevX = x;
            prevY = y;
        }

        context.setStroke( prevColor );
    }

}
