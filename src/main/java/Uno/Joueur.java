package Uno;

import Carte.Cartes;
import Exception.*;

import java.util.ArrayList;
import Uno.Partie;

public class Joueur
{
    private String nom ;
    private ArrayList<Cartes> main = new ArrayList<Cartes>();
    private boolean uno = false;

    public Joueur(String nom){
        if(nom.trim().equals("")|| nom == null)
            throw new IllegalArgumentException("Le nom ne peut pas être vide");
        this.nom=nom;
    }
    public void setUno(boolean uno) {
        this.uno = uno;
    }

    public boolean getUno(){
        return uno;
    }

    public void ajouterMainCarte(Cartes carte)
    {
        main.add(carte);
    }

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

    public void pioche() throws tourException, valideException {
        Partie partie = Partie.getInstance();
        partie.piocher(this);
    }

    public void poser(Cartes carte) throws tourException, valideException {
        Partie partie = Partie.getInstance();
        partie.poser(carte, this);
    }

    public void fini() throws tourException, valideException, unoException {
        Partie partie = Partie.getInstance();
        partie.fini(this);
    }

    public Cartes getCarte(String typeCarte, Cartes.Color Couleur,int numero)
    {
        return Cartes.getCarteInList(main,typeCarte,Couleur,numero);
    }

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
