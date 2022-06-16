package Uno;

import Carte.Cartes;
import Exception.*;

import java.util.ArrayList;
import Uno.Partie;

public class Joueur
{
    private boolean Ajouer = false;
    private String nom ;
    private ArrayList<Cartes> main = new ArrayList<Cartes>();
    private boolean uno = false;
    /**
     * la fonction créer un joueur à partir d'un nom.
     * @param nom est le nom du joueur
     *@throws IllegalArgumentException si le nom est vide ou null.
     */
    public Joueur(String nom){
        if(nom.trim().equals("")|| nom == null)
            throw new IllegalArgumentException("Le nom ne peut pas être vide");
        this.nom=nom;
    }


    public void setUno(boolean uno) {
        this.uno = uno;
    }

    public boolean getAjouer(){ return Ajouer;}

    public void setAjouer(boolean ajouer) {
        this.Ajouer  = ajouer;
    }

    public boolean getUno(){
        return uno;
    }
    /**
     * la fonction sert à ajouter une carte à la main.
     * @param carte la carte à ajouter à la main.
     */
    public void ajouterMainCarte(Cartes carte)
    {
        main.add(carte);
    }
    /**
     * la fonction sert à enlever une carte de la main.
     * @param carte la carte à enlever de la main.
     */
    public void poseMainCarte(Cartes carte)
    {
        main.remove(carte);
    }

    public int getNbCarte() {
        return main.size();
    }

    public Cartes getCarte(String typeCarte, Cartes.Color Couleur)
    {
        return Cartes.getCarteInList(main,typeCarte,Couleur);
    }
    /**
     * la fonction sert à faire piocher un joueur.
     *@throws tourException si ce n'est pas le tour du joueur.
     *@throws valideException si le joueur a déjà joué .
     */
    public void pioche() throws tourException, valideException {
        Partie partie = Partie.getInstance();
        partie.piocher(this);
    }

    /**
     * la fonction fait dire Uno à un joueur.
     *@throws tourException si ce n'est pas le tour du joueur.
     *@throws unoException si le joueur a plus d'une carte.
     */
    public void Uno() throws tourException,unoException
    {
        Partie partie = Partie.getInstance();
        partie.uno(this);
    }
    /**
     * la fonction sert à faire poser la carte du joueur.
     *
     * @param carte La carte que le joueur va poser.
     *@throws tourException si ce n'est pas le tour du joueur.
     *@throws valideException si le joueur a déjà joué.
     */
    public void poser(Cartes carte) throws tourException, valideException {
        Partie partie = Partie.getInstance();
        partie.poser(carte, this);
    }
    /**
     * la fonction sert à faire finir de jouer un joueur.
     *
     *@throws tourException si ce n'est pas le tour du joueur.
     *@throws valideException si le joueur n'a déjà joué.
     *@throws unoException si le joueur n'a pas dit uno avant de terminer et qu'il lui reste une carte.
     */
    public void fini() throws tourException, unoException, valideException {
        Partie partie = Partie.getInstance();
        partie.fini(this);
    }

    public Cartes getCarte(String typeCarte, Cartes.Color Couleur,int numero)
    {
        return Cartes.getCarteInList(main,typeCarte,Couleur,numero);
    }
    /**
     * la fonction nous dis si un joueur peut jouer après une suite de carte qui a un cumul (+2).
     */
    public void peutJouer()
    {
        Partie partie = Partie.getInstance();
        partie.peutJouer(this);
    }


    public String getNom() {
        return nom;
    }

    public ArrayList<Cartes> getMain() {
        return main;
    }

    /**
     * la fonction renvoie si le joueur la carte dans sa main.
     *
     * @param carte la carte à trouver dans la main.
     * @param main  la main ou l'on effectue la recherche.
     * @return vrai si il a la carte.
     * @return faux si il ne l'a pas.
     */
    private boolean carteDansMain(Cartes carte,ArrayList main){
        return main.contains(carte);
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "nom='" + nom + '\'' +
                ", main=" + main.toString() +
                '}';
    }
}
