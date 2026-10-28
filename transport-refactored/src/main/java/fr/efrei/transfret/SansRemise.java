package fr.efrei.transfret;

public class SansRemise implements PolitiqueRemise {

    public double appliquer(double prix) {
        return prix;
    }
}
