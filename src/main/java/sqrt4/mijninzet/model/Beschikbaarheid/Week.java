package sqrt4.mijninzet.model.Beschikbaarheid;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Dag> dagenlijst;

    private int weekNummer;
    private int jaarNummer;

    @ManyToOne
    @JoinColumn
    private Semester semester;


    public Week(int weekNummer, int jaarNummer){
        super();
        this.weekNummer = weekNummer;
        this.jaarNummer = jaarNummer;
        dagenlijst = new ArrayList<>();
        dagenlijst.add(new Dag("maandag", weekNummer, jaarNummer));
        dagenlijst.add(new Dag("dinsdag", weekNummer, jaarNummer));
        dagenlijst.add(new Dag("woensdag", weekNummer, jaarNummer));
        dagenlijst.add(new Dag("donderdag", weekNummer, jaarNummer));
        dagenlijst.add(new Dag("vrijdag", weekNummer,jaarNummer));
        setWeekVoorDagen();
    }

    public Week() {
        this(1,2019);
        dagenlijst = new ArrayList<>();
        dagenlijst.add(new Dag("maandag", weekNummer, jaarNummer));
        dagenlijst.add(new Dag("dinsdag", weekNummer, jaarNummer));
        dagenlijst.add(new Dag("woensdag", weekNummer, jaarNummer));
        dagenlijst.add(new Dag("donderdag", weekNummer, jaarNummer));
        dagenlijst.add(new Dag("vrijdag", weekNummer,jaarNummer));
        setWeekVoorDagen();
    }


    @Override
    public String toString() {
        return "Week{" +
                "dagenlijst=" + dagenlijst +
                ", weekNummer=" + weekNummer +
                '}';
    }

    public int getWeekNummer() {
        return weekNummer;
    }

    public void setWeekNummer(int weekNummer) {
        this.weekNummer = weekNummer;
    }

    public int getJaarNummer() {
        return jaarNummer;
    }

    public void setJaarNummer(int jaarNummer) {
        this.jaarNummer = jaarNummer;
    }

    public Dag getDag(String dagnaam) {
        Dag dag = null;

        for (Dag d : dagenlijst) {
            if (d.getDagnaam().equals(dagnaam)) {
                dag = d;
            }
        }
        return dag;
    }

    public int getDaysInTheWeek() {

        int teller = 0;
        for (Object o: dagenlijst) {
            if(o instanceof Dag){
                teller++;
        }
        }
        return teller;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    private void setWeekVoorDagen() {
        for (Dag dag : dagenlijst) {
            dag.setWeek(this);
        }
    }

    public void pasWeekNummerDagenAan(int nieuwNummer) {
        for (Dag dag : dagenlijst) {
            dag.setWeekNummer(nieuwNummer);
        }
    }
}
