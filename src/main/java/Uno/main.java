package Uno;

import Carte.Cartes;
import Carte.Normale;
import Exception.*;

public class main {
    public static void main(String[] args){
        Partie partie = new Partie(3);

        Normale vert2 = new Normale(2,Cartes.Color.VERT); //ALICE
        Normale jaune6 = new Normale(6,Cartes.Color.JAUNE);

        Normale bleu2 = new Normale(2,Cartes.Color.BLEU); // BOB
        Normale jaune4 = new Normale(4,Cartes.Color.JAUNE);

        Normale bleu9 = new Normale(9,Cartes.Color.BLEU);//CHARLES
        Normale bleu7 = new Normale(7,Cartes.Color.BLEU);

        Normale vert8 = new Normale(8,Cartes.Color.VERT); // TAS

        Normale rouge1 = new Normale(1,Cartes.Color.ROUGE);
        Normale rouge9 = new Normale(0,Cartes.Color.BLEU);
        /*Normale bleu7 = new Normale(7,Cartes.Color.BLEU);
        Normale bleu7 = new Normale(7,Cartes.Color.BLEU);*/

        Joueur Alice = new Joueur("Alice");partie.listeDesJoueurs.add(Alice);
        Joueur Bob = new Joueur("Bob");partie.listeDesJoueurs.add(Bob);
        Joueur Charles = new Joueur("Charles");partie.listeDesJoueurs.add(Charles);

        Bob.main.add(bleu2);
        Bob.main.add(jaune4);
        Bob.main.add(rouge9);
        Alice.main.add(vert2);
        Alice.main.add(jaune6);
        Alice.main.add(rouge1);

        partie.tas.add(vert8);

//-------------------TEST 1
            System.out.println(partie.toString()) ;
            if(Alice==partie.listeDesJoueurs.get(partie.getNumJoueurCourant()))
                System.out.println("C'est le tour d'Alice");
            if(3==Alice.main.size())
                System.out.println("elle a bien 3 carte");
            try{
                partie.poser(vert2,Alice);
            }catch (tourException e) {
                e.printStackTrace();
                System.exit(1);
            }catch (valideException e) {
                e.printStackTrace();
                System.exit(1);
            }
            if(2==Alice.main.size())
                System.out.println("elle a bien une carte");
            if(Alice.main.contains(jaune6)&&Alice.main.contains(rouge1))
                System.out.println("elle a bien les deux bonne cartes");
            if(partie.getHautTas()==vert2){
                System.out.println("La carte en haut est bien celle d'Alice");
            }
            if(partie.getNumCarteTas()==2)
                System.out.println("il y a bien deux cartes dans le tas");
            partie.fini(Alice);
            if(Bob==partie.listeDesJoueurs.get(partie.getNumJoueurCourant()))
                System.out.println("C'est bien le tour de Bob");





    }
}
