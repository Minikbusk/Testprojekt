package ordinationstuderende.ordination;

import java.time.LocalDate;
import java.time.LocalTime;

public class DagligFast extends Ordination{
    public DagligFast(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
    }

    private final Dosis[] doser = new Dosis[4];

    public Dosis[] getDoser() {
        return doser;
    }
    public Dosis opretDosis(LocalTime tid, double antal) {
        Dosis dosis = new Dosis(tid, antal);
        if (tid == LocalTime.of(6,0)) {
            doser[0] = dosis;
        } else if (tid == LocalTime.of(12,0)) {
            doser[1] = dosis;
        } else if (tid == LocalTime.of(18,0)) {
            doser[2] = dosis;
        } else if (tid == LocalTime.of(0,0)) {
            doser[3] = dosis;
        } else throw new RuntimeException("Ikke gyldigt tidspunkt. Vælg enten kl.6, 12, 18, eller 0.");

        return dosis;
    }

    @Override
    public double samletDosis() {
        double samletDosis = antalDage() * doegnDosis();
        return samletDosis;
    }

    @Override
    public double doegnDosis() {
        int antal = 0;
        for (Dosis d : doser) {
            antal += (int) d.getAntal();
        }
        return antal;
    }

    @Override
    public String getType() {
        return "Daglig Fast";
    }
}
