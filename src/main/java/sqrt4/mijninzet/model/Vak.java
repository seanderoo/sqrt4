package sqrt4.mijninzet.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public Vak(String vakNaam, int aantalUren){
        this.vakNaam = vakNaam;
        this.aantalUren = aantalUren;
    }

    public Vak() {}

    public int getVakId() {
        return vakId;
    }

    public void setVakId(int vakId) {
        this.vakId = vakId;
    }

    public String getVakNaam() {
        return vakNaam;
    }

    public void setVakNaam(String vakNaam) {
        this.vakNaam = vakNaam;
    }

    public int getAantalUren() {
        return aantalUren;
    }

    public void setAantalUren(int aantalUren) {
        this.aantalUren = aantalUren;
    }

    @Override
    public String toString() {
        return "Vak{" +
                "vakId=" + vakId +
                ", vakNaam='" + vakNaam + '\'' +
                ", aantalUren=" + aantalUren +
                '}';
    }
}



