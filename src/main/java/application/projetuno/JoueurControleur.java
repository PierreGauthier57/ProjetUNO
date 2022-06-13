package application.projetuno;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Carte.*;
import Uno.*;
import Exception.*;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class JoueurControleur {

    private static int H_CANVAS ;
    private static int L_CANVAS ;
    private static int L_CARTE ;
    private static int ECART ;

    public JoueurControleur(String nom)
    {
        initJoueur(nom);
    }

    public static void initJoueurControleur(int H_CAN, int L_CAN, int L_CAR, int ECA)
    {
        H_CANVAS = H_CAN;
        L_CANVAS = L_CAN;
        L_CARTE = L_CAR;
        ECART = ECA;
    }

    private VBox Vbox ;
    private Joueur joueur ;
    private Label nom ;
    private Canvas canMain ;
    private HBox boutons ;
    private Cartes carteSelect ;
    private int num;

    public Cartes getcarteSelect() {
        return carteSelect;
    }

    public Canvas getCanMain() {
        return canMain;
    }

    public HBox getBoutons() {
        return boutons;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public Label getNom()
    {
        return nom;
    }

    public VBox getVbox()
    {
        return Vbox;
    }

    private void setCanMain(Canvas canMain)
    {
        this.canMain = canMain;
    }

    public void setJoueur(Joueur joueur)
    {
        this.joueur = joueur;
    }

    public void newJoueur(String nom)
    {
        joueur = Partie.getInstance().ajouterJoueur(nom);
    }

    public void setNom(Label nom)
    {
        Vbox.getChildren().remove(this.nom);
        this.nom = nom;
        Vbox.getChildren().add(this.nom);
    }

    public void setNom(String nom)
    {
        Vbox.getChildren().remove(this.nom);
        this.nom = initLabelNom(nom);
        Vbox.getChildren().add(this.nom);
    }

    private void setBoutons(HBox boutons)
    {
        this.boutons = boutons;
    }

    private void setVbox(VBox vbox)
    {
        this.Vbox = vbox;
    }

    private void setCarte(Cartes carteSelect) {
        this.carteSelect = carteSelect;
    }

    private VBox initJoueur(String name) {

        Vbox = new VBox();

        Vbox.setAlignment(Pos.CENTER);

        setNom(name);

        newJoueur(name);

        initBoutonUno();

        initMain();

        return Vbox;
    }

    private HBox initBoutonUno() {
        /* Cette partie est sans doute incomplète. Il y a sans doute d'autres actions à
         * prévoir que piocher et dire uno !
         */
        boutons = new HBox();
        boutons.setAlignment(Pos.CENTER);
        Button boutonUno = new Button("Uno !");

        boutonUno.setOnAction(select -> {
            System.out.println(joueur.getNom() + " à dit Uno !");
            joueur.setUno(true);
        });

        Button boutonPioche = new Button("Pioche");

        boutonPioche.setOnAction(select -> {
            System.out.println(joueur.getNom() + " pioche");

            try {
                joueur.pioche();
            }
            catch (tourException e)
            {
                e.printStackTrace();
            }
            catch (valideException e)
            {
                e.printStackTrace();
            }

            updateMain();
        });

        Button boutonTerminer = new Button("Terminer");

        boutonTerminer.setOnAction(select -> {
            System.out.println(joueur.getNom() + " à terminer");

            try
            {
                joueur.fini();
            }
            catch (tourException e)
            {
                e.printStackTrace();
            }
            catch (valideException e)
            {
                e.printStackTrace();
            }
            catch (unoException e)
            {
                e.printStackTrace();
            }

            updateMain();
        });

        boutons.getChildren().addAll(boutonUno, boutonPioche,boutonTerminer);
        Vbox.getChildren().add(boutons);
        return boutons;
    }

    private Label initLabelNom(String nom) {

        Label lNom = new Label(nom);
        lNom.setFont(new Font("Arial", 30));

        return lNom;
    }

    private Canvas initMain() {

        canMain = new Canvas(L_CANVAS, H_CANVAS);

        canMain.setOnMouseClicked(clic -> {
            int x = (int) clic.getX();
            int nbCartes = joueur.getNbCarte();
            int lMain = L_CARTE + ((nbCartes - 1) * ECART);
            int pad = (L_CANVAS - lMain) / 2;

            if (x >= pad && x <= pad + lMain) {
                int num = (int) ((x - pad) / ECART);
                num = Math.min(nbCartes - 1, num);
                System.out.println("Le joueur a sélectionné la carte " + num);
                carteSelect = joueur.getMain().get(num);
                this.num = num;
            }
            updateMain();
        });
        Vbox.getChildren().add(canMain);
        return canMain;
    }

    public void updateMain() {
        dessinerMain(joueur.getMain(), canMain);
    }

    @Override
    public String toString() {
        return "JoueurControleur{" +
                "Vbox=" + Vbox +
                ", joueur=" + joueur +
                ", nom=" + nom +
                ", canMain=" + canMain +
                ", boutons=" + boutons +
                ", carteSelect=" + carteSelect +
                ", num=" + num +
                '}';
    }

    private void dessinerMain(ArrayList<Cartes> liste, Canvas canvas) {

        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int nbCartes = liste.size();
        int lMain = L_CARTE + ((nbCartes - 1) * ECART);
        int pad = (L_CANVAS - lMain) / 2;

        for (int i = 0; i < nbCartes; i++) {
            Image carte = null;
            Cartes.Color couleur = liste.get(i).getCouleur();
            String color = null;

            switch (couleur)
            {
                case VERT -> color = "Vert";
                case ROUGE -> color = "Rouge";
                case BLEU -> color = "Bleu";
                case JAUNE -> color = "Jaune";
                case NOIR -> color = "Noir";
            }

            if(liste.get(i) instanceof Normale)
            {
                carte = new Image(getClass().getResourceAsStream("/carte_" + ((Normale) liste.get(i)).getChiffre() + "_" + color + ".png"));
            }
            else if(liste.get(i) instanceof ChangeSens)
            {
                carte = new Image(getClass().getResourceAsStream("/carte_" + "Change_" + color + ".png"));
            }
            else if(liste.get(i) instanceof Plus2)
            {
                carte = new Image(getClass().getResourceAsStream("/carte_" + "Plus2_" + color + ".png"));
            }
            else if(liste.get(i) instanceof Couleur)
            {
                carte = new Image(getClass().getResourceAsStream("/carte_" + "Change_Couleur" + ".png"));
            }
            else if(liste.get(i) instanceof Passer)
            {
                carte = new Image(getClass().getResourceAsStream("/carte_" + "Passe_" + color + ".png"));
            }

            canvas.getGraphicsContext2D().drawImage(carte, pad + i * ECART, 0);
        }
        if (carteSelect != null) {
            Image carte = null;
            Cartes.Color couleur = carteSelect.getCouleur();
            String color = null;

            switch (couleur) {
                case VERT -> color = "Vert";
                case ROUGE -> color = "Rouge";
                case BLEU -> color = "Bleu";
                case JAUNE -> color = "Jaune";
                case NOIR -> color = "Noir";
            }

            if (carteSelect instanceof Normale) {
                carte = new Image(getClass().getResourceAsStream("/carte_" + ((Normale) carteSelect).getChiffre() + "_" + color + ".png"));
            } else if (carteSelect instanceof ChangeSens) {
                carte = new Image(getClass().getResourceAsStream("/carte_" + "Change_" + color + ".png"));
            } else if (carteSelect instanceof Plus2) {
                carte = new Image(getClass().getResourceAsStream("/carte_" + "Plus2_" + color + ".png"));
            } else if (carteSelect instanceof Couleur) {
                carte = new Image(getClass().getResourceAsStream("/carte_" + "Change_Couleur" + ".png"));
            } else if (carteSelect instanceof Passer) {
                carte = new Image(getClass().getResourceAsStream("/carte_" + "Passe_" + color + ".png"));
            }
            canvas.getGraphicsContext2D().drawImage(carte, pad + num * ECART, 0);
        }
    }
}
