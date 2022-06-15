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
    private int cumulEffet = 0;
    private boolean effet = false;

    private Partie() {}

    public boolean getEffet() {
        return effet;
    }
    public void setEffet(boolean effet) {
        this.effet = effet;
    }

    public int getCumulEffet() {
        return cumulEffet;
    }

    public void setCumulEffet(int cumulEffet) {
        this.cumulEffet = cumulEffet;
    }

    public void setCartePiocher(int cartePiocher) {
        this.cartePiocher = cartePiocher;
    }

    public void setCartePoser(int cartePoser) {
        this.cartePoser = cartePoser;
    }

    public void reinitialiseCarte()
    {
        setCartePiocher(0);
        setCartePoser(0);
        numJoueurCourant = 0;
        pioche.clear();
        tas.clear();
        for (Joueur J : listeDesJoueurs)
        {
            J.getMain().clear();
        }
    }

    public ArrayList<Joueur> getListeDesJoueurs()
    {
        return listeDesJoueurs;
    }

    public void fini(Joueur joueur) throws tourException, unoException, valideException {

        if(listeDesJoueurs.get(getNumJoueurCourant()) != joueur)
            throw new tourException("Ce n'est pas ton tour");
        if((cartePoser == 0) && (cartePiocher == 0))
            throw new tourException("tu n'a pas jouer");
        if(joueur.getNbCarte()==1 && joueur.getUno()==false)
            throw new unoException("Le joueur n'a pas dit uno : PENALITE");
        if(!joueur.getAjouer()){
            throw new valideException("Le joueur n'a pas jouer");
        }
        if(getEffet())
        {
            getHautTas().effet();
        }
        effet = false;
        joueur.setAjouer(false);
        prochainJoueur();
    }

    public void punition(Joueur joueur,boolean passeTour,int nbCarte){

        System.out.println(cumulEffet);
        for(int i = 0 ; i < (nbCarte + cumulEffet) ; i++) {
            joueur.ajouterMainCarte(pioche.get(0));
            pioche.remove(0);
        }

        if( passeTour == true)
        {
            prochainJoueur();
        }
        cumulEffet = 0;
        effet = false;
    }

    public void uno(Joueur joueur) throws unoException,tourException {
        if(joueur != getJoueurCourant()){
            throw new tourException("Ce n'est pas ton tour ! PUNITION");
        }

        if(joueur.getNbCarte()==1)
        {
            joueur.setUno(true);
        }
        else
            throw new unoException("Tu peux pas dire Uno ! PUNITION");
    }

    public boolean PiocheVide() {

        if(getNbPioche() == 0)
        {
            return true;
        }
        return false;
    }

    public boolean TasVide() {

        if(getNbTas() == 0)
        {
            return true;
        }
        return false;
    }

    public boolean verifierPioche(){

        if(PiocheVide())
        {
            if(!TasVide())
            {
                for (Cartes C : tas)
                {
                    pioche.add(C);
                    tas.remove(C);
                }
                return true;
            }
            else
            {
                return false;
            }
        }
        return true;
    }

//    public void verifierTas() throws TasException {
//        if(TasVide())
//        {
//            InitHautTas();
//        }
//        //-----------------------------------------------------------------------code a finir car pas utile pour la soutenance
//    }

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

    public void InitHautTas() {

        if (!PiocheVide())
        {
            tas.add(getHautpioche());
            pioche.remove(getHautpioche());
        }
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
        return Cartes.getCarteInList(tas,typeCarte,Couleur,numero);
    }

    public Cartes getCarteTas(String typeCarte, Cartes.Color Couleur)
    {
        return Cartes.getCarteInList(tas,typeCarte,Couleur);
    }

    public Cartes getCartePioche(String typeCarte, Cartes.Color Couleur,int numero)
    {
        return Cartes.getCarteInList(pioche,typeCarte,Couleur,numero);
    }

    public Cartes getCartePioche(String typeCarte, Cartes.Color Couleur)
    {
        return Cartes.getCarteInList(pioche,typeCarte,Couleur);
    }

    public void distributionCartePioche(int nbCarteParJ)throws PiocheException {

        if(nbCarteParJ <= 0)
            throw new PiocheException("Il est impossible de distribuer " + nbCarteParJ + " Carte par joueur");
        if(pioche.size() < nbCarteParJ * listeDesJoueurs.size())
            throw new PiocheException("Il n'y a pas assez de cartes dans la pioche pour faire cette distribution");
        for (int i = 0; i < nbCarteParJ ; i++)
        {
            for (Joueur J : listeDesJoueurs)
            {
                piocheCarte(J);
            }
        }
    }

    private void piocheCarte(Joueur joueur)
    {
        if(verifierPioche())
        {
            joueur.ajouterMainCarte(getHautpioche());
            pioche.remove(getHautpioche());
        }
    }

    public void piocher(Joueur joueur) throws valideException, tourException
    {
        if(!(listeDesJoueurs.get(numJoueurCourant) == joueur))
            throw new tourException("Ce n'est pas ton tour");
        if(cartePoser > 0 || cartePiocher >  0)
            throw new tourException("Tu a deja jouer");
        cartePiocher++;
        joueur.setAjouer(true);
        piocheCarte(joueur);
    }

    public void initExpert(ExpertValide Expert )
    {
        PremierExpert = Expert;
    }

    public void inverseSens()
    {
        sensHoraire = !sensHoraire;
    }

    public boolean peutJouer(Joueur joueur )
    {
        for (Cartes C : joueur.getMain())
        {
            if(EstValide(C,getHautTas()))
                return true;
        }
        if (cumulEffet != 0)
        {
            punition(joueur,true,0);
        }
        return false;
    }

    public void poser(Cartes carte,Joueur joueur ) throws valideException,tourException{
        if(!(listeDesJoueurs.get(numJoueurCourant) == joueur))
            throw new tourException("Ce n'est pas ton tour");
        if(cartePoser > 0 || cartePiocher > 0)
            throw new tourException("Tu a deja jouer");
        if(carte == null)
            throw new IllegalArgumentException("il ne possede pas la carte( carte == null");
        if(!EstValide(carte,getHautTas()))
            throw new valideException("La carte n'est pas valide : PENALITE");
        joueur.setAjouer(true);
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

    public int getNbPioche() {
        return pioche.size();
    }

    public int getNumJoueurCourant() {
        return numJoueurCourant;
    }

    public void setNumJoueurCourant(int numJoueurCourant) {
        this.numJoueurCourant = numJoueurCourant;
    }

    public void prochainJoueur()
    {
        if(sensHoraire)
            numJoueurCourant++;
        else
            numJoueurCourant--;

        if(numJoueurCourant > (listeDesJoueurs.size() - 1))
        {
            setNumJoueurCourant(0);
        }
        else if(numJoueurCourant < 0)
        {
            setNumJoueurCourant(listeDesJoueurs.size() - 1);
        }

        getJoueurCourant().setUno(false);
        cartePoser = 0;
        cartePiocher = 0;

        if(getJoueurCourant().getNbCarte() == 0)
        {
            prochainJoueur();
        }
    }

    public Joueur getProchainJoueur()
    {
        int prochain = numJoueurCourant;
        if(sensHoraire)
            prochain++;
        else
            prochain--;

        if(prochain > (listeDesJoueurs.size() - 1))
        {
            setNumJoueurCourant(0);
        }
        else if(prochain < 0)
        {
            setNumJoueurCourant(listeDesJoueurs.size() - 1);
        }
        return listeDesJoueurs.get(prochain);
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

