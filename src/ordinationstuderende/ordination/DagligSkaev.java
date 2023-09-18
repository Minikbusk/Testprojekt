package ordinationstuderende.ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination{
    public final ArrayList<Dosis> doser = new ArrayList();

    public DagligSkaev(LocalDate startDen, LocalDate slutDen, Patient patient) {
        super(startDen, slutDen, patient);
    }

    public void opretDosis(LocalTime tid, double antal) {
        Dosis dosis = new Dosis(tid, antal);
        doser.add(dosis);
    }

    @Override
    public double samletDosis() {
        double samletDosis = antalDage() * doegnDosis();
        return samletDosis;
    }

    @Override
    public double doegnDosis() {
        double antal = 0;
        for (Dosis d : doser) {
            antal += d.getAntal();
        }
        return antal;
    }

    @Override
    public String getType() {
        return "Daglig Skæv";
    }
}
