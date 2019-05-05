/**Copyright (C) 2017  Piotr Czapik.
* @author Piotr Czapik <latitude1001101@gmail.com>
* @version 4.01
* @license GPLv2
*/ 

package com.latidude99.mapcalculatorfx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class VertexPolygon{
    
    public static ObservableList<Vertex> readPolygonSinNum_space(){
    	ObservableList<Vertex> list = FXCollections.observableArrayList();
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showOpenDialog(new Stage());
        
        if(file != null){
	        try (BufferedReader br = new BufferedReader(new FileReader(file));){
	            int num = 1;
	            String line = null;
	            String[] os = new String[2];
	            while((line = br.readLine()) != null){
	                os = line.split("\\s+", 2);
	                Vertex vertex = new Vertex();
	                vertex.setEasting(Integer.parseInt(os[0]));
	                vertex.setNorthing(Integer.parseInt(os[1]));
	                vertex.setNumber(num);
	                num++;
	                list.add(vertex);
	            }
	            }catch(IOException e){
	            	MessageBox.show("Incorrect file format or empty file", "Input error");
	            }
	        file = null;
        }
        return list;
    }
    
    public static ObservableList<Vertex> readPolygonWithNum_space(){
    	ObservableList<Vertex> list = FXCollections.observableArrayList();
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showOpenDialog(new Stage());
        
        if(file != null){
        try (BufferedReader br = new BufferedReader(new FileReader(file));){
            String line = null;
            String[] os = new String[3];
            while((line = br.readLine()) != null){
                os = line.split("\\s+", 3);
                Vertex vertex = new Vertex();
                vertex.setNumber(Integer.parseInt(os[0]));
                vertex.setEasting(Integer.parseInt(os[1]));
                vertex.setNorthing(Integer.parseInt(os[2]));
                list.add(vertex);
            }
            }catch(IOException e){
            	MessageBox.show("Incorrect file format", "Input error");
            }
        	file = null;
        }
        return list;
    }
    
    public static ObservableList<Vertex> readPolygonSinNum_comma(){
    	ObservableList<Vertex> list = FXCollections.observableArrayList();
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showOpenDialog(new Stage());
        
        if(file != null){
        try (BufferedReader br = new BufferedReader(new FileReader(file));){
            int num = 1;
            String line = null;
            String[] os = new String[2];
            while((line = br.readLine()) != null){
                os = line.split("\\,", 2);
                Vertex vertex = new Vertex();
                vertex.setEasting(Integer.parseInt(os[0]));
                vertex.setNorthing(Integer.parseInt(os[1]));
                vertex.setNumber(num);
                num++;
                list.add(vertex);
            }
            }catch(IOException e){
            	MessageBox.show("Incorrect file format", "Input error");
            }
        	file = null;
        }
        return list;
    }
    
    public static ObservableList<Vertex> readPolygonWithNum_comma(){
    	ObservableList<Vertex> list = FXCollections.observableArrayList();
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showOpenDialog(new Stage());
        
        if(file != null){
	        try (BufferedReader br = new BufferedReader(new FileReader(file));){
	            String line = null;
	            String[] os = new String[3];
	            while((line = br.readLine()) != null){
	                os = line.split("\\,", 3);
	                Vertex vertex = new Vertex();
	                vertex.setNumber(Integer.parseInt(os[0]));
	                vertex.setEasting(Integer.parseInt(os[1]));
	                vertex.setNorthing(Integer.parseInt(os[2]));
	                list.add(vertex);
	            }
	        }catch(IOException e){
	          	MessageBox.show("Incorrect file format", "Input error");
	        }
	        file = null;
        }
        return list;
    }
        
    public static void savePolygonSinNum_space(ObservableList<Vertex> list){
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showSaveDialog(new Stage());
        
        if(file != null){
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file));){
	            
	        	    for(Vertex v: list){
		                bw.write(v.getEasting() + " " + v.getNorthing());
		                bw.newLine();
		            }
	        }catch(IOException e){
	            MessageBox.show("File not saved", "Output error");
	            //Logger.getLogger(Vertex.class.getVertexNum()).log(Level.SEVERE, null, ex);
	        }
        }
    }
    
    public static void savePolygonWithNum_space(ObservableList<Vertex> list){
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showSaveDialog(new Stage());
        
        if(file != null){
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file));){
	            
	            for(Vertex v: list){
	                bw.write(v.getNumber() + " " + v.getEasting() + " " + v.getNorthing());
	                bw.newLine();
	            }
	        }catch(IOException e){
	            MessageBox.show("File not saved", "Output error");
	            //Logger.getLogger(Vertex.class.getVertexNum()).log(Level.SEVERE, null, ex);
	        }
        }
    }
    
    public static void savePolygonSinNum_comma(ObservableList<Vertex> list){
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showSaveDialog(new Stage());
        
        if(file != null){
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file));){
	            
	            for(Vertex v: list){
	                bw.write(v.getEasting() + "," + v.getNorthing());
	                bw.newLine();
	            }
	        }catch(IOException e){
	            MessageBox.show("File not saved", "Output error");
	            //Logger.getLogger(Vertex.class.getVertexNum()).log(Level.SEVERE, null, ex);
	        }
        }
    }
    
    public static void savePolygonWithNum_comma(ObservableList<Vertex> list){
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showSaveDialog(new Stage());
        if(file != null){
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file));){
	            
	            for(Vertex v: list){
	                bw.write(v.getNumber() + "," + v.getEasting() + "," + v.getNorthing());
	                bw.newLine();
	            }
	        }catch(IOException e){
	            MessageBox.show("File not saved", "Output error");
	            //Logger.getLogger(Vertex.class.getVertexNum()).log(Level.SEVERE, null, ex);
	        }
        }
    }
    
    
}





























