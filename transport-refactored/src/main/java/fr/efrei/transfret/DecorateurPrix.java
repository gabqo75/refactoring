package fr.efrei.transfret;

public abstract class DecorateurPrix implements ComposantPrix {

    protected final ComposantPrix enveloppe;

    protected DecorateurPrix(ComposantPrix enveloppe) {
        this.enveloppe = enveloppe;
    }
}
