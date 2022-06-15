package application.projetuno;

import Carte.*;
import Uno.*;
import Exception.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class JeuControleur {

    private ArrayList<JoueurControleur> ListeJoueur;

    private VBox Vbox ;
    private Canvas canSabot;
    private Label msg ;
    private ImageView fond;
    private Scene scene;

    private static volatile JeuControleur Jeu = null;

    private JeuControleur() {}

    public static JeuControleur getJeu() {
        if(Jeu == null)
            Jeu = new JeuControleur();
        return Jeu;
    }

    private Label initLabelMsg(String Msg) {

        Label lNom = new Label(Msg);
        lNom.setFont(new Font("Arial", 25));

        return lNom;
    }

    public void setMsg(String Msg)
    {
        msg.setText(Msg);
    }

    public void setMsg(String Msg, Color color)
    {
        msg.setText(Msg);
        msg.setTextFill(color);
    }

    public Canvas getCanSabot()
    {
        return canSabot;
    }

    public VBox initSabot(ArrayList<JoueurControleur> Liste,ImageView fond,Scene scene) {

        this.fond = fond;
        this.scene = scene;
        Vbox = new VBox();

        Vbox.setAlignment(Pos.CENTER);

        canSabot = new Canvas();
        msg = initLabelMsg("Debut de la partie");
        Vbox.getChildren().add(msg);
        Vbox.setMinWidth(400);
        Vbox.getChildren().add(canSabot);

        dessinerSabot();

        ListeJoueur = Liste;

        canSabot.setOnMouseClicked(clic -> {

            try
            {
                Partie.getInstance().getJoueurCourant().pioche();
                setMsg(Partie.getInstance().getJoueurCourant().getNom() + " à Piocher",Color.BLACK);
            }
            catch (tourException e)
            {
                setMsg(Partie.getInstance().getJoueurCourant().getNom() + " , " + e.getMessage(),Color.RED);
                System.out.println(e);
                Partie.getInstance().punition(Partie.getInstance().getJoueurCourant(),true,2);
            }
            catch (valideException e)
            {
                setMsg(Partie.getInstance().getJoueurCourant().getNom() + " , " + e.getMessage(),Color.RED);
                System.out.println(e);
                Partie.getInstance().punition(Partie.getInstance().getJoueurCourant(),true,2);
            }

            for (JoueurControleur J : Liste)
            {
                J.updateMain();
            }
            dessinerSabot();
        });

        return Vbox;
    }

    public void dessinerSabot() {

        fond.setFitHeight(scene.getHeight());
        fond.setFitWidth(scene.getWidth());

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
