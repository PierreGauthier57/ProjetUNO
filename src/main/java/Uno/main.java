package Uno;

import Carte.Cartes;
import Parser.*;
import Exception.*;

import java.util.ArrayList;

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

    public static void main(String[] args)
    {
        Partie Uno = Partie.getInstance();

        Uno.ChoisirJeuDeCarte("jeux_test/JeuTestCarteSimple.csv",new ParserNormale(null));

        Joueur Alice = Uno.ajouterJoueur("Alice");
        Joueur Bob = Uno.ajouterJoueur("Bob");
        Joueur Charles = Uno.ajouterJoueur("Charles");

        Uno.distributionCartePioche(3);

        Uno.InitHautTas();

        System.out.println(" Tests coups légaux avec des cartes simples ") ;
        System.out.println("===============================================") ;
        System.out.println("TEST : Alice joue une carte de la bonne couleur ") ;
        System.out.println("-----------------------------------------------") ;

        if(Alice == Uno.getJoueurCourant())
            System.out.println("[OK] le joueur courant est bien Alice");
        else
            System.out.println("[NOT OK] le joueur courant n'est pas Alice");

        if(3==Alice.getNbCarte())
            System.out.println("[OK] Alice possède bien 3 cartes");
        else
            System.out.println("[NOT OK] Alice n'a pas 3 carte");

        try{
            System.out.println("Alice joue le « 2 Vert »");
            Uno.poser(Alice.getCarte("Normale", Cartes.Color.VERT, 2),Alice);
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
        if(2 == Alice.getNbCarte())
            System.out.println("[OK] Alice possède bien 2 cartes");
        else
            System.out.println("[NOT OK] Alice n'a pas 2 carte");

        if((Alice.getCarte("Normale", Cartes.Color.JAUNE, 6) != null) && (Alice.getCarte("Normale", Cartes.Color.ROUGE, 1) != null))
            System.out.println("[OK] Alice a bien le « 6 jaune » et le « 1 rouge »");
        else
            System.out.println("[NOT OK] Alice n'a pas le « 6 jaune » et le « 1 rouge »");

        if(Uno.getHautTas() == Uno.getCarteTas("Normale", Cartes.Color.VERT, 2))
            System.out.println("[OK] la carte au sommet du tas est le « 2 Vert »");
        else
            System.out.println("[NOT OK] la carte au sommet du tas n'est pas le « 2 Vert »");

        if(Uno.getNbTas() == 2)
            System.out.println("[OK] le nombre de cartes du tas est 2");
        else
            System.out.println("[NOT OK] il y a pas deux cartes dans le tas ");

        System.out.println("Alice finit le tour");
        Uno.fini(Alice);

        if(Bob==Uno.getJoueurCourant())
            System.out.println("[OK] C'est bien le tour de Bob");
        else
            System.out.println("[NOT OK] C'est pas le tour de Bob");

        System.out.println("-----------------------------------------------") ;
        System.out.println("TEST : Bob joue une carte de couleur différente mais de même valeur") ;
        System.out.println("-----------------------------------------------") ;
        if(3 == Bob.getNbCarte())
            System.out.println("[OK] Bob possède bien 3 cartes");
        else
            System.out.println("[NOT OK] Bob n'a pas 3 carte");

        try
        {
            System.out.println("Bob joue le « 2 Bleu »");
            Uno.poser(Bob.getCarte("Normale", Cartes.Color.BLEU, 2),Bob);
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

        if(2 == Bob.getNbCarte())
            System.out.println("[OK] Bob possède bien 2 cartes");
        else
            System.out.println("[NOT OK] Bob n'a pas 2 carte");

        if((Bob.getCarte("Normale", Cartes.Color.JAUNE, 4) != null) && (Bob.getCarte("Normale", Cartes.Color.ROUGE, 9) != null))
            System.out.println("[OK] Bob a bien le « 4 jaune » et le « 9 rouge »");
        else
            System.out.println("[NOT OK] Bob n'a pas le « 4 jaune » et le « 9 rouge »");

        if(Uno.getHautTas() == Uno.getCarteTas("Normale", Cartes.Color.BLEU, 2))
            System.out.println("[OK] la carte au sommet du tas est le « 2 Bleu »");
        else
            System.out.println("[NOT OK] la carte au sommet du tas n'est pas le « 2 Bleu »");

        if(Uno.getNbTas() == 3)
            System.out.println("[OK] le nombre de cartes du tas est 3");
        else
            System.out.println("[NOT OK] il y a pas deux cartes dans le tas ");

        System.out.println("Bob finit le tour");
        Uno.fini(Bob);

        if(Charles == Uno.getJoueurCourant())
            System.out.println("[OK] C'est le tour de Charles de jouer");
        else
            System.out.println("[NOT OK] C'est pas le tour de Charles");

    }
}
