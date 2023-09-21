package ordinationstuderende.ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {
    @Test
    void PN_StartDen_Er_SlutDen_Enheder_Er_0() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        LocalDate startDen = LocalDate.of(2023,9,19);
        LocalDate slutDen = LocalDate.of(2023,9,19);
        double antalEnheder = 0;
//        Act
        PN pn = new PN(startDen, slutDen, patient, antalEnheder);
//        Assert
        assertEquals(startDen,pn.getStartDen());
        assertEquals(slutDen,pn.getSlutDen());
        assertEquals(antalEnheder,pn.getAntalEnheder());
        assertEquals(patient,pn.getPatient());
    }
    @Test
    void PN__Enheder_Er_1() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        LocalDate startDen = LocalDate.of(2023,9,19);
        LocalDate slutDen = LocalDate.of(2023,9,20);
        double antalEnheder = 1;
//        Act
        PN pn = new PN(startDen, slutDen, patient, antalEnheder);
//        Assert
        assertEquals(startDen,pn.getStartDen());
        assertEquals(slutDen,pn.getSlutDen());
        assertEquals(antalEnheder,pn.getAntalEnheder());
        assertEquals(patient,pn.getPatient());
    }

    @Test
    void givDosis_GivesDen_er_StartDen() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        LocalDate startDen = LocalDate.of(2023,9,19);
        LocalDate slutDen = LocalDate.of(2023,9,21);
        double antalEnheder = 5;
        PN pn = new PN(startDen, slutDen, patient, antalEnheder);;
        LocalDate givesDen = LocalDate.of(2023,9,19);
//        Act
        boolean dosisGivet = pn.givDosis(givesDen);
//        Assert
        assertEquals(1,pn.getAntalGangeGivet());
    }
    @Test
    void givDosis_GivesDen_er_SlutDen() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        LocalDate startDen = LocalDate.of(2023,9,19);
        LocalDate slutDen = LocalDate.of(2023,9,21);
        double antalEnheder = 5;
        PN pn = new PN(startDen, slutDen, patient, antalEnheder);;
        LocalDate givesDen = LocalDate.of(2023,9,21);
//        Act
        pn.givDosis(givesDen);
//        Assert
        assertEquals(1,pn.getAntalGangeGivet());
    }
    @Test
    void givDosis_GivesDen_er_Indenfor_StartOgSlut() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        LocalDate startDen = LocalDate.of(2023,9,19);
        LocalDate slutDen = LocalDate.of(2023,9,21);
        double antalEnheder = 5;
        PN pn = new PN(startDen, slutDen, patient, antalEnheder);;
        LocalDate givesDen = LocalDate.of(2023,9,20);
//        Act
        pn.givDosis(givesDen);
//        Assert
        assertEquals(1,pn.getAntalGangeGivet());
    }
    @Test
    void givDosis_GivesDen_er_Udenfor_StartOgSlut() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        LocalDate startDen = LocalDate.of(2023,9,19);
        LocalDate slutDen = LocalDate.of(2023,9,21);
        double antalEnheder = 5;
        PN pn = new PN(startDen, slutDen, patient, antalEnheder);;
        LocalDate givesDen = LocalDate.of(2023,9,17);
//        Act
        pn.givDosis(givesDen);
//        Assert
        assertEquals(0,pn.getAntalGangeGivet());
    }

    @Test
    void doegnDosis_90Samlet_2Dage() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        LocalDate startDen = LocalDate.of(2023,9,10);
        LocalDate slutDen = LocalDate.of(2023,9,11);
        double antalEnheder = 10;
        PN pn = new PN(startDen, slutDen, patient, antalEnheder);
        pn.givDosis(LocalDate.of(2023,9,10));
        pn.givDosis(LocalDate.of(2023,9,10));
        pn.givDosis(LocalDate.of(2023,9,10));
        pn.givDosis(LocalDate.of(2023,9,10));
        pn.givDosis(LocalDate.of(2023,9,11));
        pn.givDosis(LocalDate.of(2023,9,11));
        pn.givDosis(LocalDate.of(2023,9,11));
        pn.givDosis(LocalDate.of(2023,9,11));
        pn.givDosis(LocalDate.of(2023,9,11));
//        Act
        double doegnDosis = pn.doegnDosis();
//        Assert
        assertEquals(45, doegnDosis);
    }
    @Test
    void doegnDosis_20Samlet_1Dage() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        LocalDate startDen = LocalDate.of(2023,9,10);
        LocalDate slutDen = LocalDate.of(2023,9,11);
        double antalEnheder = 10;
        PN pn = new PN(startDen, slutDen, patient, antalEnheder);
        pn.givDosis(LocalDate.of(2023,9,11));
        pn.givDosis(LocalDate.of(2023,9,11));
//        Act
        double doegnDosis = pn.doegnDosis();
//        Assert
        assertEquals(20, doegnDosis);
    }

    @Test
    void samletDosis_1Dosis_1Enhed() {
//        Arrange
            Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
            LocalDate startDen = LocalDate.of(2023,9,19);
            LocalDate slutDen = LocalDate.of(2023,9,20);
            double antalEnheder = 1;
            PN pn = new PN(startDen, slutDen, patient, antalEnheder);
        pn.givDosis(LocalDate.of(2023,9,19));
//        Act
            double samletDosis = pn.samletDosis();
//        Assert
            assertEquals(1, samletDosis);
    }
    @Test
    void samletDosis_3Dosis_9Enhed() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        LocalDate startDen = LocalDate.of(2023,9,19);
        LocalDate slutDen = LocalDate.of(2023,9,20);
        double antalEnheder = 9;
        PN pn = new PN(startDen, slutDen, patient, antalEnheder);
        pn.givDosis(LocalDate.of(2023,9,19));
        pn.givDosis(LocalDate.of(2023,9,19));
        pn.givDosis(LocalDate.of(2023,9,19));
//        Act
        double samletDosis = pn.samletDosis();
//        Assert
        assertEquals(27, samletDosis);
    }
    @Test
    void samletDosis_0Dosis_4Enhed() {
//        Arrange
        Patient patient = new Patient("123456-7890","Fornavn Efternavn",50.0);
        LocalDate startDen = LocalDate.of(2023,9,19);
        LocalDate slutDen = LocalDate.of(2023,9,20);
        double antalEnheder = 9;
        PN pn = new PN(startDen, slutDen, patient, antalEnheder);
//        Act
        double samletDosis = pn.samletDosis();
//        Assert
        assertEquals(0, samletDosis);
    }
}