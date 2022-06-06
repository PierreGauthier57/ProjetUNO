package Test;

import Carte.Cartes;
import Expert.*;
import Parser.*;
import Uno.Joueur;
import Uno.Partie;
import Exception.*;

public class TestActionBobPasSonTour {
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

        int test8 = 0;

        System.out.println(" ");
        System.out.println("Test 8 : Test d’une action de bob lorsque ce n’est pas son tour");

        if(Alice!=partie.getJoueurCourant()){
            System.out.println("Test 8.1 :Alice n'est pas la joueur courant");
            test8++;
        }
        try{
            partie.piocher(Bob);
        }catch(valideException e){
            System.out.println(e);
        }catch(tourException e) {
            partie.punition(Bob,false,2);
            if(Alice!=partie.getJoueurCourant()){
                System.out.println("Test 8.2 :Alice n'est pas la joueur courant");
                test8++;
            }
            if(Bob.getNbCarte()!=5){
                System.out.println("Test 8.3 :Bob n'a pas 5 cartes");
                test8++;
            }
            if(Bob.getCarte("Normale", Cartes.Color.ROUGE,4)==null || Bob.getCarte("Normale",Cartes.Color.JAUNE,6)==null){
                System.out.println("Test 8.4 :Bob n'a pas les cartes de la pioche");
                test8++;
            }
            if(partie.getHautpioche()!=partie.getCartePioche("Normale", Cartes.Color.VERT,2)){
                System.out.println("le haut de la pioche n'est pas le 2vert");
                test8++;
            }
        }
        System.out.println("-----------------------------------------------");
        System.out.println(5-test8+"/5 Test reussi pour le test 8");
        System.out.println("-----------------------------------------------");
    }
}
