package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarifMerTest {

    private final TarifMer strategie = new TarifMer();

    @Test
    void prixAuKmEtAuKg() {
        assertEquals(5000 * 0.5 + 1000 * 0.03, strategie.calculer(5000, 1000, 0), 0.001);
    }

    @Test
    void minimumDeFacturation() {
        assertEquals(500, strategie.calculer(100, 10, 0), 0.001);
    }
}
