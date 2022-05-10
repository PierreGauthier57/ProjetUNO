package Uno;

import Carte.Cartes;

import java.util.ArrayList;

public class  Joueur {

    private String nom ;
    private ArrayList<Cartes> main = new ArrayList<Cartes>();

    public Joueur(String nom){
        if(nom.trim().equals("")|| nom == null)
            throw new IllegalArgumentException("Le nom ne peut pas être vide");
        this.nom=nom;
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
        for (Cartes C : main)
        {
            if (C.toString().matches("^.*"+typeCarte+".*$"))
            {
                if (C.toString().matches("^.*"+Couleur+".*$"))
                {
                    return C;
                }
            }
        }
        return null;
    }

    public Cartes getCarte(String typeCarte, Cartes.Color Couleur,int numero)
    {
        for (Cartes C : main)
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

    public String getNom() {
        return nom;
    }

    public ArrayList<Cartes> getMain() {
        return main;
    }

    /*
    private int getNBCarte(ArrayList main){
        return main.size();
    }

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
