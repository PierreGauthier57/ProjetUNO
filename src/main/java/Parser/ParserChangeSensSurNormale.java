package Parser;

import Carte.Cartes;
import Carte.Normale;

public class ParserChangeSensSurNormale extends Parser {

        public ParserChangeSensSurNormale(Parser suivant){
            super(suivant);
        }

    @Override
    public void parser(Cartes cartes) throws Exception {

    }

    @Override
    public boolean saitParser(Cartes cartes) {
        return false;
    }

    @Override
    public void parser(Normale cartes, Normale cartesTas) throws Exception {

    }

    @Override
    public boolean saitParser(Normale cartes, Normale cartesTas) {
        return false;
    }

    public boolean saitParser(String ligne){
            if(ligne.matches(".*CASE DEPART.*"))
                return true;
            return false;
        }


    }

