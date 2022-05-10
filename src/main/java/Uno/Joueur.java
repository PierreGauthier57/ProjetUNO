package Uno;

import Carte.Cartes;

import java.util.ArrayList;

public class Joueur
{
    private String nom ;
    private ArrayList<Cartes> main = new ArrayList<Cartes>();
    private boolean UNO = false;

    public Joueur(String nom){
        if(nom.trim().equals("")|| nom == null)
            throw new IllegalArgumentException("Le nom ne peut pas être vide");
        this.nom=nom;
    }

    public void ditUNO()
    {
        UNO = true;
    }

    public void setUNO(boolean UNO)
    {
        this.UNO = UNO;
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

    public Cartes getCarte(String typeCarte, Cartes.Color Couleur,int numero)
    {
        return Cartes.getCarteInList(main,typeCarte,Couleur,numero);
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
