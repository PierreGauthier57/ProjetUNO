package Parser;

import Carte.*;
import Exception.*;

public class ParserPlus2 extends ParserValide
{
    public ParserPlus2(ParserValide suivant) {

        super(suivant);
    }

    @Override
    public Cartes parser(String ligne) throws ColorException {

        return new Plus2(FichierCarteCSV.getColor(ligne));
    }

    @Override
    public boolean saitParser(String ligne) {

        if (ligne.matches("^.*CartePlus2.*$"))
            return true;
        return false;
    }
}