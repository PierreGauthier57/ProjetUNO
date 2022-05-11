package Test;
import Carte.Cartes;
import Exception.*;
import Expert.*;
import Parser.*;
import Uno.Joueur;
import Uno.Partie;

public class Test11 {
    public static void main(String[] args) {
        int test11 = 0;
        Partie partie = Partie.getInstance();

        partie.initExpert(new ExpertCouleur(new ExpertNormale(new ExpertChangeSens(new ExpertPlus2(new ExpertPasser(null))))));

        Joueur Alice = partie.ajouterJoueur("Alice");
        Joueur Bob = partie.ajouterJoueur("Bob");
        Joueur Charles = partie.ajouterJoueur("Charles");

        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCarteSimplePourTestUno.csv", new ParserNormale(null));
        try {
            partie.distributionCartePioche(2);
        } catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();
        System.out.println(" ");
        System.out.println("Test 11 : Test lorsque Bob dit « Uno ! » quand ce n’est pas son tour");
        if (Alice != partie.getJoueurCourant()) {
            System.out.println("Test 11.1 :Alice n'est pas la joueur courant");
            test11++;
        }
        try {
            partie.poser(Alice.getCarte("Normale", Cartes.Color.VERT, 2), Alice);
            partie.uno(Bob);
        } catch (valideException e) {
            System.out.println(e);
        } catch (tourException e) {
            System.out.println(e);
        } catch (unoException e) {
            partie.punition(Bob, false, 2);
            if (Alice != partie.getJoueurCourant()) {
                System.out.println("Test 11.2 :Alice n'est pas la joueur courant");
                test11++;
            }
            if (Bob.getNbCarte() != 4) {
                System.out.println("Test 11.3 :Bob n'a pas 3 cartes");
                test11++;
            }
            if (Alice != partie.getJoueurCourant()) {
                System.out.println("Test 11.4 :Alice n'est pas la joueur courant");
                test11++;
            }
            if (partie.getHautTas() != partie.getCarteTas("Normale", Cartes.Color.VERT, 2)) {
                System.out.println("Test 11.5 :le haut du tas n'est pas le 2 vert");
                test11++;
            }

        }
        System.out.println("-----------------------------------------------");
        System.out.println(5 - test11 + "/5 Test reussi pour le test 11");
        System.out.println("-----------------------------------------------");
    }
}