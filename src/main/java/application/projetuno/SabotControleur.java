package application.projetuno;

import Carte.*;
import Uno.*;
import Exception.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class SabotControleur {

    private ArrayList<JoueurControleur> ListeJoueur;

    private Canvas canSabot;

    private static volatile SabotControleur Sabot = null;

    private SabotControleur()
    {

    }

    public static SabotControleur getSabot() {
        if(Sabot == null)
            Sabot = new SabotControleur();
        return Sabot;
    }

    public Canvas getCanSabot()
    {
        return canSabot;
    }

    public Canvas initSabot(ArrayList<JoueurControleur> Liste) {

        canSabot = new Canvas();

        dessinerSabot();

        ListeJoueur = Liste;

        canSabot.setOnMouseClicked(clic -> {
            System.out.println("Pioche!");
            try
            {
                Partie.getInstance().getJoueurCourant().pioche();
            }
            catch (tourException e)
            {
                System.out.println(e);
                Partie.getInstance().punition(Partie.getInstance().getJoueurCourant(),true,2);
            }
            catch (valideException e)
            {
                System.out.println(e);
                Partie.getInstance().punition(Partie.getInstance().getJoueurCourant(),true,2);
            }

            for (JoueurControleur J : Liste)
            {
                J.updateMain();
            }
            dessinerSabot();
        });

        return canSabot;
    }

    public void dessinerSabot() {
        Image sabot = new Image(getClass().getResourceAsStream("/Sabot.png"));
        Image dos = new Image(getClass().getResourceAsStream("/carte_dos.png"));
        canSabot.setWidth(sabot.getWidth());
        canSabot.setHeight(sabot.getHeight());

        Cartes hautTas = Partie.getInstance().getHautTas();

        Cartes.Color couleur = hautTas.getCouleur();
        String color = null;

        switch (couleur) {
            case VERT -> color = "Vert";
            case ROUGE -> color = "Rouge";
            case BLEU -> color = "Bleu";
            case JAUNE -> color = "Jaune";
            case NOIR -> color = "Noir";
        }

        Image imageCarte = null;

        if (hautTas instanceof Normale) {
            imageCarte  = new Image(getClass().getResourceAsStream("/carte_" + ((Normale) hautTas).getChiffre() + "_" + color + ".png"));
        } else if (hautTas instanceof ChangeSens) {
            imageCarte  = new Image(getClass().getResourceAsStream("/carte_" + "Change_" + color + ".png"));
        } else if (hautTas instanceof Plus2) {
            imageCarte  = new Image(getClass().getResourceAsStream("/carte_" + "Plus2_" + color + ".png"));
        } else if (hautTas instanceof Couleur) {
            imageCarte  = new Image(getClass().getResourceAsStream("/carte_" + "Change_Couleur" + ".png"));
        } else if (hautTas instanceof Passer) {
            imageCarte  = new Image(getClass().getResourceAsStream("/carte_" + "Passe_" + color + ".png"));
        }

        canSabot.getGraphicsContext2D().drawImage(sabot, 0, 0);

        if (Partie.getInstance().getNbTas() > 0)
        for (int i = 0; i < Partie.getInstance().getNbTas(); i++)
        {
            canSabot.getGraphicsContext2D().drawImage(imageCarte, 25 + i*0.25, 25 - i * 0.1);
        }

        if (Partie.getInstance().getNbPioche() > 0)
        for (int i = 0; i < Partie.getInstance().getNbPioche(); i++)
        {
            canSabot.getGraphicsContext2D().drawImage(dos, 124 +  i*0.25, 25 -  i * 0.1);
        }

        if(ListeJoueur != null)
        for (JoueurControleur J : ListeJoueur)
        {
            J.updateMain();
        }
    }
}
