package ordinationstuderende.ordination;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;

public class DagligFast extends Ordination {
    private final Dosis[] doser = new Dosis[4];

    public DagligFast() {
        super();
    }

    public Dosis[] getDoser() {
        return doser;
    }

    public Dosis opretDosis(LocalTime tid, double antal) {
    Dosis dosis = new Dosis(tid, antal);
        if (tid == LocalTime.of(6, 0)) {
            doser[0] = dosis;
        } else if (tid == LocalTime.of(12, 0)) {
            doser[1] = dosis;

        } else if (tid == LocalTime.of(18,0)){
            doser[2] = dosis;

        } else if(tid == LocalTime.of(0, 0)){
            doser[3] = dosis;

        } else throw new RuntimeException("Ikke et gyldigt tidspunkt. Kl 6, 12, 18 eller 00 skal vælges!");

        return dosis;
    }

    @Override
    public double samletDosis() {
        int samletDosis = antalDage() * (int) doegnDosis();
        return 0;
    }

    @Override
    public double doegnDosis() {
        int antal = 0;
        for(Dosis d : doser){
            antal += (int) d.getAntal();
        }
        return antal;
    }

    @Override
    public String getType() {
        return "Daglig fast dosis";
    }
}
