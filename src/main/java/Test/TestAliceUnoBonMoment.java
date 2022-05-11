package Test;
import Carte.Cartes;
import Exception.*;
import Expert.*;
import Parser.*;
import Uno.Joueur;
import Uno.Partie;

public class TestAliceUnoBonMoment {
    public static void main(String[] args) {
        int test9 = 0;
        Partie partie = Partie.getInstance();

        partie.initExpert(new ExpertCouleur(new ExpertNormale(new ExpertChangeSens(new ExpertPlus2(new ExpertPasser(null))))));

        Joueur Alice = partie.ajouterJoueur("Alice");
        Joueur Bob = partie.ajouterJoueur("Bob");
        Joueur Charles = partie.ajouterJoueur("Charles");

        partie.ChoisirJeuDeCarte("jeux_test/JeuTestCarteSimplePourTestUno.csv",new ParserNormale(null));
        try {
            partie.distributionCartePioche(2);
        } catch (PiocheException e) {
            System.out.println(e);
            System.exit(1);
        }
        partie.InitHautTas();
        System.out.println(" ");
        System.out.println("Test 9 : Test lorsqu’Alice dit Uno ! » au bon moment");

        if(Alice.getNbCarte()!=2){
            System.out.println("Test 9.1 :Alice n'a pas 2 cartes");
            test9++;
        }
        try{
            Alice.poser(Alice.getCarte("Normale", Cartes.Color.VERT, 2));
            partie.uno(Alice);
            partie.fini(Alice);
        }catch(valideException e){
            System.out.println(e);
        } catch (tourException e){
            System.out.println(e);
        }catch(unoException e) {
            System.out.println(e);
        }
        if(Alice.getNbCarte()!=1){
            System.out.println("Test 9.2 :Alice n'a pas 1 cartes");
            test9++;
        }
        if(partie.getHautTas()!=partie.getCarteTas("Normale", Cartes.Color.VERT,2)){
            System.out.println("Test 9.3 :le haut du tas n'est pas le 2 vert");
            test9++;
        }
        if(Bob!=partie.getJoueurCourant()){
            System.out.println("Test 9.4 :Bob n'est pas la joueur courant");
            test9++;
        }
        System.out.println("-----------------------------------------------");
        System.out.println(4-test9+"/4 Test reussi pour le test 9");
        System.out.println("-----------------------------------------------");
    }
}