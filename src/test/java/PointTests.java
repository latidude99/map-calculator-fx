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

import com.latidude99.mapcalculatorfx.Point;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointTests {
    Point point;

    @BeforeAll
    public static void initAll(){

    }

    @AfterAll
    public static void tearDownAll(){

    }


    @Test
    @DisplayName("Convert OS Easting/Northing to Latitude/Longitude")
    public void setLatLonFromEastNorth_Test(){
        double expectedLatitude = 51.1497357112;
        double expectedLongitude = -2.7572295964;

        double easting = 347131;
        double northing = 139214;

        point = new Point();
        point.setEasting(easting);
        point.setNorthing(northing);

        point.setLatLonFromEastNorth();

        double actualLatitude = point.getLatDegreesDecDeg();
        double actualLongitude = point.getLonDegreesDecDeg();
        double actualLatitudeRounded = new BigDecimal(actualLatitude).setScale(
                10, RoundingMode.HALF_UP).doubleValue();
        double actualLongitudeRounded = new BigDecimal(actualLongitude).setScale(
                10, RoundingMode.HALF_UP).doubleValue();

        assertEquals(expectedLatitude, actualLatitudeRounded, "incorrect Latitude");
        assertEquals(expectedLongitude, actualLongitudeRounded, "incorrect Longitude");
    }

    @Test
    @DisplayName("Convert OS Latitude/Longitude to Easting/Northing")
    public void setEastNorthFromLatLon_Test(){
        double expectedEasting = 347131;
        double expectedNorthing = 139214;

        double latitude = 51.1497357112;
        double longitude = -2.7572295964;

        point = new Point();
        point.setLatDegreesDecDeg(latitude);
        point.setLonDegreesDecDeg(longitude);

        point.setEastNorthFromLatLon();

        double actualEasting = point.getEasting();
        double actualNorthing = point.getNorthing();
        double actualEastingRounded = new BigDecimal(actualEasting).setScale(
                0, RoundingMode.HALF_UP).doubleValue();
        double actualNorthingRounded = new BigDecimal(actualNorthing).setScale(
                0, RoundingMode.HALF_UP).doubleValue();

        assertEquals(expectedEasting, actualEastingRounded, "incorrect Easting");
        assertEquals(expectedNorthing, actualNorthingRounded, "incorrect Northing");
    }

}
