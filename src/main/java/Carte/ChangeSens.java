package Carte;

import Uno.Partie;

public class ChangeSens extends Cartes
{
    public ChangeSens(Color couleur)
    {
        super(couleur);
    }

    public void effet()
    {
        Partie.getInstance().inverseSens();
    }

    @Override
    public String toString() {
        return "ChangeSens{" + super.toString();
    }
}
