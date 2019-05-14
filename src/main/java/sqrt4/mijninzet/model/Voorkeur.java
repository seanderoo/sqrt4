package sqrt4.mijninzet.model;


import sqrt4.mijninzet.model.users.User;
import javax.persistence.*;


@Entity
public class Voorkeur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int voorkeurId;
    @OneToOne
    private User user;
    @OneToOne
    private Vak vak;
    private int voorkeur;

    public Voorkeur() {

    }
}
