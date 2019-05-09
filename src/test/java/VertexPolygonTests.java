/**Copyright (C) 2019 Piotr Czapik.
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

import com.latidude99.mapcalculatorfx.Vertex;
import com.latidude99.mapcalculatorfx.VertexPolygon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


/*
 * Checks importing vertexes to a table from a file,
 * formats:
 *  - no line numbers, space separated
 *  - line numbers, space separated
 *  - no line numbers, comma separated
 *  - line numbers, comma separated
 *
 * Tests use pre-defined files in the resource folder to test import form file to list
 * and JUnit5 tmpDir to test writing to a file and then reading back to a list.
 */
public class VertexPolygonTests {

    // EXPORT & IMPORT test section
    @TempDir
    static Path tempDir;
    static ObservableList<Vertex> vertexes;
    static ObservableList<Vertex> vertexesSavedFile;

    // IMPORT test section
    static ObservableList<Vertex> vertexesExistingFile;

    public static final String NO_NUM_SPACE =
            "src/test/resources/polygonTest_space_no_numbers.txt";
    public static final String WITH_NUM_SPACE =
            "src/test/resources/polygonTest_space_with_numbers.txt";
    public static final String NO_NUM_COMMA =
            "src/test/resources/polygonTest_comma_no_numbers.txt";
    public static final String WITH_NUM_COMMA =
            "src/test/resources/polygonTest_comma_with_numbers.txt";

    // both sections
    int expectedVertex7Number = 7;
    int expectedVertex7Easting = 527706;
    int expectedVertex7Northing = 179772;


    @BeforeAll
    public static void initAll(){
        vertexesSavedFile = FXCollections.observableArrayList();
        vertexes = FXCollections.observableArrayList();
        vertexes.add(new Vertex(1,527907, 180981));
        vertexes.add(new Vertex(2,528017, 180663));
        vertexes.add(new Vertex(3,528360,180202));
        vertexes.add(new Vertex(4,528350,179990));
        vertexes.add(new Vertex(5,528284,179888));
        vertexes.add(new Vertex(6,528198,179865));
        vertexes.add(new Vertex(7,527706,179772));
        vertexes.add(new Vertex(8,526804,179717));
        vertexes.add(new Vertex(9,526790,179673));
        vertexes.add(new Vertex(10,526597,179651));
    }

    @AfterAll
    public static void tearDownAll(){
        vertexesSavedFile = null;
        vertexesExistingFile = null;
    }

    @BeforeEach
    public void init(){
    }

    @AfterEach
    public void tearDown(){
    }

    /********************* IMPORT LIST FROM EXISTING FILE *********************/

    @Test
    @DisplayName("Read file format: no line numbers, space separated")
    public void importPolygonSinNum_space_Test(){
        File file = new File(NO_NUM_SPACE);
        try {
            vertexesExistingFile = VertexPolygon.importPolygonSinNum_space(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int actualVertex7Number = vertexesExistingFile.get(6).getNumber();
        int actualVertex7Easting = vertexesExistingFile.get(6).getEasting();
        int actualVertex7Northing = vertexesExistingFile.get(6).getNorthing();

        assertAll("vertexesExistingFile",
                () -> assertEquals(expectedVertex7Number, actualVertex7Number,
                        "incorrect vertex number"),
                () -> assertEquals(expectedVertex7Easting, actualVertex7Easting,
                "incorrect vertex easting"),
                () -> assertEquals(expectedVertex7Northing, actualVertex7Northing,
                        "incorrect vertex northing"));
    }

    @Test
    @DisplayName("Read file format: with line numbers, space separated")
    public void importPolygonWithNum_space_Test(){
        File file = new File(WITH_NUM_SPACE);
        try {
            vertexesExistingFile = VertexPolygon.importPolygonWithNum_space(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int actualVertex7Number = vertexesExistingFile.get(6).getNumber();
        int actualVertex7Easting = vertexesExistingFile.get(6).getEasting();
        int actualVertex7Northing = vertexesExistingFile.get(6).getNorthing();

        assertAll("vertexesExistingFile",
                () -> assertEquals(expectedVertex7Number, actualVertex7Number,
                        "incorrect vertex number"),
                () -> assertEquals(expectedVertex7Easting, actualVertex7Easting,
                        "incorrect vertex easting"),
                () -> assertEquals(expectedVertex7Northing, actualVertex7Northing,
                        "incorrect vertex northing"));
    }

    @Test
    @DisplayName("Read file format: no line numbers, comma separated")
    public void importPolygonSinNum_comma_Test(){
        File file = new File(NO_NUM_COMMA);
        try {
            vertexesExistingFile = VertexPolygon.importPolygonSinNum_comma(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int actualVertex7Number = vertexesExistingFile.get(6).getNumber();
        int actualVertex7Easting = vertexesExistingFile.get(6).getEasting();
        int actualVertex7Northing = vertexesExistingFile.get(6).getNorthing();

        assertAll("vertexesExistingFile",
                () -> assertEquals(expectedVertex7Number, actualVertex7Number,
                        "incorrect vertex number"),
                () -> assertEquals(expectedVertex7Easting, actualVertex7Easting,
                        "incorrect vertex easting"),
                () -> assertEquals(expectedVertex7Northing, actualVertex7Northing,
                        "incorrect vertex northing"));
    }

    @Test
    @DisplayName("Read file format: with line numbers, comma separated")
    public void importPolygonWithNum_comma_Test(){
        File file = new File(WITH_NUM_COMMA);
        try {
            vertexesExistingFile = VertexPolygon.importPolygonWithNum_comma(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int actualVertex7Number = vertexesExistingFile.get(6).getNumber();
        int actualVertex7Easting = vertexesExistingFile.get(6).getEasting();
        int actualVertex7Northing = vertexesExistingFile.get(6).getNorthing();

        assertAll("vertexesExistingFile",
                () -> assertEquals(expectedVertex7Number, actualVertex7Number,
                        "incorrect vertex number"),
                () -> assertEquals(expectedVertex7Easting, actualVertex7Easting,
                        "incorrect vertex easting"),
                () -> assertEquals(expectedVertex7Northing, actualVertex7Northing,
                        "incorrect vertex northing"));
    }


    /************************** EXPORT & IMPORT LIST **************************/

    @Test
    @DisplayName("List export & import: no line numbers, space separated")
    public void export_import_PolygonSinNum_space_Test(){

        // saving list to a JUnit5 temp file
        Path noNumSpace = tempDir.resolve("polygonTest_space_no_numbers.txt");
        File noNumSpaceFile = new File(noNumSpace.toString());
        try {
            VertexPolygon.exportPolygonSinNum_space(vertexes, noNumSpaceFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(vertexes.size());

        // reading from the JUnit5 temp file
        try {
            vertexesSavedFile = VertexPolygon.importPolygonSinNum_space(noNumSpaceFile);
        } catch (IOException e) {
            e.getMessage();
        }

        int actualVertex7Number = vertexesSavedFile.get(6).getNumber();
        int actualVertex7Easting = vertexesSavedFile.get(6).getEasting();
        int actualVertex7Northing = vertexesSavedFile.get(6).getNorthing();

        // expected values defined at the beginning of this class
        assertAll("vertexesSavedFile",
                () -> assertEquals(expectedVertex7Number, actualVertex7Number,
                        "incorrect vertex number"),
                () -> assertEquals(expectedVertex7Easting, actualVertex7Easting,
                        "incorrect vertex easting"),
                () -> assertEquals(expectedVertex7Northing, actualVertex7Northing,
                        "incorrect vertex northing"));
    }

    @Test
    @DisplayName("List export & import: with line numbers, space separated")
    public void export_import_PolygonWithNum_space_Test(){

        // saving list to a JUnit5 temp file
        Path numSpace = tempDir.resolve("polygonTest_space_with_numbers.txt");
        File numSpaceFile = new File(numSpace.toString());
        try {
            VertexPolygon.exportPolygonWithNum_space(vertexesSavedFile, numSpaceFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(vertexes.size());

        // reading from the JUnit5 temp file
        try {
            vertexesSavedFile = VertexPolygon.importPolygonWithNum_space(numSpaceFile);
        } catch (IOException e) {
            e.getMessage();
        }

        int actualVertex7Number = vertexesSavedFile.get(6).getNumber();
        int actualVertex7Easting = vertexesSavedFile.get(6).getEasting();
        int actualVertex7Northing = vertexesSavedFile.get(6).getNorthing();

        // expected values defined at the beginning of this class
        assertAll("vertexesSavedFile",
                () -> assertEquals(expectedVertex7Number, actualVertex7Number,
                        "incorrect vertex number"),
                () -> assertEquals(expectedVertex7Easting, actualVertex7Easting,
                        "incorrect vertex easting"),
                () -> assertEquals(expectedVertex7Northing, actualVertex7Northing,
                        "incorrect vertex northing"));
    }

    @Test
    @DisplayName("List export & import: no line numbers, comma separated")
    public void export_import_PolygonSinNum_comma_Test(){

        // saving list to a JUnit5 temp file
        Path noNumComma = tempDir.resolve("polygonTest_comma_no_numbers.txt");
        File noNumCommaFile = new File(noNumComma.toString());
        try {
            VertexPolygon.exportPolygonSinNum_comma(vertexesSavedFile, noNumCommaFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(vertexes.size());

        // reading from the JUnit5 temp file
        try {
            vertexesSavedFile = VertexPolygon.importPolygonSinNum_comma(noNumCommaFile);
        } catch (IOException e) {
            e.getMessage();
        }

        int actualVertex7Number = vertexesSavedFile.get(6).getNumber();
        int actualVertex7Easting = vertexesSavedFile.get(6).getEasting();
        int actualVertex7Northing = vertexesSavedFile.get(6).getNorthing();

        // expected values defined at the beginning of this class
        assertAll("vertexesSavedFile",
                () -> assertEquals(expectedVertex7Number, actualVertex7Number,
                        "incorrect vertex number"),
                () -> assertEquals(expectedVertex7Easting, actualVertex7Easting,
                        "incorrect vertex easting"),
                () -> assertEquals(expectedVertex7Northing, actualVertex7Northing,
                        "incorrect vertex northing"));
    }

    @Test
    @DisplayName("List export & import: with line numbers, comma separated")
    public void export_import_PolygonWithNum_comma_Test(){

        // saving list to a JUnit5 temp file
        Path numComma = tempDir.resolve("polygonTest_comma_with_numbers.txt");
        File numCommaFile = new File(numComma.toString());
        try {
            VertexPolygon.exportPolygonWithNum_comma(vertexesSavedFile, numCommaFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(vertexes.size());

        // reading from the JUnit5 temp file
        try {
            vertexesSavedFile = VertexPolygon.importPolygonWithNum_comma(numCommaFile);
        } catch (IOException e) {
            e.getMessage();
        }

        int actualVertex7Number = vertexesSavedFile.get(6).getNumber();
        int actualVertex7Easting = vertexesSavedFile.get(6).getEasting();
        int actualVertex7Northing = vertexesSavedFile.get(6).getNorthing();

        // expected values defined at the beginning of this class
        assertAll("vertexesSavedFile",
                () -> assertEquals(expectedVertex7Number, actualVertex7Number,
                        "incorrect vertex number"),
                () -> assertEquals(expectedVertex7Easting, actualVertex7Easting,
                        "incorrect vertex easting"),
                () -> assertEquals(expectedVertex7Northing, actualVertex7Northing,
                        "incorrect vertex northing"));
    }

}
