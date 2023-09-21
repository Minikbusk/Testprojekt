package ordinationstuderende.ordination;

import ordinationstuderende.storage.Storage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {
    @Test
    void DagligSkaev_StartDen_Er_SlutDen() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        LocalDate startDen = LocalDate.of(2023,9,19);
        LocalDate slutDen = LocalDate.of(2023,9,19);
//        Act
        DagligSkaev dagligSkaev = new DagligSkaev(startDen, slutDen, patient);

//        Assert
        assertEquals(startDen,dagligSkaev.getStartDen());
        assertEquals(slutDen,dagligSkaev.getSlutDen());
        assertEquals(patient,dagligSkaev.getPatient());
    }
    @Test
    void DagligSkaev_SlutDen_Er_Efter_StartDen() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        LocalDate startDen = LocalDate.of(2023,9,20);
        LocalDate slutDen = LocalDate.of(2023,9,30);
//        Act
        DagligSkaev dagligSkaev = new DagligSkaev(startDen, slutDen, patient);

//        Assert
        assertEquals(startDen,dagligSkaev.getStartDen());
        assertEquals(slutDen,dagligSkaev.getSlutDen());
        assertEquals(patient,dagligSkaev.getPatient());
    }
    @Test
    void opretDosis_Tid0000_Antal0() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        DagligSkaev dagligSkaev = new DagligSkaev(LocalDate.of(2023,9,9), LocalDate.of(2023,9,10), patient);
        LocalTime tid = LocalTime.of(00,00);
        double antal = 0;
//        Act
        dagligSkaev.opretDosis(tid, antal);
//        Assert
        assertTrue(dagligSkaev.getDoser().size() == 1);
        assertTrue(dagligSkaev.getDoser().get(0).getAntal() == 0);
    }

    @Test
    void opretDosis_Tid2359_Antal1() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        DagligSkaev dagligSkaev = new DagligSkaev(LocalDate.of(2023,9,9), LocalDate.of(2023,9,10), patient);
        LocalTime tid = LocalTime.of(23,59);
        double antal = 1;
//        Act
        dagligSkaev.opretDosis(tid, antal);
//        Assert
        assertTrue(dagligSkaev.getDoser().get(0).getAntal() == 1);
    }

    @Test
    void opretDosis_Tid1200_Antal100() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        DagligSkaev dagligSkaev = new DagligSkaev(LocalDate.of(2023,9,9), LocalDate.of(2023,9,10), patient);
        LocalTime tid = LocalTime.of(12,00);
        double antal = 100;
//        Act
        dagligSkaev.opretDosis(tid, antal);
//        Assert
        assertTrue(dagligSkaev.getDoser().get(0).getAntal() == 100);
    }

    @Test
    void samletDosis_10Dage_8Dosis() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        LocalDate startDen = LocalDate.of(2023,9,9);
        LocalDate slutDen = startDen.plusDays(9);
        DagligSkaev dagligSkaev = new DagligSkaev(startDen, slutDen, patient);
        for (int i = 0; i < 8; i++) {
            dagligSkaev.opretDosis(LocalTime.of(00,0+i),1);
        }
//        Act
        double samletDosis = dagligSkaev.samletDosis();
//        Assert
        assertEquals(80.0,samletDosis);
    }
    @Test
    void samletDosis_1Dage_8Dosis() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        LocalDate startDen = LocalDate.of(2023,9,9);
        LocalDate slutDen = startDen;
        DagligSkaev dagligSkaev = new DagligSkaev(startDen, slutDen, patient);
        for (int i = 0; i < 8; i++) {
            dagligSkaev.opretDosis(LocalTime.of(00,0+i),1);
        }
//        Act
        double samletDosis = dagligSkaev.samletDosis();
//        Assert
        assertEquals(8.0,samletDosis);
    }
    @Test
    void samletDosis_0Dage_4Dosis() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        LocalDate startDen = LocalDate.of(2023,9,9);
        LocalDate slutDen = startDen.minusDays(1);
        DagligSkaev dagligSkaev = new DagligSkaev(startDen, slutDen, patient);
        for (int i = 0; i < 8; i++) {
            dagligSkaev.opretDosis(LocalTime.of(00,0+i),1);
        }
//        Act
        double samletDosis = dagligSkaev.samletDosis();
//        Assert
        assertEquals(0.0,samletDosis);
    }

    @Test
    void doegnDosis_Doser_4_3_2_4() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        DagligSkaev dagligSkaev = new DagligSkaev(LocalDate.of(2023,9,9), LocalDate.of(2023,9,10), patient);
        dagligSkaev.opretDosis(LocalTime.of(00,00),4);
        dagligSkaev.opretDosis(LocalTime.of(00,00),3);
        dagligSkaev.opretDosis(LocalTime.of(00,00),2);
        dagligSkaev.opretDosis(LocalTime.of(00,00),4);

//        Act
        double doegnDosis = dagligSkaev.doegnDosis();
//        Assert
        assertEquals(13, doegnDosis);
    }

    @Test
    void doegnDosis_Doser_4() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        DagligSkaev dagligSkaev = new DagligSkaev(LocalDate.of(2023,9,9), LocalDate.of(2023,9,10), patient);
        dagligSkaev.opretDosis(LocalTime.of(00,00),4);

//        Act
        double doegnDosis = dagligSkaev.doegnDosis();
//        Assert
        assertEquals(4, doegnDosis);
    }
}