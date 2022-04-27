import java.util.Objects;

public class Cartes {
    enum Color {BLEU,ROUGE,VERT,NOIR,JAUNE};
    Color couleur;
    int chiffre;

    public Cartes(int chiffre,Color couleur){
        if (chiffre <0 || chiffre >9)
            throw new IllegalArgumentException("Le chiffre doit être compris entre 0 et 9!");
        this.chiffre=chiffre;
        this.couleur= couleur;
    }
    public boolean EstValide(Cartes carteTas,Cartes carteAPoser){
        if((carteTas.chiffre != carteAPoser.chiffre )&&( carteTas.couleur!=carteAPoser.couleur)&&(carteAPoser.couleur!=Color.NOIR))
            return false;
        return true;
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
