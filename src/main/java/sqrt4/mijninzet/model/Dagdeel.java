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

    public Dagdeel(int dagdeelNummer){
        this.dagdeelNaam = vak.getVakNaam() + Integer.toString(dagdeelNummer);
    }

}



