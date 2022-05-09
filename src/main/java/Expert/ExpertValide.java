package Expert;

import Carte.Cartes;
import Exception.ExpertManquantException;

public abstract class ExpertValide {

    private ExpertValide suivant = null;

    public ExpertValide(ExpertValide suivant) {
        this.suivant = suivant;
    }

    public boolean traiter(Cartes cartes,Cartes carteTas) throws Exception {
        if (saitParser(cartes,carteTas))
            return parser(cartes,carteTas);
        else if (aUnSuivant())
            return getSuivant().traiter(cartes,carteTas);
        else
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