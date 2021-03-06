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
 * Converts distance between metres, kilometres, feet, statue miles and nautical miles
 */

public class Distance {

	/*
	 * Distance in metres
	 */
	private double dist = 0;

	public double getDist() {
		return dist;
	}
	public void setDist(double dist) {
		this.dist = dist;
	}
    
	public double distKm(double dist){
		return dist / 1000;
	}
	
	public double distMileStatue(double dist){
		return dist / 1609.3440;
	}

	/*
	 * This is the US (International) Nautical Mile, the UK Nautical Mile is 1853.184 metres
	 */
	public double distMileNautical(double dist){
		return dist / 1852;
	}
	
	public double distMetre(double dist){
		return dist;
	}
	
	public double distFt(double dist){
		return dist * 3.280840; 
	}
    
}

