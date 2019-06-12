package sqrt4.mijninzet.model;

import javax.persistence.*;

@Entity
public class Vakdagdeel implements Comparable<Vakdagdeel>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String vakdagdeelNaam;

    @ManyToOne
    private Vak vak;


    public Vakdagdeel() {
    }

    public Vakdagdeel(int vakdagdeelNummer, Vak vak){
        this.vak = vak;
        this.vakdagdeelNaam = vak.getVakNaam() + ", dagdeel: "+Integer.toString(vakdagdeelNummer);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVakdagdeelNaam() {
        return vakdagdeelNaam;
    }

    public void setVakdagdeelNaam(String VakdagdeelNaam) {
        this.vakdagdeelNaam = VakdagdeelNaam;
    }

    public Vak getVak() {
        return vak;
    }

    public void setVak(Vak vak) {
        this.vak = vak;
    }

    @Override
    public String toString() {
        return vakdagdeelNaam;
    }

    @Override
    public int compareTo(Vakdagdeel o) {
        return this.vakdagdeelNaam.compareTo(o.vakdagdeelNaam);
    }
}



