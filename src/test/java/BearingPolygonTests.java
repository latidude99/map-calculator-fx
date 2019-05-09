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

import com.latidude99.mapcalculatorfx.BearingPolygon;
import com.latidude99.mapcalculatorfx.Point;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BearingPolygonTests {
    Point pointLeft;
    Point pointRight;
    BearingPolygon bearingPolygon;
    double expected;
    double actualRaw;
    double actualRounded;

    @BeforeEach
    public void init(){
        bearingPolygon = new BearingPolygon();
        pointLeft = new Point();
        pointRight = new Point();

        pointLeft.setEasting(347131);
        pointLeft.setNorthing(139214);
        pointRight.setEasting(516592);
        pointRight.setNorthing(184356);

        pointLeft.setLatDegreesDecDeg(51.1497357112);
        pointLeft.setLonDegreesDecDeg(-2.7572295964);
        pointRight.setLatDegreesDecDeg(51.5460635583);
        pointRight.setLonDegreesDecDeg(-0.3199872630);
    }

    @AfterEach
    public void tearDown(){
        bearingPolygon = null;
        pointLeft = null;
        pointRight = null;
        expected = 0;
        actualRaw = 0;
        actualRounded = 0;
    }

    @Test
    @DisplayName("Distance between two points in metres using 12 digit OS reference")
    public void distLtoRFromOS_Test(){
        expected = 175370.55;
        actualRaw = bearingPolygon.distLtoRFromOS(pointLeft, pointRight);
        actualRounded = new BigDecimal(actualRaw).setScale(2,
                RoundingMode.HALF_UP).doubleValue();

        assertEquals(expected, actualRounded, "distance from OS ref error");
    }

    /*
     * Distance calculated from Latitude/Longitude will always be different than
     * calculated from cartesian coordinates (OS grid reference).
     * Calculations using Haversine formula, which assumes the earth is a sphere,
     * results with errors of up to about 0.5% of the actual distance.
     * This test checks if the error is within acceptable limit.
     */
    @Test
    @DisplayName("Distance between two points in metres using using Latitude and Longitude")
    public void distLtoRFromLatLon_Test(){
        expected = 175370.55;
        actualRaw = bearingPolygon.distLtoRFromLatLon(pointLeft, pointRight);
        actualRounded = new BigDecimal(actualRaw).setScale(2,
                RoundingMode.HALF_UP).doubleValue();

        double distanceError = expected - actualRounded;
        double distanceErrorModule =
                distanceError > 0 ? distanceError : distanceError * (-1);

        assertTrue(distanceErrorModule < 0.005 * actualRaw,
                "distance error from Lat/Lon > 0.005 * distance ");
    }

    @Test
    @DisplayName("Bearing from Left to Right Point, decimal degrees")
    public void bearingFromLtoR_Test(){
        expected = 74.5;

        actualRaw = bearingPolygon.bearingFromLtoR(pointLeft, pointRight);
        actualRounded = new BigDecimal(actualRaw).setScale(1,
                RoundingMode.HALF_UP).doubleValue();

        assertEquals(expected, actualRounded, "bearing calculation error");
    }



}
























