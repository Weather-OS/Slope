package com.slope.ui;

import com.slope.math.ExprDiffFunc;
import com.slope.math.ExprFunc;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OpacityBox
{
    public VBox layout;
    public static double currentFuncOpacity = 100.0;
    public static double currentSlopeOpacity = 100.0;

    public static void updateOpacities( Group group )
    {
        group.getChildren().get( 1 ).setOpacity( currentFuncOpacity );
        group.getChildren().get( 2 ).setOpacity( currentSlopeOpacity );
    }

    public OpacityBox( Group group )
    {
        Label funcOpacityLabel = new Label( " Function Opacity: " );
        Slider funcOpacitySlider = new Slider( 0, 100, 100 );
        Button funcOpacityButton = new Button( "Set Opacity" );

        funcOpacityButton.setOnAction( e ->
        {
            currentFuncOpacity = funcOpacitySlider.getValue();
            group.getChildren().get( 1 ).setOpacity( currentFuncOpacity );
        } );

        HBox funcOpacityBox = new HBox( 10, funcOpacityLabel, funcOpacitySlider, funcOpacityButton );

        Label slopeOpacityLabel = new Label( "       Slope Opacity: " );
        Slider slopeOpacitySlider = new Slider( 0, 100, 100 );
        Button slopeOpacityButton = new Button( "Set Opacity" );

        slopeOpacityButton.setOnAction( e ->
        {
            currentSlopeOpacity = slopeOpacitySlider.getValue();
            group.getChildren().get( 2 ).setOpacity( currentSlopeOpacity );
        } );

        HBox slopeOpacityBox = new HBox( 10, slopeOpacityLabel, slopeOpacitySlider, slopeOpacityButton );

        layout = new VBox( 10, funcOpacityBox, slopeOpacityBox );
    }
}
