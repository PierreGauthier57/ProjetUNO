package Test;

import Carte.Cartes;
import Expert.*;
import Parser.*;
import Uno.Joueur;
import Uno.Partie;
import Exception.*;

public class TestJoueurPose2CartesLégale {
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

        int test4 = 0;

        System.out.println(" ");
        System.out.println("Test4 : Test d'un Joueur qui pose deux cartes légales de suite");
        try {
            Alice.poser(Alice.getCarte("Normale", Cartes.Color.VERT, 2));
            partie.fini(Alice);
            Bob.poser(Bob.getCarte("Normale", Cartes.Color.BLEU, 2));
            partie.fini(Bob);
            Charles.poser(Charles.getCarte("Normale", Cartes.Color.BLEU, 9));
            if(Charles.getNbCarte()!=2){
                System.out.println("Charle n'a pas 2 cartes");
                test4++;
            }
            Charles.poser(Charles.getCarte("Normale", Cartes.Color.BLEU, 7));


        }catch (tourException e) {
            if (Charles.getNbCarte() != 2) {
                System.out.println("Charle n'a pas 2 cartes");
                test4++;
            }
            if(Charles.getCarte("Normale",Cartes.Color.BLEU,2)!=null){
                System.out.println("Charle n'a pas le 2 bleu");
                test4++;
            }
        }catch (valideException e){
            System.out.println(e);
        }catch(unoException e) {
            System.out.println(e);
        }
        System.out.println("-----------------------------------------------");
        System.out.println(3-test4+"/3 Test reussi pour le test 4");
        System.out.println("-----------------------------------------------");
    }
}
