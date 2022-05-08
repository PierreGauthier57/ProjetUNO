package Parser;

import Carte.*;
import Exception.*;

public class ParserNormale extends ParserValide
{
    public ParserNormale(ParserValide suivant) {

        super(suivant);
    }

    @Override
    public Cartes parser(String ligne) throws ColorException ,NumberException {

        return new Normale(FichierCarteCSV.getNumber(ligne),FichierCarteCSV.getColor(ligne));
    }

    @Override
    public boolean saitParser(String ligne) {

        if(ligne.matches("^.*CarteSimple.*$"))
            return true;
        return false;
    }
}