import ordinationstuderende.controller.Controller;
import ordinationstuderende.ordination.*;
import ordinationstuderende.storage.Storage;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {

    @Test
    @Order(1)
    void opretDosisTid0600() {
        //Arrange
        Storage storage = new Storage();
        Patient patient = new Patient("000000-0000", "Gammel mand", 100.0);
        LocalTime tid = LocalTime.of(06, 00);
        double antal = 1;
        DagligFast dagligFast = new DagligFast(LocalDate.of(2023, 9, 21), LocalDate.of(2023, 9, 23), patient);
        //Act
        dagligFast.opretDosis(tid, antal);
        //Assert
        assertTrue(dagligFast.getDoser()[0].getAntal() == 1);
    }
    @Test
    @Order(2)
    void opretDosisTid1200() {
        //Arrange
        Storage storage = new Storage();
        Patient patient = new Patient("000000-0000", "Gammel mand", 100.0);
        LocalTime tid = LocalTime.of(12, 00);
        double antal = 1;
        DagligFast dagligFast = new DagligFast(LocalDate.of(2023, 9, 21), LocalDate.of(2023, 9, 23), patient);
        //Act
        dagligFast.opretDosis(tid, antal);
        //Assert
        assertTrue(dagligFast.getDoser()[1].getAntal() == 1);
    }
    @Test
    @Order(3)
    void opretDosisTid1800() {
        //Arrange
        Storage storage = new Storage();
        Patient patient = new Patient("000000-0000", "Gammel mand", 100.0);
        LocalTime tid = LocalTime.of(18, 00);
        double antal = 1;
        DagligFast dagligFast = new DagligFast(LocalDate.of(2023, 9, 21), LocalDate.of(2023, 9, 23), patient);
        //Act
        dagligFast.opretDosis(tid, antal);
        //Assert
        assertTrue(dagligFast.getDoser()[2].getAntal() == 1);
    }
    @Test
    @Order(4)
    void opretDosisTid0000() {
        //Arrange
        Storage storage = new Storage();
        Patient patient = new Patient("000000-0000", "Gammel mand", 100.0);
        LocalTime tid = LocalTime.of(00, 00);
        double antal = 1;
        DagligFast dagligFast = new DagligFast(LocalDate.of(2023, 9, 21), LocalDate.of(2023, 9, 23), patient);
        //Act
        dagligFast.opretDosis(tid, antal);
        //Assert
        assertTrue(dagligFast.getDoser()[3].getAntal() == 1);
    }

    @Test
    @Order(5)
    void samletDosis5Doegn() {
        //Arrange
        Patient patient = new Patient("000000-0000", "Gammel dame", 75);
        LocalDate startDen = LocalDate.of(2023, 9, 21);
        LocalDate slutDen = startDen.plusDays(5);
        DagligFast dagligFast = new DagligFast(startDen, slutDen, patient);
        double forventetAntalDosis = 20;
        double faktiskAntalDosis = 0;

        //Act
            while (faktiskAntalDosis < 20) {
                int k = 6;
                dagligFast.opretDosis(LocalTime.of(6 + k, 0), 1);
                faktiskAntalDosis++;
            }
        //Assert
        assertTrue(faktiskAntalDosis == forventetAntalDosis);

    }
    @Test
    @Order(6)
    void samletDosis1Doegn() {
        //Arrange
        Patient patient = new Patient("000000-0000", "Gammel dame", 75);
        LocalDate startDen = LocalDate.of(2023, 9, 21);
        LocalDate slutDen = startDen.plusDays(5);
        DagligFast dagligFast = new DagligFast(startDen, slutDen, patient);
        double forventetAntalDosis = 4;
        double faktiskAntalDosis = 0;

        //Act
        while (faktiskAntalDosis < 4) {
            int k = 6;
            dagligFast.opretDosis(LocalTime.of(6 + k, 0), 1);
            faktiskAntalDosis++;
        }
        //Assert
        assertTrue(faktiskAntalDosis == forventetAntalDosis);
    }
    @Test
    @Order(7)
    void samletDosis0Doegn() {
        //Arrange
        Patient patient = new Patient("000000-0000", "Gammel dame", 75);
        LocalDate startDen = LocalDate.of(2023, 9, 21);
        LocalDate slutDen = startDen.minusDays(1);
        DagligFast dagligFast = new DagligFast(startDen, slutDen, patient);
        double forventetAntalDosis = 0;
        double faktiskAntalDosis = 0;

        //Act
        while (faktiskAntalDosis < 0) {
            int k = 6;
            dagligFast.opretDosis(LocalTime.of(6 + k, 0), 1);
            faktiskAntalDosis++;
        }
        //Assert
        assertTrue(faktiskAntalDosis == forventetAntalDosis);
    }

    @Test
    @Order(8)
    void doegnDosis14() {
        //Assign
        Patient patient = new Patient("000000-0000", "Gammel dame", 75);
        DagligFast dagligFast = new DagligFast(LocalDate.of(2023, 9, 21), LocalDate.of(2023, 9, 21), patient);
        LocalTime tid = LocalTime.of(0,0);
        double forventetAntal = 14;
        //Act
        dagligFast.opretDosis(LocalTime.of(06, 00), 2);
        dagligFast.opretDosis(LocalTime.of(12, 00), 3);
        dagligFast.opretDosis(LocalTime.of(18, 00), 4);
        dagligFast.opretDosis(LocalTime.of(00, 00), 5);
        double doegnDosis = dagligFast.doegnDosis();
        //Assert
        assertTrue(forventetAntal == dagligFast.doegnDosis());
    }
    @Test
    @Order(9)
    void doegnDosis4() {
        //Assign
        Patient patient = new Patient("000000-0000", "Gammel dame", 75);
        DagligFast dagligFast = new DagligFast(LocalDate.of(2023, 9, 21), LocalDate.of(2023, 9, 21), patient);
        LocalTime tid = LocalTime.of(0,0);
        double forventetAntal = 4;
        //Act
        dagligFast.opretDosis(LocalTime.of(06, 00), 4);
        dagligFast.opretDosis(LocalTime.of(12, 00), 0);
        dagligFast.opretDosis(LocalTime.of(18, 00), 0);
        dagligFast.opretDosis(LocalTime.of(00, 00), 0);
        double doegnDosis = dagligFast.doegnDosis();
        //Assert
        assertTrue(forventetAntal == dagligFast.doegnDosis());
    }
}