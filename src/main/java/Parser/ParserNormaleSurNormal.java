package Parser;
import Uno.Partie;
import Carte.Cartes;
import Carte.Normale;

public class ParserNormaleSurNormal extends Parser{
    public ParserNormaleSurNormal(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(Cartes cartes) throws Exception {
        //if(cartes.getCouleur() == )
    }


    @Override
    public boolean saitParser(Cartes cartes) {
        if(cartes instanceof Normale)
            //if(super.getPartie().getHautTas() instanceof  Normale)
            //return true;

        return false;
    }
}
