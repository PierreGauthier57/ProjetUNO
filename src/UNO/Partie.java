package UNO;

import UNO.Carte.Cartes;
import UNO.Exception.tourException;
import UNO.Exception.valideException;

import java.util.ArrayList;

public class Partie {
    private int nbJoueur;
    private boolean sensHoraire = true;
    private int numJoueurTour= 0;
    private ArrayList<Cartes> pioche = new ArrayList<Cartes>();
    private ArrayList<Cartes> tas = new ArrayList<Cartes>();
    private ArrayList<Joueur> listeDesJoueurs= new ArrayList<Joueur>();

    public Partie(int nbJoueur,Cartes carte){
        this.nbJoueur=nbJoueur;
        pioche.add(carte);
    }

    public void piocher(Joueur joueur)throws valideException, tourException {
        if(!(listeDesJoueurs.get(numJoueurTour) == joueur))
            throw new tourException("Ce n'est pas ton tour");

        joueur.main.add(pioche.get(0));
        pioche.remove(0);
    }

    public void poser(Cartes carte,Joueur joueur ) throws valideException,tourException{
        if(!(listeDesJoueurs.get(numJoueurTour) == joueur))
            throw new tourException("Ce n'est pas ton tour");

        if(!EstValide(carte,tas.get(0))) ///  LES EFFETS DE CARTES ?----------------------------------------------
            throw new valideException("La carte n'est pas valide : PENALITE");
        joueur.main.remove(carte);
        tas.add(0,carte);
    }

    public boolean EstValide(Cartes carte,Cartes tas){
        
    }

    @Override
    public String toString() {
        return "nbJoueur=" + nbJoueur +
                ", sensHoraire=" + sensHoraire +
                ", numJoueurTour=" + numJoueurTour +
                ", pioche=" + pioche +
                ", tas=" + tas
                ;
    }
}

