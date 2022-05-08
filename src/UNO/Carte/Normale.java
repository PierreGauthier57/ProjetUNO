package UNO.Carte;

public class Normale extends Cartes{
    private int chiffre;

    public Normale(int chiffre,Color couleur){
        super(couleur);
        setChiffre(chiffre);
    }

    public void setChiffre(int chiffre) {
        this.chiffre = chiffre;
    }

    public int getChiffre() {
        return chiffre;
    }

    public boolean EstValide(int chiffre){
        return true;
    }
    @Override
    public Color getCouleur() {
        return super.getCouleur();
    }

    @Override
    public String toString() {
        return "Normale{" +
                "chiffre=" + chiffre +" "+getCouleur()+
                '}';
    }
}
