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

import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.OSRef;

public class Point {
	
	private double easting;
	private double northing;
	private String osSixFigure;
	
	private double latDegreesDecDeg;
	
	private int latMinutesDecDeg;
	private double latMinutesDecMin;
	
	private int latSecondsDecDeg;
	private int latSecondsDecMin;
	private double latSecondsDecSec;
	
	private double lonDegreesDecDeg;
	
	private int lonMinutesDecDeg;
	private double lonMinutesDecMin;
	
	private int lonSecondsDecDeg;
	private int lonSecondsDecMin;
	private double lonSecondsDecSec;
	
	
	public String getOsSixFigure() {
		return osSixFigure;
	}
	public void setOsSixFigure(String osSixFigure) {
		this.osSixFigure = osSixFigure;
	}
	public double getEasting() {
		return easting;
	}
	public void setEasting(double easting) {
		this.easting = easting;
	}
	public double getNorthing() {
		return northing;
	}
	public void setNorthing(double northing) {
		this.northing = northing;
	}
	public double getLatDegreesDecDeg() {
		return latDegreesDecDeg;
	}
	public void setLatDegreesDecDeg(double latDegreesDecDeg) {
		this.latDegreesDecDeg = latDegreesDecDeg;
	}
	public int getLatMinutesDecDeg() {
		return latMinutesDecDeg;
	}
	public void setLatMinutesDecDeg(int latMinutesDecDeg) {
		this.latMinutesDecDeg = latMinutesDecDeg;
	}
	public double getLatMinutesDecMin() {
		return latMinutesDecMin;
	}
	public void setLatMinutesDecMin(double latMinutesDecMin) {
		this.latMinutesDecMin = latMinutesDecMin;
	}
	public int getLatSecondsDecDeg() {
		return latSecondsDecDeg;
	}
	public void setLatSecondsDecDeg(int latSecondsDecDeg) {
		this.latSecondsDecDeg = latSecondsDecDeg;
	}
	public int getLatSecondsDecMin() {
		return latSecondsDecMin;
	}
	public void setLatSecondsDecMin(int latSecondsDecMin) {
		this.latSecondsDecMin = latSecondsDecMin;
	}
	public double getLatSecondsDecSec() {
		return latSecondsDecSec;
	}
	public void setLatSecondsDecSec(double latSecondsDecSec) {
		this.latSecondsDecSec = latSecondsDecSec;
	}
	public double getLonDegreesDecDeg() {
		return lonDegreesDecDeg;
	}
	public void setLonDegreesDecDeg(double lonDegreesDecDeg) {
		this.lonDegreesDecDeg = lonDegreesDecDeg;
	}
	public int getLonMinutesDecDeg() {
		return lonMinutesDecDeg;
	}
	public void setLonMinutesDecDeg(int lonMinutesDecDeg) {
		this.lonMinutesDecDeg = lonMinutesDecDeg;
	}
	public double getLonMinutesDecMin() {
		return lonMinutesDecMin;
	}
	public void setLonMinutesDecMin(double lonMinutesDecMin) {
		this.lonMinutesDecMin = lonMinutesDecMin;
	}
	public int getLonSecondsDecDeg() {
		return lonSecondsDecDeg;
	}
	public void setLonSecondsDecDeg(int lonSecondsDecDeg) {
		this.lonSecondsDecDeg = lonSecondsDecDeg;
	}
	public int getLonSecondsDecMin() {
		return lonSecondsDecMin;
	}
	public void setLonSecondsDecMin(int lonSecondsDecMin) {
		this.lonSecondsDecMin = lonSecondsDecMin;
	}
	public double getLonSecondsDecSec() {
		return lonSecondsDecSec;
	}
	public void setLonSecondsDecSec(double lonSecondsDecSec) {
		this.lonSecondsDecSec = lonSecondsDecSec;
	}
	
	public void setLatLonFromDegDec(){
		latMinutesDecDeg = (int) latDegreesDecDeg;
		latMinutesDecMin = (latDegreesDecDeg - (int) latDegreesDecDeg) * 60;
		lonMinutesDecDeg = (int) lonDegreesDecDeg;
		lonMinutesDecMin = (lonDegreesDecDeg - (int) lonDegreesDecDeg) * 60;
		
		latSecondsDecDeg = (int) latDegreesDecDeg;
		latSecondsDecMin = (int) ((latDegreesDecDeg - (int) latDegreesDecDeg) * 60);
		latSecondsDecSec = ((latDegreesDecDeg - (int) latDegreesDecDeg) - ((int) (latDegreesDecDeg - (int) latDegreesDecDeg)) * 3600);
		lonSecondsDecDeg = (int) lonDegreesDecDeg;
		lonSecondsDecMin = (int) ((lonDegreesDecDeg - (int) lonDegreesDecDeg) * 60);
		lonSecondsDecSec = ((lonDegreesDecDeg - (int) lonDegreesDecDeg) - ((int) (lonDegreesDecDeg - (int) lonDegreesDecDeg)) * 3600);
	}
	
	public void setLatLonFromMinDec(){
		if(latMinutesDecDeg < 0) 
			latDegreesDecDeg = latMinutesDecDeg - (latMinutesDecMin / 60);
		else
			latDegreesDecDeg = latMinutesDecDeg + (latMinutesDecMin / 60);
		if(lonMinutesDecDeg < 0)
			lonDegreesDecDeg = lonMinutesDecDeg - (lonMinutesDecMin / 60);
								
		latSecondsDecDeg = latMinutesDecDeg;
		latSecondsDecMin = (int) latMinutesDecMin;
		latSecondsDecSec = (latMinutesDecMin - ((int) latMinutesDecMin)) * 60;
		lonSecondsDecDeg = lonMinutesDecDeg;
		lonSecondsDecMin = (int) lonMinutesDecMin;
		lonSecondsDecSec = (lonMinutesDecMin - ((int) lonMinutesDecMin)) * 60;

	}
	
	public void setLatLonFromSecDec(){
		latMinutesDecDeg = latSecondsDecDeg;
		latMinutesDecMin = latSecondsDecMin + (latSecondsDecSec / 60);
		lonMinutesDecDeg = lonSecondsDecDeg;
		lonMinutesDecMin = lonSecondsDecMin + (lonSecondsDecSec /60);
			
		setLatLonFromMinDec();	
			
			
	}
	
	public void setLatLonFromEastNorth(){
		OSRef os = new OSRef(easting, northing);
		LatLng latLng = os.toLatLng();
		latLng.toWGS84();
		latDegreesDecDeg = latLng.getLat();
		lonDegreesDecDeg = latLng.getLng();
		setLatLonFromDegDec();
		osSixFigure = os.toSixFigureString();
	}
	
	public void setLatLonFromOSSixFigure(){
		OSRef os = new OSRef(osSixFigure);
		//System.out.println(osSixFigure);
		LatLng latLng = os.toLatLng();
		latLng.toWGS84();
		latDegreesDecDeg = latLng.getLat();
		lonDegreesDecDeg = latLng.getLng();
		setLatLonFromDegDec();
		easting = os.getEasting();
		northing = os.getNorthing();
	}
		
	public void setEastNorthFromLatLon(){
		LatLng latLng = new LatLng(latDegreesDecDeg, lonDegreesDecDeg);
		latLng.toOSGB36();
		OSRef os = latLng. toOSRef();
		easting = os.getEasting();
		northing = os.getNorthing();
		osSixFigure = os.toSixFigureString();
	}
	
		
	public void clearCoords(){
		osSixFigure = null;
		easting = 0;
		northing = 0;
		
		latDegreesDecDeg = 0;
		latMinutesDecDeg = 0;
		latMinutesDecMin = 0;
		latSecondsDecDeg = 0;
		latSecondsDecMin = 0;
		latSecondsDecSec = 0;
		
		lonDegreesDecDeg = 0;
		lonMinutesDecDeg = 0;
		lonMinutesDecMin = 0;
		lonSecondsDecDeg = 0;
		lonSecondsDecMin = 0;
		lonSecondsDecSec = 0;
	}
	
		
}

/*
 * Convert Six-Figure OS Grid Reference String to an OSRef Object
 * 
 * To convert a string representing a six-figure OSGB grid reference:
 *

System.out
    .println("Convert Six-Figure OS Grid Reference String to an OSRef Object");
String os6 = "TG514131";
System.out.println("Six figure string: " + os6);
OSRef os6x = new OSRef(os6);
System.out.println("Converted to OS Grid Ref: " + os6x.toString() + " - "
    + os6x.toSixFigureString());
System.out.println();

*/















