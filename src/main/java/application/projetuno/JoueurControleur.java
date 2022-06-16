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
import javafx.scene.paint.Color;
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
    private boolean Ajouer;


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
        this.nom.setText(nom);
    }

    public void setNomC()
    {
        nom.setFont(new Font("Arial", 60));
    }
    public void setNomPC()
    {
        nom.setFont(new Font("Arial", 30));
    }



    public void CouleurNom(Color color)
    {
        nom.setTextFill(color);
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

        initLabelNom(name);

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
            try {
                joueur.Uno();
                JeuControleur.getJeu().setMsg(joueur.getNom()+" a dit UNO!",Color.BLACK);
            } catch (unoException e) {
                    System.out.println(e);
                    JeuControleur.getJeu().setMsg(e.toString().substring(24),Color.RED);
                    Partie.getInstance().punition(joueur,true,2);
            } catch (tourException e) {
                System.out.println(e);
                JeuControleur.getJeu().setMsg(e.toString().substring(25),Color.RED);
                Partie.getInstance().punition(joueur,false,2);
            }
            JeuControleur.getJeu().dessinerSabot();
        });

        Button boutonPioche = new Button("Pioche");

        boutonPioche.setOnAction(select -> {
            System.out.println(joueur.getNom() + " pioche");

            try {
                joueur.pioche();
                JeuControleur.getJeu().setMsg(joueur.getNom()+" à piocher !",Color.BLACK);
            }
            catch (tourException e)
            {

                Partie.getInstance().punition(joueur,false,2);
                JeuControleur.getJeu().setMsg(e.toString().substring(26),Color.RED);
            }
            catch (valideException e)
            {
                System.out.println(e);
                Partie.getInstance().punition(joueur,false,2);
                JeuControleur.getJeu().setMsg(e.toString().substring(26),Color.RED);
            }
            JeuControleur.getJeu().dessinerSabot();
        });

        Button boutonPoser = new Button("Poser");

        boutonPoser.setOnAction(select -> {
            System.out.println(joueur.getNom() + " à poser");

            try
            {
                joueur.poser(getcarteSelect());
                JeuControleur.getJeu().setMsg("",Color.BLACK);
                if(joueur.getNbCarte() == 0) {
                    System.out.println("BRAVOOOOOO " + joueur.getNom() + " A GAGNE");
                    JeuControleur.getJeu().setMsg("BRAVO " + joueur.getNom(), Color.RED);
                }
                else
                {
                    JeuControleur.getJeu().setMsg(joueur.getNom()+" à poser une carte !",Color.BLACK);
                }
                carteSelect = null;
            }
            catch (tourException e)
            {
                System.out.println(e);
                Partie.getInstance().punition(joueur,false,2);
                JeuControleur.getJeu().setMsg(e.toString().substring(26),Color.RED);
            }
            catch (valideException e)
            {
                System.out.println(e);
                Partie.getInstance().punition(joueur,true,2);
                JeuControleur.getJeu().setMsg(e.toString().substring(26),Color.RED);
            }
            JeuControleur.getJeu().dessinerSabot();
        });

        Button boutonTerminer = new Button("Terminer");

        boutonTerminer.setOnAction(select -> {
            System.out.println(joueur.getNom() + " à terminer");

            try
            {
                joueur.fini();
                JeuControleur.getJeu().setMsg(joueur.getNom()+" à terminer !",Color.BLACK);
                Partie.getInstance().getJoueurCourant().peutJouer();
            }
            catch (tourException e)
            {
                System.out.println(e);
                if(Partie.getInstance().getJoueurCourant().equals(joueur))
                {
                    Partie.getInstance().punition(joueur,true,2);
                }
                else
                {
                    Partie.getInstance().punition(joueur,false,2);
                }
                JeuControleur.getJeu().setMsg(e.toString().substring(26),Color.RED);
            }
            catch (unoException e)
            {
                System.out.println(e);
                if(Partie.getInstance().getJoueurCourant().equals(joueur))
                {
                    Partie.getInstance().punition(joueur,true,2);
                }
                else
                {
                    Partie.getInstance().punition(joueur,false,2);
                }
                JeuControleur.getJeu().setMsg(e.toString().substring(26),Color.RED);

            } catch (valideException e) {
                JeuControleur.getJeu().setMsg(e.toString().substring(26),Color.RED);
                System.out.println("Tu n'as pas jouer, PUNITION");
                Partie.getInstance().punition(joueur,true, 2);
            }
            JeuControleur.getJeu().dessinerSabot();
        });

        boutons.getChildren().addAll(boutonUno, boutonPioche,boutonTerminer,boutonPoser);
        Vbox.getChildren().add(boutons);
        return boutons;
    }
    private Label initLabelNom(String nom) {

        this.nom = new Label(nom);
        this.nom.setFont(new Font("Arial", 30));
        Vbox.getChildren().add(this.nom);
        return this.nom;
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

    public void updateMain()
    {
        if(Partie.getInstance().getJoueurCourant().equals(joueur))
        {
            CouleurNom(Color.RED);
        }
        else
        {
            CouleurNom(Color.BLACK);
        }
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

        //L_CANVAS = ECART * (liste.size() + 1);
        //canvas.setWidth(L_CANVAS);

        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());


        int nbCartes = liste.size();
        int lMain = L_CARTE + ((nbCartes - 1) * ECART);
        int pad = (L_CANVAS - lMain) / 2;



        for (int i = 0; i < nbCartes; i++) {
            //System.out.println("Ok");
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
