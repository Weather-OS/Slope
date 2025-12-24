/*
 * Copyright (c) 2025 Weather. All Rights Reserved.
 *
 * This code is for educational purposes only.
 * Unauthorized copying, distribution, or modification is prohibited.
 */

package com.slope.ui;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class GraphPropertyBox
{
    public HBox layout;

    public GraphPropertyBox( Group group )
    {
        var boundariesField = new TextField();
        var label = new Label( "Graph Boundaries:" );
        var submitButton = new Button( "Update Graph" );

        boundariesField.setPromptText( "10.0" );

        submitButton.setOnAction( e ->
        {
            Plotter slope;
            Plotter plotter;
            Graph graph;
            double newBoundaries;

            if ( boundariesField.getText().isEmpty() )
            {
                //default boundary is 10.0
                newBoundaries = 10.0;
            } else
            {
                try
                {
                    newBoundaries = Double.parseDouble(boundariesField.getText());
                } catch (NumberFormatException exception)
                {
                    throw new RuntimeException("Boundaries must be a number!");
                }
            }

            Plotter.boundary = newBoundaries;

            plotter = new Plotter( FuncInputBox.lastFunc, FuncInputBox.currentColor );
            graph = new Graph( Plotter.graphSize, newBoundaries );
            slope = new Plotter( SlopeBox.lastSlope, SlopeBox.currentColor );
            group.getChildren().set( 0, graph.canvas );
            group.getChildren().set( 1, plotter.canvas );
            group.getChildren().set( 2, slope.canvas );
        });

        layout = new HBox( 10, label, boundariesField, submitButton );
    }
}
