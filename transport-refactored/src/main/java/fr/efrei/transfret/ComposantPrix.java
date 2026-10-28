package fr.efrei.transfret;

import java.util.List;

public interface ComposantPrix {

    double calculer(List<Colis> colis, int distance, TypeClient typeClient);
}
