package fr.efrei.transfret;

public class TarifMer implements TarifTransport {

    private static final double PRIX_KM = 0.5;
    private static final double PRIX_KG = 0.03;
    private static final double MINIMUM = 500;

    public double calculer(int distance, double poids, double volume) {
        double prix = distance * PRIX_KM + poids * PRIX_KG;
        if (prix < MINIMUM) prix = MINIMUM;
        return prix;
    }
}
