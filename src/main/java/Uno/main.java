package Uno;

import Carte.Cartes;
import Carte.Normale;
import Exception.*;

public class main {
    public static void main(String[] args){
        Partie partie = new Partie(3);
        int  test1 = 0;
        int  test2 = 0;
        int  test3 = 0;

        Normale vert2 = new Normale(2,Cartes.Color.VERT); //ALICE
        Normale jaune6 = new Normale(6,Cartes.Color.JAUNE);

        Normale bleu2 = new Normale(2,Cartes.Color.BLEU); // BOB
        Normale jaune4 = new Normale(4,Cartes.Color.JAUNE);

        Normale bleu9 = new Normale(9,Cartes.Color.BLEU);//CHARLES
        Normale bleu7 = new Normale(7,Cartes.Color.BLEU);

        Normale vert8 = new Normale(8,Cartes.Color.VERT); // TAS

        Normale rouge1 = new Normale(1,Cartes.Color.ROUGE);
        Normale rouge9 = new Normale(0,Cartes.Color.BLEU);
        /*Normale bleu7 = new Normale(7,Cartes.Color.BLEU);
        Normale bleu7 = new Normale(7,Cartes.Color.BLEU);*/

        Joueur Alice = new Joueur("Alice");partie.listeDesJoueurs.add(Alice);
        Joueur Bob = new Joueur("Bob");partie.listeDesJoueurs.add(Bob);
        Joueur Charles = new Joueur("Charles");partie.listeDesJoueurs.add(Charles);

        Bob.main.add(bleu2);
        Bob.main.add(jaune4);
        Bob.main.add(rouge9);
        Alice.main.add(vert2);
        Alice.main.add(jaune6);
        Alice.main.add(rouge1);

        partie.tas.add(vert8);

//-------------------TEST 1------------------------------------------------------------
        System.out.println("Test1 : Alice joue une carte de même couleur");


        if(Alice!=partie.listeDesJoueurs.get(partie.getNumJoueurCourant())){
                System.out.println("Test 1.1 = Ce n'est pas le tour d'Alice");
        test1++;}
            if(3!=Alice.main.size()){
                System.out.println("Test 1.2  =elle n'a pas 3 carte");
            test1++;}
            try{
                partie.poser(vert2,Alice);
            }catch (valideException e) {
                e.printStackTrace();
                System.exit(1);
            }catch (tourException e) {
                e.printStackTrace();
                System.exit(1);
            }
            if(2!=Alice.main.size()){
                System.out.println("Test 1.3 = Elle n'a pas deux cartes");
                test1++;}
            if(!Alice.main.contains(jaune6)||!Alice.main.contains(rouge1)){
                System.out.println("Test 1.4 = Elle n'a pas les bonnes cartes");
        test1++;}
            if(partie.getHautTas()!=vert2){
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

            if(Bob!=partie.listeDesJoueurs.get(partie.getNumJoueurCourant())){
                System.out.println("Test 1.7 = Ce n'est pas le tour de Bob");
        test1++;}
        System.out.println("-----------------------------------------------");
        System.out.println(7-test1+"/7 Test reussi pour le test 1");
        System.out.println("-----------------------------------------------");


//-------------FIN TEST 1---------------------------------------------------
// ---------------TEST2-----------------------------------------------------
        System.out.println(" ");
        System.out.println("Test2 : Bob joue une carte differente mais de même valeur");

        if(3!=Bob.main.size()){
            System.out.println("Test 2.1  =Bob n'a pas 3 carte");
            test2++;}
        try{
            partie.poser(bleu2,Bob);
        }catch (valideException e) {
            e.printStackTrace();
            partie.punition(Bob);
        }catch (tourException e) {
            e.printStackTrace();
            partie.punition(Bob);
        }
        if(2!=Bob.main.size()){
            System.out.println("Test 2.2 = Bob n'a pas deux cartes");
            test2++;}
        if(!Bob.main.contains(jaune4)||!Bob.main.contains(rouge9)){
            System.out.println("Test 2.3 = Bob n'a pas les bonnes cartes");
            test2++;}
        if(partie.getHautTas()!=bleu2){
            System.out.println("Test 2.4 =La carte en haut n'est pas celle de Bob");
            test2++;
        }
        if(partie.getNumCarteTas()!=3){
            System.out.println("Test 2.5 = il n'y a pas trois cartes dans le tas");
            test2++;}

        try{
            partie.fini(Bob);
        }catch (tourException e) {
        e.printStackTrace();
        partie.punition(Bob);
    }
        if(Charles!=partie.listeDesJoueurs.get(partie.getNumJoueurCourant())){
            System.out.println("Test 2.6 = Ce n'est pas le tour de Charles");
            test1++;}
        System.out.println("-----------------------------------------------");
        System.out.println(6-test2+"/6 Test reussi pour le test 2");
        System.out.println("-----------------------------------------------");
//---------------TEST3--------------------------------------------------------



// ---------------TEST FINAL-------------------------------------------------------
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("-----------------------------------------------");
        System.out.println(13-(test2+test3+test2)+"/13 Test reussi Dans le projet uno");
        System.out.println("-----------------------------------------------");


    }
}
