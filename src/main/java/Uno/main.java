package Uno;

import Carte.Cartes;
import Parser.*;
import Exception.*;

import java.util.ArrayList;

public class main {
    public static void main(String[] args)
    {
        Partie Uno = Partie.getInstance();

        Uno.ChoisirJeuDeCarte("jeux_test/JeuTestCarteSimple.csv",new ParserNormale(null));

        Joueur Alice = Uno.ajouterJoueur("Alice");
        Joueur Bob = Uno.ajouterJoueur("Bob");
        Joueur Charles = Uno.ajouterJoueur("Charles");

        Uno.distributionCartePioche(3);

        System.out.println(Uno.toString()) ;
        if(Alice == Uno.getJoueurCourant())
            System.out.println("C'est le tour d'Alice");
        if(3==Alice.main.size())
            System.out.println("elle a bien 3 carte");
        try{
            Uno.poser(Alice.getCarte("Normal", Cartes.Color.VERT, 2),Alice);
        }
        catch (tourException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        catch (valideException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        if(2==Alice.main.size())
            System.out.println("elle a bien 2 carte");

        if(Alice.main.contains(Alice.getCarte("Normal", Cartes.Color.JAUNE, 6))&&Alice.main.contains(Alice.getCarte("Normal", Cartes.Color.ROUGE, 1)))
            System.out.println("elle a bien les deux bonne cartes");
        if(Uno.getHautTas()==Uno.getCarteTas("Normal", Cartes.Color.VERT, 2)){
            System.out.println("La carte en haut est bien celle d'Alice");
        }
        if(Uno.getNumCarteTas()==2)
            System.out.println("il y a bien deux cartes dans le tas");
        Uno.fini(Alice);
        if(Bob==Uno.getJoueurCourant())
            System.out.println("C'est bien le tour de Bob");



    }
}
