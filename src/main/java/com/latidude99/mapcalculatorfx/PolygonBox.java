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

import javafx.collections.ObservableList;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Scale;
import javafx.scene.control.*;
import javafx.geometry.*;

/*
 * Generic pane, displays polygons read from an observable table, used in TabD.
 * Does not preserve geometry, its sole purpose is to conduct a visual
 * check if the polygon in question is not self-intersecting.
 * Although such polygons may occur in real life it is extremely rare.
 * In those cases it is better to treat polygons (fields) like that as
 * two separate entities and calculate its area and perimeter separately.
 */

public class PolygonBox {

    public static void show(String message, String title, ObservableList<Vertex> vertexes) {
        Stage stage = new Stage();
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(250);
        
        double lowest = 21;
        double highestE = 750;
        double highestN = 501;
        int minE = 10000000;
        int minN = 10000000;
        int maxE = 0;
        int maxN = 0;


        // calculates the extends of a polygon
        for(Vertex v: vertexes){
        	if(v.getEasting() < minE)
        		minE = v.getEasting();
        	if(v.getEasting() > maxE)
        		maxE = v.getEasting();
        	if(v.getNorthing() < minN)
        		minN = v.getNorthing();
        	if(v.getNorthing() > maxN)
        		maxN = v.getNorthing();
        }

        // normalizes coordinates of a polygon (makes it fit the window canvas area)
        int num = vertexes.size();
        Double[] points = new Double[num * 2];
        int n = 0;
        for(Vertex v: vertexes){
            points[n] = (((highestE - lowest) * (v.getEasting() - minE)) / (maxE - minE)) + lowest;
            points[n+1] =(((highestN - lowest) * (v.getNorthing() - minN)) / (maxN - minN)) + lowest;
            n = n + 2;
        }

        // creates polygon out of normalized points
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(points);
        polygon.setFill(Color.WHITE);
        polygon.setStrokeWidth(1);
        polygon.setStroke(Color.BLUE);

        // adds polygon to pane and reverts Y axis to conform with the OS coordinate system
        // where Y values increase towards North
        Pane panePolygon = new Pane(polygon);
        Scale scale = new Scale();
        scale.setX(1);
        scale.setY(-1);
        //panePolygon.getTransforms().add(scale);
        
        HBox panePolyWrapper = new HBox(panePolygon);
        panePolyWrapper.setStyle("-fx-border-color: grey");
        panePolyWrapper.setAlignment(Pos.CENTER);
        
        Label lbl = new Label();
        lbl.setText(message);
        lbl.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        lbl.setTextFill(Color.NAVY);
        HBox paneLblWrapper = new HBox(lbl);
        paneLblWrapper.setAlignment(Pos.CENTER);
        paneLblWrapper.setPadding(new Insets(0,10,10,10));
        
        Button btnOK = new Button();
        btnOK.setText("OK");
        btnOK.setOnAction(e -> stage.close());
        HBox paneOKWrapper = new HBox(btnOK);
        paneOKWrapper.setAlignment(Pos.BOTTOM_RIGHT);
        paneOKWrapper.setPadding(new Insets(10,10,0,10));
        
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(15,15,15,15));
        BorderPane.setAlignment(panePolyWrapper, Pos.CENTER);
        pane.setTop(paneLblWrapper);
        pane.setCenter(panePolyWrapper);
        pane.setBottom(paneOKWrapper);
        //pane.getChildren().addAll(lbl, panePolygon, btnOK);
        
        
        Scene scene = new Scene(pane, 800, 600, Color.BEIGE);
        stage.setScene(scene);
        //stage.showAndWait();
        stage.show();
        }
}
