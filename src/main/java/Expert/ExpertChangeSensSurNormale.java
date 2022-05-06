package Expert;

import Carte.Cartes;

public class ExpertChangeSensSurNormale extends ExpertValide {

        public ExpertChangeSensSurNormale(ExpertValide suivant){
            super(suivant);
        }
        
    @Override
    public boolean parser(Cartes cartes, Cartes cartesTas) throws Exception {
return true;
    }

    @Override
    public boolean saitParser(Cartes cartes, Cartes cartesTas) {
        return false;
    }

    }

