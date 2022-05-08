package Carte;
import Uno.*;
public class Plus2 extends Cartes {

    public Plus2(Color couleur) {
        super(couleur);
    }

    public void Plus2(Color couleur){
        this.setCouleur(couleur);
    }

    public void donne2(Joueur joueur, Partie pioche){

    }

    @Override
    public String toString() {

        return "Plus2{" + super.toString();
    }
}
