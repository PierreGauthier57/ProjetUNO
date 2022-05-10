package Expert;

import Carte.*;

public class ExpertPasser extends ExpertValide {
    public ExpertPasser(ExpertValide suivant) {
        super(suivant);
    }

    @Override
    public boolean parser(Cartes cartes, Cartes cartesTas) throws Exception {
        if((cartes.getCouleur() == cartesTas.getCouleur()))
            return true;
        return false;
    }

    @Override
    public boolean saitParser(Cartes carte,Cartes carteTas) {

        return carte instanceof Passer;
    }
}
