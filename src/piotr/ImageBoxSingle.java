/**Copyright (C) 2017  Piotr Czapik.
* @author Piotr Czapik <latitude1001101@gmail.com>
* @version 4.01
* @license GPLv2
*/ 

package piotr;

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

public class ImageBoxSingle{
    public static void show(String message, String title, String imageName) {
        
        Stage stage = new Stage();
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(250);
        stage.setMaxWidth(900);
        stage.setMaxHeight(600);
        
        final ImageView imageView = new ImageView();
        File file = new File(imageName);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        
        imageView.fitWidthProperty().bind(stage.widthProperty());
        imageView.fitHeightProperty().bind(stage.heightProperty());
        
        Label lbl = new Label();
        lbl.setText(message);
        lbl.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");
        
        Button btnClose = new Button();
        btnClose.setText(" Close ");
        btnClose.setPrefWidth(80);
        //btnClose.setAlignment(Pos.BOTTOM_RIGHT);
        btnClose.setOnAction(e -> stage.close());
        btnClose.setOnKeyPressed(e -> stage.close());
        HBox paneClose = new HBox(btnClose);
        paneClose.setAlignment(Pos.BOTTOM_RIGHT);
        
        VBox pane = new VBox();
        HBox paneImage = new HBox(imageView);
        pane.getChildren().addAll(lbl, paneImage, paneClose);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 20, 20, 20));
        
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        }

	
}
