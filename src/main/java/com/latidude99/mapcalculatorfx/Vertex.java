/**Copyright (C) 2017  Piotr Czapik.
* @author Piotr Czapik <latitude1001101@gmail.com>
* @version 4.01
* @license GPLv2
*/ 

package com.latidude99.mapcalculatorfx;

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



















