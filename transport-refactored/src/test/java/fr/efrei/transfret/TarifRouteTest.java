package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarifRouteTest {

    private final TarifRoute strategie = new TarifRoute();

    @Test
    void prixAuKmEtAuKg() {
        assertEquals(100 * 1.2 + 50 * 0.10, strategie.calculer(100, 50, 0), 0.001);
    }
}
