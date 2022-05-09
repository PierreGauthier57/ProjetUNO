package Uno;

import Carte.Cartes;
import Exception.tourException;
import Exception.valideException;

import java.util.ArrayList;

public class Partie {
    private int nbJoueur;
    private boolean sensHoraire = true;
<<<<<<< Updated upstream
    private int numJoueurCourant= 0;
    public ArrayList<Cartes> pioche = new ArrayList<Cartes>();
    public ArrayList<Cartes> tas = new ArrayList<Cartes>();
    public ArrayList<Joueur> listeDesJoueurs= new ArrayList<Joueur>();
    private static  volatile Partie instance = null;
=======
    private int numJoueurCourant = 0;
    private ArrayList<Cartes> pioche = new ArrayList<Cartes>();
    private ArrayList<Cartes> tas = new ArrayList<Cartes>();
    private ArrayList<Joueur> listeDesJoueurs = new ArrayList<Joueur>();
    private static volatile Partie instance = null;
    private ExpertValide PremierExpert = null;
>>>>>>> Stashed changes


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

<<<<<<< Updated upstream
    public void fini(Joueur joueur)throws tourException {
        if(listeDesJoueurs.get(getNumJoueurCourant())!=joueur){
            throw new tourException("Ce n'est pas ton tour");
=======
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
>>>>>>> Stashed changes
        }
        prochainJoueur();
    }
<<<<<<< Updated upstream
    public void poser(Cartes carte,Joueur joueur ) throws valideException,tourException{
=======

    public void piocher(Joueur joueur)throws valideException, tourException
    {
>>>>>>> Stashed changes
        if(!(listeDesJoueurs.get(numJoueurCourant) == joueur))
            throw new tourException("Ce n'est pas ton tour");

        if(!EstValide(carte,tas.get(0))) ///  LES EFFETS DE CARTES ?----------------------------------------------
            throw new valideException("La carte n'est pas valide : PENALITE");
        joueur.main.remove(carte);
        tas.add(0,carte);
    }

<<<<<<< Updated upstream
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
=======
    public void initExpert(ExpertValide Expert )
    {
        PremierExpert = Expert;
    }

    public void poser(Cartes carte,Joueur joueur ) throws valideException,tourException{
        if(!(listeDesJoueurs.get(numJoueurCourant) == joueur))
            throw new tourException("Ce n'est pas ton tour");

        if(!EstValide(carte,getHautTas())) ///  LES EFFETS DE CARTES ?----------------------------------------------
            throw new valideException("La carte n'est pas valide : PENALITE");
        joueur.poseMainCarte(carte);
        tas.add(carte);
>>>>>>> Stashed changes
    }

    public boolean EstValide(Cartes carte,Cartes tas){

<<<<<<< Updated upstream

        return true;
    }

    private int getNBCarteTas(ArrayList tas){
=======
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

    public void fini(Joueur joueur){

        if(listeDesJoueurs.get(getNumJoueurCourant())==joueur)
            prochainJoueur();
    }

    public int getNbTas(){
>>>>>>> Stashed changes
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

