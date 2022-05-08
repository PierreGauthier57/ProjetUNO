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

        int i = FichierCarteCSV.getNumber(ligne);
        Cartes.Color couleur = FichierCarteCSV.getColor(ligne);
        return new Normale(i, couleur);
    }

    @Override
    public boolean saitParser(String ligne) {

        if(ligne.matches("^.*CarteSimple.*$"))
            return true;
        return false;
    }
}