package fr.efrei.transfret;

import java.util.List;

public interface TarifTransport extends ComposantPrix {

    double calculer(int distance, double poids, double volume);

    default double calculer(List<Colis> colis, int distance, TypeClient typeClient) {
        double poids = 0;
        double volume = 0;
        for (Colis c : colis) {
            poids = poids + c.getPoids();
            volume = volume + c.getVolume();
        }
        return calculer(distance, poids, volume);
    }
}
