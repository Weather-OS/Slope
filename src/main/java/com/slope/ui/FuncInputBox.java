/*
 * Copyright (c) 2025 Weather. All Rights Reserved.
 *
 * This code is for educational purposes only.
 * Unauthorized copying, distribution, or modification is prohibited.
 */

package com.slope.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InputBox
{
    VBox inputField = new VBox( 10 );

    public InputBox( Plotter plotter )
    {
        var inputField = new TextField();
        var submitButton = new Button( "Plot" );

        inputField.setPromptText( "x^2" );


        submitButton.setOnAction();
    }
}
