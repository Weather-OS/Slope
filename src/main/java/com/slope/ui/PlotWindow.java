/*
 * Copyright (c) 2025 Weather. All Rights Reserved.
 *
 * This code is for educational purposes only.
 * Unauthorized copying, distribution, or modification is prohibited.
 */

package com.slope.ui;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public final class PlotWindow extends Application
{
    private static String name;
    private static int x;
    private static int y;

    @Override
    public void start( Stage stage )
    {
        var graph = new Graph( x, 10 );

        Plotter.boundary = 10;
        Plotter.graphSize = x;

        var funcPlot = new Plotter( FuncInputBox.lastFunc, FuncInputBox.currentColor );
        var slopePlot = new Plotter( SlopeBox.lastSlope, SlopeBox.currentColor );
        slopePlot.collisionDot( 2, 4 );

        var root = new VBox( 10 );
        var group = new Group();
        var scene = new Scene( root, x, y );
        // Index 0 is for the graph, Index 1 is for the function and Index 2 is for the slope line.
        group.getChildren().add( graph.canvas );
        group.getChildren().add( funcPlot.canvas );
        group.getChildren().add( slopePlot.canvas );

        var funcInput = new FuncInputBox( group );
        var graphProperty = new GraphPropertyBox( group );
        var slope = new SlopeBox( group );
        var opacity = new OpacityBox( group );
        root.getChildren().add( group );
        root.getChildren().add( graphProperty.layout );
        root.getChildren().add( funcInput.layout );
        root.getChildren().add( slope.layout );
        root.getChildren().add( opacity.layout );

        stage.setTitle( name );
        stage.setScene( scene );
        stage.setResizable( false );
        stage.show();
    }

    public void RunWindow( String windowName, int windowSize_x, int windowSize_y )
    {
        this.name = windowName;
        this.x = windowSize_x;
        this.y = windowSize_y;
        launch();
    }
}
