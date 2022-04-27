package UNO.Carte;

import java.util.Objects;

public class Cartes {
    private enum Color {BLEU,ROUGE,VERT,NOIR,JAUNE};
    private Color couleur;
    private int chiffre;

    public Cartes(Color couleur){
        this.Color = setCouleur(couleur);
    }
    public boolean EstValide(Cartes carteTas,Cartes carteAPoser){
        if((carteTas.chiffre != carteAPoser.chiffre )&&( carteTas.couleur!=carteAPoser.couleur)&&(carteAPoser.couleur!=Color.NOIR))
            return false;
        return true;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cartes cartes = (Cartes) o;
        return chiffre == cartes.chiffre && couleur == cartes.couleur;
    }

    @Override
    public int hashCode() {
        return Objects.hash(couleur, chiffre);
    }
}
