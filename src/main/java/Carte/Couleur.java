package Carte;

public class Couleur extends Cartes
{
    public Couleur(Color couleur)
    {
        super(couleur);
    }

    public void effet()
    {

    }

    @Override
    public String toString() {
        return "Couleur{" + super.toString();
    }
}
