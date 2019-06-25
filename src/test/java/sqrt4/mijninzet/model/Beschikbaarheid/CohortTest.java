package sqrt4.mijninzet.model.Beschikbaarheid;

import org.junit.Test;

import static org.junit.Assert.*;

public class CohortTest {

    @Test
    public void aantalWekenInCohort() {
        //arrange
        int startweek1 = 4;
        int eindweek1 = 25;
        int startweek2 = 45;
        int eindweek2 = 12;
        Cohort cohort = new Cohort();
        int verwachtAantalWeken1 = 22;
        int verwachtAantalWeken2 = 20;

        //act
        int aantalWeken1 = cohort.aantalWekenInCohort(startweek1, eindweek1);
        int aantalWeken2 = cohort.aantalWekenInCohort(startweek2, eindweek2);

        //assert
        assertEquals(aantalWeken1, verwachtAantalWeken1);
        assertEquals(aantalWeken2, verwachtAantalWeken2);
    }

    @Test
    public void hoeveelWekenInJaar() {
        //arrange
        int REG_JAAR = 52;
        int SPEC_JAAR = 53;
        int[] jaren = {2019, 2020, 2021, 2022, 2023, 2024, 2025};
        int[] aantallenWeken = new int[jaren.length];
        Cohort cohort = new Cohort();
        int[] verwachteWeken = {REG_JAAR, SPEC_JAAR, REG_JAAR, REG_JAAR, REG_JAAR, REG_JAAR, REG_JAAR};

        //act
        for (int i = 0; i < jaren.length; i++) {
            int aantalWeken = cohort.hoeveelWekenInJaar(jaren[i]);
            aantallenWeken[i] = aantalWeken;
        }

        //assert
        for (int i = 0; i < aantallenWeken.length; i++) {
            assertEquals(aantallenWeken[i], verwachteWeken[i]);
        }
    }
}