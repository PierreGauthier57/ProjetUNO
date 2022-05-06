package Uno;

import Carte.Cartes;
import Exception.*;
import Expert.ExpertValide;

import java.util.ArrayList;

public class Partie {
    private int nbJoueur;
    private boolean sensHoraire = true;
    private int numJoueurCourant= 0;
    private ArrayList<Cartes> pioche = new ArrayList<Cartes>();
    private ArrayList<Cartes> tas = new ArrayList<Cartes>();
    private ArrayList<Joueur> listeDesJoueurs= new ArrayList<Joueur>();
    private static  volatile Partie instance = null;
    private ExpertValide PremierExpert = null;


    public Partie(int nbJoueur)
    {
        this.nbJoueur=nbJoueur;
    }

    public Cartes getHautTas(){
        return tas.get(0);
    }

    public void piocher(Joueur joueur)throws valideException, tourException {
        if(!(listeDesJoueurs.get(numJoueurCourant) == joueur))
            throw new tourException("Ce n'est pas ton tour");

        joueur.main.add(pioche.get(0));
        pioche.remove(0);
    }

    public void poser(Cartes carte,Joueur joueur ) throws valideException,tourException{
        if(!(listeDesJoueurs.get(numJoueurCourant) == joueur))
            throw new tourException("Ce n'est pas ton tour");

        if(!EstValide(carte,tas.get(0))) ///  LES EFFETS DE CARTES ?----------------------------------------------
            throw new valideException("La carte n'est pas valide : PENALITE");
        joueur.main.remove(carte);
        tas.add(0,carte);
    }

    public boolean EstValide(Cartes carte,Cartes tas){

        try {
            return  PremierExpert.traiter(carte,tas);
        }
        catch (ExpertManquantException e)
        {

        }
        catch (Exception e)
        {
            //e.printStackTrace();
        }
        return true;
    }

    public static Partie getInstance() {
        if(instance == null)
            instance = new Partie(instance.nbJoueur);
        return instance;
    }

    @Override
    public String toString() {
        return "nbJoueur=" + nbJoueur +
                ", sensHoraire=" + sensHoraire +
                ", numJoueurTour=" + numJoueurCourant +
                ", pioche=" + pioche +
                ", tas=" + tas
                ;
    }
}

