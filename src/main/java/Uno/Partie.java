package Uno;

import Carte.Cartes;
import Exception.tourException;
import Exception.*;
import Parser.*;

import java.util.ArrayList;

public class Partie {
    private int nbJoueur;
    private boolean sensHoraire = true;
    private int numJoueurTour = 0;
    private ArrayList<Cartes> pioche = new ArrayList<Cartes>();
    private ArrayList<Cartes> tas = new ArrayList<Cartes>();
<<<<<<< Updated upstream
    private ArrayList<Joueur> listeDesJoueurs = new ArrayList<Joueur>();

    public Partie(int nbJoueur, Cartes carte) {
        this.nbJoueur = nbJoueur;
        pioche.add(carte);
=======
    private ArrayList<Joueur> listeDesJoueurs= new ArrayList<Joueur>();
    private static  volatile Partie instance = null;
    private Parser PremierExper =  null;



    public Partie(int nbJoueur){
        this.nbJoueur=nbJoueur;
    }

    public void AjouterParser(Parser parser)
    {
        PremierParser = parser;
    }
    public Cartes getHautTas(){
        return tas.get(0);
>>>>>>> Stashed changes
    }

    public void piocher(Joueur joueur) throws valideException, tourException {
        if (!(listeDesJoueurs.get(numJoueurTour) == joueur))
            throw new tourException("Ce n'est pas ton tour");

        joueur.main.add(pioche.get(0));
        pioche.remove(0);
    }

    public void poser(Cartes carte, Joueur joueur) throws valideException, tourException {
        if (!(listeDesJoueurs.get(numJoueurTour) == joueur))
            throw new tourException("Ce n'est pas ton tour");

        if (!EstValide(carte, tas.get(0))) ///  LES EFFETS DE CARTES ?----------------------------------------------
            throw new valideException("La carte n'est pas valide : PENALITE");
        joueur.main.remove(carte);
<<<<<<< Updated upstream
        tas.add(0, carte);
=======
        tas.add(0,carte);
    }

    public boolean EstValide(Cartes carte,Cartes tas)
    {
        try {
            return PremierExper.traiter(carte,tas);
        }
        catch (ParserManquantException e) {
            System.err.println("aucune regle existe");
        }

        return true;
>>>>>>> Stashed changes
    }

    public boolean EstValide(Cartes carte, Cartes tas) {
        return false;
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

