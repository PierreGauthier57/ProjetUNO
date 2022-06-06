package Expert;

import Carte.*;

public class ExpertCouleur extends ExpertValide {
    public ExpertCouleur(ExpertValide suivant) {
        super(suivant);
    }

    @Override
    public boolean expert(Cartes cartes, Cartes cartesTas) throws Exception {
        if((cartes.getCouleur() == cartesTas.getCouleur()))
        {

            return true;
        }
        return false;
    }

    @Override
    public boolean saitExpertiser(Cartes carte, Cartes carteTas) {

        return carte instanceof Couleur;
    }
}
