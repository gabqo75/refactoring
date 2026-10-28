package fr.efrei.transfret;

import java.util.List;
import java.util.Map;

public class CalculateurPrix {

    private final Map<ModeTransport, TarifTransport> tarifs;
    private final Map<TypeClient, PolitiqueRemise> remises;

    public CalculateurPrix(Map<ModeTransport, TarifTransport> tarifs, Map<TypeClient, PolitiqueRemise> remises) {
        this.tarifs = tarifs;
        this.remises = remises;
    }

    public double calculerPrixHT(List<Colis> colis, int distance, ModeTransport mode, boolean urgent, boolean assurance, TypeClient typeClient) {
        ComposantPrix prix = tarifs.get(mode);
        prix = new SupplementFragile(prix);
        prix = new SupplementDangereux(prix);
        prix = new SupplementRefrigere(prix);
        // if (urgent) prix = prix * SUPPLEMENT_URGENCE;
        if (urgent) prix = new SupplementUrgence(prix);
        prix = new Remise(prix, remises);
        if (assurance) prix = new Assurance(prix);
        return Utils.arrondir(prix.calculer(colis, distance, typeClient));
    }
}
