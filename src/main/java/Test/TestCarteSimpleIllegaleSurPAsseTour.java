package Test;
import Carte.Cartes;
import Exception.*;
import Expert.*;
import Parser.*;
import Uno.Joueur;
import Uno.Partie;

public class TestCarteSimpleIllegaleSurPAsseTour {
    public static void main(String[] args) {
        int test13 = 0;
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
        System.out.println("Test 13 : Test d’une carte simple illégale sur un « Passe ton tour »");

        if (Alice != partie.getJoueurCourant()) {
            System.out.println("Test 13.1 :Alice n'est pas la joueur courant");
            test13++;
        }
        try {
            Alice.poser(Alice.getCarte("Passer", Cartes.Color.ROUGE));
            partie.fini(Alice);
            if (Charles != partie.getJoueurCourant()) {
                System.out.println("Test 13.2 :Charles n'est pas la joueur courant");
                test13++;
            }
            if (Charles.getNbCarte() != 3) {
                System.out.println("Test 13.3 :Charles n'a pas 3 cartes");
                test13++;
            }
            Charles.poser(Charles.getCarte("Normale", Cartes.Color.BLEU, 1));
            partie.fini(Charles);
        } catch (tourException e) {
            System.out.println(e);
        } catch (valideException e) {
            if (Charles.getNbCarte() != 3) {
                System.out.println("Test 13.3 :Charles n'a pas 3 cartes");
                test13++;
            }
        } catch (unoException e) {
            System.out.println(e);
        }
        System.out.println("-----------------------------------------------");
        System.out.println(4 - test13 + "/4 Test reussi pour le test 13");
        System.out.println("-----------------------------------------------");
    }
}