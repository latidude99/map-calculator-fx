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





























