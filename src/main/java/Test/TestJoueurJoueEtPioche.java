package Test;

import Carte.Cartes;
import Expert.*;
import Parser.*;
import Uno.Joueur;
import Uno.Partie;
import Exception.*;

public class TestJoueurJoueEtPioche {
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
        }
        partie.InitHautTas();

        int test6 = 0;

        System.out.println(" ");
        System.out.println("Test 6 : Test d’un joueur qui joue puis pioche");

        try{
            Alice.poser(Alice.getCarte("Normale", Cartes.Color.VERT, 2));
            partie.piocher(Alice);
        }catch(valideException e){
            System.out.println(e);
        }catch(tourException e){
            if(2!=Alice.getNbCarte()){
                System.out.println("Test 6.1 = Elle n'a pas 2 cartes");
                test6++;}
        }
        if(partie.getHautpioche()!= partie.getCartePioche("Normale", Cartes.Color.JAUNE,6)){
            System.out.println("Test 6.2 :Ce n'est pas une carte jaune 6 en haut de la pioche" );
            test6++;
        }
        System.out.println("-----------------------------------------------");
        System.out.println(2-test6+"/2 Test reussi pour le test 6");
        System.out.println("-----------------------------------------------");
    }
}
