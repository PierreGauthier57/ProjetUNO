package Parser;
import Carte.Cartes;
import Carte.Normale;

public class ParserNormaleSurNormal extends Parser{
    public ParserNormaleSurNormal(Parser suivant) {
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
    public void parser(Normale cartes,Normale cartesTas) throws Exception {

    }


    @Override
    public boolean saitParser(Cartes cartes,Cartes cartesTas) {
        if(cartes instanceof Normale || cartesTas instanceof Normale)
            if((cartes.getCouleur()==cartesTas.getCouleur()) ||(cartes.getChiffre()==cartesTas.getChiffre()))
            return true;
        return false;
    }
}
