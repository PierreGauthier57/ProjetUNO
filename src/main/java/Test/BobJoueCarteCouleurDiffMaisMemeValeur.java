package Test;

import Carte.Cartes;
import Expert.*;
import Parser.*;
import Uno.Joueur;
import Uno.Partie;
import Exception.*;

public class BobJoueCarteCouleurDiffMaisMemeValeur {
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

        int test1 = 0;
        int test2 = 0;

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


//-------------FIN TEST 1---------------------------------------------------
        // ---------------TEST2-----------------------------------------------------
        System.out.println(" ");
        System.out.println("Test2 : Bob joue une carte differente mais de même valeur");

        if(3!=Bob.getNbCarte()){
            System.out.println("Test 2.1  = Bob n'a pas 3 carte");
            test2++;}
        try{
            Bob.poser(Bob.getCarte("Normale", Cartes.Color.BLEU, 2));
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
        if(partie.getNbTas()!=3){
            System.out.println("Test 2.5 = il n'y a pas trois cartes dans le tas");
            test2++;}

        try{
            partie.fini(Bob);
        }
        catch(tourException e) {
        }
        catch(unoException e) {
            System.out.println(e);
        } catch (valideException e) {
            e.printStackTrace();
        }

        if(Charles != partie.getJoueurCourant()){
            System.out.println("Test 2.6 = Ce n'est pas le tour de Charles");
            test2++;}
        System.out.println("-----------------------------------------------");
        System.out.println(6-test2+"/6 Test reussi pour le test 2");
        System.out.println("-----------------------------------------------");
    }
}
