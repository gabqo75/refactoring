package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarifRailTest {

    private final TarifRail strategie = new TarifRail();

    @Test
    void prixAuKmEtAuKg() {
        assertEquals(1000 * 0.8 + 100 * 0.07, strategie.calculer(1000, 100, 0), 0.001);
    }

    @Test
    void minimumDeFacturation() {
        assertEquals(150, strategie.calculer(10, 10, 0), 0.001);
    }
}
