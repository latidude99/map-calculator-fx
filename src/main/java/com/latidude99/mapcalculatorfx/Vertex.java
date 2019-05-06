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

/*
 * Polygon points, used in TabD to display polygons from the table
 */

public class Vertex{
    int number;
    int easting;
    int northing;
    
    public Vertex(){
    	number = 0;
        easting = 0;
        northing = 0;
    }
    
    public Vertex(int num, int east, int north){
    	this.number = num;
    	this.easting = east;
    	this.northing = north;    	
    }
    
    public int getNumber(){
        return number;
    }
    public void setNumber(int num){
    	this.number = num;
    }
        
    public int getEasting(){
        return easting;
    }
    public void setEasting(int east){
    	this.easting = east;
    }
       
    public int getNorthing(){
        return northing;
    }
    public void setNorthing(int north){
    	this.northing = north; 
    }
    
}

