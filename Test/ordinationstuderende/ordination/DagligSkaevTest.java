package ordinationstuderende.ordination;

import ordinationstuderende.storage.Storage;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions.*;


class DagligSkaevTes {



    @Test
    @Order(1)
    void opretDosis() {
//        //Arrange
        Storage storage = new Storage();
        Patient patient = new Patient("000000-0000", "Gammel mand", 100.0);
        LocalTime tid = LocalTime.of(00, 00);
        double antal = 0;
        DagligSkaev dagligSkaev = new DagligSkaev(LocalDate.of(2023, 9, 21), LocalDate.of(2023, 9, 23), patient);
//        //Act
        Dosis dosis = new Dosis(tid, antal);
        dagligSkaev.opretDosis(tid, antal);
//        //Assert
        assertTrue(dagligSkaev.getDoser().size() == 1);
    }

    @Test
    @Order(2)
    void testSamletDosis() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    @Order(3)
    void testDoegnDosis() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    @Order(4)
    void testGetType() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    void getDoser() {
        //Arrange

        //Act

        //Assert
    }

}