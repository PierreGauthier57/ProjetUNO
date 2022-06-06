package Test;
import Carte.Cartes;
import Exception.*;
import Expert.*;
import Parser.*;
import Uno.Joueur;
import Uno.Partie;

public class TestCoupLegauxAvecPasseTour {
    public static void main(String[] args) {
        int test12 = 0;
        Partie partie = Partie.getInstance();

        partie.initExpert(new ExpertCouleur(new ExpertNormale(new ExpertChangeSens(new ExpertPlus2(new ExpertPasser(null))))));

        Joueur Alice = partie.ajouterJoueur("Alice");
        Joueur Bob = partie.ajouterJoueur("Bob");
        Joueur Charles = partie.ajouterJoueur("Charles");

        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCartePasser.csv", new ParserNormale(new ParserPasser(null)));
        try {
            partie.distributionCartePioche(3);
        } catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();
        System.out.println(" ");
        System.out.println("Test 12 : Test de coups légaux avec des cartes « Passe ton tour »\n");
        if (Alice != partie.getJoueurCourant()) {
            System.out.println("Test 12.1 :Alice n'est pas la joueur courant");
            test12++;
        }
        try {
            Alice.poser(Alice.getCarte("Passer", Cartes.Color.ROUGE));
            partie.fini(Alice);


            if (Charles != partie.getJoueurCourant()) {
                System.out.println("Test 12.2 :Charles n'est pas la joueur courant");
                test12++;
            }
            if (partie.getHautTas() != partie.getCarteTas("Passer", Cartes.Color.ROUGE)) {
                System.out.println("Test 12.3 :le haut du tas n'est pas le passer rouge");
                test12++;
            }
            Charles.poser(Charles.getCarte("Passer", Cartes.Color.VERT));
            partie.fini(Charles);
            if (Bob != partie.getJoueurCourant()) {
                System.out.println("Test 12.4 :Bob n'est pas la joueur courant");
                test12++;
            }
            if (partie.getHautTas() != partie.getCarteTas("Passer", Cartes.Color.VERT)) {
                System.out.println("Test 12.5 :le haut du tas n'est pas le passer Vert");
                test12++;
            }
            Bob.poser(Bob.getCarte("Normale", Cartes.Color.VERT, 6));
            partie.fini(Bob);
            if (Charles != partie.getJoueurCourant()) {
                System.out.println("Test 12.6 :Charles n'est pas la joueur courant");
                test12++;
            }
            if (partie.getHautTas() != partie.getCarteTas("Normale", Cartes.Color.VERT, 6)) {
                System.out.println("Test 12.7 :le haut du tas n'est pas le 6 Vert");
                test12++;
            }

        } catch (tourException e) {
            System.out.println(e);
        } catch (valideException e) {
            System.out.println(e);
        } catch (unoException e) {
            System.out.println(e);
        }
        System.out.println("-----------------------------------------------");
        System.out.println(7 - test12 + "/7 Test reussi pour le test 12");
        System.out.println("-----------------------------------------------");
    }
}