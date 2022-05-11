package Expert;

import Carte.*;
import Uno.Partie;

public class ExpertPlus2  extends ExpertValide {
    public ExpertPlus2(ExpertValide suivant) {
        super(suivant);
    }

    @Override
    public boolean parser(Cartes cartes, Cartes cartesTas) throws Exception {

        if (Partie.getInstance().getCumulEffet() > 0)
        {
            if ((cartesTas instanceof Plus2))
            {
                Partie.getInstance().setEffet(true);
                return true;
            }
            else
                return false;
        }
        else if((cartes.getCouleur() == cartesTas.getCouleur()))
        {
            Partie.getInstance().setEffet(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean saitParser(Cartes carte,Cartes carteTas) {

        return carte instanceof Plus2;
    }
}