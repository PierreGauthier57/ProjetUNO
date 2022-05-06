package Parser;
import Carte.Cartes;
import Carte.Normale;

public abstract class ParserNormaleSurNormal extends Parser{
    public ParserNormaleSurNormal(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(Cartes cartes,Cartes cartesTas) throws Exception {
        
    }


    @Override
    public boolean saitParser(Cartes carte,Cartes carteTas) {
        Normale cartes= (Normale)carte;
        Normale cartesTas= (Normale)carte;
        if(cartes instanceof Normale || cartesTas instanceof Normale)
            if((cartes.getCouleur()==cartesTas.getCouleur()) ||(cartes.getChiffre()==cartesTas.getChiffre()))
            return true;
        return false;
    }
}
