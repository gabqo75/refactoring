package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SansRemiseTest {

    private final SansRemise strategie = new SansRemise();

    @Test
    void prixInchange() {
        assertEquals(1000, strategie.appliquer(1000), 0.001);
    }
}
