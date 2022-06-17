package Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Carte.Cartes;
import Exception.*;

public class FichierCarteCSV {

    public static void initJeuCarte(String nomFichier, ArrayList<Cartes> cartes, ParserValide parser) {
        if (nomFichier == null)
            throw new IllegalArgumentException("Erreur : Le fichier est null");

        File fichier = new File(nomFichier);

        if (! fichier.isFile())
            throw new IllegalArgumentException("Erreur : Le fichier n'existe pas");

        BufferedReader reader = null;

        String ligne;

        try
        {
            reader = new BufferedReader(new FileReader(fichier));
            int i = 1;
            while ((ligne = reader.readLine()) != null)
            {
                if (parser == null)
                    System.out.println("Ligne n'est pas gerer par les parsers : " + i + " | " + ligne);
                else
                    try
                    {
                        cartes.add(parser.traiter(ligne));
                    }
                    catch (ParserManquantException e)
                    {
                        System.err.println("Erreur : Aucun parser n'existe pour la ligne : " + i + " | " + ligne);
                    }
                    catch (ColorException e)
                    {
                        System.err.println("Erreur : Impossible de charger la couleur pour la carte a la ligne : " + i + " | " + ligne);
                    }
                    catch (NumberException e)
                    {
                        System.err.println("Erreur : Impossible de charger le numero pour la carte a la ligne : " + i + " | " + ligne);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                i++;
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static Cartes.Color getColor(String ligne) throws ColorException {
        try {
            String buffer = ligne.split(";")[1];
            Cartes.Color couleur;
            switch (buffer) {
                case "Bleu":
                    couleur = Cartes.Color.BLEU;
                    break;
                case "Jaune":
                    couleur = Cartes.Color.JAUNE;
                    break;
                case "Rouge":
                    couleur = Cartes.Color.ROUGE;
                    break;
                case "Vert":
                    couleur = Cartes.Color.VERT;
                    break;
                default:
                    throw new ColorException();
            }
            return couleur;
        }
        catch (Exception e) {
            throw new ColorException();
        }
    }

    public static int getNumber(String ligne) throws NumberException {
        try
        {
            String buffer = ligne.split(";")[2];
            int i = Integer.parseInt(buffer);
            if((i < 0) || (i > 9))
                throw new NumberException();
            return i;
        }
        catch (Exception e) {
            throw new NumberException();
        }

    }

}