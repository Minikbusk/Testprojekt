package ordinationstuderende.ordination;

import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class PN  extends Ordination {

    private double antalEnheder;
    private final ArrayList<LocalDate> datoer = new ArrayList<>();

    public PN(LocalDate startDen, LocalDate slutDen, Patient patient, double antalEnheder) {
        super(startDen, slutDen, patient);
        this.antalEnheder = antalEnheder;
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
        boolean gyldig = false;
        if (givesDen.isEqual(getStartDen()) || givesDen.isAfter(getStartDen()) && givesDen.isBefore(getSlutDen()) || givesDen.isEqual(getSlutDen())) {
            datoer.add(givesDen);
            gyldig = true;
        }

        return gyldig;
    }

    public double doegnDosis() {
        double doegnDosis = samletDosis() / (ChronoUnit.DAYS.between(datoer.get(0), datoer.get(datoer.size())));
        return doegnDosis;
    }

    @Override
    public String getType() {
        return "PN";
    }


    public double samletDosis() {
        double samletDosis = getAntalGangeGivet() * antalEnheder;
        return samletDosis;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        return datoer.size();
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}
