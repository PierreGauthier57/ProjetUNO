package Uno;

import Carte.Cartes;
import Exception.tourException;
import Exception.valideException;
import Expert.ExpertValide;

import java.util.ArrayList;

public class Partie {
    private int nbJoueur;
    private boolean sensHoraire = true;
    private int numJoueurCourant= 0;
    public ArrayList<Cartes> pioche = new ArrayList<Cartes>();
    public ArrayList<Cartes> tas = new ArrayList<Cartes>();
    public ArrayList<Joueur> listeDesJoueurs= new ArrayList<Joueur>();
    private static  volatile Partie instance = null;


    public Partie(int nbJoueur){
        this.nbJoueur=nbJoueur;
    }

    public Cartes getHautTas(){
        return tas.get(0);
    }

    public void piocher(Joueur joueur)throws tourException {
        if(!(listeDesJoueurs.get(numJoueurCourant) == joueur))
            throw new tourException("Ce n'est pas ton tour (PUNITION)");

        joueur.main.add(pioche.get(0));
        pioche.remove(0);
    }

    public void fini(Joueur joueur)throws tourException {
        if(listeDesJoueurs.get(getNumJoueurCourant())!=joueur){
            throw new tourException("Ce n'est pas ton tour");
        }
        prochainJoueur();
    }
    public void poser(Cartes carte,Joueur joueur ) throws valideException,tourException{
        if(!(listeDesJoueurs.get(numJoueurCourant) == joueur))
            throw new tourException("Ce n'est pas ton tour");

        if(!EstValide(carte,tas.get(0))) ///  LES EFFETS DE CARTES ?----------------------------------------------
            throw new valideException("La carte n'est pas valide : PENALITE");
        joueur.main.remove(carte);
        tas.add(0,carte);

    }

    public void punition(Joueur joueur){
        joueur.main.add(pioche.get(0));
        pioche.remove(0);
        VerifierTas();
        joueur.main.add(pioche.get(0));
        pioche.remove(0);

    }
    public int getNumCarteTas(){
        return tas.size();
    }
    public void VerifierTas(){
        if(getNumCarteTas()==0){}
            //-----------------------------------------------------------------------code a finir car pas utile pour la soutenance
    }

    public boolean EstValide(Cartes carte,Cartes tas){

return true;
       // return ExpertValide.traiter(carte,tas);
    }

    private int getNBCarteTas(ArrayList tas){
        return tas.size();
    }

    public int getNBPioche() {
        return pioche.size();
    }

    public int getNumJoueurCourant() {
        return numJoueurCourant;
    }

    public void setNumJoueurCourant(int numJoueurCourant) {
        this.numJoueurCourant = numJoueurCourant;
    }

    public static Partie getInstance() {
        if(instance == null)
            instance = new Partie(instance.nbJoueur);
        return instance;
    }
    public void prochainJoueur(){
        if(numJoueurCourant==nbJoueur-1)
            setNumJoueurCourant(0);
        else
            setNumJoueurCourant(numJoueurCourant+1);
    }
    @Override
    public String toString() {
        return "nbJoueur=" + nbJoueur +
                ", sensHoraire=" + sensHoraire +
                ", numJoueurTour=" + numJoueurCourant +
                //",nomJoueurTour ="+ listeDesJoueurs.get(numJoueurCourant).getNom()+
                ", pioche=" + pioche +
                ", tas=" + tas
                ;
    }
}

