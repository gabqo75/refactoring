package fr.efrei.transfret;

import java.util.List;

public class SupplementUrgence extends DecorateurPrix {

    private static final double SUPPLEMENT = 1.5;

    public SupplementUrgence(ComposantPrix enveloppe) {
        super(enveloppe);
    }

    public double calculer(List<Colis> colis, int distance, TypeClient typeClient) {
        return enveloppe.calculer(colis, distance, typeClient) * SUPPLEMENT;
    }
}
