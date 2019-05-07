package sqrt4.mijninzet.model.Users;

import sqrt4.mijninzet.model.Voorkeur;

import java.util.ArrayList;

public class Docent extends User {

    ArrayList<Voorkeur> voorkeur;

    public Docent(int userId, String userName, String password, ArrayList<Voorkeur> voorkeur) {
        super(userId, userName, password);
        this.voorkeur = voorkeur;

    }
}
