/*
 * Copyright (c) 2025 Weather. All Rights Reserved.
 *
 * This code is for educational purposes only.
 * Unauthorized copying, distribution, or modification is prohibited.
 */

package com.slope.ui;

import com.slope.math.ExprDiffFunc;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

import com.slope.math.ExprFunc;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.function.DoubleUnaryOperator;

public class FuncInputBox
{
    public HBox layout;
    public static Color currentColor = Color.BLACK;
    public static DoubleUnaryOperator lastFunc = x -> x*x;
    public static String lastFuncString = "x*x";

    public FuncInputBox( Group group )
    {
        var inputField = new TextField();
        var label = new Label( " SymJa Expression:" );
        var submitButton = new Button( "Plot" );
        var redButton = new RadioButton( "Red" );
        var greenButton = new RadioButton( "Green" );
        var blueButton = new RadioButton( "Blue" );
        var blackButton = new RadioButton( "Black" );
        var colorGroup = new ToggleGroup();

        inputField.setPromptText( "x^2" );

        redButton.setToggleGroup( colorGroup );
        greenButton.setToggleGroup( colorGroup );
        blueButton.setToggleGroup( colorGroup );
        blackButton.setToggleGroup( colorGroup );

        blackButton.setSelected( true );

        colorGroup.selectedToggleProperty().addListener( ( observable, oldToggle, newToggle ) ->
        {
            if (newToggle != null)
            {
                RadioButton selected = (RadioButton) newToggle;
                switch ( selected.getText() )
                {
                    case "Red" -> currentColor = Color.RED;
                    case "Green" -> currentColor = Color.GREEN;
                    case "Blue" -> currentColor = Color.BLUE;
                    case "Black" -> currentColor = Color.BLACK;
                }
            }
        } );

        submitButton.setOnAction( e ->
        {
            Plotter newSlope;
            lastFuncString = inputField.getText().isEmpty() ? "x^2" : inputField.getText();

            lastFunc = ExprFunc.convertExpr( lastFuncString );
            var funcPlot = new Plotter( lastFunc, currentColor );
            SlopeBox.lastSlope = ExprDiffFunc.slopeFunc( lastFuncString, SlopeBox.lastSlopeAt );
            newSlope = new Plotter( SlopeBox.lastSlope, SlopeBox.currentColor );
            newSlope.collisionDot( SlopeBox.lastSlopeAt, FuncInputBox.lastFunc.applyAsDouble( SlopeBox.lastSlopeAt ) );

            group.getChildren().set( 1, funcPlot.canvas );
            group.getChildren().set( 2, newSlope.canvas );

            OpacityBox.updateOpacities( group );
        } );

        layout = new HBox( 10, label, inputField, submitButton, redButton, greenButton, blueButton, blackButton );
    }
}
