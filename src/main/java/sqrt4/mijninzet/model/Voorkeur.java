package sqrt4.mijninzet.model;

import javax.persistence.*;

@Entity
public class Voorkeur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Vak vak;
    private int voorkeur;


    public Voorkeur(int id, Vak vak, int voorkeur){
        this.id = id;
        this.vak = vak;
        this.voorkeur = voorkeur;
    }
}
