package sqrt4.mijninzet.model;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "vak",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Dagdeel> dagdelen = new ArrayList<>();

    public Vak(String vakNaam, int aantalUren) {
        this.vakNaam = vakNaam;
        this.aantalUren = aantalUren;
        this.dagdelen = aantalDagdelenBerekenen();
    }

    public Vak() { super(); }

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

    public List<Dagdeel> getDagdelen(){
        return dagdelen;
    }

    @Override
    public String toString() {
        return "Vak{" +
                "vakId=" + vakId +
                ", vakNaam='" + vakNaam + '\'' +
                ", aantalUren=" + aantalUren +
                '}';
    }

    public List<Dagdeel> aantalDagdelenBerekenen(){
        List<Dagdeel> vakDagdelen= new ArrayList();
        final int DAGDEELUREN = 4;
        int aantalUren = this.aantalUren;
        int aantalDagdelen = aantalUren / DAGDEELUREN;
        if ((aantalUren % DAGDEELUREN) < DAGDEELUREN){
            aantalDagdelen++;
        }
        for (int i = 0; i == aantalDagdelen; i++) {
            vakDagdelen.add(new Dagdeel(i));
        }
        return vakDagdelen;

    }


}





