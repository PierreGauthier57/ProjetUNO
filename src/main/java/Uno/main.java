package UNO;

import UNO.Carte.Cartes;

public class main {
    public static void main(String[] args) {
        Cartes sixrouge = new Cartes(Cartes.Color.ROUGE);
        Cartes dixrouge = new Cartes(Cartes.Color.ROUGE);
        Joueur joueur1 = new Joueur("anna",sixrouge);
        //Joueur joueur2 = new Joueur("luc",dixrouge);

        Partie partie = new Partie(1,dixrouge);
        System.out.println(partie.toString());
    }
}
