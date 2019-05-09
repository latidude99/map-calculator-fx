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

import com.latidude99.mapcalculatorfx.AreaAndPerimeter;
import com.latidude99.mapcalculatorfx.Vertex;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;


import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AreaAndPerimeterTests {
    static ObservableList<Vertex> vertexes;

    double expectedArea = 1828738.5;
    double expectedPerimeter = 6660.4;

    @BeforeAll
    public static void initAll(){
        vertexes = FXCollections.observableArrayList();
        vertexes.add(new Vertex(1,527907, 180981));
        vertexes.add(new Vertex(1,528017, 180663));
        vertexes.add(new Vertex(1,528360, 180202));
        vertexes.add(new Vertex(1,528350, 179990));
        vertexes.add(new Vertex(1,525680, 180096));
        vertexes.add(new Vertex(1,525770, 180121));
        vertexes.add(new Vertex(1,525645, 180491));
        vertexes.add(new Vertex(1,525735, 180511));
        vertexes.add(new Vertex(1,525719, 180568));
        vertexes.add(new Vertex(1,526229, 180636));
        vertexes.add(new Vertex(1,526924, 180814));
        vertexes.add(new Vertex(1,527828, 180977));
    }

    @AfterAll
    public static void tearDownAll(){
        vertexes = null;
    }


    @Test
    @DisplayName("Check polygon area & perimeter calculations")
    public void calcAreaAndPerimeter(){
        double actualArea = AreaAndPerimeter.calcArea(vertexes);
        double actualPerimeter = AreaAndPerimeter.calcPerim(vertexes);
        double actualAreaRounded = new BigDecimal(actualArea).setScale(1,
                RoundingMode.HALF_UP).doubleValue();
        double actualPerimeterRounded = new BigDecimal(actualPerimeter).setScale(1,
                RoundingMode.HALF_UP).doubleValue();

        assertEquals(expectedArea,  actualAreaRounded, "incorrect area");
        assertEquals(expectedPerimeter, actualPerimeterRounded, "incorrect Perimeter");
    }


}
