package fr.efrei.transfret;

import java.util.List;

public class Assurance extends DecorateurPrix {

    private static final double TAUX = 0.02;
    private static final double MINIMUM = 15;

    public Assurance(ComposantPrix enveloppe) {
        super(enveloppe);
    }

    public double calculer(List<Colis> colis, int distance, TypeClient typeClient) {
        double valeur = 0;
        for (Colis c : colis) {
            valeur = valeur + c.getValeurDeclaree();
        }
        double montantAssurance = valeur * TAUX;
        if (montantAssurance < MINIMUM) montantAssurance = MINIMUM;
        return enveloppe.calculer(colis, distance, typeClient) + montantAssurance;
    }
}
