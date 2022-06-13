package application.projetuno;

import Carte.*;
import Expert.*;
import Parser.*;
import Uno.*;
import Exception.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Pour iMac :
 * Le chemin des modules FX sont là :
 * /Users/dordal/cdt-master/javafx-sdk-11.0.2/lib
 * Il faut ajouter les arguments :
 *  --module-path /Users/dordal/cdt-master/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml
 * et décocher la case ...Xstart....
 */

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Uno extends Application {

    private static final int H_CANVAS = 130;
    private static final int L_CANVAS = 400;
    private static final int L_CARTE = 80;
    private static final int ECART = 50;

    private Canvas canSabot;
    private Partie partie = Partie.getInstance();

    private JoueurControleur J1 ;
    private JoueurControleur J2 ;
    private JoueurControleur J3 ;
    private JoueurControleur J4 ;

    @Override
    public void start(Stage stage) throws IOException {

        try {

            BorderPane root = new BorderPane();

            Scene scene = new Scene(root);
            stage.setScene(scene);

            JoueurControleur.initJoueurControleur(H_CANVAS,L_CANVAS,L_CARTE,ECART);
            J1 = new JoueurControleur("Yann");
            J2 = new JoueurControleur("Camille");
            J3 = new JoueurControleur("Isabelle");
            J4 = new JoueurControleur("Charlotte");

            partie.initExpert(new ExpertCouleur(new ExpertNormale(new ExpertChangeSens(new ExpertPlus2(new ExpertPasser(null))))));
            partie.ChoisirJeuDeCarte("jeux_test/JeuTest.csv", new ParserNormale(new ParserPlus2(new ParserPasser(new ParserCouleur(new ParserChangeSens(null))))));
            partie.distributionCartePioche(5);
            partie.InitHautTas();

            J1.updateMain();
            J2.updateMain();
            J3.updateMain();
            J4.updateMain();

            root.setTop(J1.getVbox());
            root.setRight(J2.getVbox());
            root.setBottom(J3.getVbox());
            root.setLeft(J4.getVbox());

            root.setCenter(initSabot());

            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private Canvas initSabot() {

        canSabot = new Canvas();

        dessinerSabot();

        canSabot.setOnMouseClicked(clic -> {
            System.out.println("Pioche!");

            try
            {
                Partie.getInstance().getJoueurCourant().pioche();
            }
            catch (tourException e)
            {
                e.printStackTrace();
            }
            catch (valideException e)
            {
                e.printStackTrace();
            }
            J1.updateMain();
            J2.updateMain();
            J3.updateMain();
            J4.updateMain();
        });

        return canSabot;
    }

    private void dessinerSabot() {
        Image sabot = new Image(getClass().getResourceAsStream("/Sabot.png"));
        Image dos = new Image(getClass().getResourceAsStream("/carte_dos.png"));
        canSabot.setWidth(sabot.getWidth());
        canSabot.setHeight(sabot.getHeight());

        /* normalement, il faut retourner la première carte de la pioche pour amorcer
         * la manche. J'initialise cela en dur mais vous devrez changer cela en fonction
         * de vos classes
         */

        Image imageCarte = new Image(getClass().getResourceAsStream("/carte_6_Rouge.png"));

        canSabot.getGraphicsContext2D().drawImage(sabot, 0, 0);
        canSabot.getGraphicsContext2D().drawImage(imageCarte, 25, 20);
        canSabot.getGraphicsContext2D().drawImage(dos, 124, 20);
    }

    public static void main(String[] args)
    {
        launch(args);

    }
}