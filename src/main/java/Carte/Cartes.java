package Carte;

import java.util.ArrayList;
import java.util.Objects;

public class Cartes {
    public enum Color {BLEU,ROUGE,VERT,NOIR,JAUNE};
    private Color couleur;

    public Cartes(Color couleur){
        setCouleur(couleur);
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public static Cartes getCarteInList(ArrayList<Cartes> cartes, String typeCarte, Cartes.Color Couleur)
    {
        for (Cartes C : cartes)
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

    public static Cartes getCarteInList(ArrayList<Cartes> cartes, String typeCarte, Cartes.Color Couleur, int numero)
    {
        for (Cartes C : cartes)
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

    public void getType(Color couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return couleur + "}";
    }
}
