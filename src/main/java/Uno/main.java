package Uno;

import Carte.Cartes;
import Carte.Normale;
import Exception.*;
import Expert.*;
import Parser.*;

import javax.xml.catalog.Catalog;

public class main {
    public static void main(String[] args){

        Partie partie = Partie.getInstance();

        partie.initExpert(new ExpertColor(new ExpertSimple(null)));

        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCarteSimple.csv",new ParserNormale(null));

        Joueur Alice = partie.ajouterJoueur("Alice");
        Joueur Bob = partie.ajouterJoueur("Bob");
        Joueur Charles = partie.ajouterJoueur("Charles");

        partie.distributionCartePioche(3);

        partie.InitHautTas();

        int  test1 = 0;
        int  test2 = 0;
        int  test3 = 0;
        int test4 = 0;

//-------------------TEST 1------------------------------------------------------------
        System.out.println("Test1 : Alice joue une carte de même couleur");


        if(Alice != partie.getJoueurCourant()){
                System.out.println("Test 1.1 = Ce n'est pas le tour d'Alice");
        test1++;}
            if(3!=Alice.getNbCarte()){
                System.out.println("Test 1.2  =elle n'a pas 3 carte");
            test1++;}
            try{
                partie.poser(Alice.getCarte("Normale", Cartes.Color.VERT, 2),Alice);
            }catch (valideException e) {
                e.printStackTrace();

            }catch (tourException e) {
                e.printStackTrace();
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
            if(partie.getNumCarteTas()!=2){
                System.out.println("Test 1.6 = il n'y a pas deux cartes dans le tas");
        test1++;}
            try {
                partie.fini(Alice);
            }catch (tourException e){
                e.printStackTrace();
                partie.punition(Alice);
            }

            if(Bob != partie.getJoueurCourant()){
                System.out.println("Test 1.7 = Ce n'est pas le tour de Bob");
        test1++;}
        System.out.println("-----------------------------------------------");
        System.out.println(7-test1+"/7 Test reussi pour le test 1");
        System.out.println("-----------------------------------------------");


//-------------FIN TEST 1---------------------------------------------------
// ---------------TEST2-----------------------------------------------------
        System.out.println(" ");
        System.out.println("Test2 : Bob joue une carte differente mais de même valeur");

        if(3!=Bob.getNbCarte()){
            System.out.println("Test 2.1  = Bob n'a pas 3 carte");
            test2++;}
        try{
            partie.poser(Bob.getCarte("Normale", Cartes.Color.BLEU, 2),Bob);
        }catch (valideException e) {

        }catch (tourException e) {
            e.printStackTrace();
        }
        if(2!=Bob.getNbCarte()){
            System.out.println("Test 2.2 = Bob n'a pas deux cartes");
            test2++;}
        if(!(Bob.getCarte("Normale", Cartes.Color.JAUNE, 4) != null) && (Bob.getCarte("Normale", Cartes.Color.ROUGE, 9) != null)){
            System.out.println("Test 2.3 = Bob n'a pas les bonnes cartes");
            test2++;}
        if(partie.getHautTas() != partie.getCarteTas("Normale", Cartes.Color.BLEU, 2)){
            System.out.println("Test 2.4 =La carte en haut n'est pas celle de Bob");
            test2++;
        }
        if(partie.getNumCarteTas()!=3){
            System.out.println("Test 2.5 = il n'y a pas trois cartes dans le tas");
            test2++;}

        try{
            partie.fini(Bob);
        }catch (tourException e) {
        partie.punition(Bob);
    }
        if(Charles != partie.getJoueurCourant()){
            System.out.println("Test 2.6 = Ce n'est pas le tour de Charles");
            test1++;}
        System.out.println("-----------------------------------------------");
        System.out.println(6-test2+"/6 Test reussi pour le test 2");
        System.out.println("-----------------------------------------------");

//---------------TEST3---INITIALISATION-----------------------------------------------------

        partie.reinitialiseCarte();
        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCarteSimple.csv",new ParserNormale(null));
        partie.distributionCartePioche(3);
        partie.InitHautTas();
        System.out.println(partie.toString());
// ---------------TEST3--------------------------------------------------------
        System.out.println(" ");
        System.out.println("Test3 : Test d'une carte Illegale");

        try{
            partie.poser(Alice.getCarte("Normale", Cartes.Color.JAUNE, 6),Alice);
        }catch (tourException e){
            System.out.println(e);
        }catch (valideException e){
            System.out.println(e);
        }
        if(3!=Alice.getNbCarte()){
            System.out.println("Test 3.1 = Elle n'a pas 3 cartes");
            test3++;}
        if(!(Alice.getCarte("Normale", Cartes.Color.JAUNE, 6) != null)){
            System.out.println("Test 3.2 = Elle n'a pas le six jaune");
            test3++;}

        System.out.println("-----------------------------------------------");
        System.out.println(2-test3+"/2 Test reussi pour le test 2");
        System.out.println("-----------------------------------------------");

//---------------TEST4---INITIALISATION-----------------------------------------------------
        partie.reinitialiseCarte();
        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCarteSimple.csv",new ParserNormale(null));
        partie.distributionCartePioche(3);
        partie.InitHautTas();

        System.out.println(partie.toString());
        System.out.println(Alice.getMain().toString());
        System.out.println(Bob.getMain().toString());
        System.out.println(Charles.getMain().toString());
//---------------TEST4--------------------------------------------------------
        System.out.println(" ");
        System.out.println("Test4 : Test d'un Joueur qui pose deux cartes légales de suite");
    try {
        partie.poser(Alice.getCarte("Normale", Cartes.Color.VERT, 2),Alice);
        partie.fini(Alice);
        partie.poser(Bob.getCarte("Normale", Cartes.Color.BLEU, 2),Bob);
        partie.fini(Bob);
        partie.poser(Charles.getCarte("Normale", Cartes.Color.BLEU, 9),Charles);
        System.out.println(Charles.toString());
        partie.poser(Charles.getCarte("Normale", Cartes.Color.BLEU, 7),Charles);
        System.out.println(Charles.toString());

    }catch (tourException e){
        System.out.println(e);
    }catch (valideException e){
        System.out.println(e);
    }

// ---------------TEST FINAL-------------------------------------------------------
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("-----------------------------------------------");
        System.out.println(15-(test1+test3+test2+test4)+"/15 Test reussi Dans le projet uno");
        System.out.println("-----------------------------------------------");


    }
}
