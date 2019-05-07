package sqrt4.mijninzet.model;

import javax.persistence.Entity;

@Entity
public class Voorkeur {

    private Vak vak;
    private int voorkeur;


    public Voorkeur(Vak vak, int voorkeur){
        this.vak = vak;
        this.voorkeur = voorkeur;
    }
}
