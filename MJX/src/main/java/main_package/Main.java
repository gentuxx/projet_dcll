package main_package;

//Made in java
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

//Nos fonctions spéciale
import json2xml.Json2Xml;
import xml2json.Xml2Json;

enum exportFormat { JSON, XML }
/**
 * Classe servant d'interface pour la réalisation
 * des différentes convertions offertes par le logiciel.
 *
 * @author florian, nicolas et jérémy
 *
 */
public class Main {
    /**
     * Raccourci pour l'affichage.
     * @param string la string à afficher
     */
    private static void print(final String string) {
        System.out.println(string);
    }
    /**
     * Main lancé à l'exécution.
     * @param args donnés à l'appel du programme
     */
    public static void main(final String[] args) {
        int choice = -1;
        while (choice != 0) {
            print("Que voulez vous faire?");
            print("1 : Conversion XML -> JSON (A VERIFIER)");
            print("2 : Conversion JSON -> XML (A FAIRE)");
            print("0 : Quitter");
            System.out.print("Votre choix : ");
            choice = new Scanner(System.in).nextInt();
            switch(choice) {
            case 0:
                print("Au plaisir de vous revoir !");
                System.exit(0);
            case 1:
                if (!export(exportFormat.JSON)) {
                    print("Un problème est survenu lors de la conversion XML -> JSON");
                }
                break;
            case 2:
                print("\nA FAIRE\n");
                break;
            default:
                print("Mauvais Choix.");
                break;
            }
        }
    }
    /**
     * Fonction servant à créer un nouveau fichier à partir d'un
     * fichier qui sera demandé par une dialogue box.
     *
     * Output voulu : JSON	-> input demandé XML
     * Output voulu : XML	-> input demandé JSON
     *
     * @param choice format de sortie voulue
     * @return true si tout c'est bien passé, false sinon
     */
    private static boolean export(exportFormat choice) {
        switch (choice) {
        case JSON:
            //selectionner fichier
            File fichier = null;
            JFileChooser dialogue = new JFileChooser();
            //On autorise seulement les fichiers xml
            FileNameExtensionFilter filter =
                   new FileNameExtensionFilter("Fichiers xml", "xml");
            dialogue.setFileFilter(filter);
            dialogue.setAcceptAllFileFilterUsed(false);
            dialogue.setDialogTitle("Importation du fichier XML Moodle");
            if (dialogue.showOpenDialog(null)
                == JFileChooser.APPROVE_OPTION) {
                fichier = dialogue.getSelectedFile();
            }
            //objet pour la conversion
            Xml2Json xml_json_conv = new Xml2Json();
            //Récuperation du contenu du fichier .xml
            String strToConvert = xml_json_conv.selectionnerFichier(fichier);
            //Conversion du XML en JSON
            String strConverted = xml_json_conv.conversion(strToConvert);
            //Création du fichier .json
            File jsonResult = new File(
                    fichier.getAbsolutePath().substring(
                           0, fichier.getAbsolutePath().length() - 4)
                           + ".json");
            try {
                jsonResult.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            //Ecriture de la chaine json dans le fichier .json
            PrintWriter pr = null;
            try {
                pr = new PrintWriter(jsonResult);
                for (int i = 0; i < strConverted.length(); i++) {
                    pr.print(strConverted.charAt(i));
                }
                pr.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            print("Conversion terminée, résultat dans "
                   + jsonResult.getAbsolutePath()
                   + "\n\n");
             return true;
        case XML:
            return true;
        default :
            print("Format d'export demandé invalide.");
            break;
        }
         return false;
    }
}
