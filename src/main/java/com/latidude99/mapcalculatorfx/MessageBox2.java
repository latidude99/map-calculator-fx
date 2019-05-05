/**Copyright (C) 2017  Piotr Czapik.
* @author Piotr Czapik <latitude1001101@gmail.com>
* @version 4.01
* @license GPLv2
*/ 

package com.latidude99.mapcalculatorfx;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.geometry.*;

public class MessageBox2 {
    public static void show(String message, String title) {
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(250);
        
        Text txt = new Text();
        txt.setText(message);
        txt.setFill(Color.rgb(255, 255, 255));
        txt.setStyle("-fx-font: 24 arial;");
        
        Button btnOK = new Button();
        btnOK.setText("OK");
        btnOK.setMinWidth(80);
        btnOK.setOnAction(e -> stage.close());
        btnOK.setOnKeyPressed(e -> stage.close());
        
        VBox pane = new VBox(20);
        pane.setMinWidth(300);
        pane.getChildren().addAll(txt, btnOK);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 20, 20, 20));
        //pane.setStyle("-fx-background: #ffaadd;");
        pane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();
        }
}
