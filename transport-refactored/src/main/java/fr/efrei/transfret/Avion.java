package fr.efrei.transfret;

import java.util.List;

public class Avion extends Vehicule {

    private static final int DISTANCE_ROULAGE_KM = 2;
    private static final int ALTITUDE_CROISIERE_PIEDS = 35000;

    public Avion(String immatriculation, double capaciteMax) {
        super(immatriculation, capaciteMax);
    }

    public boolean peutPrendreEnCharge(List<Colis> colis, ModeTransport mode) {
        return mode == ModeTransport.AIR;
    }

    public void partir(int distance) {
        faireLePlein();
        rouler(DISTANCE_ROULAGE_KM);
        voler(ALTITUDE_CROISIERE_PIEDS);
    }

    public void rouler(int km) {
        // roulage sur la piste
        System.out.println("L'avion " + immatriculation + " roule sur la piste");
    }

    public void voler(int altitude) {
        System.out.println("L'avion " + immatriculation + " vole à " + altitude + " pieds");
    }
}
