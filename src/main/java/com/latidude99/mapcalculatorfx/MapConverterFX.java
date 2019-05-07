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
import java.text.NumberFormat;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/*
 * This class include the app's GUI and most of the calculations.
 * The GUI is built here, programmatically instead of declaring
 * Views in FXML files with Controllers (MVC pattern) to see if
 * this would be a viable alternative and learn building GUI with
 * Java code.
 *
 * Created after a few months of learning Java and programming at all.
 *
 * Although it should be split in multiple classes, it is left as
 * originally written (with repeating blocks of code here and there)
 * mainly to remind me how NOT to do it in the future.
 *
 * The only things changed/added later are one major bug fix,
 * comments and JUnit5 tests. Also unused lines of code were deleted.
 */

public class MapConverterFX extends Application {

    /*
     * Location of the help images
     */
    private final String IMAGE_TAB_B1 = "images/help_TabB_1.png";
    private final String IMAGE_TAB_C1 = "images/help_TabC_1.png";
    private final String IMAGE_TAB_C2 = "images/help_TabC_2.png";
    private final String IMAGE_TAB_D1 = "images/help_TabD_1.png";
    private final String IMAGE_TAB_D2 = "images/help_TabD_2.png";

    /**
     * File formats for drop down lists, TabD
     */
    final static String SPACE_SIN_NUMBER = "easting northing (separated with space)";
    final static String COMMA_SIN_NUMBER = "easting,northing (separated with comma)";
    final static String SPACE_WITH_NUMBER = "number easting northing (separated with space)";
    final static String COMMA_WITH_NUMBER = "number,easting,northing (separated with comma)";


    /*
     *  Controls declarations for Scale Converter, TabA
     */
    TextField txtScaleFra,  txtScaleImpIn; TextField txtScaleImpMile; TextField txtScaleMetricCm;
    TextField txtScaleMetricKm; Text txtFra1; Button btnClear; Button btnConvertFra; Button btnConvertIn;
    Button btnConvertMile; Button btnConvertCm; Button btnConvertKm; Label lblScaleFra; Label lblScaleImpIn;
    Label lblScaleImpMile; Label lblScaleMetricCm; Label lblScaleMetricKm; Text resultFra; Text resultIn;
    Text resultMile; Text resultCm; Text resultKm;


    /*
     *  Controls declarations for Distance Calculator, TabB
     */
    Label lblMm; Label lblCm; Label lblIn; Label lblKm; Label lblMetre; Label lblMile;
    Label lblFt; Label lblYard; Label lblScaleFractional; Label lblScaleInch;
    Label lblScaleMile; Label lblScaleCm; Label lblScaleMetre; Label lblScaleKm;
    
    TextField txtMm; TextField txtCm; TextField txtIn; TextField txtKm; TextField txtMetre;
    TextField txtMile;  TextField txtFt; TextField txtYard; TextField txtScaleFractional; TextField txtScaleInch;
    TextField txtScaleMile; TextField txtScaleCm; TextField txtScaleMetre; TextField txtScaleKm;
    
    Button buttonHelpTabB = new Button(); Label labelGapHelp = new Label();
    
    Label lblGap3 = new Label(); Label lblGap3a = new Label(); Label lblGap3b = new Label();
    Label lblGap3c = new Label(); Label lblGap3d = new Label(); Label lblGap3e = new Label();
    Label lblGap2 = new Label(); Label lblGap2a = new Label(); Label lblGap2b = new Label();
    Label lblGap2c = new Label(); Label lblGap2d = new Label(); Label lblGap1 = new Label();
    Label lblGap1a = new Label(); Label lblGap1b = new Label();


    /*
     *  Controls declarations for Coordinates Calculator, TabC
     */
    Label labelLeftPoint = new Label();  Label labelRightPoint = new Label();
    
    Button buttonLeftPointConvert = new Button(); Button buttonLeftPointClear = new Button();
    Button buttonRightPointConvert = new Button(); Button buttonRightPointClear = new Button();
    
    Label labelLeftOS = new Label(); Label labelLeftOSEast = new Label();
    Label labelLeftOSNorth = new Label(); Label labelLeftOsSixFigure = new Label();
    Label labelLeftLat = new Label(); Label labelLeftWGS84_degDec_Lat = new Label();
    Label labelLeftWGS84_minDec_Lat = new Label(); Label labelLeftWGS84_degDecMin_Lat = new Label();
    Label labelLeftWGS84_minDecMin_Lat = new Label(); Label labelLeftWGS84_secDec_Lat = new Label();
    Label labelLeftWGS84_degDecSec_Lat = new Label(); Label labelLeftWGS84_minDecSec_Lat = new Label();
    Label labelLeftWGS84_secDecSec_Lat = new Label(); Label labelLeftLon = new Label();
    Label labelLeftWGS84_degDec_Lon = new Label(); Label labelLeftWGS84_minDec_Lon = new Label();
    Label labelLeftWGS84_degDecMin_Lon = new Label(); Label labelLeftWGS84_minDecMin_Lon = new Label();
    Label labelLeftWGS84_secDec_Lon = new Label(); Label labelLeftWGS84_degDecSec_Lon = new Label();
    Label labelLeftWGS84_minDecSec_Lon = new Label(); Label labelLeftWGS84_secDecSec_Lon = new Label();
    
    TextField textLeftOSEast = new TextField(); TextField textLeftOSNorth = new TextField();
    TextField textLeftOsSixFigure = new TextField(); TextField textLeftWGS84_degDec_Lat = new TextField();
    TextField textLeftWGS84_degDecMin_Lat = new TextField(); TextField textLeftWGS84_minDecMin_Lat = new TextField();
    TextField textLeftWGS84_degDecSec_Lat = new TextField(); TextField textLeftWGS84_minDecSec_Lat = new TextField();
    TextField textLeftWGS84_secDecSec_Lat = new TextField();
    
    TextField textLeftWGS84_degDec_Lon = new TextField(); TextField textLeftWGS84_degDecMin_Lon = new TextField();
    TextField textLeftWGS84_minDecMin_Lon = new TextField(); TextField textLeftWGS84_degDecSec_Lon = new TextField();
    TextField textLeftWGS84_minDecSec_Lon = new TextField(); TextField textLeftWGS84_secDecSec_Lon = new TextField();

    Label labelRightOS = new Label(); Label labelRightOSEast = new Label();
    Label labelRightOSNorth = new Label(); Label labelRightOsSixFigure = new Label();
    Label labelRightLat = new Label(); Label labelRightWGS84_degDec_Lat = new Label();
    Label labelRightWGS84_minDec_Lat = new Label(); Label labelRightWGS84_degDecMin_Lat = new Label();
    Label labelRightWGS84_minDecMin_Lat = new Label(); Label labelRightWGS84_secDec_Lat = new Label();
    Label labelRightWGS84_degDecSec_Lat = new Label(); Label labelRightWGS84_minDecSec_Lat = new Label();
    Label labelRightWGS84_secDecSec_Lat = new Label(); Label labelRightLon = new Label();
    Label labelRightWGS84_degDec_Lon = new Label(); Label labelRightWGS84_minDec_Lon = new Label();
    Label labelRightWGS84_degDecMin_Lon = new Label(); Label labelRightWGS84_minDecMin_Lon = new Label();
    Label labelRightWGS84_secDec_Lon = new Label(); Label labelRightWGS84_degDecSec_Lon = new Label();
    Label labelRightWGS84_minDecSec_Lon = new Label(); Label labelRightWGS84_secDecSec_Lon = new Label();
    
    TextField textRightOSEast = new TextField(); TextField textRightOSNorth = new TextField();
    TextField textRightOsSixFigure = new TextField(); TextField textRightWGS84_degDec_Lat = new TextField();
    TextField textRightWGS84_degDecMin_Lat = new TextField(); TextField textRightWGS84_minDecMin_Lat = new TextField();
    TextField textRightWGS84_degDecSec_Lat = new TextField(); TextField textRightWGS84_minDecSec_Lat = new TextField();
    TextField textRightWGS84_secDecSec_Lat = new TextField(); TextField textRightWGS84_degDec_Lon = new TextField();
    TextField textRightWGS84_degDecMin_Lon = new TextField(); TextField textRightWGS84_minDecMin_Lon = new TextField();
    TextField textRightWGS84_degDecSec_Lon = new TextField(); TextField textRightWGS84_minDecSec_Lon = new TextField();
    TextField textRightWGS84_secDecSec_Lon = new TextField();

    Label lableDistTitle = new Label(); Label labelDistKm = new Label(); Label labelDistMilesStatue = new Label();
    Label labelDistMilesNautical = new Label(); Label labelDistMetres = new Label(); Label labelDistFt = new Label();
    
    TextField textDistKm = new TextField(); TextField textDistMilesStatue = new TextField();
    TextField textDistMilesNautical = new TextField(); TextField textDistMetres = new TextField();
    TextField textDistFt = new TextField();
    
    Button buttonDistCalculate = new Button(); Button buttonDistConvert = new Button();
    Button buttonDistClear = new Button(); Button buttonBearingCalculate = new Button(),
    buttonBearingClear = new Button(); TextField textBearing = new TextField();
    TextField textAzimuth = new TextField(); Button buttonHelp = new Button();
    Label labelAzimuth = new Label(); Label labelBearing = new Label();
        
    Label labelGap00a = new Label(); Label labelGap00b = new Label(); Label labelGap0a = new Label();
    Label labelGap0b = new Label(); Label labelGap1 = new Label(); Label labelGap2 = new Label();
    Label labelGap3 = new Label(); Label labelGap4 = new Label(); Label labelGap5 = new Label();
    Label labelGap6 = new Label(); Label labelGap7 = new Label(); Label labelGap8 = new Label();
    Label labelGap9 = new Label(); Label labelGap10 = new Label(); Label labelGap11 = new Label();
    Label labelGap12 = new Label(); Label labelGap12a = new Label(); Label labelGap13 = new Label();
    Label labelGap14 = new Label(); Label labelGap15 = new Label(); Label labelGap16 = new Label();
    Label labelGap17 = new Label(); Label labelGap18 = new Label(); Label labelGap19 = new Label();
    Label labelGap20 = new Label(); Label labelGap21 = new Label(); Label labelGap22 = new Label();
    Label labelGap23 = new Label(); Label labelGap24 = new Label(); Label labelGap25 = new Label();
    Label labelGap26 = new Label(); Label labelGap0aa = new Label(); Label labelGap00aa = new Label();
    Label labelGap0aaa = new Label(); Label labelGap00aaa = new Label(); Label labelGap0ba = new Label();
    Label labelGap00ba = new Label(); Label labelGap0baa = new Label(); Label labelGap00baa = new Label();
    
    String temp = "";

    /*
     *  Controls declarations for Area & Perimeter Calculator, TabD
     */
    Button buttonCalcArea, buttonCalcPerim, buttonAddPoint, buttonDelPoint, buttonClearList,
    		buttonExport, buttonImport, buttonSubmitLeft, buttonSubmitRight, buttonHelpTabD,
    		buttonValidatePoint, buttonClearPointText, buttonClearArea, buttonClearPerim;
    
    TextField textCalcAreaMetre2, textCalcAreaFt2, textCalcAreaAcre, textCalcAreaHa,
    		textCalcPerimMetre, textCalcPerimFt, textCalcPerimKm, textCalcPerimMile,
            textPointNum, textPointEast, textPointNorth;

    Label labelTitleTabD, labelPointSubmittedLeft, labelPointSubmittedRight, labelPointAdded,
            labelPointDeleted, labelFt2, labelMetre2, labelAcre, labelHa, labelFt, labelMetre,
            labelKm, labelMile,	labelNumber, labelEasting, labelNorthing;
    
    TableView<Vertex> table;
    ComboBox<String> comboImport, comboExport;
    
    /*********************************** Application *****************************************/
    
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        /*
         * Setting up TabPane
         */
        TabPane tabPane = new TabPane();
        BorderPane mainPane = new BorderPane();


    	/************************************* TabA *************************************/

        Tab tabA = new Tab();
        tabA.setText("  Scale Converter  ");

        /*
         * Top pane with title
         */
        Text titleA = new Text("S c a l e    C o n v e r t e r");
        titleA.setFont(Font.font("Arial", FontWeight.NORMAL, 22));
        titleA.setFill(Color.NAVY);
        HBox paneTopA = new HBox(titleA);
        paneTopA.setAlignment(Pos.CENTER);
        paneTopA.setPadding(new Insets(20, 20, 20, 0));

        /*
         * Formatter for text input , 0-9 digits only
         */
        TextFormatter<String> txtScaleFraFormatter = new TextFormatter<>(value -> {
            return value.getText().matches("[0-9]*")? value : null;
        });

        /*
         * HBox pane with: Label + Text Field + Button and Text - Fractional scale section
         */
        txtFra1 = new Text(" 1 :");
        txtScaleFra = new TextField();
        txtScaleFra.setMinWidth(155);
        txtScaleFra.setStyle("-fx-font: 12 arial;");
        txtScaleFra.setPromptText("Enter denominator (eg.10000)");
        txtScaleFra.setFocusTraversable(false);
        txtScaleFra.setTextFormatter(txtScaleFraFormatter);
        txtScaleFra.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		if(event.getCode().equals(KeyCode.ENTER)){
        			convertFra();
        		}
        	}
        });
        lblScaleFra = new Label("Fractional  e.g. 1:10 000");
        lblScaleFra.setMinWidth(240);
        lblScaleFra.setStyle("-fx-font: 15 arial;");
        resultFra = new Text();
        resultFra.setStyle("-fx-font: 15 arial;");
        btnConvertFra = new Button("Convert");
        btnConvertFra.setStyle("-fx-font: 13 arial; -fx-text-fill: navy;");
        btnConvertFra.setPrefWidth(100);
        btnConvertFra.setPrefHeight(30);
        btnConvertFra.setOnAction(e-> convertFra());
        Region spacer = new Region();
        HBox paneFra = new HBox(10, spacer, lblScaleFra, txtFra1, txtScaleFra, btnConvertFra, resultFra);
        paneFra.setAlignment(Pos.CENTER_LEFT);
        paneFra.setPadding(new Insets(10, 10, 10, 10));

        /*
         * HBox pane with: Label + Text Field + Button and Text - Imperial1 (inches to the mile) scale section
         */
        txtScaleImpIn = new TextField();
        txtScaleImpIn.setMinWidth(180);
        txtScaleImpIn.setStyle("-fx-font: 12 arial;");
        txtScaleImpIn.setPromptText("Enter number of inches");
        txtScaleImpIn.textProperty().addListener(
            (obs, oldVal, newVal) -> txtScaleImpIn.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        txtScaleImpIn.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		if(event.getCode().equals(KeyCode.ENTER)){
        			convertIn();
        		}
        	}
        });
        lblScaleImpIn = new Label("Inches to the mile (Imperial)");
        lblScaleImpIn.setMinWidth(240);
        lblScaleImpIn.setStyle("-fx-font: 15 arial;");
        resultIn = new Text();
        resultIn.setStyle("-fx-font: 15 arial;");
        btnConvertIn = new Button("Convert");
        btnConvertIn.setStyle("-fx-font: 13 arial; -fx-text-fill: navy;");
        btnConvertIn.setPrefWidth(100);
        btnConvertIn.setPrefHeight(30);
        btnConvertIn.setOnAction(e-> convertIn());
        HBox paneIn = new HBox(10, spacer, lblScaleImpIn, txtScaleImpIn, btnConvertIn, resultIn);
        paneIn.setAlignment(Pos.CENTER_LEFT);
        paneIn.setPadding(new Insets(20, 10, 20, 10));

        /*
         * HBox pane with: Label + Text Field + Button and Text - Imperial2 (miles to an inch) scale section
         */
        txtScaleImpMile = new TextField();
        txtScaleImpMile.setMinWidth(180);
        txtScaleImpMile.setStyle("-fx-font: 12 arial;");
        txtScaleImpMile.setPromptText("Enter number of miles");
        txtScaleImpMile.textProperty().addListener(
                (obs, oldVal, newVal) -> txtScaleImpMile.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        txtScaleImpMile.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		if(event.getCode().equals(KeyCode.ENTER)){
        			convertMile();
        		}
        	}
        });
        lblScaleImpMile = new Label("Miles to an inch (Imperial)");
        lblScaleImpMile.setMinWidth(240);
        lblScaleImpMile.setStyle("-fx-font: 15 arial;");
        resultMile = new Text();
        resultMile.setStyle("-fx-font: 15 arial;");
        btnConvertMile = new Button("Convert");
        btnConvertMile.setStyle("-fx-font: 13 arial; -fx-text-fill: navy;");
        btnConvertMile.setPrefWidth(100);
        btnConvertMile.setPrefHeight(30);
        btnConvertMile.setOnAction(e-> convertMile());
        HBox paneMile = new HBox(10, spacer, lblScaleImpMile, txtScaleImpMile, btnConvertMile, resultMile);
        paneMile.setAlignment(Pos.CENTER_LEFT);
        paneMile.setPadding(new Insets(20, 10, 20, 10));

        /*
         * HBox pane with: Label + Text Field + Button and Text - Metric1 (cm to a km) scale section
         */
        txtScaleMetricCm = new TextField();
        txtScaleMetricCm.setMinWidth(180);
        txtScaleMetricCm.setStyle("-fx-font: 12 arial;");
        txtScaleMetricCm.setPromptText("Enter number of centimetres");
        txtScaleMetricCm.textProperty().addListener(
                (obs, oldVal, newVal) -> txtScaleMetricCm.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        txtScaleMetricCm.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		if(event.getCode().equals(KeyCode.ENTER)){
        			convertCm();
        		}
        	}
        });
        lblScaleMetricCm = new Label("Centimetres to a kilometre (Metric)");
        lblScaleMetricCm.setMinWidth(240);
        lblScaleMetricCm.setStyle("-fx-font: 15 arial;");
        resultCm = new Text();
        resultCm.setStyle("-fx-font: 15 arial;");
        btnConvertCm = new Button("Convert");
        btnConvertCm.setStyle("-fx-font: 13 arial; -fx-text-fill: navy;");
        btnConvertCm.setPrefWidth(100);
        btnConvertCm.setPrefHeight(30);
        btnConvertCm.setOnAction(e-> convertCm());
        HBox paneMetre = new HBox(10, spacer, lblScaleMetricCm, txtScaleMetricCm, btnConvertCm, resultCm);
        paneMetre.setAlignment(Pos.CENTER_LEFT);
        paneMetre.setPadding(new Insets(20, 10, 20, 10));

        /*
         * HBox pane with: Label + Text Field + Button and Text - Metric2 (km to a cm) scale section
         */
        txtScaleMetricKm = new TextField();
        txtScaleMetricKm.setMinWidth(180);
        txtScaleMetricKm.setStyle("-fx-font: 12 arial;");
        txtScaleMetricKm.setPromptText("Enter number of kilometres");
        txtScaleMetricKm.textProperty().addListener(
                (obs, oldVal, newVal) -> txtScaleMetricKm.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        txtScaleMetricKm.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		if(event.getCode().equals(KeyCode.ENTER)){
        			convertKm();
        		}
        	}
        });
        lblScaleMetricKm = new Label("Kilometres to a centimetre (Metric)");
        lblScaleMetricKm.setMinWidth(240);
        lblScaleMetricKm.setStyle("-fx-font: 15 arial;");
        resultKm = new Text();
        resultKm.setStyle("-fx-font: 15 arial;");
        btnConvertKm = new Button("Convert");
        btnConvertKm.setStyle("-fx-font: 13 arial; -fx-text-fill: navy;");
        btnConvertKm.setPrefWidth(100);
        btnConvertKm.setPrefHeight(30);
        btnConvertKm.setOnAction(e-> convertKm());
        HBox paneKm = new HBox(10, spacer, lblScaleMetricKm, txtScaleMetricKm, btnConvertKm, resultKm);
        paneKm.setAlignment(Pos.CENTER_LEFT);
        paneKm.setPadding(new Insets(20, 10, 20, 10));

        /*
         * Vbox pane with all the HBox panes defined above (fractional, imperial 1 and 2, metric 1 and 2
         */
        VBox paneCenter = new VBox(10, spacer, paneFra, paneIn, paneMile, paneMetre, paneKm);
        paneCenter.setPadding(new Insets(0, 0, 0, 20));

        /*
         * Bottom HBox pane with 'clear' button
         */
        btnClear = new Button("CLEAR");
        btnClear.setTextFill(Color.RED);
        btnClear.setStyle("-fx-font: 20 arial;");
        btnClear.setAlignment(Pos.CENTER);
        btnClear.setPrefWidth(170);
        btnClear.setPrefHeight(50);
        btnClear.setOnAction(e-> clearFields());
        HBox paneBottom = new HBox(10);
        paneBottom.setAlignment(Pos.BOTTOM_RIGHT);
        paneBottom.getChildren().add(btnClear);
        paneBottom.setPadding(new Insets(0, 20, 10, 20));

        /*
         * Setting TabA layout
         */
        BorderPane tabA_boderPane = new BorderPane();
        tabA_boderPane.setTop(paneTopA);
        tabA_boderPane.setCenter(paneCenter);
        tabA_boderPane.setBottom(paneBottom);
        tabA.setContent(tabA_boderPane);


        /************************************* TabB *************************************/
        
        Tab tabB = new Tab();
        tabB.setText("Distance Calculator");

        /*
         * Top pane with title
         */
        Text titleB = new Text("  D i s t a n c e   C a l c u l a t o r  ");
        titleB.setFont(Font.font("Arial", FontWeight.NORMAL, 22));
        titleB.setFill(Color.NAVY);
        HBox paneTopB = new HBox(titleB);
        paneTopB.setAlignment(Pos.CENTER);
        paneTopB.setPadding(new Insets(20, 20, 30, 0));

        /*
         * Formatter for text input , 0-9 digits only
         */
        TextFormatter<String> txtMmFraFormatter = new TextFormatter<>(value -> {
            return value.getText().matches("[0-9]*")? value : null;
        });

        /*
         * Vbox pane with everything for map distance measurements
         */
        Label lblMap = new Label("Map measurement");
        lblMap.setStyle("-fx-font: 16 arial");
        lblMap.setPadding(new Insets(0, 0, 0, 35));
        HBox hboxTitle1 = new HBox(lblMap);
        hboxTitle1.setPadding(new Insets(0, 0, 10, 0));

        lblMm = new Label("Map measurement in milimetres:");
        lblMm.setMinWidth(210);
        lblMm.setStyle("-fx-font: 13 arial;");
        txtMm = new TextField();
        txtMm.setMinWidth(210);
        txtMm.setPromptText("Enter number of mm");
        txtMm.setTextFormatter(txtMmFraFormatter);
        txtMm.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		txtCm.clear();
        		txtIn.clear();
        	}
        });
        
        lblCm = new Label("Map measurement in centimetres:");
        lblCm.setMinWidth(210);
        lblCm.setStyle("-fx-font: 13 arial;");
        txtCm = new TextField();
        txtCm.setMinWidth(210);
        txtCm.setPromptText("Enter number of cm");
        txtCm.textProperty().addListener(
                (obs, oldVal, newVal) -> txtCm.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        txtCm.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		txtMm.clear();
        		txtIn.clear();
        	}
        });

        lblIn = new Label("Map measurement in inches:");
        lblIn.setMinWidth(210);
        lblIn.setStyle("-fx-font: 13 arial;");
        txtIn = new TextField();
        txtIn.setMinWidth(210);
        txtIn.setPromptText("Enter number of in inches");
        txtIn.textProperty().addListener(
                (obs, oldVal, newVal) -> txtIn.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        txtIn.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		txtMm.clear();
        		txtCm.clear();
        	}
        });
        
        VBox vboxMap = new VBox();
        vboxMap.setPadding(new Insets(0, 20, 0, 20));
        vboxMap.setSpacing(5);
        vboxMap.getChildren().addAll(hboxTitle1, lblMm, txtMm, lblGap1a, lblCm, txtCm, lblGap1b, lblIn, txtIn);


        /*
         * Vbox pane with everything for ground distance measurements
         */
        Label lblGround = new Label("Ground distance");
        lblGround.setStyle("-fx-font: 16 arial");
        lblGround.setPadding(new Insets(0, 0, 0, 45));
        HBox hboxTitle2 = new HBox(lblGround);
        hboxTitle2.setPadding(new Insets(0, 0, 10, 0));
        
        lblKm = new Label("Ground distance in kilometres:");
        lblKm.setMinWidth(210);
        lblKm.setStyle("-fx-font: 13 arial;");
        txtKm = new TextField();
        txtKm.setMinWidth(210);
        txtKm.setPromptText("Enter distance in km");
        txtKm.textProperty().addListener(
                (obs, oldVal, newVal) -> txtKm.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        txtKm.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		txtMetre.clear();
    			txtMile.clear();
    			txtFt.clear();
    			txtYard.clear();
        	}
        });
        
        lblMetre = new Label("Ground distance in metres:");
        lblMetre.setMinWidth(210);
        lblMetre.setStyle("-fx-font: 13 arial;");
        txtMetre = new TextField();
        txtMetre.setMinWidth(210);
        txtMetre.setPromptText("Enter distance in metres");
        txtMetre.textProperty().addListener(
                (obs, oldVal, newVal) -> txtMetre.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        txtMetre.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		txtKm.clear();
    			txtMile.clear();
    			txtFt.clear();
    			txtYard.clear();
        	}
        });

        lblMile = new Label("Ground distance in miles:");
        lblMile.setMinWidth(210);
        lblMile.setStyle("-fx-font: 13 arial;");
        txtMile = new TextField();
        txtMile.setMinWidth(210);
        txtMile.setPromptText("Enter distance in miles");
        txtMile.textProperty().addListener(
                (obs, oldVal, newVal) -> txtMile.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        txtMile.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		txtMetre.clear();
    			txtKm.clear();
    			txtFt.clear();
    			txtYard.clear();
        	}
        });

        lblFt = new Label("Ground distance in feet:");
        lblFt.setMinWidth(210);
        lblFt.setStyle("-fx-font: 13 arial;");
        txtFt = new TextField();
        txtFt.setMinWidth(210);
        txtFt.setPromptText("Enter distance in feet");
        txtFt.textProperty().addListener(
                (obs, oldVal, newVal) -> txtFt.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        txtFt.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		txtMetre.clear();
    			txtMile.clear();
    			txtKm.clear();
    			txtYard.clear();
        	}
        });

        lblYard = new Label("Ground distance in yards:");
        lblYard.setMinWidth(210);
        lblYard.setStyle("-fx-font: 13 arial;");
        txtYard = new TextField();
        txtYard.setMinWidth(210);
        txtYard.setPromptText("Enter distance in yards");
        txtYard.textProperty().addListener(
                (obs, oldVal, newVal) -> txtYard.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        txtYard.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		txtMetre.clear();
    			txtMile.clear();
    			txtFt.clear();
    			txtKm.clear();
        	}
        });

        VBox vboxGround = new VBox();
        vboxGround.setPadding(new Insets(0, 20, 0, 20));
        vboxGround.setSpacing(5);
        vboxGround.getChildren().addAll(hboxTitle2, lblKm, txtKm, lblGap2a, lblMetre, txtMetre, lblGap2b, lblMile, txtMile, 
        								lblGap2c, lblFt, txtFt, lblGap2d, lblYard, txtYard);


        /*
         * Vbox pane with everything for scale calculations
         */
        Label lblScale = new Label("Map scale");
        lblScale.setStyle("-fx-font: 16 arial");
        lblScale.setPadding(new Insets(0, 0, 0, 45));
        HBox hboxTitle3 = new HBox(lblScale);
        hboxTitle3.setPadding(new Insets(0, 0, 10, 0));

        lblScaleFractional = new Label("Fractional e.g. 1:10,000");
        lblScaleFractional.setMinWidth(210);
        lblScaleFractional.setStyle("-fx-font: 13 arial;");
        txtScaleFractional = new TextField();
        txtScaleFractional.setMinWidth(190);
        txtScaleFractional.setPromptText("Enter denominator (eg.10000)");
        txtScaleFractional.textProperty().addListener(
                (obs, oldVal, newVal) -> txtScaleFractional.setText(newVal.replaceAll("[^\\d\\,\\d]", "")));
        txtScaleFractional.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		txtScaleInch.clear();
    			txtScaleMile.clear();
    			txtScaleCm.clear();
    			txtScaleKm.clear();
        	}
        });
        Label lbltxtFractional = new Label("1 : ");
        lbltxtFractional.setStyle("-fx-font: 15 arial;");
        HBox hboxFractional = new HBox(lbltxtFractional, txtScaleFractional);
        hboxFractional.setAlignment(Pos.CENTER_LEFT);
        
        lblScaleInch = new Label("Inches to the mile (Imperial):");
        lblScaleInch.setMinWidth(210);
        lblScaleInch.setStyle("-fx-font: 13 arial;");
        txtScaleInch = new TextField();
        txtScaleInch.setPrefWidth(135);
        txtScaleInch.setPromptText("Enter number of inches");
        txtScaleInch.setAlignment(Pos.BASELINE_RIGHT);
        txtScaleInch.textProperty().addListener(
                (obs, oldVal, newVal) -> txtScaleInch.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        txtScaleInch.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		txtScaleFractional.clear();
    			txtScaleMile.clear();
    			txtScaleCm.clear();
    			txtScaleKm.clear();
        	}
        });
        Label lbltxtInch = new Label(" in to the mile");
        lbltxtInch.setStyle("-fx-font: 11 arial;");
        lbltxtInch.setStyle("-fx-text-fill: grey;");
        HBox hboxInch = new HBox(txtScaleInch, lbltxtInch);
        hboxInch.setAlignment(Pos.CENTER_LEFT);

        lblScaleMile = new Label("Miles to an inch (Imperial):");
        lblScaleMile.setMinWidth(210);
        lblScaleMile.setStyle("-fx-font: 13 arial;");
        txtScaleMile = new TextField();
        txtScaleMile.setPrefWidth(116);
        txtScaleMile.setPromptText("Enter num. of miles");
        txtScaleMile.setAlignment(Pos.BASELINE_RIGHT);
        txtScaleMile.textProperty().addListener(
                (obs, oldVal, newVal) -> txtScaleMile.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        txtScaleMile.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		txtScaleInch.clear();
    			txtScaleFractional.clear();
    			txtScaleCm.clear();
    			txtScaleKm.clear();
        	}
        });
        Label lbltxtMile = new Label(" mile(s) to an inch");
        lbltxtMile.setStyle("-fx-font: 11 arial;");
        lbltxtMile.setStyle("-fx-text-fill: grey;");
        HBox hboxMile = new HBox(txtScaleMile, lbltxtMile);
        hboxMile.setAlignment(Pos.CENTER_LEFT);

        lblScaleCm = new Label("Centimetres to a kilometre (Metric):");
        lblScaleCm.setMinWidth(210);
        lblScaleCm.setStyle("-fx-font: 13 arial;");
        txtScaleCm = new TextField();
        txtScaleCm.setPrefWidth(146);
        txtScaleCm.setPromptText("Enter num. of centimetres");
        txtScaleCm.setAlignment(Pos.BASELINE_RIGHT);
        txtScaleCm.textProperty().addListener(
                (obs, oldVal, newVal) -> txtScaleCm.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        txtScaleCm.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		txtScaleInch.clear();
    			txtScaleMile.clear();
    			txtScaleFractional.clear();
    			txtScaleKm.clear();
        	}
        });
        Label lbltxtCm = new Label(" cm to a km");
        lbltxtCm.setStyle("-fx-font: 11 arial;");
        lbltxtCm.setStyle("-fx-text-fill: grey;");
        HBox hboxCm = new HBox(txtScaleCm, lbltxtCm);
        hboxCm.setAlignment(Pos.CENTER_LEFT);
        
        lblScaleKm = new Label("Kilometres to a centimetre (Metric):");
        lblScaleKm.setMinWidth(210);
        lblScaleKm.setStyle("-fx-font: 13 arial;");
        txtScaleKm = new TextField();
        txtScaleKm.setMinWidth(146);
        txtScaleKm.setPromptText("Enter num. of kilometres");
        txtScaleKm.setAlignment(Pos.BASELINE_RIGHT);
        txtScaleKm.textProperty().addListener(
                (obs, oldVal, newVal) -> txtScaleKm.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        txtScaleKm.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		txtScaleInch.clear();
    			txtScaleMile.clear();
    			txtScaleCm.clear();
    			txtScaleFractional.clear();
        	}
        });
        Label lbltxtKm = new Label(" km to a cm");
        lbltxtKm.setStyle("-fx-font: 11 arial;");
        lbltxtKm.setStyle("-fx-text-fill: grey;");
        HBox hboxKm = new HBox(txtScaleKm, lbltxtKm);
        hboxKm.setAlignment(Pos.CENTER_LEFT);
        
        VBox vboxScale = new VBox();
        vboxScale.setPadding(new Insets(0, 20, 0, 20));
        vboxScale.setSpacing(5);
        vboxScale.getChildren().addAll(hboxTitle3, lblScaleFractional, hboxFractional, lblGap3a, lblScaleInch, hboxInch, lblGap3b, lblScaleMile, hboxMile, 
        								lblGap3c, lblScaleCm, hboxCm, lblGap3d, lblScaleKm, hboxKm, lblGap3e);

        /*
         * Setting ''Help', Calculate' and 'Clear' buttons
         */
        Button btnCalculate = new Button();
        Label lblCalculate = new Label("CALCULATE");
        lblCalculate.setTextFill(Color.NAVY);
        lblCalculate.setStyle("-fx-font: 18 arial;");
        lblCalculate.setRotate(-90);
        btnCalculate.setGraphic(new Group(lblCalculate));
        btnCalculate.setMinHeight(170);
        btnCalculate.setMinWidth(50);
        btnCalculate.setOnAction(new EventHandler<ActionEvent>(){
        	@Override
        	public void handle (ActionEvent event){
        		calculate();
        	}
        });

        Button btnClear = new Button();
        Label lblClear = new Label("CLEAR");
        lblClear.setTextFill(Color.RED);
        lblClear.setStyle("-fx-font: 20 arial;");
        lblClear.setRotate(-90);
        btnClear.setGraphic(new Group(lblClear));
        btnClear.setMinHeight(170);
        btnClear.setMinWidth(50);
        btnClear.setOnAction(new EventHandler<ActionEvent>(){
        	@Override
        	public void handle (ActionEvent event){
        		clearFieldsTabB();       		
        	}
        });
        
        labelGapHelp.setMinWidth(150);
        buttonHelpTabB.setText("HELP");
        buttonHelpTabB.setStyle("-fx-text-fill: DarkGoldenRod;");
        buttonHelpTabB.setMaxHeight(40);
        buttonHelpTabB.setMinWidth(70);
        buttonHelpTabB.setOnAction
        				(e -> ImageBoxSingle.show("Help for Distance Calculator", "Help", IMAGE_TAB_B1));

        /*
         * Setting TabB layout
         */
        Text textBottomB1 = new Text("Please fill in ONE field in TWO out of the three columns" +
                " - the third column will be calcutated");
        textBottomB1.setFill(Color.STEELBLUE);
        textBottomB1.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        HBox vboxBottomB = new HBox(textBottomB1, labelGapHelp, buttonHelpTabB);
        vboxBottomB.setAlignment(Pos.BOTTOM_RIGHT);
        vboxBottomB.setPadding(new Insets(0, 35, 20, 20));
        
        VBox vboxButtons = new VBox(btnCalculate, btnClear);
        vboxButtons.setPadding(new Insets(0, 20, 0, 20));
        vboxButtons.setSpacing(10);
        
        BorderPane tabB_borderPane = new BorderPane();
        HBox hboxB = new HBox(10, vboxMap, vboxGround, vboxScale, vboxButtons);
        tabB_borderPane.setTop(paneTopB);
        tabB_borderPane.setCenter(hboxB);
        tabB_borderPane.setBottom(vboxBottomB);
        tabB.setContent(tabB_borderPane);


        /************************************* TabC *************************************/
        
        Tab tabC = new Tab();
        tabC.setText("Coordinate Converter");


        /*
         * Top pane title
         */
        Text titleC = new Text("  C o o r d i n a t e    C o n v e r t e r  ");
        titleC.setFont(Font.font("Arial", FontWeight.NORMAL, 22));
        titleC.setFill(Color.NAVY);
        HBox paneTopC = new HBox(titleC);
        paneTopC.setAlignment(Pos.CENTER);
        paneTopC.setPadding(new Insets(20, 20, 10, 0));


        /*
         * ******************** Left Point section
         */
        labelLeftPoint.setText("LEFT POINT");
        labelLeftPoint.setStyle("-fx-font: 13 arial; -fx-text-fill: grey;");
        
        labelLeftOS.setText("OS Grid Reference");
        labelLeftOS.setStyle("-fx-font: 14 arial;");
        
        labelLeftOsSixFigure.setText("Six-Figure Reference");
        labelLeftOsSixFigure.setStyle("-fx-font: 11 arial; -fx-text-fill: grey;");
        labelLeftOsSixFigure.setMinWidth(110);
        textLeftOsSixFigure.setPromptText("e.g. TQ571168");
        textLeftOsSixFigure.setMaxWidth(105);
        labelGap0a.setMinWidth(5); labelGap0b.setMinWidth(10);
        labelGap0aa.setMinWidth(10); labelGap0ba.setMinWidth(10);
        labelGap0aaa.setMinWidth(5); labelGap0baa.setMinWidth(15);
                
        labelLeftOSEast.setText("Easting ");
        labelLeftOSEast.setMinWidth(140);
        labelLeftOSEast.setStyle("-fx-font: 11 arial; -fx-text-fill: grey;");
        labelLeftOSNorth.setText("                                Northing");
        labelLeftOSNorth.setMinWidth(150);
        labelLeftOSNorth.setStyle("-fx-font: 11 arial; -fx-text-fill: grey;");
        textLeftOSEast.setPromptText("Easting (6 digits)");
        textLeftOSEast.setMaxWidth(140);
        textLeftOSNorth.setPromptText("Northing (6 digits)");
        textLeftOSNorth.setMaxWidth(140);

        labelLeftLat.setText("Latitude");
        labelLeftLat.setMinWidth(120);
        labelLeftLat.setAlignment(Pos.BASELINE_RIGHT);
        labelLeftLat.setStyle("-fx-font: 14 arial;");
        
        textLeftWGS84_degDec_Lat.setPromptText("decimal Degrees");
        textLeftWGS84_degDecMin_Lat.setPromptText("Degrees");
        textLeftWGS84_degDecMin_Lat.setMaxWidth(100);
        textLeftWGS84_minDecMin_Lat.setPromptText("decimal Minutes");
        textLeftWGS84_degDecSec_Lat.setPromptText("Degrees");
        textLeftWGS84_minDecSec_Lat.setPromptText("Minutes");
        textLeftWGS84_secDecSec_Lat.setPromptText("Seconds");
        
        labelLeftLon.setText("Longitude");
        labelLeftLon.setMinWidth(120);
        labelLeftLon.setAlignment(Pos.BASELINE_RIGHT);
        labelLeftLon.setStyle("-fx-font: 14 arial;");
        
        textLeftWGS84_degDec_Lon.setPromptText("decimal Degrees");
        textLeftWGS84_degDecMin_Lon.setPromptText("Degrees");
        textLeftWGS84_degDecMin_Lon.setMaxWidth(100);
        textLeftWGS84_minDecMin_Lon.setPromptText("decimal Minutes");
        textLeftWGS84_degDecSec_Lon.setPromptText("Degrees");
        textLeftWGS84_minDecSec_Lon.setPromptText("Minutes");
        textLeftWGS84_secDecSec_Lon.setPromptText("Seconds");
                
        buttonLeftPointConvert.setText("CONVERT / SUBMIT");
        buttonLeftPointConvert.setMinWidth(150);
        buttonLeftPointConvert.setMaxHeight(50);
        buttonLeftPointConvert.setStyle("-fx-text-fill: navy;");
        buttonLeftPointClear.setText("RESET");
        buttonLeftPointClear.setMinWidth(150);
        buttonLeftPointClear.setMaxHeight(50);
        buttonLeftPointClear.setStyle("-fx-text-fill: darkred;");

        /*
         * Degree, Minute and Second symbols for Left Point
         */
        labelLeftWGS84_degDec_Lat.setText("\u00b0"); // degrees
        labelLeftWGS84_degDec_Lat.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");

        labelLeftWGS84_degDecMin_Lat.setText("\u00b0 ");
        labelLeftWGS84_degDecMin_Lat.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");
        labelLeftWGS84_degDecMin_Lat.setMinWidth(77);
        labelLeftWGS84_minDecMin_Lat.setText("\u2019 "); // minutes
        labelLeftWGS84_minDecMin_Lat.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");

        labelLeftWGS84_degDecSec_Lat.setText("\u00b0 ");
        labelLeftWGS84_degDecSec_Lat.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");
        labelLeftWGS84_degDecSec_Lat.setMinWidth(65);
        labelLeftWGS84_minDecSec_Lat.setText("\u2019 ");
        labelLeftWGS84_minDecSec_Lat.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");
        labelLeftWGS84_minDecSec_Lat.setMinWidth(65);
        labelLeftWGS84_secDecSec_Lat.setText("\u201D  "); // seconds
        labelLeftWGS84_secDecSec_Lat.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");


        labelLeftWGS84_degDec_Lon.setText("\u00b0");
        labelLeftWGS84_degDec_Lon.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");

        labelLeftWGS84_degDecMin_Lon.setText("\u00b0");
        labelLeftWGS84_degDecMin_Lon.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");
        labelLeftWGS84_degDecMin_Lon.setMinWidth(75);
        labelLeftWGS84_minDecMin_Lon.setText("\u2019 "); // minutes
        labelLeftWGS84_minDecMin_Lon.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");

        labelLeftWGS84_degDecSec_Lon.setText("\u00b0 ");
        labelLeftWGS84_degDecSec_Lon.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");
        labelLeftWGS84_degDecSec_Lon.setMinWidth(65);
        labelLeftWGS84_minDecSec_Lon.setText("\u2019 ");
        labelLeftWGS84_minDecSec_Lon.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");
        labelLeftWGS84_minDecSec_Lon.setMinWidth(65);
        labelLeftWGS84_secDecSec_Lon.setText("\u201D  "); // seconds
        labelLeftWGS84_secDecSec_Lon.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");

        /*
         * Setting  Left Point layout
         */
        BorderPane tabC_borderPane = new BorderPane();

        HBox leftOSPane = new HBox(labelLeftOS);
        leftOSPane.setAlignment(Pos.CENTER);
        leftOSPane.setPadding(new Insets(0, 0, 5, 0));
        HBox leftOSPane_lbl = new HBox(labelLeftOSEast,labelGap0aa, labelLeftOsSixFigure, labelGap0a,
                labelLeftOSNorth, labelGap0aaa);
        HBox leftOSPane_txt = new HBox(textLeftOSEast, labelGap0ba, textLeftOsSixFigure, labelGap0b,
                textLeftOSNorth, labelGap0baa);
        VBox leftOSPane_all = new VBox(leftOSPane, leftOSPane_lbl, leftOSPane_txt);
        leftOSPane_all.setPadding(new Insets(0, 0, 20, 0));
        leftOSPane_all.setAlignment(Pos.CENTER);

        VBox leftWGS84_degDecimalPane_Lat = new VBox(labelLeftLat, labelLeftWGS84_degDec_Lat,
                textLeftWGS84_degDec_Lat);
        HBox leftWGS84_minDecimalPane_lbl_Lat = new HBox(labelLeftWGS84_degDecMin_Lat,
                labelLeftWGS84_minDecMin_Lat);
        HBox leftWGS84_minDecimalPane_txt_Lat = new HBox(textLeftWGS84_degDecMin_Lat,
                labelGap5,textLeftWGS84_minDecMin_Lat);
        HBox leftWGS84_secDecimalPane_lbl_Lat = new HBox(labelLeftWGS84_degDecSec_Lat,
                labelLeftWGS84_minDecSec_Lat, labelLeftWGS84_secDecSec_Lat);
        HBox leftWGS84_secDecimalPane_txt_Lat = new HBox(textLeftWGS84_degDecSec_Lat, labelGap6,
                textLeftWGS84_minDecSec_Lat, labelGap7, textLeftWGS84_secDecSec_Lat);

        VBox leftWGS84_degDecimalPane_Lon = new VBox(labelLeftLon, labelLeftWGS84_degDec_Lon,
                textLeftWGS84_degDec_Lon);
        HBox leftWGS84_minDecimalPane_lbl_Lon = new HBox(labelLeftWGS84_degDecMin_Lon,
                labelLeftWGS84_minDecMin_Lon);
        HBox leftWGS84_minDecimalPane_txt_Lon = new HBox(textLeftWGS84_degDecMin_Lon, labelGap8,
                textLeftWGS84_minDecMin_Lon);
        HBox leftWGS84_secDecimalPane_lbl_Lon = new HBox(labelLeftWGS84_degDecSec_Lon,
                labelLeftWGS84_minDecSec_Lon, labelLeftWGS84_secDecSec_Lon);
        HBox leftWGS84_secDecimalPane_txt_Lon = new HBox(textLeftWGS84_degDecSec_Lon,
                labelGap9, textLeftWGS84_minDecSec_Lon, labelGap10, textLeftWGS84_secDecSec_Lon);

        VBox leftPointPane_Lat = new VBox(leftWGS84_degDecimalPane_Lat, labelLeftWGS84_minDec_Lat,
                leftWGS84_minDecimalPane_lbl_Lat, leftWGS84_minDecimalPane_txt_Lat, labelLeftWGS84_secDec_Lat,
                leftWGS84_secDecimalPane_lbl_Lat, leftWGS84_secDecimalPane_txt_Lat);
        leftPointPane_Lat.setPadding(new Insets(0, 15, 8, 0));
        VBox leftPointPane_Lon = new VBox(leftWGS84_degDecimalPane_Lon, labelLeftWGS84_minDec_Lon,
                leftWGS84_minDecimalPane_lbl_Lon, leftWGS84_minDecimalPane_txt_Lon, labelLeftWGS84_secDec_Lon,
                leftWGS84_secDecimalPane_lbl_Lon, leftWGS84_secDecimalPane_txt_Lon);
        leftPointPane_Lon.setPadding(new Insets(0, 15, 8, 0));
        HBox leftPointPane_LatLong = new HBox(leftPointPane_Lon, leftPointPane_Lat);

        HBox buttonLeftPointPane = new HBox(buttonLeftPointConvert, labelGap13, buttonLeftPointClear);
        buttonLeftPointPane.setAlignment(Pos.CENTER);
        HBox labelLeftPointPane = new HBox(labelLeftPoint);
        labelLeftPointPane.setAlignment(Pos.BASELINE_LEFT);
        VBox coordsLeftPane = new VBox(labelLeftPointPane, leftOSPane_all, leftPointPane_LatLong, buttonLeftPointPane);
        coordsLeftPane.setPadding(new Insets(0, 10, 0, 0));
        coordsLeftPane.setAlignment(Pos.CENTER);

        /*
         * ******************** Right Point section
         */
        labelRightPoint.setText("RIGHT POINT");
        labelRightPoint.setStyle("-fx-font: 13 arial; -fx-text-fill: grey;");
        
        labelRightOS.setText("OS Grid Reference");
        labelRightOS.setStyle("-fx-font: 14 arial;");
        
        labelRightOsSixFigure.setText("Six-Figure Reference");
        labelRightOsSixFigure.setStyle("-fx-font: 11 arial; -fx-text-fill: grey;");
        labelRightOsSixFigure.setMinWidth(110);
        textRightOsSixFigure.setPromptText("e.g. TQ571168");
        textRightOsSixFigure.setMaxWidth(105);
        labelGap00a.setMinWidth(5); labelGap00b.setMinWidth(10);
        labelGap00aa.setMinWidth(10); labelGap00ba.setMinWidth(10);
        labelGap00aaa.setMinWidth(5); labelGap00baa.setMinWidth(15);

        labelRightOSEast.setText("Easting ");
        labelRightOSEast.setMinWidth(140);
        labelRightOSEast.setStyle("-fx-font: 11 arial; -fx-text-fill: grey;");
        labelRightOSNorth.setText("                                Northing");
        labelRightOSNorth.setMinWidth(150);
        labelRightOSNorth.setStyle("-fx-font: 11 arial; -fx-text-fill: grey;");
        textRightOSEast.setPromptText("Easting (6 digits)");
        textRightOSEast.setMaxWidth(1140);
        textRightOSNorth.setPromptText("Northing (6 digits)");
        textRightOSNorth.setMaxWidth(1140);

        labelRightLat.setText("Latitude");
        labelRightLat.setMinWidth(120);
        labelRightLat.setAlignment(Pos.BASELINE_RIGHT);
        labelRightLat.setStyle("-fx-font: 14 arial;");
        
        textRightWGS84_degDec_Lat.setPromptText("decimal Degrees");
        textRightWGS84_degDecMin_Lat.setPromptText("Degrees");
        textRightWGS84_degDecMin_Lat.setMaxWidth(100);
        textRightWGS84_minDecMin_Lat.setPromptText("decimal Minutes");
        textRightWGS84_degDecSec_Lat.setPromptText("Degrees");
        textRightWGS84_minDecSec_Lat.setPromptText("Minutes");
        textRightWGS84_secDecSec_Lat.setPromptText("Seconds");
        
        labelRightLon.setText("Longitude");
        labelRightLon.setMinWidth(120);
        labelRightLon.setAlignment(Pos.BASELINE_RIGHT);
        labelRightLon.setStyle("-fx-font: 14 arial;");
        
        textRightWGS84_degDec_Lon.setPromptText("decimal Degrees");
        textRightWGS84_degDecMin_Lon.setPromptText("Degrees");
        textRightWGS84_degDecMin_Lon.setMaxWidth(100);
        textRightWGS84_minDecMin_Lon.setPromptText("decimal Minutes");
        textRightWGS84_degDecSec_Lon.setPromptText("Degrees");
        textRightWGS84_minDecSec_Lon.setPromptText("Minutes");
        textRightWGS84_secDecSec_Lon.setPromptText("Seconds");
        
        buttonRightPointConvert.setText("CONVERT / SUBMIT");
        buttonRightPointConvert.setMinWidth(150);
        buttonRightPointConvert.setMaxHeight(50);
        buttonRightPointConvert.setStyle("-fx-text-fill: navy;");
        buttonRightPointClear.setText("RESET");
        buttonRightPointClear.setMinWidth(150);
        buttonRightPointClear.setMaxHeight(50);
        buttonRightPointClear.setStyle("-fx-text-fill: darkred;");

        /*
         * Degree, Minute and Second symbols for Right Point
         */
        labelRightWGS84_degDec_Lat.setText("\u00b0"); // degrees
        labelRightWGS84_degDec_Lat.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");

        labelRightWGS84_degDecMin_Lat.setText("\u00b0 ");
        labelRightWGS84_degDecMin_Lat.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");
        labelRightWGS84_degDecMin_Lat.setMinWidth(77);
        labelRightWGS84_minDecMin_Lat.setText("\u2019 "); // minutes
        labelRightWGS84_minDecMin_Lat.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");

        labelRightWGS84_degDecSec_Lat.setText("\u00b0 ");
        labelRightWGS84_degDecSec_Lat.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");
        labelRightWGS84_degDecSec_Lat.setMinWidth(65);
        labelRightWGS84_minDecSec_Lat.setText("\u2019 ");
        labelRightWGS84_minDecSec_Lat.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");
        labelRightWGS84_minDecSec_Lat.setMinWidth(65);
        labelRightWGS84_secDecSec_Lat.setText("\u201D  "); // seconds
        labelRightWGS84_secDecSec_Lat.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");


        labelRightWGS84_degDec_Lon.setText("\u00b0");
        labelLeftWGS84_degDec_Lon.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");

        labelRightWGS84_degDecMin_Lon.setText("\u00b0");
        labelRightWGS84_degDecMin_Lon.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");
        labelRightWGS84_degDecMin_Lon.setMinWidth(75);
        labelRightWGS84_minDecMin_Lon.setText("\u2019 "); // minutes
        labelRightWGS84_minDecMin_Lon.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");

        labelRightWGS84_degDecSec_Lon.setText("\u00b0 ");
        labelRightWGS84_degDecSec_Lon.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");
        labelRightWGS84_degDecSec_Lon.setMinWidth(65);
        labelRightWGS84_minDecSec_Lon.setText("\u2019 ");
        labelRightWGS84_minDecSec_Lon.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");
        labelRightWGS84_minDecSec_Lon.setMinWidth(65);
        labelRightWGS84_secDecSec_Lon.setText("\u201D  "); // seconds
        labelRightWGS84_secDecSec_Lon.setStyle("-fx-font: 15 arial; -fx-text-fill: black;");

        /*
         * Setting  Right Point layout
         */
        HBox rightOSPane = new HBox(labelRightOS);
        rightOSPane.setAlignment(Pos.CENTER);
        rightOSPane.setPadding(new Insets(0, 0, 5, 0));
        HBox rightOSPane_lbl = new HBox(labelRightOSEast,labelGap00aa, labelRightOsSixFigure,
                labelGap00a, labelRightOSNorth, labelGap00aaa);
        HBox rightOSPane_txt = new HBox(textRightOSEast, labelGap00ba, textRightOsSixFigure,
                labelGap00b, textRightOSNorth, labelGap00baa);
        VBox rightOSPane_all = new VBox(rightOSPane, rightOSPane_lbl, rightOSPane_txt);
        rightOSPane_all.setPadding(new Insets(0, 0, 20, 0));
        rightOSPane_all.setAlignment(Pos.CENTER);

        VBox rightWGS84_degDecimalPane_Lat = new VBox(labelRightLat, labelRightWGS84_degDec_Lat,
                textRightWGS84_degDec_Lat);
        HBox rightWGS84_minDecimalPane_lbl_Lat = new HBox(labelRightWGS84_degDecMin_Lat,
                labelRightWGS84_minDecMin_Lat);
        HBox rightWGS84_minDecimalPane_txt_Lat = new HBox(textRightWGS84_degDecMin_Lat, labelGap14,
                textRightWGS84_minDecMin_Lat);
        HBox rightWGS84_secDecimalPane_lbl_Lat = new HBox(labelRightWGS84_degDecSec_Lat,
                labelRightWGS84_minDecSec_Lat, labelRightWGS84_secDecSec_Lat);
        HBox rightWGS84_secDecimalPane_txt_Lat = new HBox(textRightWGS84_degDecSec_Lat, labelGap15,
                textRightWGS84_minDecSec_Lat, labelGap16, textRightWGS84_secDecSec_Lat);

        VBox rightWGS84_degDecimalPane_Lon = new VBox(labelRightLon, labelRightWGS84_degDec_Lon,
                textRightWGS84_degDec_Lon);
        HBox rightWGS84_minDecimalPane_lbl_Lon = new HBox(labelRightWGS84_degDecMin_Lon,
                labelRightWGS84_minDecMin_Lon);
        HBox rightWGS84_minDecimalPane_txt_Lon = new HBox(textRightWGS84_degDecMin_Lon, labelGap17,
                textRightWGS84_minDecMin_Lon);
        HBox rightWGS84_secDecimalPane_lbl_Lon = new HBox(labelRightWGS84_degDecSec_Lon,
                labelRightWGS84_minDecSec_Lon, labelRightWGS84_secDecSec_Lon);
        HBox rightWGS84_secDecimalPane_txt_Lon = new HBox(textRightWGS84_degDecSec_Lon,
                labelGap18, textRightWGS84_minDecSec_Lon, labelGap19, textRightWGS84_secDecSec_Lon);

        VBox rightPointPane_Lat = new VBox(rightWGS84_degDecimalPane_Lat, labelRightWGS84_minDec_Lat,
                rightWGS84_minDecimalPane_lbl_Lat, rightWGS84_minDecimalPane_txt_Lat, labelRightWGS84_secDec_Lat,
                rightWGS84_secDecimalPane_lbl_Lat, rightWGS84_secDecimalPane_txt_Lat);
        rightPointPane_Lat.setPadding(new Insets(0, 15, 8, 0));
        VBox rightPointPane_Lon = new VBox(rightWGS84_degDecimalPane_Lon, labelRightWGS84_minDec_Lon,
                rightWGS84_minDecimalPane_lbl_Lon, rightWGS84_minDecimalPane_txt_Lon, labelRightWGS84_secDec_Lon,
                rightWGS84_secDecimalPane_lbl_Lon, rightWGS84_secDecimalPane_txt_Lon);
        rightPointPane_Lon.setPadding(new Insets(0, 15, 8, 0));
        HBox rightPointPane_LatLong = new HBox(rightPointPane_Lon, rightPointPane_Lat);

        HBox buttonRightPointPane = new HBox(buttonRightPointConvert, labelGap20, buttonRightPointClear);
        buttonRightPointPane.setAlignment(Pos.CENTER);
        HBox labelRightPointPane = new HBox(labelRightPoint);
        labelRightPointPane.setAlignment(Pos.BASELINE_LEFT);
        VBox coordsRightPane = new VBox(labelRightPointPane, rightOSPane_all,rightPointPane_LatLong, buttonRightPointPane);
        coordsRightPane.setPadding(new Insets(0, 0, 0, 10));
        coordsRightPane.setAlignment(Pos.CENTER);

        /*
         * Putting together Left and Right Points sections
         */
        HBox coordsPane = new HBox(coordsLeftPane, coordsRightPane);

        /*
         * ******************** Distance and Bearing section
         */
        lableDistTitle.setText("D I S T A N C E   &   B E A R I N G");
        lableDistTitle.setStyle("-fx-font: 15 arial;");
        lableDistTitle.setMinWidth(450);
        lableDistTitle.setAlignment(Pos.BOTTOM_CENTER);
        lableDistTitle.setPadding(new Insets(10, 0, 10, 0));
        
        labelDistKm.setText("Kilometres");
        labelDistKm.setStyle("-fx-font: 14 arial;");
        labelDistKm.setMinWidth(145);
        labelDistKm.setAlignment(Pos.BASELINE_CENTER);
        labelDistMilesStatue.setText("Statue Miles");
        labelDistMilesStatue.setStyle("-fx-font: 14 arial;");
        labelDistMilesStatue.setMinWidth(175);
        labelDistMilesStatue.setAlignment(Pos.BASELINE_CENTER);
        labelDistMilesNautical.setText("Nautical Miles");
        labelDistMilesNautical.setStyle("-fx-font: 14 arial;");
        labelDistMilesNautical.setMinWidth(160);
        labelDistMilesNautical.setAlignment(Pos.BASELINE_CENTER);
        labelDistMetres.setText("Metres");
        labelDistMetres.setStyle("-fx-font: 14 arial;");
        labelDistMetres.setMinWidth(165);
        labelDistMetres.setAlignment(Pos.BASELINE_CENTER);
        labelDistFt.setText("Feet");
        labelDistFt.setStyle("-fx-font: 14 arial;");
        labelDistFt.setMinWidth(165);
        labelDistFt.setAlignment(Pos.BASELINE_CENTER);
        
        textDistKm.setPromptText("kilometres");
        textDistMilesStatue.setPromptText("statue miles");
        textDistMilesNautical.setPromptText("nautical miles");
        textDistMetres.setPromptText("metres");
        textDistFt.setPromptText("feet");

        buttonDistCalculate.setText("CALCULATE (between points)");
        buttonDistCalculate.setStyle("-fx-text-fill: darkblue;");
        buttonDistCalculate.setMaxHeight(40);
        buttonDistCalculate.setPrefWidth(300); 

        buttonDistConvert.setText("CONVERT (between units)");
        buttonDistConvert.setStyle("-fx-text-fill: navy;");
        buttonDistConvert.setMaxHeight(40);
        buttonDistConvert.setPrefWidth(300);
        
        buttonDistClear.setText("CLEAR");
        buttonDistClear.setStyle("-fx-text-fill: darkred;");
        buttonDistClear.setMaxHeight(35);
        buttonDistClear.setPrefWidth(300);
        
        buttonBearingCalculate.setText("CALCULATE BEARING / AZIMUTH   (left -> right)");
        buttonBearingCalculate.setStyle("-fx-text-fill: darkblue;");
        buttonBearingCalculate.setMaxHeight(35);
        buttonBearingCalculate.setMinWidth(300);
        
        buttonBearingClear.setText("CLEAR");
        buttonBearingClear.setStyle("-fx-text-fill: darkred;");
        buttonBearingClear.setMaxHeight(35);
        buttonBearingClear.setMinWidth(70);
        
        textAzimuth.setPromptText("in degrees");
        textAzimuth.setStyle("-fx-font: 11 arial; -fx-text-fill: brown;");
        textAzimuth.setMaxHeight(35);
        textAzimuth.setPrefWidth(130);
        textAzimuth.setEditable(false);
        textAzimuth.setMouseTransparent(true);
        textAzimuth.setFocusTraversable(false);
        
        textBearing.setPromptText("in degrees");
        textBearing.setStyle("-fx-font: 11 arial; -fx-text-fill: brown;");
        textBearing.setMaxHeight(35);
        textBearing.setPrefWidth(130);
        textBearing.setEditable(false);
        textBearing.setMouseTransparent(true);
        textBearing.setFocusTraversable(false);
        
        buttonHelp.setText("HELP");
        buttonHelp.setStyle("-fx-text-fill: DarkGoldenRod;");
        buttonHelp.setMaxHeight(40);
        buttonHelp.setMinWidth(70);
       
        labelGap1.setMinWidth(20); labelGap2.setMinWidth(20); labelGap3.setMinWidth(20); labelGap4.setMinWidth(20);
        labelGap5.setMinWidth(7); labelGap6.setMinWidth(7); labelGap7.setMinWidth(7); labelGap8.setMinWidth(7);
        labelGap9.setMinWidth(7); labelGap10.setMinWidth(7); labelGap11.setMaxHeight(3); labelGap12.setMinWidth(30);
        labelGap12a.setMinWidth(30); labelGap13.setMinWidth(30); labelGap14.setMinWidth(7); labelGap15.setMinWidth(7);
        labelGap16.setMinWidth(7); labelGap17.setMinWidth(7); labelGap18.setMinWidth(7); labelGap19.setMinWidth(7);
        labelGap20.setMinWidth(30); labelGap22.setMinWidth(20);
        
        labelGap21.setMinWidth(40);
        labelGap25.setMinWidth(20);
        labelGap26.setMinWidth(20);
        
        labelAzimuth.setText("Azimuth ->");
        labelAzimuth.setStyle("-fx-font: 11 arial; -fx-text-fill: black;");
        labelAzimuth.setMinWidth(60);
        labelBearing.setText("Bearing ->");
        labelBearing.setStyle("-fx-font: 11 arial; -fx-text-fill: black;");
        labelBearing.setMinWidth(60);

        /*
         * Setting Distance and Bearing layout
         */
        HBox buttonDistPane = new HBox(buttonDistCalculate, labelGap12, buttonDistConvert,
                labelGap12a, buttonDistClear);
        buttonDistPane.setPadding(new Insets(8, 0, 0, 0));
        buttonDistPane.setAlignment(Pos.CENTER);
        HBox distLabelPane = new HBox(labelDistKm, labelDistMilesStatue, labelDistMilesNautical,
                labelDistMetres, labelDistFt);
        distLabelPane.setPadding(new Insets(0, 0, 0, 15));
        HBox distTextPane = new HBox(textDistKm,labelGap1, textDistMilesStatue, labelGap2,
                textDistMilesNautical, labelGap3, textDistMetres, labelGap4, textDistFt);
        distTextPane.setPadding(new Insets(0, 0, 0, 15));
        HBox distTitlePane = new HBox(lableDistTitle);
        distTitlePane.setPadding(new Insets(10, 0, 0, 180));
        VBox distPane = new VBox(distTitlePane, distLabelPane, distTextPane, buttonDistPane);
        distPane.setPadding(new Insets(0, 15, 0, 5));
        
        HBox bearingPane = new HBox(buttonBearingCalculate, labelGap22, labelAzimuth, textAzimuth,
                labelGap26, labelBearing, textBearing, labelGap25, buttonBearingClear, labelGap21, buttonHelp);
        bearingPane.setPadding(new Insets(25, 15, 0, 5));
        bearingPane.setAlignment(Pos.BASELINE_LEFT);


        /*
         * Setting TabC layout
         */
        VBox paneCenterC = new VBox(coordsPane, distPane, bearingPane);
        paneCenterC.setPadding(new Insets(0, 20, 20, 20));
        
        tabC_borderPane.setTop(paneTopC);
        tabC_borderPane.setCenter(paneCenterC);
        
        tabC.setContent(tabC_borderPane);

        /************************************* TabD *************************************/

        /*
         * Top pane with title
         */
        Tab tabD = new Tab();
        tabD.setText("Area & Perimeter  Calculator");
        Text titleD = new Text("  A r e a   &   P e r i m e t e r     C a l c u l a t o r  ");
        titleD.setFont(Font.font("Arial", FontWeight.NORMAL, 22));
        titleD.setFill(Color.NAVY);

        /*
         * Area section
         */
        buttonCalcArea = new Button("Calculate Polygon Area");
        buttonCalcArea.setMinWidth(200);
        buttonClearArea= new Button("Clear Area");
        buttonClearArea.setMinWidth(100);
        buttonClearArea.setStyle("-fx-text-fill: red;");

        HBox paneAreaButton = new HBox(buttonCalcArea, buttonClearArea);
        paneAreaButton.setPadding(new Insets(0, 0, 10, 0));
        paneAreaButton.setSpacing(15);

        textCalcAreaFt2 = new TextField();
        textCalcAreaFt2.setPrefWidth(80);
        textCalcAreaMetre2 = new TextField();
        textCalcAreaMetre2.setPrefWidth(80);
        textCalcAreaAcre = new TextField();
        textCalcAreaAcre.setPrefWidth(80);
        textCalcAreaHa = new TextField();
        textCalcAreaHa.setPrefWidth(80);
        labelFt2 = new Label("sq ft");
        labelFt2.setMinWidth(50);
        labelFt2.setTextAlignment(TextAlignment.CENTER);
        labelMetre2 = new Label("sq m");
        labelMetre2.setMinWidth(50);
        labelMetre2.setTextAlignment(TextAlignment.CENTER);
        labelAcre = new Label("acre(s)");
        labelAcre.setMinWidth(50);
        labelAcre.setTextAlignment(TextAlignment.CENTER);
        labelHa = new Label("ha");
        labelHa.setMinWidth(50);
        labelHa.setTextAlignment(TextAlignment.CENTER);

        HBox paneAreaText = new HBox(textCalcAreaFt2, labelFt2,textCalcAreaMetre2, labelMetre2,
                textCalcAreaAcre, labelAcre, textCalcAreaHa, labelHa);
        paneAreaText.setPadding(new Insets(0, 0, 20, 0));
        paneAreaText.setSpacing(5);

        VBox paneArea = new VBox(paneAreaButton, paneAreaText);

        /*
         * Perimeter section
         */
        buttonCalcPerim = new Button("Calculate Polygon Perimeter");
        buttonCalcPerim.setMinWidth(200);
        buttonClearPerim= new Button("Clear Perimeter");
        buttonClearPerim.setMinWidth(100);
        buttonClearPerim.setStyle("-fx-text-fill: red;");

        HBox panePerimButton = new HBox(buttonCalcPerim, buttonClearPerim);
        panePerimButton.setPadding(new Insets(0, 0, 10, 0));
        panePerimButton.setSpacing(15);

        textCalcPerimFt = new TextField();
        textCalcPerimFt.setPrefWidth(80);
        textCalcPerimMetre = new TextField();
        textCalcPerimMetre.setPrefWidth(80);
        textCalcPerimKm = new TextField();
        textCalcPerimKm.setPrefWidth(80);
        textCalcPerimMile = new TextField();
        textCalcPerimMile.setPrefWidth(80);
        labelFt = new Label("ft");
        labelFt.setMinWidth(50);
        labelFt.setTextAlignment(TextAlignment.CENTER);
        labelMetre = new Label("m");
        labelMetre.setMinWidth(50);
        labelMetre.setTextAlignment(TextAlignment.CENTER);
        labelKm = new Label("Km");
        labelKm.setMinWidth(50);
        labelKm.setTextAlignment(TextAlignment.CENTER);
        labelMile = new Label("Miles");
        labelMile.setMinWidth(50);
        labelMile.setTextAlignment(TextAlignment.CENTER);

        HBox panePerimText = new HBox(textCalcPerimFt, labelFt, textCalcPerimMetre, labelMetre,
                textCalcPerimKm, labelKm, textCalcPerimMile, labelMile);
        panePerimText.setPadding(new Insets(0, 0, 20, 0));
        panePerimText.setSpacing(5);

        VBox panePerim = new VBox(panePerimButton, panePerimText);

        VBox paneCalc = new VBox(paneArea, panePerim);
        paneCalc.setPadding(new Insets(0, 0, 10, 0));

        /*
         * Adding Points section
         */
        labelNumber = new Label("Number");
        labelNumber.setMinWidth(50);
        labelNumber.setTextAlignment(TextAlignment.RIGHT);
        labelEasting = new Label("   Easting");
        labelEasting.setMinWidth(50);
        labelEasting.setTextAlignment(TextAlignment.RIGHT);
        labelNorthing = new Label("  Northing");
        labelNorthing.setMinWidth(60);
        labelNorthing.setTextAlignment(TextAlignment.RIGHT);
        textPointNum = new TextField();
        textPointNum.setMinWidth(50);
        textPointNum.setMaxWidth(50);
        textPointEast = new TextField();
        textPointEast.setMinWidth(40);
        textPointNorth = new TextField();
        textPointNorth.setMinWidth(40);
        labelPointAdded = new Label("     ");
        labelPointAdded.setMinWidth(28);

        HBox panePointText = new HBox(labelNumber, textPointNum, labelEasting, textPointEast,
                labelNorthing, textPointNorth, labelPointAdded);
        panePointText.setPadding(new Insets(0, 10, 10, 0));
        panePointText.setSpacing(5);

        buttonAddPoint = new Button("Add New Point");
        buttonAddPoint.setMinWidth(170);
        buttonClearPointText = new Button("Clear Point");
        buttonClearPointText.setMinWidth(170);
        buttonClearPointText.setStyle("-fx-text-fill: red;");
        buttonValidatePoint = new Button("Check Polygon");
        buttonValidatePoint.setMinWidth(110);
        buttonValidatePoint.setStyle("-fx-text-fill: beige;");
        HBox paneAddPoint = new HBox(buttonAddPoint, buttonClearPointText, buttonValidatePoint);
        paneAddPoint.setPadding(new Insets(0, 10, 10, 0));
        paneAddPoint.setSpacing(15);

        VBox paneInput = new VBox(paneAddPoint, panePointText);
        paneInput.setPadding(new Insets(0, 10, 10, 0));

        // submit point as the Left Point in the TabC
        buttonSubmitLeft = new Button("Submit Left Point");
        buttonSubmitLeft.setPrefWidth(230);
        labelPointSubmittedLeft = new Label("                     Left Point Submitted");
        labelPointSubmittedLeft.setPrefWidth(230);
        labelPointSubmittedLeft.setStyle("-fx-text-fill: grey;");
        VBox paneLeftSubmitted = new VBox(buttonSubmitLeft, labelPointSubmittedLeft);
        paneLeftSubmitted.setPadding(new Insets(0, 10, 10, 0));
        paneLeftSubmitted.setSpacing(10);

        // submit point as the Right Point in the TabC
        buttonSubmitRight = new Button("Submit Rght Point");
        buttonSubmitRight.setPrefWidth(230);
        labelPointSubmittedRight = new Label("                     Right Point Submitted");
        labelPointSubmittedRight.setPrefWidth(230);
        labelPointSubmittedRight.setStyle("-fx-text-fill: grey;");
        VBox paneRightSubmitted = new VBox(buttonSubmitRight, labelPointSubmittedRight);
        paneRightSubmitted.setPadding(new Insets(0, 10, 10, 0));
        paneRightSubmitted.setSpacing(10);

        HBox paneSubmit = new HBox(paneLeftSubmitted, paneRightSubmitted);
        paneSubmit.setAlignment(Pos.BASELINE_LEFT);
        paneSubmit.setPadding(new Insets(0, 10, 40, 0));
        paneSubmit.setSpacing(10);

        /*
         * Read polygon from / Save polygon to a file section
         */
        buttonImport = new Button("Import Points From File:");
        buttonImport.setPrefWidth(200);
        comboImport = new ComboBox<>();//"Choose file format"
        comboImport.setPrefWidth(260);
        comboImport.setPromptText("                    choose file format");
        comboImport.getItems().addAll(SPACE_SIN_NUMBER, SPACE_WITH_NUMBER, COMMA_SIN_NUMBER, COMMA_WITH_NUMBER);

        buttonExport = new Button("Export List To File:");
        buttonExport.setPrefWidth(200);
        comboExport = new ComboBox<>();//"Choose file format"
        comboExport.setPrefWidth(260);
        comboExport.setPromptText("                    choose file format");
        comboExport.getItems().addAll(SPACE_SIN_NUMBER, SPACE_WITH_NUMBER, COMMA_SIN_NUMBER, COMMA_WITH_NUMBER);

        HBox paneImport = new HBox(buttonImport, comboImport);
        paneImport.setPadding(new Insets(0, 10, 10, 0));
        paneImport.setSpacing(20);
        HBox paneExport = new HBox(buttonExport, comboExport);
        paneExport.setPadding(new Insets(0, 10, 20, 0));
        paneExport.setSpacing(20);

        /*
         *  Setting 'Help' button
         */
        buttonHelpTabD = new Button("Help");
        buttonHelpTabD.setStyle("-fx-text-fill: DarkGoldenRod;");
        HBox paneIHelp = new HBox(buttonHelpTabD);
        paneIHelp.setAlignment(Pos.BASELINE_LEFT);
        paneIHelp.setPadding(new Insets(20, 0, 0, 0));

        /*
         * Table with 'Delete Point' and 'Clear' buttons section
         */
        buttonDelPoint = new Button("Delete Point");
        buttonDelPoint.setPrefWidth(100);
        buttonClearList = new Button("Clear List");
        buttonClearList.setPrefWidth(100);
        buttonClearList.setStyle("-fx-text-fill: red;");
        labelPointDeleted= new Label("");
        labelPointDeleted.setPrefWidth(60);
        labelPointDeleted.setPadding(new Insets(0, 0, 0, 20));
        HBox paneClearList = new HBox(buttonDelPoint, labelPointDeleted, buttonClearList);
        paneClearList.setAlignment(Pos.CENTER);
        paneClearList.setPadding(new Insets(10, 0, 0, 0));
        paneClearList.setSpacing(10);

        /*
         * Table for points / polygons
         */
        table = new TableView<>();
        table.setEditable(true);

        TableColumn<Vertex, Integer> colNum = new TableColumn<>("Number");
        colNum.setMinWidth(50);
        colNum.setCellValueFactory(new PropertyValueFactory<Vertex, Integer>("Number"));
        colNum.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colNum.setOnEditCommit(e -> colNum_onEditCommit(e));

        TableColumn<Vertex, Integer> colEast = new TableColumn<>("Easting");
        colEast.setMinWidth(100);
        colEast.setCellValueFactory(new PropertyValueFactory<Vertex, Integer>("Easting"));
        colEast.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colEast.setOnEditCommit(e -> colEast_onEditCommit(e));

        TableColumn<Vertex, Integer> colNorth = new TableColumn<>("Northing");
        colNorth.setMinWidth(100);
        colNorth.setCellValueFactory(new PropertyValueFactory<Vertex, Integer>("Northing"));
        colNorth.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colNorth.setOnEditCommit(e -> colNorth_onEditCommit(e));

        table.getColumns().addAll(colNum, colEast, colNorth);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setMinHeight(410);

        /*
         * Setting TabD layout
         */
        BorderPane tabD_borderPane = new BorderPane();

        HBox paneTopD = new HBox(titleD);
        paneTopD.setAlignment(Pos.CENTER);
        paneTopD.setPadding(new Insets(20, 20, 30, 0));

        VBox paneControl = new VBox(paneCalc, paneInput, paneSubmit, paneImport, paneExport, paneIHelp);
        paneControl.setPadding(new Insets(0, 15, 0, 0));

        VBox paneTable = new VBox(table, paneClearList);
        paneTable.setPadding(new Insets(0, 20, 0, 0));
        paneTable.setAlignment(Pos.BOTTOM_RIGHT);

        HBox paneCenterD = new HBox(paneControl);
        paneCenterD.setPadding(new Insets(0, 0, 15, 20));

        HBox paneRightD = new HBox(paneTable);
        paneRightD.setPadding(new Insets(0, 0, 15, 20));

        tabD_borderPane.setTop(paneTopD);
        tabD_borderPane.setCenter(paneCenterD);
        tabD_borderPane.setRight(paneRightD);

        tabD.setContent(tabD_borderPane);


        /************************************* TabE *************************************/

        Tab tabE = new Tab();
        tabE.setText("About");

        TextArea about = new TextArea();
        about.setEditable(false);
        about.setText(""
                + "Copyright (C) 2017  Piotr Czapik.\n"
                + "Copyright (C) 2019  Piotr Czapik.\n"

                + "@author Piotr Czapik\n"
                + "@version 4.5\n"
                + "\n"
                + "MapCalculatorFX is free software: you can redistribute it and/or modify\n"
                + "it under the terms of the GNU General Public License as published by\n"
                + "the Free Software Foundation, either version 3 of the License, or\n"
                + "(at your option) any later version.\n"
                + "\n"
                + "MapCalculatorFX is distributed in the hope that it will be useful,\n"
                + "but WITHOUT ANY WARRANTY; without even the implied warranty of\n"
                + "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n"
                + "GNU General Public License for more details.\n"
                + "\n"
                + "You should have received a copy of the GNU General Public License\n"
                + "along with MapCalculatorFX.  If not, see <http://www.gnu.org/licenses/>\n"
                + "or write to: latidude99@gmail.com \n"
                + "\n"
                + "\n"
                + "\n"
                + "Libraries used:\n"
                + "\n"
                + "(for calculations on Coordinate Converter tab)\n"
                + "\n"
                + "Jcoord v.1.0 (C) 2006 Jonathan Stott\n"
                + "available at http://www.jstott.me.uk/jcoord/ under GPLv2 license        		\n");

        tabE.setContent(about);

        /****************************** Setting up Scene and Stage *****************************/

        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        tabPane.getTabs().addAll(tabA, tabB, tabC, tabD, tabE);
        mainPane.setCenter(tabPane);

        Group root = new Group();
        root.getChildren().add(mainPane);
        Scene scene = new Scene(root, 885, 555);

        mainPane.prefHeightProperty().bind(scene.heightProperty());
        mainPane.prefWidthProperty().bind(scene.widthProperty());


        primaryStage.setScene(scene);
        primaryStage.setTitle(" Map Calculator v4.5");
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        primaryStage.show();



        /*************************** TabC buttons and text fields actions ***************************/

        /*
         * Left Point
         */
        buttonLeftPointConvert.setOnAction(this::readLeftPoint);
        buttonLeftPointClear.setOnAction(this::clearLeftPoint);
        
        textLeftOsSixFigure.textProperty().addListener((obs, oldVal, newVal) -> textLeftOsSixFigure.setText(newVal.replaceAll("[^A-Z0-9]", "")));
        textLeftOsSixFigure.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearLeftAllFromOSSixFigure();
          	}
        });
        textLeftOSEast.textProperty().addListener((obs, oldVal, newVal) -> textLeftOSEast.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textLeftOSEast.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearLeftAllFromOS();
        	}
        });
        textLeftOSNorth.textProperty().addListener((obs, oldVal, newVal) -> textLeftOSNorth.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textLeftOSNorth.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearLeftAllFromOS();
        	}
        });
        textLeftWGS84_degDec_Lat.textProperty().addListener((obs, oldVal, newVal) -> textLeftWGS84_degDec_Lat.setText(newVal.replaceAll("[^\\-\\d.\\.\\d.]", "")));
        textLeftWGS84_degDec_Lat.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearLeftAllFromDecDeg();
        		
        	}
        });
        textLeftWGS84_degDec_Lon.textProperty().addListener((obs, oldVal, newVal) -> textLeftWGS84_degDec_Lon.setText(newVal.replaceAll("[^\\-\\d.\\.\\d.]", "")));
        textLeftWGS84_degDec_Lon.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearLeftAllFromDecDeg();
        	}
        });
        textLeftWGS84_degDecMin_Lat.textProperty().addListener((obs, oldVal, newVal) -> textLeftWGS84_degDecMin_Lat.setText(newVal.replaceAll("[^\\-\\d.\\.\\d.]", "")));
        textLeftWGS84_degDecMin_Lat.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearLeftAllFromDecMin();
        	}
        });
        textLeftWGS84_minDecMin_Lat.textProperty().addListener((obs, oldVal, newVal) -> textLeftWGS84_minDecMin_Lat.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textLeftWGS84_minDecMin_Lat.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearLeftAllFromDecMin();
        	}
        });
        textLeftWGS84_degDecMin_Lon.textProperty().addListener((obs, oldVal, newVal) -> textLeftWGS84_degDecMin_Lon.setText(newVal.replaceAll("[^\\-\\d.\\.\\d.]", "")));
        textLeftWGS84_degDecMin_Lon.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearLeftAllFromDecMin();
        	}
        });
        textLeftWGS84_minDecMin_Lon.textProperty().addListener((obs, oldVal, newVal) -> textLeftWGS84_minDecMin_Lon.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textLeftWGS84_minDecMin_Lon.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearLeftAllFromDecMin();
        	}
        });
        
        textLeftWGS84_degDecSec_Lat.textProperty().addListener((obs, oldVal, newVal) -> textLeftWGS84_degDecSec_Lat.setText(newVal.replaceAll("[^\\-\\d.\\.\\d.]", "")));
        textLeftWGS84_degDecSec_Lat.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearLeftAllFromDecSec();
        	}
        });
        textLeftWGS84_minDecSec_Lat.textProperty().addListener((obs, oldVal, newVal) -> textLeftWGS84_minDecSec_Lat.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textLeftWGS84_minDecSec_Lat.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearLeftAllFromDecSec();
        	}
        });
        textLeftWGS84_secDecSec_Lat.textProperty().addListener((obs, oldVal, newVal) -> textLeftWGS84_secDecSec_Lat.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textLeftWGS84_secDecSec_Lat.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearLeftAllFromDecSec();
        	}
        });
        textLeftWGS84_degDecSec_Lon.textProperty().addListener((obs, oldVal, newVal) -> textLeftWGS84_degDecSec_Lon.setText(newVal.replaceAll("[^\\-\\d.\\.\\d.]", "")));
        textLeftWGS84_degDecSec_Lon.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearLeftAllFromDecSec();
        	}
        });
        textLeftWGS84_minDecSec_Lon.textProperty().addListener((obs, oldVal, newVal) -> textLeftWGS84_minDecSec_Lon.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textLeftWGS84_minDecSec_Lon.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearLeftAllFromDecSec();
        	}
        });
        textLeftWGS84_secDecSec_Lon.textProperty().addListener((obs, oldVal, newVal) -> textLeftWGS84_secDecSec_Lon.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textLeftWGS84_secDecSec_Lon.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearLeftAllFromDecSec();
        	}
        });

        /*
         * Right Point
         */
        buttonRightPointConvert.setOnAction(this::readRightPoint);
        buttonRightPointClear.setOnAction(this::clearRightPoint);
        
        textRightOsSixFigure.textProperty().addListener((obs, oldVal, newVal) -> textRightOsSixFigure.setText(newVal.replaceAll("[^A-Z0-9]", "")));
        textRightOsSixFigure.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearRightAllFromOSSixFigure();
        	}
        });
        textRightOSEast.textProperty().addListener((obs, oldVal, newVal) -> textRightOSEast.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textRightOSEast.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearRightAllFromOS();
        	}
        });
        textRightOSNorth.textProperty().addListener((obs, oldVal, newVal) -> textRightOSNorth.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textRightOSNorth.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearRightAllFromOS();
        	}
        });
        textRightWGS84_degDec_Lat.textProperty().addListener((obs, oldVal, newVal) -> textRightWGS84_degDec_Lat.setText(newVal.replaceAll("[^\\-\\d.\\.\\d.]", "")));
        textRightWGS84_degDec_Lat.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearRightAllFromDecDeg();
        	}
        });
        textRightWGS84_degDec_Lon.textProperty().addListener((obs, oldVal, newVal) -> textRightWGS84_degDec_Lon.setText(newVal.replaceAll("[^\\-\\d.\\.\\d.]", "")));
        textRightWGS84_degDec_Lon.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearRightAllFromDecDeg();
        	}
        });
        textRightWGS84_degDecMin_Lat.textProperty().addListener((obs, oldVal, newVal) -> textRightWGS84_degDecMin_Lat.setText(newVal.replaceAll("[^\\-\\d.\\.\\d.]", "")));
        textRightWGS84_degDecMin_Lat.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearRightAllFromDecMin();
        	}
        });
        textRightWGS84_minDecMin_Lat.textProperty().addListener((obs, oldVal, newVal) -> textRightWGS84_minDecMin_Lat.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textRightWGS84_minDecMin_Lat.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearRightAllFromDecMin();
        	}
        });
        textRightWGS84_degDecMin_Lon.textProperty().addListener((obs, oldVal, newVal) -> textRightWGS84_degDecMin_Lon.setText(newVal.replaceAll("[^\\-\\d.\\.\\d.]", "")));
        textRightWGS84_degDecMin_Lon.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearRightAllFromDecMin();
        	}
        });
        textRightWGS84_minDecMin_Lon.textProperty().addListener((obs, oldVal, newVal) -> textRightWGS84_minDecMin_Lon.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textRightWGS84_minDecMin_Lon.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearRightAllFromDecMin();
        	}
        });
        textRightWGS84_degDecSec_Lat.textProperty().addListener((obs, oldVal, newVal) -> textRightWGS84_degDecSec_Lat.setText(newVal.replaceAll("[^\\-\\d.\\.\\d.]", "")));
        textRightWGS84_degDecSec_Lat.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearRightAllFromDecSec();
        	}
        });
        textRightWGS84_minDecSec_Lat.textProperty().addListener((obs, oldVal, newVal) -> textRightWGS84_minDecSec_Lat.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textRightWGS84_minDecSec_Lat.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearRightAllFromDecSec();
        	}
        });
        textRightWGS84_secDecSec_Lat.textProperty().addListener((obs, oldVal, newVal) -> textRightWGS84_secDecSec_Lat.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textRightWGS84_secDecSec_Lat.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearRightAllFromDecSec();
        	}
        });
        textRightWGS84_degDecSec_Lon.textProperty().addListener((obs, oldVal, newVal) -> textRightWGS84_degDecSec_Lon.setText(newVal.replaceAll("[^\\-\\d.\\.\\d.]", "")));
        textRightWGS84_degDecSec_Lon.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearRightAllFromDecSec();
        	}
        });
        textRightWGS84_minDecSec_Lon.textProperty().addListener((obs, oldVal, newVal) -> textRightWGS84_minDecSec_Lon.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textRightWGS84_minDecSec_Lon.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearRightAllFromDecSec();
        	}
        });
        textRightWGS84_secDecSec_Lon.textProperty().addListener((obs, oldVal, newVal) -> textRightWGS84_secDecSec_Lon.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textRightWGS84_secDecSec_Lon.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		clearRightAllFromDecSec();
        	}
        });

        /*
         * Distance and Bearing
         */
        buttonDistConvert.setOnAction(this::convertAndSetDistanceTabC); //calculating and setting DISTANCE fields
        buttonDistClear.setOnAction(this::clearDistFields); //clears DISTANCE text fields
        
        buttonDistCalculate.setOnAction(this::calculateSetDistance);
        buttonBearingCalculate.setOnAction(this::calculateSetAzimuthBearing);
        buttonBearingClear.setOnAction( e -> {
        	textAzimuth.clear();
        	textBearing.clear();
        });
        textDistKm.textProperty().addListener((obs, oldVal, newVal) -> textDistKm.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textDistKm.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		textDistMilesStatue.clear();
        		textDistMilesNautical.clear();
        		textDistMetres.clear();
        		textDistFt.clear();
        	}
        });
        textDistMilesStatue.textProperty().addListener((obs, oldVal, newVal) -> textDistMilesStatue.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textDistMilesStatue.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		textDistKm.clear();
        		textDistMilesNautical.clear();
        		textDistMetres.clear();
        		textDistFt.clear();
        	}
        });
        textDistMilesNautical.textProperty().addListener((obs, oldVal, newVal) -> textDistMilesNautical.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textDistMilesNautical.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		textDistKm.clear();
        		textDistMilesStatue.clear();
        		textDistMetres.clear();
        		textDistFt.clear();
        	}
        });
        textDistMetres.textProperty().addListener((obs, oldVal, newVal) -> textDistMetres.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textDistMetres.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		textDistKm.clear();
        		textDistMilesStatue.clear();
        		textDistMilesNautical.clear();
        		textDistFt.clear();
        	}
        });
        textDistFt.textProperty().addListener((obs, oldVal, newVal) -> textDistFt.setText(newVal.replaceAll("[^\\d.\\.\\d.]", "")));
        textDistFt.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		textDistKm.clear();
        		textDistMilesStatue.clear();
        		textDistMilesNautical.clear();
        		textDistMetres.clear();
        	}
        });
        
        buttonHelp.setOnAction(e -> ImageBox.show("Help for Coordinate Converter", "Help", IMAGE_TAB_C1, IMAGE_TAB_C2));


        /*************************** TabD buttons and text fields actions ***************************/

        buttonCalcArea.setOnAction(this::setArea);
        buttonCalcPerim.setOnAction(this::setPerimeter);
        buttonClearArea.setOnAction(e -> clearAreaFields());
        buttonClearPerim.setOnAction(e -> clearPerimFields());
        
        buttonValidatePoint.setOnAction(
                e -> PolygonBox.show("Polygon", "Polygon Check", table.getItems()));
        buttonSubmitLeft.setOnAction(e -> {clearLeftPoint(e);
        									selectLeftPoint(e);
        									readLeftPoint(e);
        							});
        buttonSubmitRight.setOnAction(e -> {clearRightPoint(e);
        										selectRightPoint(e);
												readRightPoint(e);
        							});
        buttonImport.setOnAction(e -> {importVertexes(e);
        								labelPointDeleted.setStyle("-fx-text-fill: blue;");
        								labelPointDeleted.setText(table.getItems().size() + " pts");
        						});
        buttonExport.setOnAction(this::exportVertexes);
        
        buttonAddPoint.setOnAction(e -> {addVertex(e);
        								labelPointDeleted.setText(table.getItems().size() + " pts");
        							});
        buttonClearPointText.setOnAction(e -> {textPointNum.clear();
        										textPointEast.clear();
        										textPointNorth.clear();
        								});
        buttonDelPoint.setOnAction(e -> {deleteVertex(e);
        								labelPointDeleted.setText(table.getItems().size() + " pts");
        							});
        buttonClearList.setOnAction(e -> {table.getItems().clear();
        									clearAreaFields();
        									clearPerimFields();
        									labelPointDeleted.setText(table.getItems().size() + " pts");
        								});
        buttonHelpTabD.setOnAction(e -> ImageBox.show(
                "Area Calculator Help", "Help", IMAGE_TAB_D1, IMAGE_TAB_D2));
        textPointNum.textProperty().addListener((obs, oldVal, newVal) ->
                textPointNum.setText(newVal.replaceAll("[^0-9]", "")));
        textPointNum.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		labelPointAdded.setText("     ");
        	}
        });
        textPointEast.textProperty().addListener((obs, oldVal, newVal) ->
                textPointEast.setText(newVal.replaceAll("[^0-9]", "")));
        textPointEast.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		labelPointAdded.setText("     ");
        	}
        });
        textPointNorth.textProperty().addListener((obs, oldVal, newVal) -> textPointNorth.setText(newVal.replaceAll("[^0-9]", "")));
        textPointNorth.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event){
        		labelPointAdded.setText("     ");
        	}
        });

    }
    

    /********************************* TabA methods *********************************/

    /*
     * Converts Fractional scale to all the others
     */
    private void convertFra(){
    	try{
    		if((Long.parseLong(txtScaleFra.getText())) > 999999999L){
    			MessageBox.show("The scale has to be smaller than 1:999,999,999", "Wrong scale input");
        		txtScaleFra.clear();
    		} else {
    			if(Integer.parseInt(txtScaleFra.getText()) == 0){
    				MessageBox.show("The scale has to be larger than zero", "Wrong scale input");
    				txtScaleFra.clear();
    			} else {
    				resultFra.setText("1 : " + NumberFormat.getIntegerInstance().format(Integer.parseInt(txtScaleFra.getText())));
    			
				    if((63360 / (Double.parseDouble(txtScaleFra.getText()))) < 0.1){
				      	resultIn.setText("less than 0.1 inch to the mile");
			        } else {
				        resultIn.setText(String.format("%.1f", (63360 / (Double.parseDouble(txtScaleFra.getText())))) + " inches to the mile");
			        }
				    if((Double.parseDouble(txtScaleFra.getText()) / 63360) < 0.001){
				       	resultMile.setText("less than 0.001 mile to an inch");
			        } else{
			        	resultMile.setText(String.format("%.3f", (Double.parseDouble(txtScaleFra.getText()) / 63360)) + " miles to an inch");
			        }
				    if((100000 / (Double.parseDouble(txtScaleFra.getText()))) < 0.1){
				       	resultCm.setText("less than 0.1 cm to a kilometre");
			        } else {
				        resultCm.setText(String.format("%.1f", (100000 / (Double.parseDouble(txtScaleFra.getText())))) + " centimetres to a kilometre");
			        }
			        if((Double.parseDouble(txtScaleFra.getText()) / 100000) >= 1){
			            resultKm.setText(String.format("%.1f", (Double.parseDouble(txtScaleFra.getText()) / 100000)) + " km to a centimetre");
			        } else {
			            resultKm.setText(String.format("%.1f", (Double.parseDouble(txtScaleFra.getText()) / 100)) + "  metres to a centimetre");
			        }
			    }
    		}
	    } catch (NumberFormatException e){
    	    MessageBox.show("This is not a valid number", "Wrong scale format");
    	    txtScaleFra.clear();
	    }
    }

    /*
     * Converts Imperial1 scale (inches to the Mile) to all the others
     */
    private void convertIn(){
    	try{
    		if(txtScaleImpIn.getText().equals("0")){
    			MessageBox.show("The scale has to be larger than zero", "Wrong scale input");
    			txtScaleImpIn.clear();
    		} else {
		    	resultFra.setText("1 : " + NumberFormat.getIntegerInstance().format((int) (63360 / Double.parseDouble(txtScaleImpIn.getText()))));
		        resultIn.setText(txtScaleImpIn.getText() + " inches to the mile");
		        resultMile.setText(String.format("%.3f", (1 / Double.parseDouble(txtScaleImpIn.getText()))) + " miles to an inch");
		        resultCm.setText(String.format("%.1f", (100000 / (((63360 / Double.parseDouble(txtScaleImpIn.getText())))))) + " centimetres to a kilometre");
		        if(((63360 * (Double.parseDouble(txtScaleImpIn.getText())) / 100000)) <= 1){
		            resultKm.setText(String.format("%.1f", ((63360 / Double.parseDouble(txtScaleImpIn.getText())) / 100000)) + " km to a centimetre");
		        } else {
		            resultKm.setText(String.format("%.1f", ((63360 / Double.parseDouble(txtScaleImpIn.getText())) / 100)) + "  metres to a centimetre");
		        }
    		}
    	} catch (NumberFormatException e){
    	    MessageBox.show("This is not a valid number", "Wrong number format");
    	    txtScaleImpIn.clear();
    	}
    }



    /*
     * Converts Imperial2 scale (Miles to an inch) to all the others
     */
    private void convertMile(){
    	try{
    		if(txtScaleImpMile.getText().equals("0")){
    			MessageBox.show("The scale has to be larger than zero", "Wrong scale input");
    			txtScaleImpMile.clear();
    		} else {
		    	resultFra.setText("1 : " + NumberFormat.getIntegerInstance().format((int) (((Double.parseDouble(txtScaleImpMile.getText()))) * 63360)));
		        resultIn.setText(String.format("%.1f", (1 / (Double.parseDouble(txtScaleImpMile.getText())))) + " inches to the mile");
		        resultMile.setText(txtScaleImpMile.getText() + " miles to an inch");
		        resultCm.setText(String.format("%.1f", (100000 / ((Double.parseDouble(txtScaleImpMile.getText()) * 63360)))) + " centimetres to a kilometre");
		        if((((Double.parseDouble(txtScaleImpMile.getText()) * 63360) / 100000)) >= 1){
		            resultKm.setText(String.format("%.1f", ((Double.parseDouble(txtScaleImpMile.getText()) * 63360) / 100000)) + " km to a centimetre");
		        } else {
		            resultKm.setText(String.format("%.1f", ((Double.parseDouble(txtScaleImpMile.getText()) * 63360) / 100)) + "  metres to a centimetre");
		        }
    		}
    	} catch (NumberFormatException e){
    	    MessageBox.show("This is not a valid number", "Wrong number format");
    	    txtScaleImpMile.clear();
	    }
    }

    /*
     * Converts Metric scale (centimetres to a kilometre) to all the others
     */
    private void convertCm(){
    	try{
    		if(txtScaleMetricCm.getText().equals("0")){
    			MessageBox.show("The scale has to be larger than zero", "Wrong scale input");
    			txtScaleMetricCm.clear();
    		} else {
		    	resultFra.setText("1 : " + NumberFormat.getIntegerInstance().format((int) (100000 / Double.parseDouble(txtScaleMetricCm.getText()))));
		        resultIn.setText(String.format("%.1f", (63360 / (100000 / (Double.parseDouble(txtScaleMetricCm.getText()))))) + " inches to the mile");
		        resultMile.setText(String.format("%.3f", ((100000 / Double.parseDouble(txtScaleMetricCm.getText())) / 63360)) + " miles to an inch");
		        resultCm.setText(txtScaleMetricCm.getText() + " centimetres to a kilometre");
		        if((1 / Double.parseDouble(txtScaleMetricCm.getText())) >= 1){
		            resultKm.setText(String.format("%.1f", (1 / (Double.parseDouble(txtScaleMetricCm.getText())))) + " km to a centimetre");
		        } else {
		            resultKm.setText(String.format("%.1f", ((1 / (Double.parseDouble(txtScaleMetricCm.getText())))) * 1000) + "  metres to a centimetre");
		        }
    		}
    	} catch (NumberFormatException e){
    	    MessageBox.show("This is not a valid number", "Wrong number format");
	    	txtScaleMetricCm.clear();
	    }
    }
   
    /*
     * Converts Metric scale (kilometres to a cm) to all the others
     */
    private void convertKm(){
    	try{
    		if(txtScaleMetricKm.getText().equals("0")){
    			MessageBox.show("The scale has to be larger than zero", "Wrong scale input");
    			txtScaleMetricKm.clear();
    		} else {
		    	resultFra.setText("1 : " + NumberFormat.getIntegerInstance().format((int) (100000 * Double.parseDouble(txtScaleMetricKm.getText()))));
		        resultIn.setText(String.format("%.1f", (63360 / (100000 * (Double.parseDouble(txtScaleMetricKm.getText()))))) + " inches to the mile");
		        resultMile.setText(String.format("%.3f", ((100000 * Double.parseDouble(txtScaleMetricKm.getText())) / 63360)) + " miles to an inch");
		        resultCm.setText(String.format("%.1f", (1 / (Double.parseDouble(txtScaleMetricKm.getText())))) + " centimetres to a kilometre");
		        resultKm.setText(txtScaleMetricKm.getText() + " km to a centimetre");
    		}
    	} catch (NumberFormatException e){
    	    MessageBox.show("This is not a valid number", "Wrong number format");
	    	txtScaleMetricKm.clear();
	    }
    }
    
    private void clearFields(){
        txtScaleFra.clear(); txtScaleImpIn.clear(); txtScaleImpMile.clear(); txtScaleMetricCm.clear(); txtScaleMetricKm.clear();
        resultFra.setText(""); resultIn.setText(""); resultMile.setText(""); resultCm.setText(""); resultKm.setText("");
    }

    /********************************* TabB methods *********************************/


    /*
     * !!! intermediate variables return values in milimetres !!!
     */


    private double mapDist;
    private double groundDist;
    private double mapScale;
    int count = 0; // supposed to help to recognize when 2 out of 3 needed fields are filled in but abandoned for now

    /*
     * Reads map distance measurement and returns value in milimetres
     */
    private double getMapDist(){
        if(!(txtMm.getText().equals(null)) & !(txtMm.getText().equals("")) & (!txtMm.getText().equals("0"))){
            count++;
            return Double.parseDouble(txtMm.getText());
        }
        if(!(txtCm.getText().equals(null)) & !(txtCm.getText().equals("")) & (!txtCm.getText().equals("0"))){
            count++;
            return Double.parseDouble(txtCm.getText()) * 10;
        }
        if(!(txtIn.getText().equals(null)) & !(txtIn.getText().equals("")) & (!txtIn.getText().equals("0"))){
            count++;
            return Double.parseDouble(txtIn.getText()) * 25.4;
        }else{
            return 0;
        }
    }

    /*
     * Reads ground distance measurement and returns value in milimetres
     */
    private double getGroundDist(){
        if(!(txtKm.getText().equals(null)) & !(txtKm.getText().equals("")) & (!txtKm.getText().equals("0"))){
            count++;
            return Double.parseDouble(txtKm.getText()) * 1000000;
        }
        if(!(txtMetre.getText().equals(null)) & !(txtMetre.getText().equals("")) & (!txtMetre.getText().equals("0"))){
            count++;
            return Double.parseDouble(txtMetre.getText()) * 1000;
        }
        if(!(txtMile.getText().equals(null)) & !(txtMile.getText().equals("")) & (!txtMile.getText().equals("0"))){
            count++;
            return Double.parseDouble(txtMile.getText()) * 1609344;
        }
        if(!(txtFt.getText().equals(null)) & !(txtFt.getText().equals("")) & (!txtFt.getText().equals("0"))){
            count++;
            return Double.parseDouble(txtFt.getText()) * 304.8;
        }
        if(!(txtYard.getText().equals(null)) & !(txtYard.getText().equals("")) & (!txtYard.getText().equals("0"))){
            count++;
            return Double.parseDouble(txtYard.getText()) * 914.4;
        }else{
            return 0;
        }
    }

    /*
     * Reads map scale and returns Fractional scale value
     */
    private double getScale(){
        if(txtScaleFractional.getText().length() > 0){
        	count++;
        	String tmp = txtScaleFractional.getText().replace(",", "").replace(".", "");
        	return Double.parseDouble(tmp);
        }
        if(!txtScaleInch.getText().equals(null) & !txtScaleInch.getText().equals("") & (!txtScaleInch.getText().equals("0"))){
            count++;
            return (63360 / Double.parseDouble(txtScaleInch.getText()));
        }
        if(!txtScaleMile.getText().equals(null) & !txtScaleMile.getText().equals("") & (!txtScaleMile.getText().equals("0"))){
            count++;
            return Double.parseDouble(txtScaleMile.getText()) * 63360;
        }
        if(!txtScaleCm.getText().equals(null) & !txtScaleCm.getText().equals("") & (!txtScaleCm.getText().equals("0"))){
            count++;
            return 100000 / Double.parseDouble(txtScaleCm.getText());
        }
        if(!txtScaleKm.getText().equals(null) & !txtScaleKm.getText().equals("") & (!txtScaleKm.getText().equals("0"))){
            count++;
            return 100000 * Double.parseDouble(txtScaleKm.getText());
        }else{
            return 0;
        }
    }

    /*
     * Checks if 2 out of 3 fields necessary for calculations in this Tab (TabB) are present and valid
     */
    private boolean validateInput(){
    	mapDist = getMapDist();
        groundDist = getGroundDist();
        mapScale = getScale();
    	if(((getMapDist() != 0) & (getGroundDist() != 0) & (getScale() != 0))
    			|| ((getMapDist() == 0) & (getGroundDist() == 0) & (getScale() == 0))){
    		return false;
    	}else{
    		return true;
    	}
    }

    /*
     * Validates input and disttributes calculations to the methods depending
     * what the entry parameters are (2 out of 3) and sets the results to all
     * remaining fields
     */
    private void calculate(){
    	if(validateInput()){
            if((mapDist != 0) & (groundDist != 0)){
                mapScale = groundDist / mapDist;
                setCalculatedFields();
            }else{
	            if((mapDist != 0) & (mapScale != 0)){
	                groundDist = mapDist * mapScale;
	                setCalculatedFields();
	            }else{
		            if((mapScale != 0) & (groundDist != 0)){
		                mapDist = groundDist / mapScale;
		                setCalculatedFields();
		            }
	            }
            }
            count = 0;
        } else {
            MessageBox.show("Two out of the three columns must have a field with a value. " +
                    "One column has to remain empty", "Wrong input format");
        }
    }
    
    private void setCalculatedFields(){
    	setMapFields();
    	setGroundFields();
    	setScaleFields();
    }

    /*
     * sets calculated values to map distance fields
     */
    private void setMapFields(){
    	if(mapDist > 99999999){
        	lblGap1a.setText("more than 99,999,999 mm");
        	lblGap1a.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 11; -fx-text-fill: red;");
    	}else{
    		if(mapDist < 1){
    			lblGap1a.setText("less than 1 mm");
            	lblGap1a.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 11; -fx-text-fill: red;");
    		}else{
        		txtMm.setText(NumberFormat.getIntegerInstance().format((int) mapDist));
    		}
    	}
        txtCm.setText(String.format("%.2f", (mapDist / 10)));
        txtIn.setText(String.format("%.2f", (mapDist / 25.4)));
    }

    /*
     * sets calculated values to ground distance fields
     */
    private void setGroundFields(){
        txtKm.setText(String.format("%.3f", (groundDist / 1000000)));
        txtMetre.setText(String.format("%.1f", (groundDist / 1000)));
        txtMile.setText(String.format("%.3f", (groundDist / 1609000)));
        txtFt.setText(String.format("%.1f", (groundDist / 304.8)));
        txtYard.setText(String.format("%.1f", (groundDist / 914.4)));
    }

    /*
     * sets calculated values to map scale fields
     */
    private void setScaleFields(){
    	    if(mapScale < 1){
            	lblGap3a.setText("bigger than 1 : 1");
            	lblGap3a.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 11; -fx-text-fill: red;");
            	//txtScaleFractional.setText(",");	
            }else{
            	if(mapScale > 99999999){
                	lblGap3a.setText("smaller than 1 : 99,999,999");
                	lblGap3a.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 11; -fx-text-fill: red;");
                	//txtScaleFractional.setText(",");	
            	}else{
            		txtScaleFractional.setText(NumberFormat.getIntegerInstance().format((int) mapScale));
            	}
            }
    	    if((63360 / mapScale) < 0.1){
            	lblGap3b.setText("less than 0.1 inch to the mile");
            	lblGap3b.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 11; -fx-text-fill: red;");
            	//txtScaleInch.setText(",");	
            }else{
            	txtScaleInch.setText(String.format("%.1f", (63360 / mapScale)));
            }
        if((mapScale / 63360) < 0.001){
        	lblGap3c.setText("less than 0.001 mile to an inch");
        	lblGap3c.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 11; -fx-text-fill: red;");
        	//txtScaleMile.setText(",");	
        }else{
        	txtScaleMile.setText(String.format("%.3f", (mapScale / 63360)));
        }
        if((100000 / mapScale) < 0.1){
        	lblGap3d.setText("less than 0.1 cm to a km");
        	lblGap3d.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 11; -fx-text-fill: red;");
        	//txtScaleCm.setText(",");	
        }else{
        	txtScaleCm.setText(String.format("%.1f", (100000 / mapScale)));
        }
        if((mapScale / 100000) < 0.001){
        	lblGap3e.setText("less than 0.001 km to a cm");
        	lblGap3e.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 11; -fx-text-fill: red;");
        	//txtScaleKm.setText(",");	
        }else{
        	txtScaleKm.setText(String.format("%.3f", (mapScale / 100000)));
        }
    }
    
    private void clearFieldsTabB(){
    	txtMm.clear(); txtCm.clear(); txtIn.clear();
    	txtKm.clear(); txtMetre.clear(); txtMile.clear(); txtFt.clear(); txtYard.clear();
    	txtScaleFractional.clear();	txtScaleInch.clear(); txtScaleMile.clear();	txtScaleCm.clear();	txtScaleKm.clear();
    	lblGap1a.setText("");
    	lblGap3a.setText(""); lblGap3b.setText(""); lblGap3c.setText(""); lblGap3d.setText(""); lblGap3e.setText("");
    	count = 0;
    }


    /********************************* TabC methods *********************************/
    
    Point pointL = new Point();
	Point pointR = new Point();
	Distance distance = new Distance();
	BearingPolygon polygon = new BearingPolygon();
	boolean hasDecimal = true;

    /*
     * Distance and Bearing calculations
     */
    private void convertAndSetDistanceTabC(ActionEvent e){
    	if((textDistKm.getText().trim().length() > 0) & (textDistMilesStatue.getText().trim().length() > 0)
    			& (textDistMilesNautical.getText().trim().length() > 0) & (textDistMetres.getText().trim().length() > 0) &
    			(textDistFt.getText().trim().length() > 0)){
    		MessageBox.show("Clear all distance fields and enter a value into one of them", "Input error");
    	}else{
    		if(textDistKm.getText().trim().length() > 0){
    			distance.setDist(Double.parseDouble(textDistKm.getText()) * 1000); //base distance in metres
    			setDistFields();
    		}else{
    			if(textDistMilesStatue.getText().trim().length() > 0){
    				distance.setDist(Double.parseDouble(textDistMilesStatue.getText()) * 1609.3440); //base distance in metres
    				setDistFields();
    			}else{
    				if(textDistMilesNautical.getText().trim().length() > 0){
    					distance.setDist(Double.parseDouble(textDistMilesNautical.getText()) * 1852); //base distance in metres
    					setDistFields();
    				}else{
    					if(textDistMetres.getText().trim().length() > 0){
    						distance.setDist(Double.parseDouble(textDistMetres.getText())); //base distance in metres
    						setDistFields();
    					}else{
    						if(textDistFt.getText().trim().length() > 0){
    							distance.setDist(Double.parseDouble(textDistFt.getText()) / 3.280840); //base distance in metres
    							setDistFields();
    						}else{
    							MessageBox.show("Plese fill in one of the DISTANCE fields", "Input error");
    						}
    					}
    				}
    			}
    		}
    	}
    }
    
    private void calculateSetDistance(ActionEvent event){
    	if((textLeftOSEast.getText().trim().length() > 0) & (textRightOSEast.getText().trim().length() > 0)){
    		distance.setDist(polygon.distLtoRFromOS(pointL, pointR));
    		setDistFields();    		
    	}else{
    		if((textLeftWGS84_degDec_Lat.getText().trim().length() > 0) & (textRightWGS84_degDec_Lat.getText().trim().length() > 0)){
    			distance.setDist(polygon.distLtoRFromLatLon(pointL, pointR));
    			setDistFields();
    		}else{
    			MessageBox.show("Please set both points", "Insufficient input error");
    		}
    	}
    }
    
    private void calculateSetAzimuthBearing(ActionEvent event){
		if((textLeftWGS84_degDec_Lat.getText().trim().length() > 0) & (textRightWGS84_degDec_Lat.getText().trim().length() > 0)){
			String azimuth = String.format("%.1f", polygon.bearingFromLtoR(pointL, pointR)) + " \u00b0";
			textAzimuth.setText(azimuth);
			if((polygon.bearingFromLtoR(pointL, pointR) >= 0) & (polygon.bearingFromLtoR(pointL, pointR) <= 90)){
				String bearing = "N " + String.format("%.1f", polygon.bearingFromLtoR(pointL, pointR)) + "\u00b0" + " E";
				textBearing.setText(bearing);
			}
			if((polygon.bearingFromLtoR(pointL, pointR) > 90) & (polygon.bearingFromLtoR(pointL, pointR) <= 180)){
				String bearing = "S " + String.format("%.1f", (180 - polygon.bearingFromLtoR(pointL, pointR))) + "\u00b0" + " E";
				textBearing.setText(bearing);
			}
			if((polygon.bearingFromLtoR(pointL, pointR) > 180) & (polygon.bearingFromLtoR(pointL, pointR) <= 270)){
				String bearing = "S " + String.format("%.1f", (polygon.bearingFromLtoR(pointL, pointR)) - 180) + "\u00b0" + " W";
				textBearing.setText(bearing);
			}
			if((polygon.bearingFromLtoR(pointL, pointR) > 270) & (polygon.bearingFromLtoR(pointL, pointR) < 360)){
				String bearing = "N " + String.format("%.1f", (360 - polygon.bearingFromLtoR(pointL, pointR))) + "\u00b0" + " W";
				textBearing.setText(bearing);
			}
			
    	}else{
			MessageBox.show("Please set both points", "Insufficient input error");
		}
    }
						
	private void setDistFields(){
		textDistKm.setText(String.format("%.3f", distance.distKm(distance.getDist())));
		textDistMilesStatue.setText(String.format("%.3f", distance.distMileStatue(distance.getDist())));
		textDistMilesNautical.setText(String.format("%.3f", distance.distMileNautical(distance.getDist())));
		textDistMetres.setText(String.format("%.2f", distance.distMetre(distance.getDist())));
		textDistFt.setText(String.format("%.2f", distance.distFt(distance.getDist())));
	}
	
	private void clearDistFields(ActionEvent event){
		textDistKm.clear();
		textDistMilesStatue.clear();
		textDistMilesNautical.clear();
		textDistMetres.clear();
		textDistFt.clear();
	}

    /*
     * Left Point calculations
     */
	private void readLeftPoint(ActionEvent event){   //decide which fields to use as input
		if(textLeftOsSixFigure.getText().trim().length() != 0){
			calcLeftFromOSSixFigure();
		}else{
			if((textLeftOSEast.getText().trim().length() > 0)
                    || (textLeftOSNorth.getText().trim().length() > 0)){
				calcLeftFromOS();
			}else{
				if((textLeftWGS84_degDec_Lat.getText().trim().length() > 0)
                        || (textLeftWGS84_degDec_Lon.getText().trim().length() > 0)){
					calcLeftFromDegDec();
				}else{
					if((textLeftWGS84_degDecMin_Lat.getText().trim().length() > 0)
                            || (textLeftWGS84_minDecMin_Lat.getText().trim().length() > 0)
					        || (textLeftWGS84_degDecMin_Lon.getText().trim().length() > 0)
                            || (textLeftWGS84_minDecMin_Lon.getText().trim().length() > 0)){
					calcLeftFromMinDec();
					}else{
						if((textLeftWGS84_degDecSec_Lat.getText().trim().length() > 0)
                                || (textLeftWGS84_degDecSec_Lon.getText().trim().length() > 0)
								|| (textLeftWGS84_minDecSec_Lat.getText().trim().length() > 0)
                                || (textLeftWGS84_minDecSec_Lon.getText().trim().length() > 0)
								|| (textLeftWGS84_secDecSec_Lat.getText().trim().length() > 0)
                                || (textLeftWGS84_secDecSec_Lon.getText().trim().length() > 0)){
							calcLeftFromSecDec();	
						}else{
							MessageBox.show("No location for the left point", "Input error");
						}
					}
				}
			}
		}
	}
	
	private void calcLeftFromOSSixFigure(){ //when user enters six-figure OS reference
		try{
			pointL.setOsSixFigure(textLeftOsSixFigure.getText().replaceAll(" ", ""));
			pointL.setLatLonFromOSSixFigure();
			setLeftPointAll();
		}catch (StringIndexOutOfBoundsException | NumberFormatException e){
			MessageBox.show("Invalid OS Six-Figure Reference", "Input error");
		}
	}
			
	private void calcLeftFromOS(){  //when user enters easting and northing
		if((textLeftOSEast.getText().trim().length() != 0) & (textLeftOSNorth.getText().trim().length() != 0)){
			pointL.setEasting(getOSFromText(textLeftOSEast.getText()));
			pointL.setNorthing(getOSFromText(textLeftOSNorth.getText()));
			if(pointL.getEasting() >0
                    & pointL.getEasting() < 1500000
                    & pointL.getNorthing() > 0
                    & pointL.getNorthing() < 1800000){
				pointL.setLatLonFromEastNorth();
				setLeftPointAll();
			}else{
				MessageBox.show("Easting has to be between 0 and 1 5000 000 and Northing" +
                        " has to be between 0 and 1 800 000", "Input error");
			}
		}else{
			MessageBox.show("Both Easting and Northing have to be entered",
                    "Insufficient input error");
		}
	}
	
	private void calcLeftFromDegDec(){  // when user enters latitude and longitude (decimal degrees)
		if((textLeftWGS84_degDec_Lat.getText().trim().length() != 0)
                & (textLeftWGS84_degDec_Lon.getText().trim().length() != 0)){
			pointL.setLatDegreesDecDeg(Double.parseDouble(textLeftWGS84_degDec_Lat.getText()));
			pointL.setLonDegreesDecDeg(Double.parseDouble(textLeftWGS84_degDec_Lon.getText()));
			pointL.setLatLonFromDegDec();
			if((pointL.getLatDegreesDecDeg() >= -90) & (pointL.getLatDegreesDecDeg() <= 90)
					& (pointL.getLonDegreesDecDeg() >= -90) & (pointL.getLonDegreesDecDeg() <= 90)){
				pointL.setEastNorthFromLatLon();
				setLeftPointAll();
			}else{
				MessageBox.show("Both Latitude and Longitude have to be between -90 na 90 degrees",
                        "Out of range");
			}
		}else{
			MessageBox.show("Both Longitude and Latitude have to be entered",
                    "Insufficient input error");
		}
	}
	
	private void calcLeftFromMinDec(){  // when user enters latitude and longitude (decimal minutes)
		if((textLeftWGS84_degDecMin_Lat.getText().trim().length() != 0)
                & (textLeftWGS84_degDecMin_Lon.getText().trim().length() != 0)
				& (textLeftWGS84_minDecMin_Lat.getText().trim().length() != 0)
                & (textLeftWGS84_minDecMin_Lon.getText().trim().length() != 0)){
			pointL.setLatMinutesDecDeg(Integer.parseInt(textLeftWGS84_degDecMin_Lat.getText()));
			pointL.setLatMinutesDecMin(Double.parseDouble(textLeftWGS84_minDecMin_Lat.getText()));
			pointL.setLonMinutesDecDeg(Integer.parseInt(textLeftWGS84_degDecMin_Lon.getText()));
			pointL.setLonMinutesDecMin(Double.parseDouble(textLeftWGS84_minDecMin_Lon.getText()));
			pointL.setLatLonFromMinDec();
			if((pointL.getLatDegreesDecDeg() >= -90) & (pointL.getLatDegreesDecDeg() <= 90)
					& (pointL.getLonDegreesDecDeg() >= -90) & (pointL.getLonDegreesDecDeg() <= 90)){
				pointL.setEastNorthFromLatLon();
				setLeftPointAll();
			}else{
				MessageBox.show("Both Latitude and Longitude have to be between -90 na 90 degrees",
                        "Out of range");
			}
		}else{
			MessageBox.show("Both Longitude and Latitude have to be entered",
                    "Insufficient input error");
		}
	}
	
	private void calcLeftFromSecDec(){  // when user enters latitude and longitude (decimal seconds-)
		if((textLeftWGS84_degDecSec_Lat.getText().trim().length() != 0)
                & (textLeftWGS84_degDecSec_Lon.getText().trim().length() != 0)
				& (textLeftWGS84_minDecSec_Lat.getText().trim().length() != 0)
                & (textLeftWGS84_minDecSec_Lon.getText().trim().length() != 0)
				& (textLeftWGS84_secDecSec_Lat.getText().trim().length() != 0)
                & (textLeftWGS84_secDecSec_Lon.getText().trim().length() != 0)){
			pointL.setLatSecondsDecDeg(Integer.parseInt(textLeftWGS84_degDecSec_Lat.getText()));
			pointL.setLatSecondsDecMin(Integer.parseInt(textLeftWGS84_minDecSec_Lat.getText()));
			pointL.setLatSecondsDecSec(Double.parseDouble(textLeftWGS84_secDecSec_Lat.getText()));
			pointL.setLonSecondsDecDeg(Integer.parseInt(textLeftWGS84_degDecSec_Lon.getText()));
			pointL.setLonSecondsDecMin(Integer.parseInt(textLeftWGS84_minDecSec_Lon.getText()));
			pointL.setLonSecondsDecSec(Double.parseDouble(textLeftWGS84_secDecSec_Lon.getText()));
			pointL.setLatLonFromSecDec();
			if((pointL.getLatDegreesDecDeg() >= -90) & (pointL.getLatDegreesDecDeg() <= 90)
					& (pointL.getLonDegreesDecDeg() >= -90) & (pointL.getLonDegreesDecDeg() <= 90)){
				pointL.setEastNorthFromLatLon();
				setLeftPointAll();
			}else{
				MessageBox.show("Both Latitude and Longitude have to be between -90 na 90 degrees",
                        "Out of range");
			}
		}else{
			MessageBox.show("Both Longitude and Latitude have to be entered",
                    "Insufficient input error");
		}
	}
				
	
	private void setLeftPointAll(){
		labelLeftPoint.setStyle("-fx-font: 13 arial; -fx-text-fill: blue;");
		if((pointL.getEasting() > 0) & (pointL.getEasting() < 1500000)
				& (pointL.getNorthing() > 0) & (pointL.getNorthing() < 1800000)){
			textLeftOSEast.setText((new Integer((int)pointL.getEasting())).toString());
			textLeftOSNorth.setText((new Integer((int)pointL.getNorthing())).toString());
			textLeftOsSixFigure.setText(pointL.getOsSixFigure());
		}else{
			textLeftOSEast.setPromptText("out of range");
			textLeftOSNorth.setPromptText("out of range");
			textLeftOsSixFigure.setPromptText("out of range");
		}
		
		textLeftWGS84_degDec_Lat.setText(String.format("%.10f", pointL.getLatDegreesDecDeg()));
		
		textLeftWGS84_degDecMin_Lat.setText((new Integer(pointL.getLatMinutesDecDeg())).toString());
		textLeftWGS84_minDecMin_Lat.setText(String.format("%.8f", pointL.getLatMinutesDecMin()));
		
		textLeftWGS84_degDecSec_Lat.setText((new Integer(pointL.getLatSecondsDecDeg())).toString());
		textLeftWGS84_minDecSec_Lat.setText((new Integer(pointL.getLatSecondsDecMin())).toString());
		textLeftWGS84_secDecSec_Lat.setText(String.format("%.2f", pointL.getLatSecondsDecSec()));

		textLeftWGS84_degDec_Lon.setText(String.format("%.10f", pointL.getLonDegreesDecDeg()));
		
		textLeftWGS84_degDecMin_Lon.setText((new Integer(pointL.getLonMinutesDecDeg())).toString());
		textLeftWGS84_minDecMin_Lon.setText(String.format("%.8f", pointL.getLonMinutesDecMin()));
		
		textLeftWGS84_degDecSec_Lon.setText((new Integer(pointL.getLonSecondsDecDeg())).toString());
		textLeftWGS84_minDecSec_Lon.setText((new Integer(pointL.getLonSecondsDecMin())).toString());
		textLeftWGS84_secDecSec_Lon.setText(String.format("%.2f", pointL.getLonSecondsDecSec()));
	}
	
	private void clearLeftPoint(ActionEvent event){
		textLeftOsSixFigure.clear();
		textLeftOsSixFigure.setPromptText("e.g. TQ571168");
		textLeftOSEast.setPromptText("Easting (6 digits)");
		textLeftOSNorth.setPromptText("Norting (6 digits)");
		textLeftOSEast.clear();
		textLeftOSNorth.clear();
		textLeftWGS84_degDec_Lat.clear();
		textLeftWGS84_degDec_Lon.clear();
		textLeftWGS84_degDecMin_Lat.clear();
		textLeftWGS84_degDecMin_Lon.clear();
		textLeftWGS84_degDecSec_Lat.clear();
		textLeftWGS84_degDecSec_Lon.clear();
		textLeftWGS84_minDecMin_Lat.clear();
		textLeftWGS84_minDecMin_Lon.clear();
		textLeftWGS84_minDecSec_Lat.clear();
		textLeftWGS84_minDecSec_Lon.clear();
		textLeftWGS84_secDecSec_Lat.clear();
		textLeftWGS84_secDecSec_Lon.clear();
		labelLeftPoint.setStyle("-fx-font: 13 arial; -fx-text-fill: grey;");
		textLeftOSEast.setStyle("-fx-text-fill: black;");
		textLeftOSNorth.setStyle("-fx-text-fill: black;");
		pointL.clearCoords();
		labelPointSubmittedLeft.setStyle("-fx-text-fill: grey;"); // label from Tab D
	}
	
	private void clearLeftAllFromOSSixFigure(){
		textLeftOSEast.clear();
		textLeftOSNorth.clear();
		textLeftOSEast.setPromptText("Easting (6 digits)");
		textLeftOSNorth.setPromptText("Norting (6 digits)");
		textLeftWGS84_degDec_Lat.clear();
		textLeftWGS84_degDec_Lon.clear();
		textLeftWGS84_degDecMin_Lat.clear();
		textLeftWGS84_degDecMin_Lon.clear();
		textLeftWGS84_degDecSec_Lat.clear();
		textLeftWGS84_degDecSec_Lon.clear();
		textLeftWGS84_minDecMin_Lat.clear();
		textLeftWGS84_minDecMin_Lon.clear();
		textLeftWGS84_minDecSec_Lat.clear();
		textLeftWGS84_minDecSec_Lon.clear();
		textLeftWGS84_secDecSec_Lat.clear();
		textLeftWGS84_secDecSec_Lon.clear();
		labelLeftPoint.setStyle("-fx-font: 13 arial; -fx-text-fill: grey;");
		textLeftOSEast.setStyle("-fx-text-fill: black;");
		textLeftOSNorth.setStyle("-fx-text-fill: black;");
		pointL.clearCoords();
		labelPointSubmittedLeft.setStyle("-fx-text-fill: grey;"); // label from Tab D
	}
	
	private void clearLeftAllFromOS(){
		textLeftOsSixFigure.clear();
		textLeftOsSixFigure.setPromptText("e.g. TQ571168");
		textLeftWGS84_degDec_Lat.clear();
		textLeftWGS84_degDec_Lon.clear();
		textLeftWGS84_degDecMin_Lat.clear();
		textLeftWGS84_degDecMin_Lon.clear();
		textLeftWGS84_degDecSec_Lat.clear();
		textLeftWGS84_degDecSec_Lon.clear();
		textLeftWGS84_minDecMin_Lat.clear();
		textLeftWGS84_minDecMin_Lon.clear();
		textLeftWGS84_minDecSec_Lat.clear();
		textLeftWGS84_minDecSec_Lon.clear();
		textLeftWGS84_secDecSec_Lat.clear();
		textLeftWGS84_secDecSec_Lon.clear();
		labelLeftPoint.setStyle("-fx-font: 13 arial; -fx-text-fill: grey;");
		textLeftOSEast.setStyle("-fx-text-fill: black;");
		textLeftOSNorth.setStyle("-fx-text-fill: black;");
		pointL.clearCoords();
		labelPointSubmittedLeft.setStyle("-fx-text-fill: grey;"); // label from Tab D
	}
	
	private void clearLeftAllFromDecDeg(){
		textLeftOsSixFigure.clear();
		textLeftOsSixFigure.setPromptText("e.g. TQ571168");
		textLeftOSEast.setPromptText("Easting (6 digits)");
		textLeftOSNorth.setPromptText("Norting (6 digits)");
		textLeftOSEast.clear();
		textLeftOSNorth.clear();
		textLeftWGS84_degDecMin_Lat.clear();
		textLeftWGS84_degDecMin_Lon.clear();
		textLeftWGS84_degDecSec_Lat.clear();
		textLeftWGS84_degDecSec_Lon.clear();
		textLeftWGS84_minDecMin_Lat.clear();
		textLeftWGS84_minDecMin_Lon.clear();
		textLeftWGS84_minDecSec_Lat.clear();
		textLeftWGS84_minDecSec_Lon.clear();
		textLeftWGS84_secDecSec_Lat.clear();
		textLeftWGS84_secDecSec_Lon.clear();
		labelLeftPoint.setStyle("-fx-font: 13 arial; -fx-text-fill: grey;");
		textLeftOSEast.setStyle("-fx-text-fill: black;");
		textLeftOSNorth.setStyle("-fx-text-fill: black;");
		pointL.clearCoords();
		labelPointSubmittedLeft.setStyle("-fx-text-fill: grey;"); // label from Tab D
	}
	
	private void clearLeftAllFromDecMin(){
		textLeftOsSixFigure.clear();
		textLeftOsSixFigure.setPromptText("e.g. TQ571168");
		textLeftOSEast.setPromptText("Easting (6 digits)");
		textLeftOSNorth.setPromptText("Norting (6 digits)");
		textLeftOSEast.clear();
		textLeftOSNorth.clear();
		textLeftWGS84_degDec_Lat.clear();
		textLeftWGS84_degDec_Lon.clear();
		textLeftWGS84_degDecSec_Lat.clear();
		textLeftWGS84_degDecSec_Lon.clear();
		textLeftWGS84_minDecSec_Lat.clear();
		textLeftWGS84_minDecSec_Lon.clear();
		textLeftWGS84_secDecSec_Lat.clear();
		textLeftWGS84_secDecSec_Lon.clear();
		labelLeftPoint.setStyle("-fx-font: 13 arial; -fx-text-fill: grey;");
		textLeftOSEast.setStyle("-fx-text-fill: black;");
		textLeftOSNorth.setStyle("-fx-text-fill: black;");
		pointL.clearCoords();
		labelPointSubmittedLeft.setStyle("-fx-text-fill: grey;"); // label from Tab D
	}
	
	private void clearLeftAllFromDecSec(){
		textLeftOsSixFigure.clear();
		textLeftOsSixFigure.setPromptText("e.g. TQ571168");
		textLeftOSEast.setPromptText("Easting (6 digits)");
		textLeftOSNorth.setPromptText("Norting (6 digits)");
		textLeftOSEast.clear();
		textLeftOSNorth.clear();
		textLeftWGS84_degDec_Lat.clear();
		textLeftWGS84_degDec_Lon.clear();
		textLeftWGS84_degDecMin_Lat.clear();
		textLeftWGS84_degDecMin_Lon.clear();
		textLeftWGS84_minDecMin_Lat.clear();
		textLeftWGS84_minDecMin_Lon.clear();
		labelLeftPoint.setStyle("-fx-font: 13 arial; -fx-text-fill: grey;");
		textLeftOSEast.setStyle("-fx-text-fill: black;");
		textLeftOSNorth.setStyle("-fx-text-fill: black;");
		pointL.clearCoords();
		labelPointSubmittedLeft.setStyle("-fx-text-fill: grey;"); // label from Tab D
	}

    /*
     * Right Point calculations
     */
		private void readRightPoint(ActionEvent event){
			if(textRightOsSixFigure.getText().trim().length() != 0){
				calcRightFromOSSixFigure();
			}else{
				if(textRightOSEast.getText().trim().length() > 0
                        || (textRightOSNorth.getText().trim().length() > 0)){
					calcRightFromOS();
				}else{
					if((textRightWGS84_degDec_Lat.getText().trim().length() > 0)
                            || (textRightWGS84_degDec_Lon.getText().trim().length() > 0)){
						calcRightFromDegDec();
					}else{
						if((textRightWGS84_degDecMin_Lat.getText().trim().length() > 0)
                                || (textRightWGS84_minDecMin_Lat.getText().trim().length() > 0)
						        || (textRightWGS84_degDecMin_Lon.getText().trim().length() > 0)
                                || (textRightWGS84_minDecMin_Lon.getText().trim().length() > 0)){
						calcRightFromMinDec();
						}else{
							if((textRightWGS84_degDecSec_Lat.getText().trim().length() > 0)
                                    || (textRightWGS84_degDecSec_Lon.getText().trim().length() > 0)
									|| (textRightWGS84_minDecSec_Lat.getText().trim().length() > 0)
                                    || (textRightWGS84_minDecSec_Lon.getText().trim().length() > 0)
									|| (textRightWGS84_secDecSec_Lat.getText().trim().length() > 0)
                                    || (textRightWGS84_secDecSec_Lon.getText().trim().length() > 0)){
								calcRightFromSecDec();
							}else{
								MessageBox.show("No location for the right point", "Input error");
							}
						}
					}
				}
			}
		}
		
		private void calcRightFromOSSixFigure(){  // when user enters six-figure OS reference
			try{
				pointR.setOsSixFigure(textRightOsSixFigure.getText().trim().replaceAll(" ", ""));
				pointR.setLatLonFromOSSixFigure();
				setRightPointAll();
			}catch (StringIndexOutOfBoundsException | NumberFormatException e){
				MessageBox.show("Invalid OS Six-Figure Reference", "Input error");
			}
		}		
			
		private void calcRightFromOS(){	// when user enters easting and northing
			if((textRightOSEast.getText().trim().length() != 0)
                    & (textRightOSNorth.getText().trim().length() != 0)){
				pointR.setEasting(getOSFromText(textRightOSEast.getText()));
				pointR.setNorthing(getOSFromText(textRightOSNorth.getText()));
				if(pointR.getEasting() > 0
                        & pointR.getEasting() < 1500000
                        & pointR.getNorthing() > 0
                        & pointR.getNorthing() < 1800000){
					pointR.setLatLonFromEastNorth();
					setRightPointAll();
				}else{
					MessageBox.show("Easting has to be between 0 and 1 500 000 and Northing " +
                            "has to be between 0 and 1 800 000", "Input error");
				}
			}else{
				MessageBox.show("Both Easting and Northing have to be entered",
                        "Insuficient input error");
			}
		}
		
		private void calcRightFromDegDec(){  // when user enters latitude and longitude (decimal degrees)
			if((textRightWGS84_degDec_Lat.getText().trim().length() != 0)
                    & (textRightWGS84_degDec_Lon.getText().trim().length() != 0)){
				pointR.setLatDegreesDecDeg(Double.parseDouble(textRightWGS84_degDec_Lat.getText()));
				pointR.setLonDegreesDecDeg(Double.parseDouble(textRightWGS84_degDec_Lon.getText()));
				pointR.setLatLonFromDegDec();
				if((pointR.getLatDegreesDecDeg() >= -90)
                        & (pointR.getLatDegreesDecDeg() <= 90)
						& (pointR.getLonDegreesDecDeg() >= -90)
                        & (pointR.getLonDegreesDecDeg() <= 90)){
					pointR.setEastNorthFromLatLon();
					setRightPointAll();
				}else{
					MessageBox.show("Both Latitude and Longitude have to be between -90 na 90 degrees",
                            "Out of range");
				}
			}else{
				MessageBox.show("Both Longitude and Latitude have to be entered",
                        "Insufficient input error");
			}
		}
		
		private void calcRightFromMinDec(){ // when user enters latitude and longitude (decimal minutes)
			if((textRightWGS84_degDecMin_Lat.getText().trim().length() != 0)
                    & (textRightWGS84_degDecMin_Lon.getText().trim().length() != 0)
					& (textRightWGS84_minDecMin_Lat.getText().trim().length() != 0)
                    & (textRightWGS84_minDecMin_Lon.getText().trim().length() != 0)){
				
				pointR.setLatMinutesDecDeg(Integer.parseInt(textRightWGS84_degDecMin_Lat.getText()));
				pointR.setLatMinutesDecMin(Double.parseDouble(textRightWGS84_minDecMin_Lat.getText()));
				pointR.setLonMinutesDecDeg(Integer.parseInt(textRightWGS84_degDecMin_Lon.getText()));
				pointR.setLonMinutesDecMin(Double.parseDouble(textRightWGS84_minDecMin_Lon.getText()));
				pointR.setLatLonFromMinDec();
				if((pointR.getLatDegreesDecDeg() >= -90)
                        & (pointR.getLatDegreesDecDeg() <= 90)
						& (pointR.getLonDegreesDecDeg() >= -90)
                        & (pointR.getLonDegreesDecDeg() <= 90)){
					pointR.setEastNorthFromLatLon();
					setRightPointAll();
				}else{
					MessageBox.show("Both Latitude and Longitude have to be between -90 na 90 degrees",
                            "Out of range");
				}
			}else{
				MessageBox.show("Both Longitude and Latitude have to be entered",
                        "Insufficient input error");
			}
		}
		
		private void calcRightFromSecDec(){  // when user enters latitude and longitude (decimal seconds-)
			if((textRightWGS84_degDecSec_Lat.getText().trim().length() != 0)
                    & (textRightWGS84_degDecSec_Lon.getText().trim().length() != 0)
					& (textRightWGS84_minDecSec_Lat.getText().trim().length() != 0)
                    & (textRightWGS84_minDecSec_Lon.getText().trim().length() != 0)
					& (textRightWGS84_secDecSec_Lat.getText().trim().length() != 0)
                    & (textRightWGS84_secDecSec_Lon.getText().trim().length() != 0)){
				pointR.setLatSecondsDecDeg(Integer.parseInt(textRightWGS84_degDecSec_Lat.getText()));
				pointR.setLatSecondsDecMin(Integer.parseInt(textRightWGS84_minDecSec_Lat.getText()));
				pointR.setLatSecondsDecSec(Double.parseDouble(textRightWGS84_secDecSec_Lat.getText()));
				pointR.setLonSecondsDecDeg(Integer.parseInt(textRightWGS84_degDecSec_Lon.getText()));
				pointR.setLonSecondsDecMin(Integer.parseInt(textRightWGS84_minDecSec_Lon.getText()));
				pointR.setLonSecondsDecSec(Double.parseDouble(textRightWGS84_secDecSec_Lon.getText()));
				pointR.setLatLonFromSecDec();
				if((pointR.getLatDegreesDecDeg() >= -90)
                        & (pointR.getLatDegreesDecDeg() <= 90)
						& (pointR.getLonDegreesDecDeg() >= -90)
                        & (pointR.getLonDegreesDecDeg() <= 90)){
					pointR.setEastNorthFromLatLon();
					setRightPointAll();
				}else{
					MessageBox.show("Both Latitude and Longitude have to be between -90 na 90 degrees",
                            "Out of range");
				}
			}else{
				MessageBox.show("Both Longitude and Latitude have to be entered",
                        "Insufficient input error");
			}
		}
		
		private void setRightPointAll(){
			
			labelRightPoint.setStyle("-fx-font: 13 arial; -fx-text-fill: blue;");
			
			if(((int)pointR.getEasting() > 0) & ((int)pointR.getEasting() < 1500000)
					& ((int)pointR.getNorthing() > 0) & ((int)pointR.getNorthing() < 1800000)){
				textRightOSEast.setText((new Integer((int)pointR.getEasting())).toString());
				textRightOSNorth.setText((new Integer((int)pointR.getNorthing())).toString());
				textRightOsSixFigure.setText(pointR.getOsSixFigure());
				
			}else{
				textRightOSEast.setPromptText("out of range");
				textRightOSNorth.setPromptText("out of range");
				textRightOsSixFigure.setPromptText("out of range");
			}
			textRightWGS84_degDec_Lat.setText(String.format("%.10f", pointR.getLatDegreesDecDeg()));
			textRightWGS84_degDecMin_Lat.setText((new Integer(pointR.getLatMinutesDecDeg())).toString());
			textRightWGS84_minDecMin_Lat.setText(String.format("%.8f", pointR.getLatMinutesDecMin()));
			textRightWGS84_degDecSec_Lat.setText((new Integer(pointR.getLatSecondsDecDeg())).toString());
			textRightWGS84_minDecSec_Lat.setText((new Integer(pointR.getLatSecondsDecMin())).toString());
			textRightWGS84_secDecSec_Lat.setText(String.format("%.2f", pointR.getLatSecondsDecSec()));
						
			textRightWGS84_degDec_Lon.setText(String.format("%.10f", pointR.getLonDegreesDecDeg()));
			textRightWGS84_degDecMin_Lon.setText((new Integer(pointR.getLonMinutesDecDeg())).toString());
			textRightWGS84_minDecMin_Lon.setText(String.format("%.8f", pointR.getLonMinutesDecMin()));
			textRightWGS84_degDecSec_Lon.setText((new Integer(pointR.getLonSecondsDecDeg())).toString());
			textRightWGS84_minDecSec_Lon.setText((new Integer(pointR.getLonSecondsDecMin())).toString());
			textRightWGS84_secDecSec_Lon.setText(String.format("%.2f", pointR.getLonSecondsDecSec()));
		}
		
		private void clearRightPoint(ActionEvent event){
			textRightOsSixFigure.clear();
			textRightOsSixFigure.setPromptText("e.g. TQ571168");
			textRightOSEast.setPromptText("Easting (6 digits)");
			textRightOSNorth.setPromptText("Norting (6 digits)");
			textRightOSEast.clear();
			textRightOSNorth.clear();
			textRightWGS84_degDec_Lat.clear();
			textRightWGS84_degDec_Lon.clear();
			textRightWGS84_degDecMin_Lat.clear();
			textRightWGS84_degDecMin_Lon.clear();
			textRightWGS84_degDecSec_Lat.clear();
			textRightWGS84_degDecSec_Lon.clear();
			textRightWGS84_minDecMin_Lat.clear();
			textRightWGS84_minDecMin_Lon.clear();
			textRightWGS84_minDecSec_Lat.clear();
			textRightWGS84_minDecSec_Lon.clear();
			textRightWGS84_secDecSec_Lat.clear();
			textRightWGS84_secDecSec_Lon.clear();
			labelRightPoint.setStyle("-fx-font: 13 arial; -fx-text-fill: grey;");
			textRightOSEast.setStyle("-fx-text-fill: black;");
			textRightOSNorth.setStyle("-fx-text-fill: black;");
			pointR.clearCoords();
			labelPointSubmittedRight.setStyle("-fx-text-fill: grey;"); // label from Tab D
		}
		
		private void clearRightAllFromOSSixFigure(){
			textRightOSEast.clear();
			textRightOSNorth.clear();
			textRightOSEast.setPromptText("Easting (6 digits)");
			textRightOSNorth.setPromptText("Norting (6 digits)");
			textRightWGS84_degDec_Lat.clear();
			textRightWGS84_degDec_Lon.clear();
			textRightWGS84_degDecMin_Lat.clear();
			textRightWGS84_degDecMin_Lon.clear();
			textRightWGS84_degDecSec_Lat.clear();
			textRightWGS84_degDecSec_Lon.clear();
			textRightWGS84_minDecMin_Lat.clear();
			textRightWGS84_minDecMin_Lon.clear();
			textRightWGS84_minDecSec_Lat.clear();
			textRightWGS84_minDecSec_Lon.clear();
			textRightWGS84_secDecSec_Lat.clear();
			textRightWGS84_secDecSec_Lon.clear();
			labelRightPoint.setStyle("-fx-font: 13 arial; -fx-text-fill: grey;");
			textRightOSEast.setStyle("-fx-text-fill: black;");
			textRightOSNorth.setStyle("-fx-text-fill: black;");
			pointR.clearCoords();
			labelPointSubmittedRight.setStyle("-fx-text-fill: grey;"); // label from Tab D
		}
		
		private void clearRightAllFromOS(){
			textRightOsSixFigure.clear();
			textRightOsSixFigure.setPromptText("e.g. TQ571168");
			textRightWGS84_degDec_Lat.clear();
			textRightWGS84_degDec_Lon.clear();
			textRightWGS84_degDecMin_Lat.clear();
			textRightWGS84_degDecMin_Lon.clear();
			textRightWGS84_degDecSec_Lat.clear();
			textRightWGS84_degDecSec_Lon.clear();
			textRightWGS84_minDecMin_Lat.clear();
			textRightWGS84_minDecMin_Lon.clear();
			textRightWGS84_minDecSec_Lat.clear();
			textRightWGS84_minDecSec_Lon.clear();
			textRightWGS84_secDecSec_Lat.clear();
			textRightWGS84_secDecSec_Lon.clear();
			labelRightPoint.setStyle("-fx-font: 13 arial; -fx-text-fill: grey;");
			textRightOSEast.setStyle("-fx-text-fill: black;");
			textRightOSNorth.setStyle("-fx-text-fill: black;");
			pointR.clearCoords();
			labelPointSubmittedRight.setStyle("-fx-text-fill: grey;"); // label from Tab D
		}
		
		private void clearRightAllFromDecDeg(){
			textRightOsSixFigure.clear();
			textRightOsSixFigure.setPromptText("e.g. TQ571168");
			textRightOSEast.setPromptText("Easting (6 digits)");
			textRightOSNorth.setPromptText("Norting (6 digits)");
			textRightOSEast.clear();
			textRightOSNorth.clear();
			
			textRightWGS84_degDecMin_Lat.clear();
			textRightWGS84_degDecMin_Lon.clear();
			textRightWGS84_degDecSec_Lat.clear();
			textRightWGS84_degDecSec_Lon.clear();
			textRightWGS84_minDecMin_Lat.clear();
			textRightWGS84_minDecMin_Lon.clear();
			textRightWGS84_minDecSec_Lat.clear();
			textRightWGS84_minDecSec_Lon.clear();
			textRightWGS84_secDecSec_Lat.clear();
			textRightWGS84_secDecSec_Lon.clear();
			labelRightPoint.setStyle("-fx-font: 13 arial; -fx-text-fill: grey;");
			textRightOSEast.setStyle("-fx-text-fill: black;");
			textRightOSNorth.setStyle("-fx-text-fill: black;");
			pointR.clearCoords();
			labelPointSubmittedRight.setStyle("-fx-text-fill: grey;"); // label from Tab D
		}
		
		private void clearRightAllFromDecMin(){
			textRightOsSixFigure.clear();
			textRightOsSixFigure.setPromptText("e.g. TQ571168");
			textRightOSEast.setPromptText("Easting (6 digits)");
			textRightOSNorth.setPromptText("Norting (6 digits)");
			textRightOSEast.clear();
			textRightOSNorth.clear();
			
			textRightWGS84_degDec_Lat.clear();
			textRightWGS84_degDec_Lon.clear();
			
			textRightWGS84_degDecSec_Lat.clear();
			textRightWGS84_degDecSec_Lon.clear();
			
			textRightWGS84_minDecSec_Lat.clear();
			textRightWGS84_minDecSec_Lon.clear();
			textRightWGS84_secDecSec_Lat.clear();
			textRightWGS84_secDecSec_Lon.clear();
			labelRightPoint.setStyle("-fx-font: 13 arial; -fx-text-fill: grey;");
			textRightOSEast.setStyle("-fx-text-fill: black;");
			textRightOSNorth.setStyle("-fx-text-fill: black;");
			pointR.clearCoords();
			labelPointSubmittedRight.setStyle("-fx-text-fill: grey;"); // label from Tab D
		}
		
		private void clearRightAllFromDecSec(){
			textRightOsSixFigure.clear();
			textRightOsSixFigure.setPromptText("e.g. TQ571168");
			textRightOSEast.setPromptText("Easting (6 digits)");
			textRightOSNorth.setPromptText("Norting (6 digits)");
			textRightOSEast.clear();
			textRightOSNorth.clear();
			
			textRightWGS84_degDec_Lat.clear();
			textRightWGS84_degDec_Lon.clear();
			textRightWGS84_degDecMin_Lat.clear();
			textRightWGS84_degDecMin_Lon.clear();
			
			textRightWGS84_minDecMin_Lat.clear();
			textRightWGS84_minDecMin_Lon.clear();
			
			labelRightPoint.setStyle("-fx-font: 13 arial; -fx-text-fill: grey;");
			textRightOSEast.setStyle("-fx-text-fill: black;");
			textRightOSNorth.setStyle("-fx-text-fill: black;");
			pointR.clearCoords();
			labelPointSubmittedRight.setStyle("-fx-text-fill: grey;"); // label from Tab D
		}


    /*
     * General for both poins, check if OS grid reference is integer
     */
	private double getOSFromText(String stringOS){
		double gridRef = 0;
		gridRef = Double.parseDouble(stringOS);
		if(gridRef % 1 == 0)
			hasDecimal = false;
		return gridRef;
	}


    /********************************* TabD methods *********************************/


    private void clearAreaFields(){
        textCalcAreaFt2.clear();
        textCalcAreaMetre2.clear();
        textCalcAreaAcre.clear();
        textCalcAreaHa.clear();
    }

    private void clearPerimFields(){
        textCalcPerimFt.clear();
        textCalcPerimMetre.clear();
        textCalcPerimKm.clear();
        textCalcPerimMile.clear();
    }

    /*
     * Submits selected point as Left Point to TabC
     */
    private void selectLeftPoint(ActionEvent event){
        if(table.getSelectionModel().getSelectedItem() != null){
            textLeftOSEast.setText(Integer.toString(table.getSelectionModel().getSelectedItem().getEasting()));
            textLeftOSNorth.setText(Integer.toString(table.getSelectionModel().getSelectedItem().getNorthing()));
            labelPointSubmittedLeft.setStyle("-fx-text-fill: blue;");
        }
    }

    /*
     * Submits selected point as Right Point to TabC
     */
    private void selectRightPoint(ActionEvent event){
        if(table.getSelectionModel().getSelectedItem() != null){
            textRightOSEast.setText(Integer.toString(table.getSelectionModel().getSelectedItem().getEasting()));
            textRightOSNorth.setText(Integer.toString(table.getSelectionModel().getSelectedItem().getNorthing()));
            labelPointSubmittedRight.setStyle("-fx-text-fill: blue;");
        }
    }

    /*
     * Points/Polygon Table section
     */
    private void colNum_onEditCommit(Event e){
        TableColumn.CellEditEvent<Vertex, Integer> ce;
        ce = (TableColumn.CellEditEvent<Vertex, Integer>) e;
        Vertex v = ce.getRowValue();
        v.setNumber(ce.getNewValue());
    }

    private void colEast_onEditCommit(Event e){
        TableColumn.CellEditEvent<Vertex, Integer> ce;
        ce = (TableColumn.CellEditEvent<Vertex, Integer>) e;
        Vertex v = ce.getRowValue();
        v.setEasting(ce.getNewValue());
    }

    private void colNorth_onEditCommit(Event e){
        TableColumn.CellEditEvent<Vertex, Integer> ce;
        ce = (TableColumn.CellEditEvent<Vertex, Integer>) e;
        Vertex v = ce.getRowValue();
        v.setNorthing(ce.getNewValue());
    }

    /*
     * Imports points to the table
     */
    private void importVertexes(ActionEvent event){
        try{
            labelPointAdded.setText("     ");
            String option;
            if(comboImport.getValue() != null){
                option = comboImport.getValue();
                switch(option){
                    case SPACE_SIN_NUMBER:
                        table.getItems().setAll(VertexPolygon.readPolygonSinNum_space());
                        break;
                    case SPACE_WITH_NUMBER:
                        table.getItems().setAll(VertexPolygon.readPolygonWithNum_space());
                        break;
                    case COMMA_SIN_NUMBER:
                        table.getItems().setAll(VertexPolygon.readPolygonSinNum_comma());
                        break;
                    case COMMA_WITH_NUMBER:
                        table.getItems().setAll(VertexPolygon.readPolygonWithNum_comma());
                        break;
                    default:
                        MessageBox.show("Choose file format", "Insufficient information");
                }
            }else{
                MessageBox.show("Choose file format", "Insufficient information");
            }
        }catch(NumberFormatException e){
            MessageBox.show("Declared file format does not match the actual file format", "Input error");
        }
    }

    /*
     * Exports points from table
     */
    private void exportVertexes(ActionEvent event){
        labelPointAdded.setText("     ");
        if (table.getItems().size() > 0){
            String option = SPACE_SIN_NUMBER;
            if(comboExport.getValue() != null)
                option = comboExport.getValue();
            //System.out.println("export = " + comboExport.getValue());
            switch(option){
                case SPACE_SIN_NUMBER:
                    VertexPolygon.savePolygonSinNum_space(table.getItems());
                    break;
                case SPACE_WITH_NUMBER:
                    VertexPolygon.savePolygonWithNum_space(table.getItems());
                    break;
                case COMMA_SIN_NUMBER:
                    VertexPolygon.savePolygonSinNum_comma(table.getItems());
                    break;
                case COMMA_WITH_NUMBER:
                    VertexPolygon.savePolygonWithNum_comma(table.getItems());
                    break;
                default:
                    MessageBox.show("Choose file format.", "Insufficient information");
            }
        }else{
            MessageBox.show("The list is empty.", "Data error");
        }
    }

    /*
     * Manual point entry to the table
     */
    private void addVertex(ActionEvent event){
        FOUND: {
            Vertex v = new Vertex();

            if(textPointNum.getText().trim().length() < 1){
                if(table.getItems() == null){
                    v.setNumber(1);
                }else{
                    v.setNumber(table.getItems().size() + 1);
                }
            }else{
                if(Integer.parseInt(textPointNum.getText()) < table.getItems().size()){
                    MessageBox.show("Number " + Integer.parseInt(textPointNum.getText()) +
                            " is already taken", "Point Number Warning");
                    break FOUND;
                }else{
                    v.setNumber(Integer.parseInt(textPointNum.getText()));
                }
            }
            if((textPointEast.getText().trim().length() > 0) & (textPointNorth.getText().trim().length() > 0)){
                v.setEasting(Integer.parseInt(textPointEast.getText()));
                v.setNorthing(Integer.parseInt(textPointNorth.getText()));
                table.getItems().add(v);
                textPointNum.clear();
                textPointEast.clear();
                textPointNorth.clear();
                labelPointAdded.setStyle("-fx-text-fill: blue;");
                labelPointAdded.setText("  OK!");
            }else{
                labelPointAdded.setStyle("-fx-text-fill: red;");
                labelPointAdded.setText("Fail!");
            }
        }
    }

    /*
     * Deletes point from the table
     */
    private void deleteVertex(ActionEvent event){
        ObservableList<Vertex> selected, vertexes;
        vertexes = table.getItems();
        selected = table.getSelectionModel().getSelectedItems();
        for(Vertex v: selected){
            vertexes.remove(v);
        }
    }

    /*
     * Area and Perimeter calculations and result values setting
     */
    private void setArea(ActionEvent event){
        double sqMetres = calcArea(table.getItems());  // area is in square metres
        textCalcAreaFt2.setText(String.format("%.1f", sqMetres * 10.76391041671));
        textCalcAreaMetre2.setText(String.format("%.1f", sqMetres));
        textCalcAreaAcre.setText(String.format("%.2f", sqMetres * 0.000247105));
        textCalcAreaHa.setText(String.format("%.2f", sqMetres * 0.0001));
    }

    private void setPerimeter(ActionEvent event){
        double metres = calcPerim(table.getItems());  // perimeter is in metres
        textCalcPerimFt.setText(String.format("%.1f", metres * 3.280840));
        textCalcPerimMetre.setText(String.format("%.1f", metres));
        textCalcPerimKm.setText(String.format("%.2f", metres / 1000));
        textCalcPerimMile.setText(String.format("%.2f", metres / 1609.3440));

    }

    private double calcArea(ObservableList<Vertex> vertexesOL){  //calculates area in the same units
        labelPointAdded.setText("     ");
        int num = vertexesOL.size();  //number of vertexes
        int last = num-1;
        int x[] = new int[num];  //eastings of point number i
        int y[] =  new int[num]; //northings of point number i
        int i = 0;
        double result = 0;
        for(Vertex v: vertexesOL){
            x[i] = v.getEasting();
            y[i] = v.getNorthing();
            i++;
        }
        for(int n = 0; n < last; n++){
            result = result + ((x[n]*y[n+1]) - (y[n]*x[n+1]));
        }
        result = (result + ((x[last]*y[0]) - (y[last]*x[0]))) * 0.5;
        result = Math.abs(result);
        return result;
    }

    private double calcPerim(ObservableList<Vertex> vertexesOL){   //calculates perimeter in the same units
        labelPointAdded.setText("     ");
        int num = vertexesOL.size();  //number of vertexes
        int last = num-1;
        int x[] = new int[num];  //eastings of point number i
        int y[] =  new int[num]; //northings of point number i
        int i = 0;
        double result = 0;
        for(Vertex v: vertexesOL){
            x[i] = v.getEasting();
            y[i] = v.getNorthing();
            i++;
        }
        for(int n = 0; n < last; n++){
            result = result + Math.sqrt(Math.pow((x[n] - x[n+1]) ,2) + Math.pow((y[n] - y[n+1]) ,2));
        }
        result = result + Math.sqrt(Math.pow((x[last] - x[0]) ,2) + Math.pow((y[last] - y[0]) ,2));
        return result;
    }

}    
 
    
