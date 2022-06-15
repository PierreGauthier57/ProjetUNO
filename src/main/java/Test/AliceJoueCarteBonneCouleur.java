package Test;

import Carte.Cartes;
import Expert.*;
import Parser.*;
import Uno.Joueur;
import Uno.Partie;
import Exception.*;

public class AliceJoueCarteBonneCouleur {
    public static void main(String[] args) {

        Partie partie = Partie.getInstance();

        partie.initExpert(new ExpertCouleur(new ExpertNormale(new ExpertChangeSens(new ExpertPlus2(new ExpertPasser(null))))));

        Joueur Alice = partie.ajouterJoueur("Alice");
        Joueur Bob = partie.ajouterJoueur("Bob");
        Joueur Charles = partie.ajouterJoueur("Charles");

        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCarteSimple.csv", new ParserNormale(null));
        try {
            partie.distributionCartePioche(3);
        } catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();

        //-------------------TEST 1------------------------------------------------------------
        System.out.println("Test1 : Alice joue une carte de même couleur");

        int test1 = 0;

        if(Alice != partie.getJoueurCourant()){
            System.out.println("Test 1.1 = Ce n'est pas le tour d'Alice");
            test1++;}
        if(3!=Alice.getNbCarte()){
            System.out.println("Test 1.2  =elle n'a pas 3 carte");
            test1++;}
        try{
            Alice.poser(Alice.getCarte("Normale", Cartes.Color.VERT, 2));
        }catch (valideException e) {
            e.printStackTrace();

        }catch (tourException e) {
            System.out.println(e);
        }
        if(2!=Alice.getNbCarte()){
            System.out.println("Test 1.3 = Elle n'a pas deux cartes");
            test1++;}
        if(!((Alice.getCarte("Normale", Cartes.Color.JAUNE, 6) != null) && (Alice.getCarte("Normale", Cartes.Color.ROUGE, 1) != null))){
            System.out.println("Test 1.4 = Elle n'a pas les bonnes cartes");
            test1++;}
        if(partie.getHautTas()!= partie.getCarteTas("Normale", Cartes.Color.VERT, 2)){
            System.out.println("Test 1.5 =La carte en haut n'est pas celle d'Alice");
            test1++;
        }
        if(partie.getNbTas()!=2){
            System.out.println("Test 1.6 = il n'y a pas deux cartes dans le tas");
            test1++;}
        try {
            partie.fini(Alice);
        }catch (tourException e){
            e.printStackTrace();
        }catch(unoException e) {
            System.out.println(e);
        } catch (valideException e) {
        e.printStackTrace();
    }

        if(Bob != partie.getJoueurCourant()){
            System.out.println("Test 1.7 = Ce n'est pas le tour de Bob");
            test1++;}
        System.out.println("-----------------------------------------------");
        System.out.println(7-test1+"/7 Test reussi pour le test 1");
        System.out.println("-----------------------------------------------");


//-------------FIN TEST 1---------------------------------------------------
    }
}
