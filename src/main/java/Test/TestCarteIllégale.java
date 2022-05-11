package Test;

import Carte.Cartes;
import Expert.*;
import Parser.*;
import Uno.Joueur;
import Uno.Partie;
import Exception.*;

public class TestCarteIllégale {
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

        int test3 = 0;

        System.out.println(" ");
        System.out.println("Test3 : Test d'une carte Illegale");

        try{
            Alice.poser(Alice.getCarte("Normale", Cartes.Color.JAUNE, 6));
        }catch (tourException e){
            System.out.println(e);
        }catch (valideException e){
        }
        if(3!=Alice.getNbCarte()){
            System.out.println("Test 3.1 = Elle n'a pas 3 cartes");
            test3++;}
        if(!(Alice.getCarte("Normale", Cartes.Color.JAUNE, 6) != null)){
            System.out.println("Test 3.2 = Elle n'a pas le six jaune");
            test3++;}

        System.out.println("-----------------------------------------------");
        System.out.println(2-test3+"/2 Test reussi pour le test 3");
        System.out.println("-----------------------------------------------");
    }
}
