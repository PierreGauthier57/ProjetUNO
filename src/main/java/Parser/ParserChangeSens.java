package Parser;

import Carte.Cartes;

public class ParserChangeSens extends ParserValide {

    public ParserChangeSens(ParserValide suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {

    }

    @Override
    public boolean saitParser(String ligne) {
        return false;
    }
}

