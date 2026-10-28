package fr.efrei.transfret;

import java.util.List;

public class Camion extends Vehicule {

    protected boolean adr; // certification matières dangereuses

    public Camion(String immatriculation, double capaciteMax, boolean adr) {
        super(immatriculation, capaciteMax);
        this.adr = adr;
    }

    public boolean peutPrendreEnCharge(List<Colis> colis, ModeTransport mode) {
        if (mode != ModeTransport.ROUTE || contient(colis, TypeMarchandise.REFRIGERE)) {
            return false;
        }
        return adr || !contient(colis, TypeMarchandise.DANGEREUX);
    }

    public void partir(int distance) {
        faireLePlein();
        rouler(distance);
    }

    public void rouler(int km) {
        System.out.println("Le camion " + immatriculation + " roule sur " + km + " km");
    }
}
