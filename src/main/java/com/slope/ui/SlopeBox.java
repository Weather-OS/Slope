/*
 * Copyright (c) 2025 Weather. All Rights Reserved.
 *
 * This code is for educational purposes only.
 * Unauthorized copying, distribution, or modification is prohibited.
 */

package com.slope.ui;

import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.layout.HBox;

import java.util.function.DoubleUnaryOperator;

import com.slope.math.ExprDiffFunc;

public class SlopeBox
{
    public HBox layout;
    public static double lastSlopeAt = 2;
    public static DoubleUnaryOperator lastSlope = x -> 4*x-4;
    public static Color currentColor = Color.ORANGE;

    public SlopeBox( Group group )
    {
        var slopeField = new TextField();
        var label = new Label( "Calculate Slop At:" );
        var submitButton = new Button( "Draw Slope" );
        var orangeButton = new RadioButton( "Orange" );
        var purpleButton = new RadioButton( "Purple" );
        var colorGroup = new ToggleGroup();

        slopeField.setPromptText( "2" );

        orangeButton.setToggleGroup( colorGroup );
        purpleButton.setToggleGroup( colorGroup );

        orangeButton.setSelected( true );

        colorGroup.selectedToggleProperty().addListener( ( observable, oldToggle, newToggle ) ->
        {
            if (newToggle != null)
            {
                RadioButton selected = (RadioButton) newToggle;
                switch ( selected.getText() )
                {
                    case "Orange" -> currentColor = Color.ORANGE;
                    case "Purple" -> currentColor = Color.PURPLE;
                }
            }
        } );

        submitButton.setOnAction( e ->
        {
            Plotter plotter;

            try
            {
                lastSlopeAt = Double.parseDouble( slopeField.getText() );
            } catch ( NumberFormatException exception )
            {
                throw new RuntimeException( "Location must be a number!" );
            }

            lastSlope = ExprDiffFunc.slopeFunc( FuncInputBox.lastFuncString, lastSlopeAt );
            plotter = new Plotter( lastSlope, currentColor );
            group.getChildren().set( 2, plotter.canvas );
        });

        layout = new HBox( 10, label, slopeField, submitButton, orangeButton, purpleButton );
    }
}
