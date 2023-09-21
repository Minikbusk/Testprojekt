package ordinationstuderende.controller;

import ordinationstuderende.ordination.DagligFast;
import ordinationstuderende.ordination.Laegemiddel;
import ordinationstuderende.ordination.Patient;
import ordinationstuderende.storage.Storage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void opretPNOrdination() {
    }

    @Test
    void opretDagligFastOrdination() {
    }

    @Test
    void opretDagligSkaevOrdination() {
        //Assign

        //Act

        //Assert

    }

    @Test
    void ordinationPNAnvendt() {
        //Assign

        //Act

        //Assert

    }

    @Test
    void anbefaletDosisPrDoegn() {
        //Assign

        //Act

        //Assert

    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddel() {
        //Assign
        Controller controller = Controller.getController();
        Storage storage = new Storage();
        Laegemiddel laegemiddel = new Laegemiddel("Saltsyre", 1.0, 1.5, 2.0,"Ml" );
        Laegemiddel havmåge = new Laegemiddel("Mågeklat", 1.9, 2.9, 3.9, "Tons");
        storage.addLaegemiddel(laegemiddel);
        storage.addLaegemiddel(havmåge);

        double vægtStart;
        double vægtSlut;
        double forventetAntalOrdinationer;
        double faktiskAntalOrdinationer;

        //Act
         faktiskAntalOrdinationer = controller.antalOrdinationerPrVægtPrLægemiddel(50.0, 70.0, havmåge);

        //Assert
        System.out.println(faktiskAntalOrdinationer);
    }
}