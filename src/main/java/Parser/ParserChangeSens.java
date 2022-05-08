package Parser;

import Carte.*;
import Exception.*;

public class ParserChangeSens extends ParserValide
{
    public ParserChangeSens(ParserValide suivant) {

        super(suivant);
    }

    @Override
    public Cartes parser(String ligne) throws ColorException {

        return new ChangeSens(FichierCarteCSV.getColor(ligne));
    }

    @Override
    public boolean saitParser(String ligne) {

        if(ligne.matches("^.*CarteChangeSens.*$"))
            return true;
        return false;
    }
}

