package Uno;

import Carte.Cartes;

import java.util.ArrayList;

public class Joueur {
    String nom;
    ArrayList<Cartes> main = new ArrayList<Cartes>();

    Joueur(String nom, Cartes carte) {
        if (nom.trim().equals("") || nom == null)
            throw new IllegalArgumentException("Le nom ne peut pas être vide");
        this.nom = nom;
        main.add(carte);
    }

}
