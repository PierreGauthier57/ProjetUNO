package Test;

import Carte.Cartes;
import Expert.*;
import Parser.*;
import Uno.Joueur;
import Uno.Partie;
import Exception.*;

public class Test7 {
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

        int test7 = 0;

        System.out.println(" ");
        System.out.println("Test 7 : Test de la punition pour un coup illégal d’Alice (joueur courant)");
        if(Alice!=partie.getJoueurCourant()){
            System.out.println("Test 7.1 :Alice n'est pas la joueur courant");
            test7++;
        }
        try{
            partie.poser(Alice.getCarte("Normale", Cartes.Color.JAUNE,6),Alice);
        }catch(valideException e){
            partie.punition(Alice,true,2);
            System.out.println(e);
            if(Bob!=partie.getJoueurCourant()){
                System.out.println(" Test 7.2 : Bob n'est pas la joueur courant");
                test7++;
            }
            if(Alice.getNbCarte()!=5){
                System.out.println("Alice n'a pas 5 cartes");
                test7++;
            }

            if(Alice.getCarte("Normale",Cartes.Color.ROUGE,4)==null || Alice.getCarte("Normale",Cartes.Color.JAUNE,6)==null){
                System.out.println("Alice n'a pas les cartes de la pioche");
                test7++;
            }
            if(partie.getHautpioche()!=partie.getCartePioche("Normale", Cartes.Color.VERT,2)){
                System.out.println("le haut de la pioche n'est pas le 2vert");
                test7++;
            }
        }catch(tourException e){
            System.out.println(e);
        }
        System.out.println("-----------------------------------------------");
        System.out.println(5-test7+"/5 Test reussi pour le test 7");
        System.out.println("-----------------------------------------------");
    }
}
