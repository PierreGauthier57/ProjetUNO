package Test;
import Carte.Cartes;
import Exception.*;
import Expert.*;
import Parser.*;
import Uno.Joueur;
import Uno.Partie;

public class Test14 {
    public static void main(String[] args) {
        int test14 = 0;
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
        System.out.println("Test 14 : Test d’un « Passe ton tour » illégal sur une carte simple");
        if (Alice != partie.getJoueurCourant()) {
            System.out.println("Test 14.1 :Alice n'est pas la joueur courant");
            test14++;
        }
        try {
            partie.poser(Alice.getCarte("Normale", Cartes.Color.BLEU, 9), Alice);
            partie.fini(Alice);
            partie.poser(Bob.getCarte("Normale", Cartes.Color.BLEU, 7), Bob);
            partie.fini(Bob);
            if (Charles != partie.getJoueurCourant()) {
                System.out.println("Test 14.1 :Charles n'est pas la joueur courant");
                test14++;
            }
            if (Charles.getNbCarte() != 3) {
                System.out.println("Test 14.2 :Charles n'a pas 3 cartes");
                test14++;
            }
            partie.poser(Charles.getCarte("Passer", Cartes.Color.VERT), Charles);
            partie.fini(Charles);
        } catch (tourException e) {
            System.out.println(e);
        } catch (unoException e) {
            System.out.println(e);
        } catch (valideException e) {
            if (Charles.getNbCarte() != 3) {
                System.out.println("Test 14.2 :Charles n'a pas 3 cartes");
                test14++;
            }
            System.out.println(e);
        }
        System.out.println("-----------------------------------------------");
        System.out.println(4 - test14 + "/4 Test reussi pour le test 14");
        System.out.println("-----------------------------------------------");
    }
}
