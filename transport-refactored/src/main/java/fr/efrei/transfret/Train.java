package fr.efrei.transfret;

import java.util.List;

public class Train extends Vehicule {

    public Train(String immatriculation, double capaciteMax) {
        super(immatriculation, capaciteMax);
    }

    public boolean peutPrendreEnCharge(List<Colis> colis, ModeTransport mode) {
        return mode == ModeTransport.RAIL;
    }

    public void partir(int distance) {
        rouler(distance);
    }

    public void rouler(int km) {
        System.out.println("Le train " + immatriculation + " circule sur " + km + " km de voies ferrées");
    }
}
