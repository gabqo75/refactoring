package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarifAirTest {

    private final TarifAir strategie = new TarifAir();

    @Test
    void poidsReelSiPlusGrandQueLePoidsVolumetrique() {
        assertEquals(1000 * 0.6 + 100 * 4.5, strategie.calculer(1000, 100, 0.1), 0.001);
    }

    @Test
    void poidsVolumetriqueSiPlusGrandQueLePoidsReel() {
        assertEquals(1000 * 0.6 + 167 * 4.5, strategie.calculer(1000, 10, 1), 0.001);
    }
}
