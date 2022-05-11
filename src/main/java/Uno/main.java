package Uno;

import Carte.Cartes;
import Exception.*;
import Expert.*;
import Parser.*;

import java.io.FilterOutputStream;

public class main {
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

        int test1 = 0;
        int test2 = 0;
        int test3 = 0;
        int test4 = 0;
        int test5 = 0;
        int test6 = 0;
        int test7 = 0;
        int test8 = 0;
        int test9 = 0;
        int test10 = 0;
        int test11 = 0;
        int test12 = 0;
        int test13 = 0;
        int test14 = 0;
        int test15 = 0;
        int test16 = 0;

//-------------------TEST 1------------------------------------------------------------
        System.out.println("Test1 : Alice joue une carte de même couleur");


        if (Alice != partie.getJoueurCourant()) {
            System.out.println("Test 1.1 = Ce n'est pas le tour d'Alice");
            test1++;
        }
        if (3 != Alice.getNbCarte()) {
            System.out.println("Test 1.2  =elle n'a pas 3 carte");
            test1++;
        }
        try {
            partie.poser(Alice.getCarte("Normale", Cartes.Color.VERT, 2), Alice);
        } catch (valideException e) {
            e.printStackTrace();

        } catch (tourException e) {
            System.out.println(e);
        }
        if (2 != Alice.getNbCarte()) {
            System.out.println("Test 1.3 = Elle n'a pas deux cartes");
            test1++;
        }
        if (!((Alice.getCarte("Normale", Cartes.Color.JAUNE, 6) != null) && (Alice.getCarte("Normale", Cartes.Color.ROUGE, 1) != null))) {
            System.out.println("Test 1.4 = Elle n'a pas les bonnes cartes");
            test1++;
        }
        if (partie.getHautTas() != partie.getCarteTas("Normale", Cartes.Color.VERT, 2)) {
            System.out.println("Test 1.5 =La carte en haut n'est pas celle d'Alice");
            test1++;
        }
        if (partie.getNbTas() != 2) {
            System.out.println("Test 1.6 = il n'y a pas deux cartes dans le tas");
            test1++;
        }
        try {
            partie.fini(Alice);
        } catch (tourException e) {
            e.printStackTrace();
        } catch (unoException e) {
            System.out.println(e);
        }

        if (Bob != partie.getJoueurCourant()) {
            System.out.println("Test 1.7 = Ce n'est pas le tour de Bob");
            test1++;
        }
        System.out.println("-----------------------------------------------");
        System.out.println(7 - test1 + "/7 Test reussi pour le test 1");
        System.out.println("-----------------------------------------------");


//-------------FIN TEST 1---------------------------------------------------
// ---------------TEST2-----------------------------------------------------
        System.out.println(" ");
        System.out.println("Test2 : Bob joue une carte differente mais de même valeur");

        if (3 != Bob.getNbCarte()) {
            System.out.println("Test 2.1  = Bob n'a pas 3 carte");
            test2++;
        }
        try {
            partie.poser(Bob.getCarte("Normale", Cartes.Color.BLEU, 2), Bob);
        } catch (valideException e) {

        } catch (tourException e) {
            e.printStackTrace();
        }
        if (2 != Bob.getNbCarte()) {
            System.out.println("Test 2.2 = Bob n'a pas deux cartes");
            test2++;
        }
        if (!(Bob.getCarte("Normale", Cartes.Color.JAUNE, 4) != null) && (Bob.getCarte("Normale", Cartes.Color.ROUGE, 9) != null)) {
            System.out.println("Test 2.3 = Bob n'a pas les bonnes cartes");
            test2++;
        }
        if (partie.getHautTas() != partie.getCarteTas("Normale", Cartes.Color.BLEU, 2)) {
            System.out.println("Test 2.4 =La carte en haut n'est pas celle de Bob");
            test2++;
        }
        if (partie.getNbTas() != 3) {
            System.out.println("Test 2.5 = il n'y a pas trois cartes dans le tas");
            test2++;
        }

        try {
            partie.fini(Bob);
        } catch (tourException e) {
        } catch (unoException e) {
            System.out.println(e);
        }

        if (Charles != partie.getJoueurCourant()) {
            System.out.println("Test 2.6 = Ce n'est pas le tour de Charles");
            test2++;
        }
        System.out.println("-----------------------------------------------");
        System.out.println(6 - test2 + "/6 Test reussi pour le test 2");
        System.out.println("-----------------------------------------------");

//---------------TEST3---INITIALISATION-----------------------------------------------------

        partie.reinitialiseCarte();
        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCarteSimple.csv", new ParserNormale(null));
        try {
            partie.distributionCartePioche(3);
        } catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();

// ---------------TEST3--------------------------------------------------------
        System.out.println(" ");
        System.out.println("Test3 : Test d'une carte Illegale");

        try {
            partie.poser(Alice.getCarte("Normale", Cartes.Color.JAUNE, 6), Alice);
        } catch (tourException e) {
            System.out.println(e);
        } catch (valideException e) {
            System.out.println(e);
        }
        if (3 != Alice.getNbCarte()) {
            System.out.println("Test 3.1 = Elle n'a pas 3 cartes");
            test3++;
        }
        if (!(Alice.getCarte("Normale", Cartes.Color.JAUNE, 6) != null)) {
            System.out.println("Test 3.2 = Elle n'a pas le six jaune");
            test3++;
        }

        System.out.println("-----------------------------------------------");
        System.out.println(2 - test3 + "/2 Test reussi pour le test 3");
        System.out.println("-----------------------------------------------");

//---------------TEST4---INITIALISATION-----------------------------------------------------
        partie.reinitialiseCarte();
        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCarteSimple.csv", new ParserNormale(null));
        try {
            partie.distributionCartePioche(3);
        } catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();

//---------------TEST4--------------------------------------------------------
        System.out.println(" ");
        System.out.println("Test4 : Test d'un Joueur qui pose deux cartes légales de suite");
        try {
            partie.poser(Alice.getCarte("Normale", Cartes.Color.VERT, 2), Alice);
            partie.fini(Alice);
            partie.poser(Bob.getCarte("Normale", Cartes.Color.BLEU, 2), Bob);
            partie.fini(Bob);
            partie.poser(Charles.getCarte("Normale", Cartes.Color.BLEU, 9), Charles);
            if (Charles.getNbCarte() != 2) {
                System.out.println("Charle n'a pas 2 cartes");
                test4++;
            }
            partie.poser(Charles.getCarte("Normale", Cartes.Color.BLEU, 7), Charles);


        } catch (tourException e) {
            System.out.println(e);
            if (Charles.getNbCarte() != 2) {
                System.out.println("Charle n'a pas 2 cartes");
                test4++;
            }
            if (Charles.getCarte("Normale", Cartes.Color.BLEU, 2) != null) {
                System.out.println("Charle n'a pas le 2 bleu");
                test4++;
            }
        } catch (valideException e) {
            System.out.println(e);
        } catch (unoException e) {
            System.out.println(e);
        }
        System.out.println("-----------------------------------------------");
        System.out.println(3 - test4 + "/3 Test reussi pour le test 4");
        System.out.println("-----------------------------------------------");
        //------------------------TEST5--INITIALISATION--------------------------------------------------------------------
        partie.reinitialiseCarte();
        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCarteSimple.csv", new ParserNormale(null));
        try {
            partie.distributionCartePioche(3);
        } catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();
        //------------------------TEST5----------------------------------------------------------------------
        System.out.println(" ");
        System.out.println("Test5 : Test d’un joueur qui finit son tour sans rien faire");
        try {
            partie.fini(Alice);
        } catch (tourException e) {
            System.out.println(e);
            if (3 != Alice.getNbCarte()) {
                System.out.println("Test 5.1 = Elle n'a pas 3 cartes");
                test5++;
            }
        } catch (unoException e) {
            System.out.println(e);
        }
        System.out.println("-----------------------------------------------");
        System.out.println(1 - test5 + "/1 Test reussi pour le test 5");
        System.out.println("-----------------------------------------------");

//------------------------TEST6--INITIALISATION--------------------------------------------------------------------
        partie.reinitialiseCarte();
        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCarteSimple.csv", new ParserNormale(null));
        try {
            partie.distributionCartePioche(3);
        } catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();
//------------------------TEST6----------------------------------------------------------------------
        System.out.println(" ");
        System.out.println("Test 6 : Test d’un joueur qui joue puis pioche");

        try {
            partie.poser(Alice.getCarte("Normale", Cartes.Color.VERT, 2), Alice);
            partie.piocher(Alice);
        } catch (valideException e) {
            System.out.println(e);
        } catch (tourException e) {
            System.out.println(e);
            if (2 != Alice.getNbCarte()) {
                System.out.println("Test 6.1 = Elle n'a pas 2 cartes");
                test6++;
            }
        }
        if (partie.getHautpioche() != partie.getCartePioche("Normale", Cartes.Color.JAUNE, 6)) {
            System.out.println("Test 6.2 :Ce n'est pas une carte jaune 6 en haut de la pioche");
            test6++;
        }
        System.out.println("-----------------------------------------------");
        System.out.println(2 - test6 + "/2 Test reussi pour le test 6");
        System.out.println("-----------------------------------------------");

//------------------------TEST7--INITIALISATION--------------------------------------------------------------------
        partie.reinitialiseCarte();
        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCarteSimple.csv", new ParserNormale(null));
        try {
            partie.distributionCartePioche(3);
        } catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();
//------------------------TEST7--INITIALISATION--------------------------------------------------------------------
        partie.reinitialiseCarte();
        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCarteSimple.csv", new ParserNormale(null));
        try {
            partie.distributionCartePioche(3);
        } catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();

//------------------------TEST7----------------------------------------------------------------------
        System.out.println(" ");
        System.out.println("Test 7 : Test de la punition pour un coup illégal d’Alice (joueur courant)");
        if (Alice != partie.getJoueurCourant()) {
            System.out.println("Test 7.1 :Alice n'est pas la joueur courant");
            test7++;
        }
        try {
            partie.poser(Alice.getCarte("Normale", Cartes.Color.JAUNE, 6), Alice);
        } catch (valideException e) {
            partie.punition(Alice, true, 2);
            System.out.println(e);
            if (Bob != partie.getJoueurCourant()) {
                System.out.println(" Test 7.2 : Bob n'est pas la joueur courant");
                test7++;
            }
            if (Alice.getNbCarte() != 5) {
                System.out.println("Alice n'a pas 5 cartes");
                test7++;
            }

            if (Alice.getCarte("Normale", Cartes.Color.ROUGE, 4) == null || Alice.getCarte("Normale", Cartes.Color.JAUNE, 6) == null) {
                System.out.println("Alice n'a pas les cartes de la pioche");
                test7++;
            }
            if (partie.getHautpioche() != partie.getCartePioche("Normale", Cartes.Color.VERT, 2)) {
                System.out.println("le haut de la pioche n'est pas le 2vert");
                test7++;
            }
        } catch (tourException e) {
            System.out.println(e);
        }
        System.out.println("-----------------------------------------------");
        System.out.println(5 - test7 + "/5 Test reussi pour le test 7");
        System.out.println("-----------------------------------------------");
//------------------------TEST8--INITIALISATION--------------------------------------------------------------------
        partie.reinitialiseCarte();
        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCarteSimple.csv", new ParserNormale(null));
        try {
            partie.distributionCartePioche(3);
        } catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();

//------------------------TEST8----------------------------------------------------------------------
        System.out.println(" ");
        System.out.println("Test 8 : Test d’une action de bob lorsque ce n’est pas son tour");

        if (Alice != partie.getJoueurCourant()) {
            System.out.println("Test 8.1 :Alice n'est pas la joueur courant");
            test8++;
        }
        try {
            partie.piocher(Bob);
        } catch (valideException e) {
            System.out.println(e);
        } catch (tourException e) {
            System.out.println(e);
            partie.punition(Bob, false, 2);
            if (Alice != partie.getJoueurCourant()) {
                System.out.println("Test 8.2 :Alice n'est pas la joueur courant");
                test8++;
            }
            if (Bob.getNbCarte() != 5) {
                System.out.println("Test 8.3 :Bob n'a pas 5 cartes");
                test8++;
            }
            if (Bob.getCarte("Normale", Cartes.Color.ROUGE, 4) == null || Bob.getCarte("Normale", Cartes.Color.JAUNE, 6) == null) {
                System.out.println("Test 8.4 :Bob n'a pas les cartes de la pioche");
                test8++;
            }
            if (partie.getHautpioche() != partie.getCartePioche("Normale", Cartes.Color.VERT, 2)) {
                System.out.println("le haut de la pioche n'est pas le 2vert");
                test8++;
            }
        }
        System.out.println("-----------------------------------------------");
        System.out.println(5 - test8 + "/5 Test reussi pour le test 8");
        System.out.println("-----------------------------------------------");
//------------------------TEST9--INITIALISATION--------------------------------------------------------------------
        partie.reinitialiseCarte();
        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCarteSimplePourTestUno.csv", new ParserNormale(null));
        try {
            partie.distributionCartePioche(2);
        } catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();

//------------------------TEST9----------------------------------------------------------------------
        System.out.println(" ");
        System.out.println("Test 9 : Test lorsqu’Alice dit Uno ! » au bon moment");

        if (Alice.getNbCarte() != 2) {
            System.out.println("Test 9.1 :Alice n'a pas 2 cartes");
            test9++;
        }
        try {
            partie.poser(Alice.getCarte("Normale", Cartes.Color.VERT, 2), Alice);
            partie.uno(Alice);
            partie.fini(Alice);
        } catch (valideException e) {
            System.out.println(e);
        } catch (tourException e) {
            System.out.println(e);
        } catch (unoException e) {
            System.out.println(e);
        }
        if (Alice.getNbCarte() != 1) {
            System.out.println("Test 9.2 :Alice n'a pas 1 cartes");
            test9++;
        }
        if (partie.getHautTas() != partie.getCarteTas("Normale", Cartes.Color.VERT, 2)) {
            System.out.println("Test 9.3 :le haut du tas n'est pas le 2 vert");
            test9++;
        }
        if (Bob != partie.getJoueurCourant()) {
            System.out.println("Test 9.4 :Bob n'est pas la joueur courant");
            test9++;
        }
        System.out.println("-----------------------------------------------");
        System.out.println(4 - test9 + "/4 Test reussi pour le test 9");
        System.out.println("-----------------------------------------------");
//------------------------TEST10--INITIALISATION--------------------------------------------------------------------
        Alice.setUno(false);
        partie.reinitialiseCarte();
        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCarteSimplePourTestUno.csv", new ParserNormale(null));
        try {
            partie.distributionCartePioche(2);
        } catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();


//------------------------TEST10----------------------------------------------------------------------
        System.out.println(" ");
        System.out.println("Test 10 : Test lorsqu’Alice oubli de dire Uno!");
        try {
            partie.poser(Alice.getCarte("Normale", Cartes.Color.VERT, 2), Alice);
            partie.fini(Alice);
        } catch (valideException e) {
            System.out.println(e);
        } catch (tourException e) {
            System.out.println(e);
        } catch (unoException e) {
            partie.punition(Alice, false, 2);
            if (Alice.getNbCarte() != 3) {
                System.out.println("Test 9.2 :Alice n'a pas 3 cartes");
                test10++;
            }
            if (partie.getHautTas() != partie.getCarteTas("Normale", Cartes.Color.VERT, 2)) {
                System.out.println("Test 9.3 :le haut du tas n'est pas le 2 vert");
                test10++;
            }
            System.out.println(e);
        }
        System.out.println("-----------------------------------------------");
        System.out.println(5 - test10 + "/5 Test reussi pour le test 10");
        System.out.println("-----------------------------------------------");

        //------------------------TEST11--INITIALISATION--------------------------------------------------------------------
        Alice.setUno(false);
        partie.reinitialiseCarte();
        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCarteSimplePourTestUno.csv", new ParserNormale(null));
        try {
            partie.distributionCartePioche(2);
        } catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();

//------------------------TEST11----------------------------------------------------------------------
       System.out.println(" ");
        System.out.println("Test 11 : Test lorsque Bob dit « Uno ! » quand ce n’est pas son tour");
        if(Alice!=partie.getJoueurCourant()){
            System.out.println("Test 11.1 :Alice n'est pas la joueur courant");
            test11++;
        }
        try{
            partie.poser(Alice.getCarte("Normale", Cartes.Color.VERT,2),Alice);
            partie.uno(Bob);
        }catch(valideException e){
            System.out.println(e);
        }catch (tourException e){
            System.out.println(e);
        }catch(unoException e) {
            partie.punition(Bob,false,2);
            if(Alice!=partie.getJoueurCourant()){
                System.out.println("Test 11.2 :Alice n'est pas la joueur courant");
                test11++;
            }
            if(Bob.getNbCarte()!=4){
                System.out.println("Test 11.3 :Bob n'a pas 3 cartes");
                test11++;
            }
            if(Alice!=partie.getJoueurCourant()){
                System.out.println("Test 11.4 :Alice n'est pas la joueur courant");
                test11++;
            }
            if(partie.getHautTas()!=partie.getCarteTas("Normale", Cartes.Color.VERT,2)){
                System.out.println("Test 11.5 :le haut du tas n'est pas le 2 vert");
                test11++;
            }

        }
        System.out.println("-----------------------------------------------");
        System.out.println(5-test11+"/5 Test reussi pour le test 11");
        System.out.println("-----------------------------------------------");
        //------------------------TEST12--INITIALISATION--------------------------------------------------------------------
        Alice.setUno(false);
        partie.reinitialiseCarte();
        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCartePasser.csv",new ParserNormale(new ParserPasser(null)));
        try {
            partie.distributionCartePioche(3);
        }
        catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();

//------------------------TEST12----------------------------------------------------------------------
        System.out.println(" ");
        System.out.println("Test 12 : Test de coups légaux avec des cartes « Passe ton tour »\n");
        if(Alice!=partie.getJoueurCourant()){
            System.out.println("Test 12.1 :Alice n'est pas la joueur courant");
            test12++;
        }
        try {
            partie.poser(Alice.getCarte("Passer", Cartes.Color.ROUGE),Alice);
            partie.fini(Alice);


            if(Charles!=partie.getJoueurCourant()){
                System.out.println("Test 12.2 :Charles n'est pas la joueur courant");
                test12++;
            }
            if(partie.getHautTas()!=partie.getCarteTas("Passer", Cartes.Color.ROUGE)){
                System.out.println("Test 12.3 :le haut du tas n'est pas le passer rouge");
                test12++;
            }

            partie.poser(Charles.getCarte("Passer", Cartes.Color.VERT),Charles);
            partie.fini(Charles);
            if(Bob!=partie.getJoueurCourant()){
                System.out.println("Test 12.4 :Bob n'est pas la joueur courant");
                test12++;
            }
            if(partie.getHautTas()!=partie.getCarteTas("Passer", Cartes.Color.VERT)){
                System.out.println("Test 12.5 :le haut du tas n'est pas le passer Vert");
                test12++;
            }
            partie.poser(Bob.getCarte("Normale", Cartes.Color.VERT,6),Bob);
            partie.fini(Bob);
            if(Charles!=partie.getJoueurCourant()){
                System.out.println("Test 12.6 :Charles n'est pas la joueur courant");
                test12++;
            }
            if(partie.getHautTas()!=partie.getCarteTas("Normale", Cartes.Color.VERT,6)){
                System.out.println("Test 12.7 :le haut du tas n'est pas le 6 Vert");
                test12++;
            }

        }catch(tourException e){
            System.out.println(e);
        }catch(valideException e){
            System.out.println(e);
        } catch (unoException e) {
            System.out.println(e);
        }
        System.out.println("-----------------------------------------------");
        System.out.println(7-test12+"/7 Test reussi pour le test 12");
        System.out.println("-----------------------------------------------");
//------------------------TEST13--INITIALISATION--------------------------------------------------------------------
       Alice.setUno(false);
        partie.reinitialiseCarte();
        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCartePasser.csv",new ParserNormale(new ParserPasser(null)));
        try {
            partie.distributionCartePioche(3);
        }
        catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();


//------------------------TEST13----------------------------------------------------------------------
        System.out.println(" ");
        System.out.println("Test 13 : Test d’une carte simple illégale sur un « Passe ton tour »");

        if(Alice!=partie.getJoueurCourant()){
            System.out.println("Test 13.1 :Alice n'est pas la joueur courant");
            test13++;
        }
        try {
            partie.poser(Alice.getCarte("Passer", Cartes.Color.ROUGE), Alice);
            partie.fini(Alice);
            if(Charles!=partie.getJoueurCourant()){
                System.out.println("Test 13.2 :Charles n'est pas la joueur courant");
                test13++;
            }
            if(Charles.getNbCarte()!=3){
                System.out.println("Test 13.3 :Charles n'a pas 3 cartes");
                test13++;
            }
            partie.poser(Charles.getCarte("Normale", Cartes.Color.BLEU,1), Charles);
            partie.fini(Charles);
        }catch(tourException e){
        System.out.println(e);
        }catch(valideException e){
            if(Charles.getNbCarte()!=3){
                System.out.println("Test 13.3 :Charles n'a pas 3 cartes");
                test13++;
            }
        System.out.println(e);
        } catch (unoException e) {
        System.out.println(e);
        }
        System.out.println("-----------------------------------------------");
        System.out.println(4-test13+"/4 Test reussi pour le test 13");
        System.out.println("-----------------------------------------------");
//------------------------TEST14--INITIALISATION--------------------------------------------------------------------
        Alice.setUno(false);
        partie.reinitialiseCarte();
        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCartePasser.csv",new ParserNormale(new ParserPasser(null)));
        try {
            partie.distributionCartePioche(3);
        }
        catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();


//------------------------TEST14----------------------------------------------------------------------
        System.out.println(" ");
        System.out.println("Test 14 : Test d’un « Passe ton tour » illégal sur une carte simple");
        if(Alice!=partie.getJoueurCourant()){
            System.out.println("Test 14.1 :Alice n'est pas la joueur courant");
            test14++;
        }
        try{
            partie.poser(Alice.getCarte("Normale", Cartes.Color.BLEU,9),Alice);
            partie.fini(Alice);
            partie.poser(Bob.getCarte("Normale", Cartes.Color.BLEU,7),Bob);
            partie.fini(Bob);
            if(Charles!=partie.getJoueurCourant()){
                System.out.println("Test 14.1 :Charles n'est pas la joueur courant");
                test14++;
            }
            if(Charles.getNbCarte()!=3){
                System.out.println("Test 14.2 :Charles n'a pas 3 cartes");
                test14++;
            }
            partie.poser(Charles.getCarte("Passer", Cartes.Color.VERT),Charles);
            partie.fini(Charles);
        } catch (tourException e) {
            System.out.println(e);
        } catch (unoException e) {
            System.out.println(e);
        } catch (valideException e) {
            if(Charles.getNbCarte()!=3){
                System.out.println("Test 14.2 :Charles n'a pas 3 cartes");
                test14++;
            }
            System.out.println(e);
        }
        System.out.println("-----------------------------------------------");
        System.out.println(4-test14+"/4 Test reussi pour le test 14");
        System.out.println("-----------------------------------------------");
//------------------------TEST15--INITIALISATION--------------------------------------------------------------------
        Alice.setUno(false);
        partie.reinitialiseCarte();
        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCartePlus2.csv",new ParserNormale(new ParserPlus2(null)));
        try {
            partie.distributionCartePioche(3);
        }
        catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();


//------------------------TEST15----------------------------------------------------------------------
        System.out.println(" ");
        System.out.println("Test 15 : Test d’un coup légal avec une carte « +2 »");
        if(Alice!=partie.getJoueurCourant()){
            System.out.println("Test 15.1 :Alice n'est pas la joueur courant");
            test15++;
        }
        try{
            partie.poser(Alice.getCarte("Plus2", Cartes.Color.VERT),Alice);
            partie.fini(Alice);

            if(Bob!=partie.getJoueurCourant()){
                System.out.println("Test 15.1 :Bob n'est pas la joueur courant");
                test15++;
            }
            if(Bob.getNbCarte()!=3){
                System.out.println("Test 15.2 :Bob n'a pas 3 cartes");
                test15++;
            }
            partie.peutJouer(Bob);
            if(Bob.getNbCarte()!=5){
                System.out.println("Test 15.3 :Bob n'a pas 5 cartes");
                test15++;
            }
            if(Charles!=partie.getJoueurCourant()){
                System.out.println("Test 15.4 :Charles n'est pas la joueur courant");
                test15++;
            }
            partie.poser(Charles.getCarte("Normale", Cartes.Color.VERT,1),Charles);
            partie.fini(Charles);
            if(Charles.getNbCarte()!=2){
                System.out.println("Test 15.5 :Charles n'a pas 2 cartes");
                test15++;
            }
        }catch (valideException e){

        }catch (unoException e){

        }catch (tourException e){

        }
        System.out.println("-----------------------------------------------");
        System.out.println(6-test15+"/6 Test reussi pour le test 15");
        System.out.println("-----------------------------------------------");

//------------------------TEST16--INITIALISATION--------------------------------------------------------------------
        Alice.setUno(false);
        partie.reinitialiseCarte();
        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCartePlus2.csv",new ParserNormale(new ParserPlus2(null)));
        try {
            partie.distributionCartePioche(3);
        }
        catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();


//------------------------TEST16----------------------------------------------------------------------
        System.out.println(" ");
        System.out.println("Test 16 : Test d’un coup légal avec cumul de cartes « +2 »");

        if(Alice!=partie.getJoueurCourant()){
            System.out.println("Test 16.1 :Alice n'est pas la joueur courant");
            test16++;
        }
        try{
            partie.piocher(Alice);
            partie.fini(Alice);
            if(Bob!=partie.getJoueurCourant()){
                System.out.println("Test 16.2 :Bob n'est pas la joueur courant");
                test16++;
            }
            partie.piocher(Bob);
            partie.fini(Bob);
            if(Charles!=partie.getJoueurCourant()){
                System.out.println("Test 16.3 :Charles n'est pas la joueur courant");
                test16++;
            }
            partie.poser(Charles.getCarte("Plus2", Cartes.Color.VERT),Charles);
            partie.fini(Charles);
            if(Alice!=partie.getJoueurCourant()){
                System.out.println("Test 16.4 :Alice n'est pas la joueur courant");
                test16++;
            }
            partie.poser(Alice.getCarte("Plus2", Cartes.Color.VERT),Alice);
            partie.fini(Alice);
            if(Bob!=partie.getJoueurCourant()){
                System.out.println("Test 16.5 :Bob n'est pas la joueur courant");
                test16++;
            }
            if(Bob.getNbCarte()!=4){
                System.out.println("Test 16.6 :Bob n'a pas 4 cartes");
            }
            partie.peutJouer(Bob);
            if(Bob.getNbCarte()!=8){
                System.out.println("Test 16.7 :Bob n'a pas 8 cartes");
                test16++;
            }
            if(Charles!=partie.getJoueurCourant()){
                System.out.println("Test 16.8 :Charles n'est pas la joueur courant");
                test16++;
            }
        }catch (unoException e){
            System.out.println(e);
        }catch(valideException e){
            System.out.println(e);
        }catch(tourException e){
            System.out.println(e);

        }
        System.out.println("-----------------------------------------------");
        System.out.println(7-test16+"/7 Test reussi pour le test 15");
        System.out.println("-----------------------------------------------");
// ---------------TEST FINAL-------------------------------------------------------
        System.out.println(" ");
        System.out.println("-----------------------------------------------");
        System.out.println(68-(test1+test3+test2+test4+test5+test6+test7+test8+test9+test10+test11+test12+test13+test14+test15+test16)+"/68 Test reussi dans le projet Uno" );
        System.out.println("-----------------------------------------------");



    }
}
