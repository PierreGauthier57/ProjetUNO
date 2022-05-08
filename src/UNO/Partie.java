package UNO;

import UNO.Carte.Cartes;
import UNO.Exception.tourException;
import UNO.Exception.valideException;

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
<<<<<<< Updated upstream:src/UNO/Partie.java
=======



>>>>>>> Stashed changes:src/main/java/Uno/Partie.java
    public Cartes getHautTas(){
        return tas.get(0);
    }

    public void piocher(Joueur joueur)throws valideException, tourException {
        if(!(listeDesJoueurs.get(numJoueurCourant) == joueur))
            throw new tourException("Ce n'est pas ton tour");

        joueur.main.add(pioche.get(0));
        pioche.remove(0);
    }

    public void fini(Joueur joueur){
        if(listeDesJoueurs.get(getNumJoueurCourant())==joueur){
            prochainJoueur();
        }
    }
    public void poser(Cartes carte,Joueur joueur ) throws valideException,tourException{
        if(!(listeDesJoueurs.get(numJoueurCourant) == joueur))
            throw new tourException("Ce n'est pas ton tour");

        if(!EstValide(carte,tas.get(0))) ///  LES EFFETS DE CARTES ?----------------------------------------------
            throw new valideException("La carte n'est pas valide : PENALITE");
        joueur.main.remove(carte);
        tas.add(0,carte);
    }
    public int getNumCarteTas(){
        return tas.size();
    }
    public boolean EstValide(Cartes carte,Cartes tas){
<<<<<<< Updated upstream:src/UNO/Partie.java
       // Expert
=======
        
>>>>>>> Stashed changes:src/main/java/Uno/Partie.java
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

