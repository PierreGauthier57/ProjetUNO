package UNO;

import UNO.Carte.Cartes;

import java.util.ArrayList;

public class  Joueur {
    String nom ;
    ArrayList<Cartes> main = new ArrayList<Cartes>();

    Joueur(String nom){
        if(nom.trim().equals("")|| nom==null)
            throw new IllegalArgumentException("Le nom ne peut pas être vide");
        this.nom=nom;
    }

    public String getNom() {
        return nom;
    }

    private int getNBCarte(ArrayList main){
        return main.size();
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
