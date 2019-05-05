/**Copyright (C) 2017  Piotr Czapik.
* @author Piotr Czapik <latitude1001101@gmail.com>
* @version 4.01
* @license GPLv2
*/ 

package com.latidude99.mapcalculatorfx;

import java.io.File;

import javafx.application.*;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.geometry.*;

public class ImageBox{
    public static void show(String message, String title, String imageName1, String imageName2) {
        
        Stage stage = new Stage();
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(250);
        stage.setMinWidth(250);
        stage.setMaxWidth(1100);
        stage.setMaxHeight(700);
        
        final ImageView imageView1 = new ImageView();
        File file1 = new File(imageName1);
        Image image1 = new Image(file1.toURI().toString());
        imageView1.setImage(image1);
        imageView1.setFitHeight(500);
        imageView1.setFitWidth(700);
        
        final ImageView imageView2 = new ImageView();
        File file2 = new File(imageName2);
        Image image2 = new Image(file2.toURI().toString());
        imageView2.setImage(image2);
        imageView2.setFitHeight(500);
        imageView2.setFitWidth(350);
                 
        Label lbl = new Label();
        lbl.setText(message);
        lbl.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");
        lbl.setPadding(new Insets(5, 5, 20, 5));
        
        Button btnClose = new Button();
        btnClose.setText(" Close ");
        btnClose.setPrefWidth(80);
        //btnClose.setAlignment(Pos.BOTTOM_RIGHT);
        btnClose.setOnAction(e -> stage.close());
        btnClose.setOnKeyPressed(e -> stage.close());
        HBox paneClose = new HBox(btnClose);
        paneClose.setAlignment(Pos.BOTTOM_RIGHT);
        paneClose.setPadding(new Insets(10,0,0,0));
        
        VBox pane = new VBox();
        HBox paneImage = new HBox(imageView1, imageView2);
        
        //paneImage.maxWidthProperty().bind(stage.widthProperty());
        //paneImage.maxHeightProperty().bind(stage.heightProperty());
        
        pane.getChildren().addAll(lbl, paneImage, paneClose);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 20, 20, 20));
        
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        }

	
}
