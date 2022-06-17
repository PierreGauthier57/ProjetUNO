package application.projetuno;

import Expert.*;
import Parser.*;
import Uno.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

import javafx.scene.layout.BorderPane;

public class Uno extends Application {

    private static final int H_CANVAS = 130;
    private static final int L_CANVAS = 550;
    private static final int L_CARTE = 80;
    private static final int ECART = 45;

    private Partie partie = Partie.getInstance();

    private ArrayList<JoueurControleur> Liste = new ArrayList<JoueurControleur>();
    private JoueurControleur J1;
    private JoueurControleur J2;
    private JoueurControleur J3;
    private JoueurControleur J4;

    @Override
    public void start(Stage stage) throws IOException {

        try {
            BorderPane root = new BorderPane();

            Image image = new Image("/Fond.jpg",1920,1080,false,true);
            ImageView imageView = new ImageView(image);
            root.getChildren().add(imageView);

            Scene scene = new Scene(root);
            stage.setScene(scene);

            imageView.setFitHeight(900);
            imageView.setFitWidth(1600);

            JeuControleur.getJeu();

            JoueurControleur.initJoueurControleur(H_CANVAS,L_CANVAS,L_CARTE,ECART);

            J1 = new JoueurControleur("Yann");
            J2 = new JoueurControleur("Camille");
            J3 = new JoueurControleur("Isabelle");
            J4 = new JoueurControleur("Charlotte");

            Liste.add(J1);
            Liste.add(J2);
            Liste.add(J3);
            Liste.add(J4);

            partie.initExpert(new ExpertCouleur(new ExpertNormale(new ExpertPlus2(new ExpertPasser(null)))));
            partie.ChoisirJeuDeCarte("jeux_test/JeuTest.csv", new ParserNormale(new ParserPlus2(new ParserPasser(null))));
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

            root.setCenter(JeuControleur.getJeu().initJeu(Liste));

            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}