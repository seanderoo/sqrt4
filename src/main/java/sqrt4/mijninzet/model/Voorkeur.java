package sqrt4.mijninzet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


public class Voorkeur {


    @Column(name = "vak")
    private Vak vak;
    @Id
    @Column(name = "voorkeur")
    private int voorkeur;


    public Voorkeur(Vak vak, int voorkeur){
        this.vak = vak;
        this.voorkeur = voorkeur;
    }
}
