/*
 * Copyright (c) 2025 Weather. All Rights Reserved.
 *
 * This code is for educational purposes only.
 * Unauthorized copying, distribution, or modification is prohibited.
 */

package com.slope.ui;

import javafx.scene.canvas.*;
import javafx.scene.paint.*;

class Graph
{
    Canvas canvas;
    double boundary;

    public Graph( double graphSize, double boundary )
    {
        canvas = new Canvas( graphSize, graphSize );
        drawAxes();
        drawAxesBoundaries( boundary );
        this.boundary = boundary;
    }

    private enum Facing
    {
        NORTH,
        SOUTH,
        WEST,
        EAST
    }

    private void drawPointedTriangle( double x, double y, int triangleSize, Facing facing )
    {
        var context = canvas.getGraphicsContext2D();

        double halfBase = triangleSize / 2.0;

        double[] xPoints = new double[3];
        double[] yPoints = new double[3];

        switch (facing) {
            case NORTH:
                // tip at (x,y), base below.
                xPoints[0] = x;            yPoints[0] = y;
                xPoints[1] = x - halfBase; yPoints[1] = y + triangleSize;
                xPoints[2] = x + halfBase; yPoints[2] = y + triangleSize;
                break;

            case SOUTH:
                // tip at (x,y), base above
                xPoints[0] = x;            yPoints[0] = y;
                xPoints[1] = x - halfBase; yPoints[1] = y - triangleSize;
                xPoints[2] = x + halfBase; yPoints[2] = y - triangleSize;
                break;

            case EAST:
                // tip at (x,y), base to the left (vertical)
                xPoints[0] = x;            yPoints[0] = y;
                xPoints[1] = x - triangleSize;   yPoints[1] = y - halfBase;
                xPoints[2] = x - triangleSize;   yPoints[2] = y + halfBase;
                break;

            case WEST:
                // tip at (x,y), base to the right (vertical)
                xPoints[0] = x;            yPoints[0] = y;
                xPoints[1] = x + triangleSize;   yPoints[1] = y - halfBase;
                xPoints[2] = x + triangleSize;   yPoints[2] = y + halfBase;
                break;
        }

        context.fillPolygon(xPoints, yPoints, 3);
        context.strokePolygon(xPoints, yPoints, 3);
    }

    private void drawAxes()
    {
        var context = canvas.getGraphicsContext2D();
        double yCenter = canvas.getHeight() / 2;
        double xCenter = canvas.getWidth() / 2;

        // X axis
        drawPointedTriangle( 5, yCenter, 5, Facing.WEST );
        context.strokeLine( 5, yCenter, canvas.getWidth() - 5, yCenter );
        drawPointedTriangle( canvas.getWidth() - 5, yCenter, 5, Facing.EAST );

        // Y axis
        drawPointedTriangle( xCenter, 5, 5, Facing.NORTH );
        context.strokeLine( xCenter, 5, xCenter, canvas.getHeight() - 5 );
        drawPointedTriangle( xCenter, canvas.getHeight() - 5, 5, Facing.SOUTH );
    }

    private void drawAxesBoundaries( double boundary )
    {
        var context = canvas.getGraphicsContext2D();
        double yCenter = canvas.getHeight() / 2;
        double xCenter = canvas.getWidth() / 2;

        // -X boundary
        context.fillText( "-" + Double.toString( boundary ), 2, yCenter + 15 );

        // X boundary
        context.fillText( "+" + Double.toString( boundary ), canvas.getWidth() - 32 - 2, yCenter + 15 );

        // -Y boundary
        context.fillText( "-" + Double.toString( boundary ), xCenter + 5, 15 );

        // Y boundary
        context.fillText( "+" + Double.toString( boundary ), xCenter + 5, canvas.getHeight() - 5 );
    }
}
