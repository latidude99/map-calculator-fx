/**Copyright (C) 2017  Piotr Czapik.
* @author Piotr Czapik <latitude1001101@gmail.com>
* @version 4.01
* @license GPLv2
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
        
                
  /*      private double normMinMax(int value, int min, int max){  // normalization between lowest and highest
            double result;                                      // min, max calculated from the list for easting and northing
            double lowest = 20;
            double highest = 400;
            result = (((highest - lowest) * (value - min)) / (max - min)) + lowest;
            return (double) result;
        }
 */                 
        int num = vertexes.size();
        Double[] points = new Double[num * 2];
        int n = 0;
        for(Vertex v: vertexes){
            points[n] = (((highestE - lowest) * (v.getEasting() - minE)) / (maxE - minE)) + lowest;
            points[n+1] =(((highestN - lowest) * (v.getNorthing() - minN)) / (maxN - minN)) + lowest;
            n = n + 2;
        }
        
  /*      System.out.println(polygon.getPoints().toString());
        System.out.println(points.length);
        System.out.println("n= " + n);
        System.out.println("min - max E " + minE + " - " + maxE);
        System.out.println("min - max N " + minN + "   " + maxN);
        
        for(int i=0; i < n; i=i+2){
        	System.out.println(points[i] + "  " + points[i+1]);
        }
    */      
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(points);
        polygon.setFill(Color.WHITE);
        polygon.setStrokeWidth(1);
        polygon.setStroke(Color.BLUE);
        
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
/*
  polygon.getPoints().addAll(100d,100d,200d,50d,400d,100d,200d,150d);
        polygon.setFill(Color.SKYBLUE);
        polygon.setStrokeWidth(3);
        polygon.setStroke(Color.BLACK);
*/













