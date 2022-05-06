package Expert;

import Carte.Cartes;
import Exception.ExpertManquantException;

public abstract class ExpertValide {

    // Un parser est en fait un maillon dans une liste chainée...
    // Cette liste chainée représente une instruction "switch"
    // Chaque maillon représente un "case" du switch
    private ExpertValide suivant = null;

    public ExpertValide(ExpertValide suivant) {
        this.suivant = suivant;
    }

    /**
     * La fonction traiter() parcours la liste à la recherche d'un maillon qui sait comment parser
     * la ligne. Dans ce cas la ligne est parsée et la recherche s'arrête
     /** @param ligne la ligne à parser
     /** @exception lance une exception si quelque chose a mal tourné
     */
    public void traiter(Cartes cartes,Cartes carteTas) throws Exception {
        if (saitParser(cartes,carteTas))
            // Si le parser sait parser la ligne, il la parse
            parser(cartes,carteTas);
        else if (aUnSuivant())
            // S'il ne sait pas mais qu'il a un suivant dans la liste chaine, il lui repasse la ligne et qu'il se débrouille !
            getSuivant().traiter(cartes,carteTas);
        else
            // Sinon, on est arrivé au bout sans trouver un parser
            // et on lance une exception ! Que le prog appelant se débrouille avec cette ligne !
            throw new ExpertManquantException();

    }
    private ExpertValide getSuivant() {
        return suivant;
    }

    private boolean aUnSuivant() {
        return suivant != null;
    }

    public abstract boolean parser(Cartes cartes, Cartes cartesTas) throws Exception;

    public abstract boolean saitParser(Cartes cartes, Cartes carteTas);







}