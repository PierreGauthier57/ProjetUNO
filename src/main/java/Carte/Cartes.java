package Carte;

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

    public void getType(Color couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return couleur + "}";
    }
}
