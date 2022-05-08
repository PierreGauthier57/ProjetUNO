package Parser;

import Carte.*;

public class ParserCouleur extends ParserValide {
    public ParserCouleur(ParserValide suivant) {

        super(suivant);
    }

    @Override
    public Cartes parser(String ligne) {

        return new Couleur(Cartes.Color.NOIR);
    }

    @Override
    public boolean saitParser(String ligne) {

        if (ligne.matches("^.*CarteCouleur.*$"))
            return true;
        return false;
    }
}
