package Uno;

import Carte.*;
import Exception.*;
import Expert.*;
import Parser.*;
import java.util.ArrayList;
import java.util.Collections;

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
    /**
     * la fonction reinitialise toute les cartes du jeu.
     *
     */
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
    /**
     * la fonction sert à faire finir de jouer un joueur.
     * @param joueur le joueur qui fini son tour.
     *@throws tourException si ce n'est pas le tour du joueur.
     *@throws valideException si le joueur n'a déjà joué.
     *@throws unoException si le joueur n'a pas dit uno avant de terminer et qu'il lui reste une carte.
     */
    public void fini(Joueur joueur) throws tourException, unoException, valideException {

        if(listeDesJoueurs.get(getNumJoueurCourant()) != joueur)
            throw new tourException("Ce n'est pas ton tour : PUNITION");
        if((cartePoser == 0) && (cartePiocher == 0))
            throw new valideException("tu n'a pas jouer : PUNITION");
        if(joueur.getNbCarte()==1 && joueur.getUno()==false)
            throw new unoException("Le joueur n'a pas dit uno : PUNITION");
        if(getEffet())
        {
            getHautTas().effet();
        }
        effet = false;
        joueur.setAjouer(false);
        prochainJoueur();
    }
    /**
     * la fonction punit un joueur en lui rajoutant nbCarte cartes et en passant son tour.
     *
     * @param joueur Le joueur à punir.
     * @param nbCarte Le nombre de carte à lui donner
     * @param passeTour un boolean qui renvoie vrai si on doit faire passer son tour au joueur et faux si non.
     *@throws tourException si ce n'est pas le tour du joueur.
     *@throws valideException si le joueur n'a déjà joué.
     *@throws unoException si le joueur n'a pas dit uno avant de terminer et qu'il lui reste une carte.
     */
    public void punition(Joueur joueur,boolean passeTour,int nbCarte){

        System.out.println(cumulEffet);
        for(int i = 0 ; i < (nbCarte + cumulEffet) ; i++) {
            //joueur.ajouterMainCarte(pioche.get(0));
            //pioche.remove(0);
            piocheCarte(joueur);
        }

        if( passeTour == true)
        {
            prochainJoueur();
        }
        cumulEffet = 0;
        effet = false;
    }
    /**
     * la fonction fait dire Uno à un joueur.
     * @param joueur Le joueur qui dit Uno.
     *@throws tourException si ce n'est pas le tour du joueur.
     *@throws unoException si le joueur a plus d'une carte.
     */
    public void uno(Joueur joueur) throws unoException,tourException {
        if(joueur != getJoueurCourant()){
            throw new tourException("Ce n'est pas ton tour! : PUNITION");
        }

        if(joueur.getNbCarte()==1)
        {
            joueur.setUno(true);
        }
        else
            throw new unoException("Il te reste plus d'une carte! : PUNITION");
    }
    /**
     * la fonction test si la pioche est vide ou non.
     * @return true si la pioche est vide.
     * @return false si la pioche n'est pas vide.
     */
    public boolean PiocheVide() {

        if(getNbPioche() == 0)
        {
            return true;
        }
        return false;
    }
    /**
     * la fonction test si le tas est vide ou non.
     * @return true si le tas est vide.
     * @return false si le tas n'est pas vide.
     */
    public boolean TasVide() {

        if(getNbTas() == 0)
        {
            return true;
        }
        return false;
    }
    /**
     * la fonction verifie si la pioche est vide ou non et corrige le nombre de carte de la pioche si besoin.
     * @return true si la pioche n'est pas vide.
     * @return false si la pioche et le tas sont vides.
     */
    public boolean verifierPioche(){

        if(PiocheVide())
        {
            if(!TasVide())
            {
                for (Cartes C : tas)
                {
                    pioche.add(C);
                }
                Collections.shuffle(pioche);
                tas.clear();
                InitHautTas();

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
    /**
     * la fonction choisi un jeu de carte pour jouer.
     * @param Parser Un parser qui permet d'initialiser un jeu de cartes.4
     * @param nomFichier L'adresse ou se trouve le jeu de cartes.
     */
    public void ChoisirJeuDeCarte(String nomFichier,ParserValide Parser)
    {
        FichierCarteCSV.initJeuCarte(nomFichier,pioche,Parser);
        Collections.shuffle(pioche);
    }
    /**
     * la fonction ajoute un joueur au jeu.
     * @param nom Nom du joueur à ajouter.
     * @return le joueur à ajouter.
     */
    public Joueur ajouterJoueur(String nom)
    {
        listeDesJoueurs.add(new Joueur(nom));
        return listeDesJoueurs.get(listeDesJoueurs.size() - 1);
    }
    /**
     * la fonction renvoie le joueur qui joue au moment t.
     * @return le joueur courant
     */
    public Joueur getJoueurCourant()
    {
        return listeDesJoueurs.get(numJoueurCourant);
    }
    /**
     * la fonction supprime un joueur au jeu.
     * @param J Joueur à supprimer
     * @throws IllegalArgumentException Si le joueur est null
     */
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
    /**
     * la fonction supprime un joueur au jeu.
     * @param numJ Numéro du joueur à supprimer
     * @throws IllegalArgumentException Si le joueur est null
     */
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
    /**
     * la fonction initialise le haut du tas.
     */
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
    /**
     * la fonction distribue nbCarteParJ à chaque joueur.
     * @param nbCarteParJ le nombres de cartes à distribuer à chaque joueur.
     * @throws PiocheException Si le joueur est null
     */
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
    /**
     * la fonction sert à faire piocher un joueur.
     * @param joueur Le joueur qui pioche.
     */
    private void piocheCarte(Joueur joueur)
    {
        if(verifierPioche())
        {
            joueur.ajouterMainCarte(getHautpioche());
            pioche.remove(getHautpioche());
        }
    }
    /**
     * la fonction sert à faire piocher un joueur.
     * @param joueur Le joueur qui pioche.
     *@throws tourException si ce n'est pas le tour du joueur.
     *@throws valideException si le joueur a déjà joué.
     */
    public void piocher(Joueur joueur) throws valideException, tourException
    {
        if(!(listeDesJoueurs.get(numJoueurCourant) == joueur))
            throw new tourException("Ce n'est pas ton tour! : PUNITION");
        if(cartePoser > 0 || cartePiocher >  0)
            throw new valideException("Tu as déjà joué! :PUNITION");
        cartePiocher++;
        joueur.setAjouer(true);
        piocheCarte(joueur);
    }
    /**
     * la fonction initialise l'expert.
     */
    public void initExpert(ExpertValide Expert )
    {
        PremierExpert = Expert;
    }
    /**
     * la fonction inverse le sens du jeu.
     */
    public void inverseSens()
    {
        sensHoraire = !sensHoraire;
    }
    /**
     * la fonction nous dis si un joueur peut jouer après une suite de carte qui a un cumul (+2).
     * @param joueur Le joueur qui joue après la suite de cartes.
     * @return faux si le joueur ne peux pas continuer la suite.
     * @return vrai si le joueur peut continuer la suite.
     */
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
    /**
     * la fonction sert à faire poser la carte du joueur.
     *
     * @param carte La carte que le joueur va poser.
     * @param joueur Le joueur qui pose la carte.
     *@throws tourException si ce n'est pas le tour du joueur.
     *@throws valideException si le joueur a déjà joué.
     */
    public void poser(Cartes carte,Joueur joueur ) throws valideException,tourException{
        if(!(listeDesJoueurs.get(numJoueurCourant) == joueur))
            throw new tourException("Ce n'est pas ton tour! : PUNITION");
        if(cartePoser > 0 || cartePiocher > 0)
            throw new valideException("Tu as déjà joué! : PUNITION");
        if(carte == null)
            throw new IllegalArgumentException("il ne possede pas la carte( carte == null");
        if(!EstValide(carte,getHautTas()))
            throw new valideException("La carte n'est pas valide! : PUNITION");
        joueur.poseMainCarte(carte);
        cartePoser++;
        tas.add(carte);
    }
    /**
     * la fonction verifie si une carte est valide, que ce soit par la couleur ou par le chiffre, via un expert.
     * @param tas La carte au dessus du tas.
     * @param carte La carte que le joueur va poser.
     * @return vrai si la carte est valide.
     * @return faux si la carte n'est pas valide.
     */
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
    /**
     * la fonction declare le prochain joueur comme le joueur courant.
     *
     */
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
    /**
     * la fonction renvoie le prochain joueur.
     *
     */
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

