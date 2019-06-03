package sqrt4.mijninzet.model;

import javax.persistence.*;

@Entity
public class Dagdeel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String dagdeelNaam;

    @ManyToOne
    private Vak vak;


    public Dagdeel() {
    }

    public Dagdeel(int dagdeelNummer, Vak vak){
        this.vak = vak;
        this.dagdeelNaam = vak.getVakNaam() + ", dagdeel: "+Integer.toString(dagdeelNummer);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDagdeelNaam() {
        return dagdeelNaam;
    }

    public void setDagdeelNaam(String dagdeelNaam) {
        this.dagdeelNaam = dagdeelNaam;
    }

    public Vak getVak() {
        return vak;
    }

    public void setVak(Vak vak) {
        this.vak = vak;
    }

    @Override
    public String toString() {
        return dagdeelNaam;
    }
}



