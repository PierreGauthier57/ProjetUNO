package Expert;

import Carte.*;
import Uno.Partie;

public class ExpertNormale extends ExpertValide {
    public ExpertNormale(ExpertValide suivant) {
        super(suivant);
    }

    @Override
    public boolean expert(Cartes cartes, Cartes cartesTas) throws Exception {
        
        if (Partie.getInstance().getCumulEffet() > 0)
        {
            return false;
        }
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
    public boolean saitExpertiser(Cartes carte, Cartes carteTas) {

        return carte instanceof Normale;
    }
}
