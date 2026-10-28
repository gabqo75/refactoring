package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplementDangereuxTest {

    private final ComposantPrix base = (colis, distance, typeClient) -> 100;

    @Test
    void appliqueLeSupplementSiUnColisEstConcerne() {
        assertEquals(130, new SupplementDangereux(base).calculer(List.of(new Colis("c", 1, 10, 10, 10, TypeMarchandise.DANGEREUX, 0)), 0, TypeClient.PARTICULIER), 0.001);
    }

    @Test
    void neChangeRienSinon() {
        assertEquals(100, new SupplementDangereux(base).calculer(List.of(new Colis("c", 1, 10, 10, 10, TypeMarchandise.STANDARD, 0)), 0, TypeClient.PARTICULIER), 0.001);
    }
}
