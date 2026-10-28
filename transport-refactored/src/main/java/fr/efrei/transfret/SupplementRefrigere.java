package fr.efrei.transfret;

import java.util.List;

public class SupplementRefrigere extends DecorateurPrix {

    private static final double SUPPLEMENT = 1.20;

    public SupplementRefrigere(ComposantPrix enveloppe) {
        super(enveloppe);
    }

    public double calculer(List<Colis> colis, int distance, TypeClient typeClient) {
        double prix = enveloppe.calculer(colis, distance, typeClient);
        for (Colis c : colis) {
            if (c.getType() == TypeMarchandise.REFRIGERE) {
                return prix * SUPPLEMENT;
            }
        }
        return prix;
    }
}
