package Expert;

import Carte.*;

public class ExpertNormale extends ExpertValide {
    public ExpertNormale(ExpertValide suivant) {
        super(suivant);
    }

    @Override
    public boolean parser(Cartes cartes, Cartes cartesTas) throws Exception {

        if (cartesTas instanceof Normale)
        {
            Normale cs = (Normale)cartes;
            Normale ct = (Normale)cartesTas;
            if((cs.getChiffre() == ct.getChiffre()))
                return true;
        }
        if(cartes.getCouleur() == cartesTas.getCouleur())
            return true;
        return false;
    }

    @Override
    public boolean saitParser(Cartes carte,Cartes carteTas) {

        return carte instanceof Normale;
    }
}