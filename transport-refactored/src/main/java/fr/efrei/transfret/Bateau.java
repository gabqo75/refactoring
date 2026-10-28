package fr.efrei.transfret;

import java.util.List;

public class Bateau extends Vehicule implements Refrigerable {

    private static final double KM_PAR_MILLE_NAUTIQUE = 1.852;

    private boolean conteneursRefrigeres;

    public Bateau(String immatriculation, double capaciteMax, boolean conteneursRefrigeres) {
        super(immatriculation, capaciteMax);
        this.conteneursRefrigeres = conteneursRefrigeres;
    }

    public boolean peutPrendreEnCharge(List<Colis> colis, ModeTransport mode) {
        if (mode != ModeTransport.MER) {
            return false;
        }
        return conteneursRefrigeres || !contient(colis, TypeMarchandise.REFRIGERE);
    }

    public void partir(int distance) {
        faireLePlein();
        naviguer((int) (distance / KM_PAR_MILLE_NAUTIQUE));
        if (contient(chargement, TypeMarchandise.REFRIGERE)) {
            activerRefrigeration(TEMPERATURE_REFRIGERATION);
        }
    }

    public void naviguer(int milesNautiques) {
        System.out.println("Le navire " + immatriculation + " navigue sur " + milesNautiques + " milles nautiques");
    }

    public void activerRefrigeration(double temperature) {
        System.out.println("Conteneurs frigorifiques réglés à " + temperature + "°C");
    }
}
