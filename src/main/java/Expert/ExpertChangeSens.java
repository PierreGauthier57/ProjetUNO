package Expert;

import Carte.*;
import Uno.Partie;

public class ExpertChangeSens extends ExpertValide
{
    public ExpertChangeSens(ExpertValide suivant) {
        super(suivant);
    }

    @Override
    public boolean parser(Cartes cartes, Cartes cartesTas) throws Exception {
        if((cartes.getCouleur() == cartesTas.getCouleur()))
        {
            Partie.getInstance().inverseSens();
            return true;
        }

        return false;
    }

    @Override
    public boolean saitParser(Cartes carte,Cartes carteTas) {

        return carte instanceof ChangeSens;
    }
}
