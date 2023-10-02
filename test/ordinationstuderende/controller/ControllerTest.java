package ordinationstuderende.controller;

import ordinationstuderende.ordination.*;
import ordinationstuderende.storage.Storage;
import org.junit.jupiter.api.Test;

import java.beans.Expression;
import java.rmi.server.ExportException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void opretPNOrdination_1AntalEnheder() {
//        Arrange
        Controller controller = Controller.getController();
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 50.0);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        LocalDate startDen = LocalDate.of(2023, 9, 9);
        LocalDate slutDen = LocalDate.of(2023, 9, 11);
        double antalEnheder = 1;
//        Act
        PN pn = controller.opretPNOrdination(startDen, slutDen, patient, laegemiddel, antalEnheder);
//        Assert
        assertEquals(startDen, pn.getStartDen());
        assertEquals(slutDen, pn.getSlutDen());
        assertEquals(antalEnheder, pn.getAntalEnheder());
        assertEquals(patient, pn.getPatient());
    }

    @Test
    void opretPNOrdination_0AntalEnheder() {
//        Arrange
        Controller controller = Controller.getController();
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 50.0);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        LocalDate startDen = LocalDate.of(2023, 9, 9);
        LocalDate slutDen = LocalDate.of(2023, 9, 11);
        double antal = 0;
//        Act
        PN pn = controller.opretPNOrdination(startDen, slutDen, patient, laegemiddel, antal);
//        Assert
        assertEquals(startDen, pn.getStartDen());
        assertEquals(slutDen, pn.getSlutDen());
        assertEquals(antal, pn.getAntalEnheder());
        assertEquals(patient, pn.getPatient());
    }

    @Test
    void opretPNOrdination_MinusAntalEnheder() {
//        Arrange
        Controller controller = Controller.getController();
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 50.0);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        LocalDate startDen = LocalDate.of(2023, 9, 9);
        LocalDate slutDen = LocalDate.of(2023, 9, 11);
        double antal = -1;
//        Act & Assert
        IllegalArgumentException expression = assertThrows(IllegalArgumentException.class, () ->
        {
            controller.opretPNOrdination(startDen, slutDen, patient, laegemiddel, antal);
        });
        assertEquals(expression.getMessage(), "Fejl i input!");
    }

    @Test
    void opretDagligFastOrdination_Doser_1_1_1_1() {
//        Arrange
        Controller controller = Controller.getController();
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 50.0);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        LocalDate startDen = LocalDate.of(2023, 9, 9);
        LocalDate slutDen = LocalDate.of(2023, 9, 11);
        double morgenAntal = 1;
        double middagAntal = 1;
        double aftenAntal = 1;
        double natAntal = 1;
//        Act
        DagligFast dagligFast = controller.opretDagligFastOrdination(startDen, slutDen, patient, laegemiddel, morgenAntal, middagAntal, aftenAntal, natAntal);
//        Assert
        assertEquals(startDen, dagligFast.getStartDen());
        assertEquals(slutDen, dagligFast.getSlutDen());
        assertEquals(patient, dagligFast.getPatient());
        assertEquals(laegemiddel, dagligFast.getLaegemiddel());
        assertEquals(1, dagligFast.getDoser()[0].getAntal());
        assertEquals(1, dagligFast.getDoser()[1].getAntal());
        assertEquals(1, dagligFast.getDoser()[2].getAntal());
        assertEquals(1, dagligFast.getDoser()[3].getAntal());
    }

    @Test
    void opretDagligFastOrdination_Doser_1_0_0_1() {
//        Arrange
        Controller controller = Controller.getController();
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 50.0);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        LocalDate startDen = LocalDate.of(2023, 9, 9);
        LocalDate slutDen = LocalDate.of(2023, 9, 11);
        double morgenAntal = 1;
        double middagAntal = 0;
        double aftenAntal = 0;
        double natAntal = 1;
//        Act
        DagligFast dagligFast = controller.opretDagligFastOrdination(startDen, slutDen, patient, laegemiddel, morgenAntal, middagAntal, aftenAntal, natAntal);
//        Assert
        assertEquals(startDen, dagligFast.getStartDen());
        assertEquals(slutDen, dagligFast.getSlutDen());
        assertEquals(patient, dagligFast.getPatient());
        assertEquals(laegemiddel, dagligFast.getLaegemiddel());
        assertEquals(1, dagligFast.getDoser()[0].getAntal());
        assertEquals(0, dagligFast.getDoser()[1].getAntal());
        assertEquals(0, dagligFast.getDoser()[2].getAntal());
        assertEquals(1, dagligFast.getDoser()[3].getAntal());
    }

    @Test
    void opretDagligFastOrdination_Doser_0_1_1_0() {
//        Arrange
        Controller controller = Controller.getController();
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 50.0);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        LocalDate startDen = LocalDate.of(2023, 9, 9);
        LocalDate slutDen = LocalDate.of(2023, 9, 11);
        double morgenAntal = 0;
        double middagAntal = 1;
        double aftenAntal = 1;
        double natAntal = 0;
//        Act
        DagligFast dagligFast = controller.opretDagligFastOrdination(startDen, slutDen, patient, laegemiddel, morgenAntal, middagAntal, aftenAntal, natAntal);
//        Assert
        assertEquals(startDen, dagligFast.getStartDen());
        assertEquals(slutDen, dagligFast.getSlutDen());
        assertEquals(patient, dagligFast.getPatient());
        assertEquals(laegemiddel, dagligFast.getLaegemiddel());
        assertEquals(0, dagligFast.getDoser()[0].getAntal());
        assertEquals(1, dagligFast.getDoser()[1].getAntal());
        assertEquals(1, dagligFast.getDoser()[2].getAntal());
        assertEquals(0, dagligFast.getDoser()[3].getAntal());
    }

    @Test
    void opretDagligFastOrdination_Doser_0_0_0_0() {
//        Arrange
        Controller controller = Controller.getController();
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 50.0);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        LocalDate startDen = LocalDate.of(2023, 9, 9);
        LocalDate slutDen = LocalDate.of(2023, 9, 11);
        double morgenAntal = 0;
        double middagAntal = 0;
        double aftenAntal = 0;
        double natAntal = 0;
//        Act & Assert
        IllegalArgumentException expression = assertThrows(IllegalArgumentException.class, () ->
        {
            controller.opretDagligFastOrdination(startDen, slutDen, patient, laegemiddel, morgenAntal, middagAntal, aftenAntal, natAntal);
        });
        assertEquals(expression.getMessage(), "Fejl i input!");
    }

    @Test
    void opretDagligSkaevOrdination_Arraystoerelse1() {
//        Arrange
        Controller controller = Controller.getController();
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 50.0);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        LocalDate startDen = LocalDate.of(2023, 9, 9);
        LocalDate slutDen = LocalDate.of(2023, 9, 11);
        LocalTime[] klokkeslet = {LocalTime.of(12, 00)};
        double[] antalEnheder = {3};
//        Act
        DagligSkaev dagligSkaev = controller.opretDagligSkaevOrdination(startDen, slutDen, patient, laegemiddel, klokkeslet, antalEnheder);
//        Assert
        assertEquals(startDen, dagligSkaev.getStartDen());
        assertEquals(slutDen, dagligSkaev.getSlutDen());
        assertEquals(patient, dagligSkaev.getPatient());
        assertEquals(laegemiddel, dagligSkaev.getLaegemiddel());
        assertEquals(1, dagligSkaev.getDoser().size());
    }

    @Test
    void opretDagligSkaevOrdination_Arraystoerelse2() {
//        Arrange
        Controller controller = Controller.getController();
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 50.0);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        LocalDate startDen = LocalDate.of(2023, 9, 9);
        LocalDate slutDen = LocalDate.of(2023, 9, 11);
        LocalTime[] klokkeslet = {LocalTime.of(12, 00), LocalTime.of(14, 00)};
        double[] antalEnheder = {3, 2};
//        Act
        DagligSkaev dagligSkaev = controller.opretDagligSkaevOrdination(startDen, slutDen, patient, laegemiddel, klokkeslet, antalEnheder);
//        Assert
        assertEquals(startDen, dagligSkaev.getStartDen());
        assertEquals(slutDen, dagligSkaev.getSlutDen());
        assertEquals(patient, dagligSkaev.getPatient());
        assertEquals(laegemiddel, dagligSkaev.getLaegemiddel());
        assertEquals(2, dagligSkaev.getDoser().size());
    }

    @Test
    void opretDagligSkaevOrdination_Arraystoerelse_1_og_2() {
//        Arrange
        Controller controller = Controller.getController();
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 50.0);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        LocalDate startDen = LocalDate.of(2023, 9, 9);
        LocalDate slutDen = LocalDate.of(2023, 9, 11);
        LocalTime[] klokkeslet = {LocalTime.of(12, 00)};
        double[] antalEnheder = {3, 2};
//        Act & Assert
        IllegalArgumentException expression = assertThrows(IllegalArgumentException.class, () ->
        {
            DagligSkaev dagligSkaev = controller.opretDagligSkaevOrdination(startDen, slutDen, patient, laegemiddel, klokkeslet, antalEnheder);
        });
        assertEquals(expression.getMessage(), "Fejl i input!");
    }

    @Test
    void opretDagligSkaevOrdination_Arraystoerelse_2_og_1() {
//        Arrange
        Controller controller = Controller.getController();
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 50.0);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        LocalDate startDen = LocalDate.of(2023, 9, 9);
        LocalDate slutDen = LocalDate.of(2023, 9, 11);
        LocalTime[] klokkeslet = {LocalTime.of(12, 00), LocalTime.of(14, 00)};
        double[] antalEnheder = {3};
//        Act & Assert
        IllegalArgumentException expression = assertThrows(IllegalArgumentException.class, () ->
        {
            DagligSkaev dagligSkaev = controller.opretDagligSkaevOrdination(startDen, slutDen, patient, laegemiddel, klokkeslet, antalEnheder);
        });
        assertEquals(expression.getMessage(), "Fejl i input!");
    }

    @Test
    void ordinationPNAnvendtDato1() {
        //Arrange
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 50.0);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        LocalDate startDato = LocalDate.of(2023, 9, 9);
        LocalDate slutDato = LocalDate.of(2023, 9, 11);
        Controller controller = Controller.getController();
        double antalEnheder = 1;
        PN pn = new PN(startDato, slutDato, patient, antalEnheder);

        //Act
        controller.ordinationPNAnvendt(pn, LocalDate.of(2023, 9, 10));

        //Assert
        assertTrue(pn.getDatoer().size() == 1);

    }
    @Test
    void ordinationPNAnvendtDato2() {
        //Arrange
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 50.0);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        LocalDate startDato = LocalDate.of(2023, 9, 9);
        LocalDate slutDato = LocalDate.of(2023, 9, 11);
        Controller controller = Controller.getController();
        double antalEnheder = 1;
        PN pn = new PN(startDato, slutDato, patient, antalEnheder);
        ArrayList<LocalDate> datoer = new ArrayList<>();

        //Act
        controller.ordinationPNAnvendt(pn, LocalDate.of(2023, 9, 9));


        //Assert
        assertTrue(pn.getDatoer().size() == 1);

    }
    @Test
    void ordinationPNAnvendtDato3() {
        //Arrange
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 50.0);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        LocalDate startDato = LocalDate.of(2023, 9, 9);
        LocalDate slutDato = LocalDate.of(2023, 9, 11);
        Controller controller = Controller.getController();
        double antalEnheder = 1;
        PN pn = new PN(startDato, slutDato, patient, antalEnheder);
        ArrayList<LocalDate> datoer = new ArrayList<>();

        //Act
        controller.ordinationPNAnvendt(pn, LocalDate.of(2023, 9, 11));


        //Assert
        assertTrue(pn.getDatoer().size() == 1);

    }
    @Test
    void ordinationPNAnvendtDato4_UgyldigDato() {
        //Arrange
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 50.0);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        LocalDate startDato = LocalDate.of(2023, 9, 9);
        LocalDate slutDato = LocalDate.of(2023, 9, 11);
        Controller controller = Controller.getController();
        double antalEnheder = 1;
        PN pn = new PN(startDato, slutDato, patient, antalEnheder);
        ArrayList<LocalDate> datoer = new ArrayList<>();

        //Act
        controller.ordinationPNAnvendt(pn, LocalDate.of(2023, 9, 8));


        //Assert
        assertTrue(pn.getDatoer().isEmpty());

    }

    @Test
    void anbefaletDosisPrDoegn24kg() {
        //Arrange
        Patient patient = new Patient("123456-7899", "Gammel mand", 24);
        Controller controller = Controller.getController();
        Storage storage = new Storage();
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        storage.addLaegemiddel(laegemiddel);
        double forventetAnbefaletDosis;
        double faktiskAnbefaletDosis;
        //Act
        forventetAnbefaletDosis = 2.4;
        faktiskAnbefaletDosis = controller.anbefaletDosisPrDoegn(patient, storage.getAllLaegemidler().get(0));
        //Assert
        assertEquals(forventetAnbefaletDosis, faktiskAnbefaletDosis, 0.001);
    }
    @Test
    void anbefaletDosisPrDoegn25kg() {
        //Arrange
        Patient patient = new Patient("123456-7899", "Gammel mand", 25);
        Controller controller = Controller.getController();
        Storage storage = new Storage();
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        storage.addLaegemiddel(laegemiddel);
        double forventetAnbefaletDosis;
        double faktiskAnbefaletDosis;
        //Act
        forventetAnbefaletDosis = 3.75;
        faktiskAnbefaletDosis = controller.anbefaletDosisPrDoegn(patient, storage.getAllLaegemidler().get(0));
        //Assert
        assertEquals(forventetAnbefaletDosis, faktiskAnbefaletDosis, 0.001);
    }
    @Test
    void anbefaletDosisPrDoegn26kg() {
        //Arrange
        Patient patient = new Patient("123456-7899", "Gammel mand", 26);
        Controller controller = Controller.getController();
        Storage storage = new Storage();
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        storage.addLaegemiddel(laegemiddel);
        double forventetAnbefaletDosis;
        double faktiskAnbefaletDosis;
        //Act
        forventetAnbefaletDosis = 3.9;
        faktiskAnbefaletDosis = controller.anbefaletDosisPrDoegn(patient, storage.getAllLaegemidler().get(0));
        //Assert
        assertEquals(forventetAnbefaletDosis, faktiskAnbefaletDosis, 0.001);
    }
    @Test
    void anbefaletDosisPrDoegn120kg() {
        //Arrange
        Patient patient = new Patient("123456-7899", "Gammel mand", 120);
        Controller controller = Controller.getController();
        Storage storage = new Storage();
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        storage.addLaegemiddel(laegemiddel);
        double forventetAnbefaletDosis;
        double faktiskAnbefaletDosis;
        //Act
        forventetAnbefaletDosis = 19.2;
        faktiskAnbefaletDosis = controller.anbefaletDosisPrDoegn(patient, storage.getAllLaegemidler().get(0));
        //Assert
        assertEquals(forventetAnbefaletDosis, faktiskAnbefaletDosis, 0.001);
    }
    @Test
    void anbefaletDosisPrDoegn121kg() {
        //Arrange
        Patient patient = new Patient("123456-7899", "Gammel mand", 121);
        Controller controller = Controller.getController();
        Storage storage = new Storage();
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        storage.addLaegemiddel(laegemiddel);
        double forventetAnbefaletDosis;
        double faktiskAnbefaletDosis;
        //Act
        forventetAnbefaletDosis = 19.36;
        faktiskAnbefaletDosis = controller.anbefaletDosisPrDoegn(patient, storage.getAllLaegemidler().get(0));
        //Assert
        assertEquals(forventetAnbefaletDosis, faktiskAnbefaletDosis, 0.001);
    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddel_TC1() {
        //Arrange
        Laegemiddel laegemiddel = new Laegemiddel("Saltsyre", 1.9, 2.9, 3.9, "Ml");
        Controller controller = Controller.getController();
        Storage storage = new Storage();
        storage.addLaegemiddel(laegemiddel);
        double forventetAntalOrdinationer;
        double faktiskAntalOrdinationer;
        double vægtStart = 50.0;
        double vægtSlut = 70.0;
        //Act
        forventetAntalOrdinationer = controller.antalOrdinationerPrVægtPrLægemiddel(vægtStart, vægtSlut, storage.getAllLaegemidler().get(0));

        //Assert
        assertTrue(controller.antalOrdinationerPrVægtPrLægemiddel(vægtStart, vægtSlut, storage.getAllLaegemidler().get(0)) == forventetAntalOrdinationer);
    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddel_TC2() {
        //Arrange
        Laegemiddel laegemiddel = new Laegemiddel("Saltsyre", 1.9, 2.9, 3.9, "Ml");
        Controller controller = Controller.getController();
        Storage storage = new Storage();
        storage.addLaegemiddel(laegemiddel);
        double forventetAntalOrdinationer;
        double faktiskAntalOrdinationer;
        double vægtStart = 50.0;
        double vægtSlut = 50.0;
        //Act
        forventetAntalOrdinationer = controller.antalOrdinationerPrVægtPrLægemiddel(vægtStart, vægtSlut, storage.getAllLaegemidler().get(0));

        //Assert
        assertTrue(controller.antalOrdinationerPrVægtPrLægemiddel(vægtStart, vægtSlut, storage.getAllLaegemidler().get(0)) == forventetAntalOrdinationer);
    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddel_UgyldigInput() {
        //Arrange
        Laegemiddel laegemiddel = new Laegemiddel("Saltsyre", 1.9, 2.9, 3.9, "Ml");
        Controller controller = Controller.getController();
        Storage storage = new Storage();
        storage.addLaegemiddel(laegemiddel);
        double forventetAntalOrdinationer;
        double faktiskAntalOrdinationer;
        double vægtStart = 70.0;
        double vægtSlut = 50.0;
        //Act
        forventetAntalOrdinationer = controller.antalOrdinationerPrVægtPrLægemiddel(vægtStart, vægtSlut, storage.getAllLaegemidler().get(0));

        //Assert
        assertTrue(controller.antalOrdinationerPrVægtPrLægemiddel(vægtStart, vægtSlut, storage.getAllLaegemidler().get(0)) == forventetAntalOrdinationer);
    }
}