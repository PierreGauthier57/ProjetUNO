package Uno;

import Carte.Cartes;
import Exception.*;
import Expert.ExpertValide;
import Parser.FichierCarteCSV;
import Parser.ParserValide;

import java.util.ArrayList;

public class Partie {
    private boolean sensHoraire = true;
    private int numJoueurCourant = 0;
    private ArrayList<Cartes> pioche = new ArrayList<Cartes>();
    private ArrayList<Cartes> tas = new ArrayList<Cartes>();
    private ArrayList<Joueur> listeDesJoueurs = new ArrayList<Joueur>();
    private static volatile Partie instance = null;
    private ExpertValide PremierExpert = null;
    private ParserValide PremierParser = null;

    private Partie()
    {

    }

    public void ChoisirJeuDeCarte(String nomFichier,ParserValide Parser)
    {
        FichierCarteCSV.initJeuCarte(nomFichier,pioche,Parser);
    }

    public Joueur ajouterJoueur(String nom)
    {
        listeDesJoueurs.add(new Joueur(nom));
        return listeDesJoueurs.get(listeDesJoueurs.size() - 1);
    }

    public Joueur getJoueurCourant()
    {
        return listeDesJoueurs.get(numJoueurCourant);
    }

    public void suprimerJoueur(Joueur J)
    {
        if (J == null)
            throw new IllegalArgumentException("Erreur : Le joueur a supprimer est null");
        if (!listeDesJoueurs.contains(J))
            throw new IllegalArgumentException("Erreur : Le joueur a supprimer ne fait pas partie de la partie");
        if (listeDesJoueurs.indexOf(J) - 1 < numJoueurCourant)
        {
            numJoueurCourant--;
            if (numJoueurCourant < 0)
                numJoueurCourant = listeDesJoueurs.size() - 1;
        }
        listeDesJoueurs.remove(J);
    }

    public void suprimerJoueur(int numJ)
    {
        if ((numJ < 0) || (numJ > (listeDesJoueurs.size() - 1)))
            throw new IllegalArgumentException("Erreur : Le numero du joueur a supprimer n'est pas valide");

        if (numJ < numJoueurCourant)
        {
            numJoueurCourant--;
            if (numJoueurCourant < 0)
                numJoueurCourant = listeDesJoueurs.size() - 1;
        }
        listeDesJoueurs.remove(numJ);
    }

    public void InitHautTas()
    {
        tas.add(pioche.get(0));
        pioche.remove(0);
    }

    public Cartes getHautTas()
    {
        return tas.get(tas.size() - 1);
    }

    public Cartes getCarteTas(String typeCarte, Cartes.Color Couleur,int numero)
    {
        for (Cartes C : tas)
        {
            if (C.toString().matches("^.*"+ typeCarte +".*$"))
            {
                if (C.toString().matches("^.*"+ Couleur +".*$"))
                {
                    if (C.toString().matches("^.*"+ numero +".*$"))
                    {
                        return C;
                    }
                }
            }

        }
        return null;
    }

    public Cartes getCarteTas(String typeCarte, Cartes.Color Couleur)
    {
        for (Cartes C : tas)
        {
            if (C.toString().matches("^.*"+ typeCarte +".*$"))
            {
                if (C.toString().matches("^.*"+ Couleur +".*$"))
                {
                    return C;
                }
            }

        }
        return null;
    }

    public void distributionCartePioche(int nbCarteParJ) {

        if(pioche.size() < nbCarteParJ * listeDesJoueurs.size())
            throw new IllegalArgumentException("Erreur : Il n'y a pas assez de cartes pour faire cette distribution");
        for (int i = 0; i < nbCarteParJ ; i++)
        {
            for (Joueur J : listeDesJoueurs)
            {
                J.ajouterMainCarte(pioche.get(0));
                pioche.remove(0);
            }
        }
    }

    public void piocher(Joueur joueur)throws valideException, tourException {
        if(!(listeDesJoueurs.get(numJoueurCourant) == joueur))
            throw new tourException("Ce n'est pas ton tour");

        joueur.ajouterMainCarte(pioche.get(0));
        pioche.remove(0);
    }

    public void poser(Cartes carte,Joueur joueur ) throws valideException,tourException{
        if(!(listeDesJoueurs.get(numJoueurCourant) == joueur))
            throw new tourException("Ce n'est pas ton tour");

        //if(!EstValide(carte,tas.get(0))) ///  LES EFFETS DE CARTES ?----------------------------------------------
        //    throw new valideException("La carte n'est pas valide : PENALITE");
        joueur.poseMainCarte(carte);
        tas.add(carte);
    }

    public boolean EstValide(Cartes carte,Cartes tas){

        /*
        try {
            return PremierExpert.traiter(carte,tas);
        }
        catch (ExpertManquantException e)
        {

        }
        catch (Exception e)
        {
            //e.printStackTrace();
        }

         */
        return true;


    }

    public static Partie getInstance() {
        if(instance == null)
            instance = new Partie();
        return instance;
    }

    public void fini(Joueur joueur){
        if(listeDesJoueurs.get(getNumJoueurCourant())==joueur){
            prochainJoueur();
        }
    }

    public int getNbTas(){
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

    public void prochainJoueur(){
        if(numJoueurCourant == listeDesJoueurs.size()-1)
            setNumJoueurCourant(0);
        else
            setNumJoueurCourant(numJoueurCourant+1);
    }



    @Override
    public String toString() {
        return "nbJoueur=" + listeDesJoueurs.size() +
                ", sensHoraire=" + sensHoraire +
                ", numJoueurTour=" + numJoueurCourant +
                ", pioche=" + pioche +
                ", tas=" + tas
                ;
    }
}

