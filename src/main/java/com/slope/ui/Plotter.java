package com.slope.ui;

import javafx.scene.canvas.*;
import javafx.scene.paint.*;
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

    public void collisionDot( double atX, double atY )
    {
        var context = canvas.getGraphicsContext2D();

        // Drawing a circle starts at it's most corner position.
        // So we have to remove 2.5 from each axis.
        context.fillOval( toScreenX( atX ) - 2.5, toScreenY( atY ) - 2.5, 5, 5 );
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
