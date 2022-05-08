package Parser;

import Exception.*;
import Carte.*;

public abstract class ParserValide
{
    private ParserValide suivant = null;

    public ParserValide(ParserValide suivant) {
        this.suivant = suivant;
    }

    public Cartes traiter(String ligne) throws Exception
    {
        if (saitParser(ligne)) {

            return parser(ligne);

        }
        else if (aUnSuivant()) {

            return getSuivant().traiter(ligne);
        }
        else

            throw new ParserManquantException();
    }

    private ParserValide getSuivant()
    {
        return suivant;
    }

    private boolean aUnSuivant()
    {
        return suivant != null;
    }

    public abstract Cartes parser(String ligne) throws Exception;

    public abstract boolean saitParser(String ligne);

}