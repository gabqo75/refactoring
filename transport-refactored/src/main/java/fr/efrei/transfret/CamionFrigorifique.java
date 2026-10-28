package fr.efrei.transfret;

import java.util.List;

public class CamionFrigorifique extends Camion implements Refrigerable {

    public CamionFrigorifique(String immatriculation, double capaciteMax) {
        super(immatriculation, capaciteMax, false);
    }

    @Override
    public boolean peutPrendreEnCharge(List<Colis> colis, ModeTransport mode) {
        // un camion frigo ne prend QUE des colis réfrigérés
        return mode == ModeTransport.ROUTE && contient(colis, TypeMarchandise.REFRIGERE);
    }

    @Override
    public void partir(int distance) {
        super.partir(distance);
        activerRefrigeration(TEMPERATURE_REFRIGERATION);
    }

    public void activerRefrigeration(double temperature) {
        System.out.println("Réfrigération activée à " + temperature + "°C sur " + immatriculation);
    }
}
