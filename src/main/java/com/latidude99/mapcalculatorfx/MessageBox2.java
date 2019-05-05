/**Copyright (C) 2019  Piotr Czapik.
 * @author Piotr Czapik
 * @version 4.5
 *
 *  This file is part of MapCalculatorFX.
 *  MapCalculatorFX is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  MapCalculatorFX is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with MapCalculatorFX.  If not, see <http://www.gnu.org/licenses/>
 *  or write to: latidude99@gmail.com
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
