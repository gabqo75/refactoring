package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemisePremiumTest {

    private final RemisePremium strategie = new RemisePremium();

    @Test
    void vingtPourCent() {
        assertEquals(800, strategie.appliquer(1000), 0.001);
    }
}
