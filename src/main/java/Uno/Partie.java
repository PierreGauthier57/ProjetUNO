package Uno;

import Carte.*;
import Exception.*;
import Expert.*;
import Parser.*;
import java.util.ArrayList;

public class Partie {
    private boolean sensHoraire = true;
    private int numJoueurCourant = 0;
    private int cartePoser = 0;
    private int cartePiocher = 0;
    private ArrayList<Cartes> pioche = new ArrayList<Cartes>();
    private ArrayList<Cartes> tas = new ArrayList<Cartes>();
    private ArrayList<Joueur> listeDesJoueurs = new ArrayList<Joueur>();
    private static volatile Partie instance = null;
    private ExpertValide PremierExpert = null;



    public void reinitialiseCarte()
    {
        numJoueurCourant = 0;
        pioche.clear();
        tas.clear();
        for (Joueur J : listeDesJoueurs)
        {
            J.getMain().clear();
        }
    }

    public void fini(Joueur joueur)throws tourException {
        if(listeDesJoueurs.get(getNumJoueurCourant())!=joueur){
            throw new tourException("Ce n'est pas ton tour");
        }
        cartePoser = 0;
        prochainJoueur();
    }

    public void punition(Joueur joueur){
        joueur.getMain().add(pioche.get(0));
        pioche.remove(0);
        VerifierTas();
        joueur.getMain().add(pioche.get(0));
        pioche.remove(0);

    }

    public int getNumCarteTas(){
        return tas.size();
    }
    public void VerifierTas(){
        if(getNumCarteTas()==0){}
            //-----------------------------------------------------------------------code a finir car pas utile pour la soutenance
    }

    private int getNBCarteTas(ArrayList tas){
        return tas.size();
    }

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
    public Cartes getHautpioche()
    {
        return pioche.get(0);
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
    public Cartes getCartePioche(String typeCarte, Cartes.Color Couleur,int numero)
    {
        for (Cartes C : pioche)
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

    public Cartes getCartePioche(String typeCarte, Cartes.Color Couleur)
    {
        for (Cartes C : pioche)
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

    public void piocher(Joueur joueur)throws valideException, tourException
    {
        if(!(listeDesJoueurs.get(numJoueurCourant) == joueur))
            throw new tourException("Ce n'est pas ton tour");
        if(cartePoser > 0 || cartePiocher>0)
            throw new tourException("Tu a deja jouer");
        joueur.ajouterMainCarte(pioche.get(0));
        pioche.remove(0);
    }

    public void initExpert(ExpertValide Expert )
    {
        PremierExpert = Expert;
    }

    public void poser(Cartes carte,Joueur joueur ) throws valideException,tourException{
        if(!(listeDesJoueurs.get(numJoueurCourant) == joueur))
            throw new tourException("Ce n'est pas ton tour");
        if(cartePoser > 0 || cartePiocher>0)
            throw new tourException("Tu a deja jouer");
        if(carte == null)
            throw new IllegalArgumentException("il ne possede pas la carte");
        if(!EstValide(carte,getHautTas())) ///  LES EFFETS DE CARTES ?----------------------------------------------
            throw new valideException("La carte n'est pas valide : PENALITE");
        joueur.poseMainCarte(carte);
        cartePoser++;
        tas.add(carte);
    }

    public boolean EstValide(Cartes carte,Cartes tas){

        try
        {
            return PremierExpert.traiter(carte,tas);
        }
        catch (ExpertManquantException e)
        {
            System.err.println("La carte " + carte.toString() + " n'est pas reconu part les Expert");
            System.exit(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        return true;
    }

    public static Partie getInstance() {
        if(instance == null)
            instance = new Partie();
        return instance;
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

                ", tas=" + tas+", pioche=" + pioche
                ;
    }



}

