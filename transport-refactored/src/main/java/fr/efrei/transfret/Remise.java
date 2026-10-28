package fr.efrei.transfret;

import java.util.List;
import java.util.Map;

public class Remise extends DecorateurPrix {

    private final Map<TypeClient, PolitiqueRemise> remises;

    public Remise(ComposantPrix enveloppe, Map<TypeClient, PolitiqueRemise> remises) {
        super(enveloppe);
        this.remises = remises;
    }

    public double calculer(List<Colis> colis, int distance, TypeClient typeClient) {
        return remises.get(typeClient).appliquer(enveloppe.calculer(colis, distance, typeClient));
    }
}
