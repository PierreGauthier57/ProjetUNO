package Carte;
import Uno.*;
public class Plus2 extends Cartes {

    public Plus2(Color couleur) {
        super(couleur);
    }

    public void effet()
    {
        Partie.getInstance().setCumulEffet(Partie.getInstance().getCumulEffet() + 2);
    }

    @Override
    public String toString() {

        return "Plus2{" + super.toString();
    }
}
