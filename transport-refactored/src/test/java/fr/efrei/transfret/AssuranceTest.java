package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AssuranceTest {

    private final ComposantPrix base = (colis, distance, typeClient) -> 100;

    @Test
    void ajouteDeuxPourCentDeLaValeurDeclaree() {
        assertEquals(300, new Assurance(base).calculer(List.of(new Colis("c", 1, 10, 10, 10, TypeMarchandise.STANDARD, 10000)), 0, TypeClient.PARTICULIER), 0.001);
    }

    @Test
    void minimumQuinzeEuros() {
        assertEquals(115, new Assurance(base).calculer(List.of(new Colis("c", 1, 10, 10, 10, TypeMarchandise.STANDARD, 100)), 0, TypeClient.PARTICULIER), 0.001);
    }
}
