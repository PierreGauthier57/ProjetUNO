package Expert;
import Carte.Cartes;
import Carte.Normale;

public class ExpertNormaleSurNormal extends ExpertValide {
    public ExpertNormaleSurNormal(ExpertValide suivant) {
        super(suivant);
    }

    @Override
    public boolean parser(Cartes cartes,Cartes cartesTas) throws Exception {
        Normale cs= (Normale)cartes;
        Normale ct= (Normale)cartesTas;
        if((cartes.getCouleur()==cartesTas.getCouleur()) ||(cs.getChiffre()==ct.getChiffre()))
            return true;
        return false;
    }


    @Override
    public boolean saitParser(Cartes carte,Cartes carteTas) {

       return carte instanceof Normale && carteTas instanceof Normale;
    }
}
