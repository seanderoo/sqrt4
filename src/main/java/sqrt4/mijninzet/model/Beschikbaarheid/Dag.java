package sqrt4.mijninzet.model.Beschikbaarheid;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Dag {
    private String dagnaam;
    private int weekNummer;
    private int jaarNummer;
    private LocalDate datum;
    private boolean ochtend;
    private boolean middag;
    private boolean avond;

    public Dag(String dagnaam, int weekNummer, int jaarNummer) {
        this(dagnaam, weekNummer, jaarNummer, true, true, true);
        this.datum = setDatumDag(dagnaam);
    }

    public Dag(String name, int weekNummer, int jaarNummer, boolean ochtend, boolean middag, boolean avond){
        super();
        this.dagnaam = name;
        this.weekNummer = weekNummer;
        this.jaarNummer = jaarNummer;
        this.ochtend = ochtend;
        this.middag = middag;
        this.avond = avond;
        this.datum = setDatumDag(dagnaam);
    }
    public LocalDate setDatumDag(String dagnaam){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, this.jaarNummer);
        cal.set(Calendar.WEEK_OF_YEAR, this.weekNummer);
        cal.set(Calendar.DAY_OF_WEEK, nlDagNaarJava(dagnaam));

        String date = sdf.format(cal.getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }

    public int nlDagNaarJava(String dagNaam){
        int var = 0;

        switch(dagNaam){
            case "maandag":
                var = Calendar.MONDAY;
                break;
            case "dinsdag":
                var = Calendar.TUESDAY;
                break;
            case "woensdag":
                var = Calendar.WEDNESDAY;
                break;
            case "donderdag":
                var = Calendar.THURSDAY;
                break;
            case "vrijdag":
                var = Calendar.FRIDAY;
                break;
        }
        return var;
    }

    @Override
    public String toString() {
        return "Dag{" +
                "dagnaam='" + dagnaam + '\'' +
                ", datum=" + datum +
                '}';
    }
}
