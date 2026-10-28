package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemiseTest {

    private final ComposantPrix base = (colis, distance, typeClient) -> 100;

    @Test
    void delegueALaPolitiqueDuTypeDeClient() {
        Remise remise = new Remise(base, Map.of(TypeClient.PREMIUM, new RemisePremium()));
        assertEquals(80, remise.calculer(List.of(new Colis("c", 1, 10, 10, 10, TypeMarchandise.STANDARD, 0)), 0, TypeClient.PREMIUM), 0.001);
    }
}
