package Test;
import Carte.Cartes;
import Exception.*;
import Expert.*;
import Parser.*;
import Uno.Joueur;
import Uno.Partie;

public class TestAliceOublieDireUno {
    public static void main(String[] args) {
        int test10 = 0;
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
        System.out.println("Test 10 : Test lorsqu’Alice oubli de dire Uno!");
        try {
            partie.poser(Alice.getCarte("Normale", Cartes.Color.VERT, 2), Alice);
            partie.fini(Alice);
        } catch (valideException e) {
            System.out.println(e);
        } catch (tourException e) {
            System.out.println(e);
        } catch (unoException e) {
            partie.punition(Alice, false, 2);
            if (Alice.getNbCarte() != 3) {
                System.out.println("Test 9.2 :Alice n'a pas 3 cartes");
                test10++;
            }
            if (partie.getHautTas() != partie.getCarteTas("Normale", Cartes.Color.VERT, 2)) {
                System.out.println("Test 9.3 :le haut du tas n'est pas le 2 vert");
                test10++;
            }
        }
        System.out.println("-----------------------------------------------");
        System.out.println(5 - test10 + "/5 Test reussi pour le test 10");
        System.out.println("-----------------------------------------------");
    }
}