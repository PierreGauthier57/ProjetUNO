package Test;

import Expert.*;
import Parser.*;
import Uno.Joueur;
import Uno.Partie;
import Exception.*;

public class TestJoueurFiniSansRienFaire {
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

        int test5 = 0;

        //------------------------TEST5----------------------------------------------------------------------
        System.out.println(" ");
        System.out.println("Test5 : Test d’un joueur qui finit son tour sans rien faire");
        try{
            partie.fini(Alice);
        }catch (tourException e){
            System.out.println(e);
            if(3!=Alice.getNbCarte()){
                System.out.println("Test 5.1 = Elle n'a pas 3 cartes");
                test5++;}
        }catch(unoException e) {
            System.out.println(e);
        } catch (valideException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------------------");
        System.out.println(1-test5+"/1 Test reussi pour le test 5");
        System.out.println("-----------------------------------------------");
    }
}
