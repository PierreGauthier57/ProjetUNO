package Parser;

import Carte.*;
import Exception.*;

public class ParserPasser extends ParserValide
{
    public ParserPasser(ParserValide suivant) {

        super(suivant);
    }

    @Override
    public Cartes parser(String ligne) throws ColorException {

        return new Passer(FichierCarteCSV.getColor(ligne));
    }

    @Override
    public boolean saitParser(String ligne) {

        if (ligne.matches("^.*CartePasser.*$"))
            return true;
        return false;
    }
}