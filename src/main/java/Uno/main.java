package Uno;

import Carte.Cartes;
import Parser.*;

import java.util.ArrayList;

public class main {
    public static void main(String[] args)
    {
        Partie Uno = Partie.getInstance();

        Uno.ChoisirJeuDeCarte("jeux_test/JeuTest.csv",new ParserNormale(null));

        Uno.ajouterJoueur("Alice");
        Uno.ajouterJoueur("Bob");
        Uno.ajouterJoueur("Charles");

        Uno.distributionCartePioche(3);
        
        /*
        Cartes sixrouge = new Cartes(Cartes.Color.ROUGE);
        Cartes dixrouge = new Cartes(Cartes.Color.ROUGE);
        Joueur joueur1 = new Joueur("anna",sixrouge);
        //Joueur joueur2 = new Joueur("luc",dixrouge);

        //Partie partie = new Partie(1,dixrouge);
        //System.out.println(partie.toString());
        */
    }
}
