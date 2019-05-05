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

import java.lang.Math;

import uk.me.jstott.jcoord.LatLng;

public class BearingPolygon {
	private double distanceLtoR;
	private double bearingLtoR;
	
	public double getDistanceLtoR() {
		return distanceLtoR;
	}
	public void setDistanceLtoR(double distanceLtoR) {
		this.distanceLtoR = distanceLtoR;
	}
	public double getBearingLtoR() {
		return bearingLtoR;
	}
	public void setBearingLtoR(double bearingLtoR) {
		this.bearingLtoR = bearingLtoR;
	}
	
		
	// calculates distance between two points in metres using 12 digit OS reference
	public double distLtoRFromOS(Point pointL, Point pointR){
		double deltaEasting = pointL.getEasting() - pointR.getEasting();
		double deltaNorthing = pointL.getNorthing() - pointR.getNorthing();
		return Math.sqrt(deltaEasting*deltaEasting + deltaNorthing*deltaNorthing);
	}
	
	// calculates distance between two points in metres using Latitude and Longitude
	public double distLtoRFromLatLon(Point pointL, Point pointR){
		LatLng latLngPointL = new LatLng(pointL.getLatDegreesDecDeg(), pointL.getLonDegreesDecDeg());
		LatLng latLngPointR = new LatLng(pointR.getLatDegreesDecDeg(), pointR.getLonDegreesDecDeg());
		return latLngPointL.distance(latLngPointR) * 1000;
	}
	
	//calculates bearing from the left point to the right point in decimal degrees
	public double bearingFromLtoR(Point pointL, Point pointR){
		double latLRadian = Math.toRadians(pointL.getLatDegreesDecDeg());
		double lonLRadian = Math.toRadians(pointL.getLonDegreesDecDeg());
		double latRRadian = Math.toRadians(pointR.getLatDegreesDecDeg());
		double lonRRadian = Math.toRadians(pointR.getLonDegreesDecDeg());
		
		double deltaLon =  lonRRadian - lonLRadian;
		
		double y = Math.sin(deltaLon) * Math.cos(latRRadian);
	    double x = Math.cos(latLRadian) * Math.sin(latRRadian) - Math.sin(latLRadian)
	            * Math.cos(latRRadian) * Math.cos(deltaLon);

	    double bearing = Math.atan2(y, x);

	    bearing = Math.toDegrees(bearing);
	    bearing = (bearing + 360) % 360;
	   // bearing = 360 - bearing;  count degrees counter-clockwise - remove to make clockwise

	    return bearing;
	}

}

/*
 public class examples
{
    public static void main(String[] args)
    {
         System.out.println( Math.toRadians( 180 ) ) ;
         System.out.println( Math.toDegrees( Math.PI ) ) ;
    }
}
If your input is in degrees, you need to convert the number going in to sin to radians:

double angle = 90 ;
double result  = Math.sin( Math.toRadians( angle ) ) ;
System.out.println( result ) ;

----------------------------------
  
 private double angleFromCoordinate(double lat1, double long1, double lat2,
        double long2) {

    double dLon = (long2 - long1);

    double y = Math.sin(dLon) * Math.cos(lat2);
    double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1)
            * Math.cos(lat2) * Math.cos(dLon);

    double brng = Math.atan2(y, x);

    brng = Math.toDegrees(brng);
    brng = (brng + 360) % 360;
    brng = 360 - brng; // count degrees counter-clockwise - remove to make clockwise

    return brng;
}
  
 */













