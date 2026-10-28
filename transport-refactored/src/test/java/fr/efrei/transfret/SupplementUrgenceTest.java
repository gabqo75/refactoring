package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplementUrgenceTest {

    private final ComposantPrix base = (colis, distance, typeClient) -> 100;

    @Test
    void multipliePar1Virgule5() {
        assertEquals(150, new SupplementUrgence(base).calculer(List.of(new Colis("c", 1, 10, 10, 10, TypeMarchandise.STANDARD, 0)), 0, TypeClient.PARTICULIER), 0.001);
    }
}
