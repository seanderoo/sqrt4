package sqrt4.mijninzet.model.Beschikbaarheid;


import sqrt4.mijninzet.model.User;

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

    @OneToOne
    User user;

    @ManyToOne
    private Cohort cohort;


    public Week(int weekNummer, int jaarNummer){
        super();
        this.weekNummer = weekNummer;
        this.jaarNummer = jaarNummer;
        voegDagenToe();
    }

    public Week() {
        this(0,0);
        voegDagenToe();
    }

    private void voegDagenToe() {
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
        return "Week " + weekNummer + "\n" +
                "User: " + user + "\n" +
                "dagenlijst=" + dagenlijst + "\n";
    }

    public int getWeekNummer() {
        return weekNummer;
    }

    public String getWeekNummerTekst() {
        return "Week " + this.weekNummer;
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

    public void setCohort(Cohort cohort) {
        this.cohort = cohort;
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

    public Dag getMaandag() {
        Dag maandag = getDag("maandag");
        return maandag;
    }
    public Dag getDinsdag() {
        Dag dinsdag = getDag("dinsdag");
        return dinsdag;
    }
    public Dag getWoensdag() {
        Dag woensdag = getDag("woensdag");
        return woensdag;
    }
    public Dag getDonderdag() {
        Dag donderdag = getDag("donderdag");
        return donderdag;
    }
    public Dag getVrijdag() {
        Dag vrijdag = getDag("vrijdag");
        return vrijdag;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }
}
