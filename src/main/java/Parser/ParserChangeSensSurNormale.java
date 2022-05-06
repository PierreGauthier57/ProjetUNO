package Parser;

import Carte.Cartes;
import Carte.Normale;

public abstract class ParserChangeSensSurNormale extends Parser {

        public ParserChangeSensSurNormale(Parser suivant){
            super(suivant);
        }

    @Override
    public void parser(Cartes cartes) throws Exception {

    }

    @Override
    public void parser(Cartes cartes, Cartes cartesTas) throws Exception {

    }

    @Override
    public boolean saitParser(Cartes cartes, Cartes cartesTas) {
        return false;
    }

    }

