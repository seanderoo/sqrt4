package sqrt4.mijninzet.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Vak {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vakId")
    private int vakId;

    @Column(name = "vakNaam")
    private String vakNaam;

    @Column(name = "aantalUren")
    private int aantalUren;

    public Vak(int vakId, String vakNaam, int aantalUren){
        this.vakId = vakId;
        this.vakNaam = vakNaam;
        this.aantalUren = aantalUren;
    }


}
