package Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Carte.Cartes;
import Exception.*;

public class FichierCarteCSV {

    public static ArrayList<Cartes> getJeuCarte(String nomFichier, ParserValide parser)
    {
        if (nomFichier == null)
            throw new IllegalArgumentException("Erreur : Le fichier est null");

        File fichier = new File(nomFichier);

        if (! fichier.isFile())
            throw new IllegalArgumentException("Erreur : Le fichier n'existe pas");

        BufferedReader reader = null;

        String ligne;

        ArrayList<Cartes> cartes = new ArrayList<Cartes>();

        try
        {
            reader = new BufferedReader(new FileReader(fichier));

            while ((ligne = reader.readLine()) != null)
            {
                if (parser == null)
                    System.out.println("Ligne n'est pas gerer par les parsers : "+ligne);
                else
                    try
                    {
                        cartes.add(parser.traiter(ligne));
                    }
                    catch (ParserManquantException e)
                    {
                        System.err.println("Erreur : Aucun parser n'existe pour la ligne : "+ligne);
                    }
                    catch (ColorException e)
                    {
                        System.err.println("Erreur : Il n'y a pas de couleur pour la carte a la ligne : "+ligne);
                    }
                    catch (NumberException e)
                    {
                        System.err.println("Erreur : Il n'y a pas de numero pour la carte a la ligne : "+ligne);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return cartes;
    }

    public static Cartes.Color getColor(String ligne) throws ColorException
    {
        String buffer = ligne.split(";")[1];
        Cartes.Color couleur;
        switch (buffer)
        {
            case "Bleu" :
                couleur = Cartes.Color.BLEU;
                break;
            case "Jaune" :
                couleur = Cartes.Color.JAUNE;
                break;
            case "Rouge" :
                couleur = Cartes.Color.ROUGE;
                break;
            case "Vert" :
                couleur = Cartes.Color.VERT;
                break;
            default:
                throw new ColorException();
        }
        return couleur;
    }

    public static int getNumber(String ligne) throws NumberException
    {
        String buffer = ligne.split(";")[2];
        int i = Integer.parseInt(buffer);
        if((i < 0) || (i > 9))
            throw new NumberException();
        return i;
    }

}