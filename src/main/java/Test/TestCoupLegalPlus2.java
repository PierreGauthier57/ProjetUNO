package Test;
import Carte.Cartes;
import Exception.*;
import Expert.*;
import Parser.ParserNormale;
import Parser.ParserPlus2;
import Uno.Joueur;
import Uno.Partie;


public class TestCoupLegalPlus2 {
    public static void main(String[] args) {
        int test15 = 0;
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
        System.out.println("Test 15 : Test d’un coup légal avec une carte « +2 »");
        if (Alice != partie.getJoueurCourant()) {
            System.out.println("Test 15.1 :Alice n'est pas la joueur courant");
            test15++;
        }
        try {
            partie.poser(Alice.getCarte("Plus2", Cartes.Color.VERT), Alice);
            partie.fini(Alice);

            if (Bob != partie.getJoueurCourant()) {
                System.out.println("Test 15.1 :Bob n'est pas la joueur courant");
                test15++;
            }
            if (Bob.getNbCarte() != 3) {
                System.out.println("Test 15.2 :Bob n'a pas 3 cartes");
                test15++;
            }
            partie.peutJouer(Bob);
            if (Bob.getNbCarte() != 5) {
                System.out.println("Test 15.3 :Bob n'a pas 5 cartes");
                test15++;
            }
            if (Charles != partie.getJoueurCourant()) {
                System.out.println("Test 15.4 :Charles n'est pas la joueur courant");
                test15++;
            }
            partie.poser(Charles.getCarte("Normale", Cartes.Color.VERT, 1), Charles);
            partie.fini(Charles);
            if (Charles.getNbCarte() != 2) {
                System.out.println("Test 15.5 :Charles n'a pas 2 cartes");
                test15++;
            }
        } catch (valideException e) {

        } catch (unoException e) {

        } catch (tourException e) {

        }
        System.out.println("-----------------------------------------------");
        System.out.println(6 - test15 + "/6 Test reussi pour le test 15");
        System.out.println("-----------------------------------------------");
    }
}