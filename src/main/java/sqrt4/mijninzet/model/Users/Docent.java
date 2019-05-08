package sqrt4.mijninzet.model.Users;

import sqrt4.mijninzet.model.Voorkeur;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class Docent extends User {

    ArrayList<Voorkeur> voorkeur;

    public Docent(ArrayList<Voorkeur> voorkeur) {
        super();
        this.voorkeur = voorkeur;

    }

    public Docent() {
        super();
    }
}
