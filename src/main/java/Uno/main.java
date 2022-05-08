package Uno;

import Carte.Cartes;
import Parser.*;

import java.util.ArrayList;

public class main {
    public static void main(String[] args)
    {
        ArrayList<Cartes> pioche = FichierCarteCSV.getJeuCarte("jeux_test/JeuTest.csv",new ParserNormale( new ParserPlus2( new ParserChangeSens (new ParserPasser(null)))));
        System.out.println("Pioche : " + pioche.toString());
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
