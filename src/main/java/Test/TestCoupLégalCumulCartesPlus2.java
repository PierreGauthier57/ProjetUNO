package Test;
import Carte.Cartes;
import Exception.*;
import Expert.*;
import Parser.ParserNormale;
import Parser.ParserPlus2;
import Uno.Joueur;
import Uno.Partie;


public class TestCoupLégalCumulCartesPlus2 {
    public static void main(String[] args) {
        int test16 = 0;
        Partie partie = Partie.getInstance();

        partie.initExpert(new ExpertCouleur(new ExpertNormale(new ExpertChangeSens(new ExpertPlus2(new ExpertPasser(null))))));

        Joueur Alice = partie.ajouterJoueur("Alice");
        Joueur Bob = partie.ajouterJoueur("Bob");
        Joueur Charles = partie.ajouterJoueur("Charles");

        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCartePlus2.csv", new ParserNormale(new ParserPlus2(null)));
        try {
            partie.distributionCartePioche(3);
        } catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();
        System.out.println(" ");
        System.out.println("Test 16 : Test d’un coup légal avec cumul de cartes « +2 »");

        if (Alice != partie.getJoueurCourant()) {
            System.out.println("Test 16.1 :Alice n'est pas la joueur courant");
            test16++;
        }
        try {
            partie.piocher(Alice);
            partie.fini(Alice);
            if (Bob != partie.getJoueurCourant()) {
                System.out.println("Test 16.2 :Bob n'est pas la joueur courant");
                test16++;
            }
            partie.piocher(Bob);
            partie.fini(Bob);
            if (Charles != partie.getJoueurCourant()) {
                System.out.println("Test 16.3 :Charles n'est pas la joueur courant");
                test16++;
            }
            partie.poser(Charles.getCarte("Plus2", Cartes.Color.VERT), Charles);
            partie.fini(Charles);
            if (Alice != partie.getJoueurCourant()) {
                System.out.println("Test 16.4 :Alice n'est pas la joueur courant");
                test16++;
            }
            partie.poser(Alice.getCarte("Plus2", Cartes.Color.VERT), Alice);
            partie.fini(Alice);
            if (Bob != partie.getJoueurCourant()) {
                System.out.println("Test 16.5 :Bob n'est pas la joueur courant");
                test16++;
            }
            if (Bob.getNbCarte() != 4) {
                System.out.println("Test 16.6 :Bob n'a pas 4 cartes");
            }
            partie.peutJouer(Bob);
            if (Bob.getNbCarte() != 8) {
                System.out.println("Test 16.7 :Bob n'a pas 8 cartes");
                test16++;
            }
            if (Charles != partie.getJoueurCourant()) {
                System.out.println("Test 16.8 :Charles n'est pas la joueur courant");
                test16++;
            }
        } catch (unoException e) {
            System.out.println(e);
        } catch (valideException e) {
            System.out.println(e);
        } catch (tourException e) {
            System.out.println(e);

        }
        System.out.println("-----------------------------------------------");
        System.out.println(7 - test16 + "/7 Test reussi pour le test 15");
        System.out.println("-----------------------------------------------");
    }
}