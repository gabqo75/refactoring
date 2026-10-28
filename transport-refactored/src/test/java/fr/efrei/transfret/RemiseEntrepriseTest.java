package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemiseEntrepriseTest {

    private final RemiseEntreprise strategie = new RemiseEntreprise();

    @Test
    void dixPourCentJusquACinqMille() {
        assertEquals(4500, strategie.appliquer(5000), 0.001);
    }

    @Test
    void quinzePourCentAuDessusDeCinqMille() {
        assertEquals(8500, strategie.appliquer(10000), 0.001);
    }
}
