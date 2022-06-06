package Expert;

import Carte.*;
import Uno.Partie;

public class ExpertChangeSens extends ExpertValide
{
    public ExpertChangeSens(ExpertValide suivant) {
        super(suivant);
    }

    @Override
    public boolean expert(Cartes cartes, Cartes cartesTas) throws Exception {
        if((cartes.getCouleur() == cartesTas.getCouleur()))
        {
            Partie.getInstance().setEffet(true);
            return true;
        }

        return false;
    }

    @Override
    public boolean saitExpertiser(Cartes carte, Cartes carteTas) {

        return carte instanceof ChangeSens;
    }
}
