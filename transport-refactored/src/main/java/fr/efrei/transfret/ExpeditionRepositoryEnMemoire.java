package fr.efrei.transfret;

import java.util.HashMap;
import java.util.Map;

public class ExpeditionRepositoryEnMemoire implements ExpeditionRepository {

    private final Map<String, Expedition> expeditions = new HashMap<String, Expedition>();

    public void sauvegarder(Expedition expedition) {
        expeditions.put(expedition.getId(), expedition);
    }

    public void mettreAJour(Expedition expedition) {
        expeditions.put(expedition.getId(), expedition);
    }

    public Expedition trouver(String id) {
        return expeditions.get(id);
    }
}
