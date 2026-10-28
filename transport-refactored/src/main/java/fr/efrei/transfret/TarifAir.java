package fr.efrei.transfret;

public class TarifAir implements TarifTransport {

    private static final double PRIX_KM = 0.6;
    private static final double PRIX_KG_TAXABLE = 4.5;
    private static final double FACTEUR_VOLUMETRIQUE = 167;

    public double calculer(int distance, double poids, double volume) {
        double poidsTaxable = poids;
        if (volume * FACTEUR_VOLUMETRIQUE > poids) {
            poidsTaxable = volume * FACTEUR_VOLUMETRIQUE;
        }
        return distance * PRIX_KM + poidsTaxable * PRIX_KG_TAXABLE;
    }
}
