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

public class AreaAndPerimeter {

    /*
     * Calculates area in the same units
     */
    public static double calcArea(ObservableList<Vertex> vertexesOL){
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
    /*
     * Calculates perimeter in the same units
     */
    public static double calcPerim(ObservableList<Vertex> vertexesOL){
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
