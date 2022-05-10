package Carte;

import Uno.Partie;

public class Passer extends Cartes {
    public Passer(Color couleur) {
        super(couleur);
    }

    public void effet()
    {
        Partie.getInstance().prochainJoueur();
    }

    @Override
    public String toString() {
        return "Passer{" + super.toString();
    }
}
