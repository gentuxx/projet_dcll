//Package
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

enum exportFormat {JSON,XML}
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
            //rendu inutile puisque on fait les tests avec des "mains" ailleur 
            //print("3 : Test XML -> JSON (A FAIRE)");
            //print("4 : Test JSON -> XML (A FAIRE)");
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
            /* choix 3 et 4 rendu invalide, je garde le code au cas où
            case 3:
                String xml = "<question><name>\n"
                + "    <text>Name of question</text>\n"
                + "</name>\n"
                + "<questiontext format=\"html\">\n"
                + "    <text>Who am I?</text>\n"
                + "</questiontext>\n"
                + "<answer fraction=\"100\">\n"
                + "    <text>My Father?</text>\n"
                + "    <feedback>\n"
                + "        <text>Correct!</text>\n"
                + "    </feedback>\n"
                + "</answer>\n"
                + "<answer fraction=\"0\">\n"
                + "    <text>GOD?</text>\n"
                + "    <feedback>\n"
                + "        <text>Ooops!</text>\n"
                + "    </feedback>\n"
                + "</answer>\n"
                + "</question>\n";
                print("\n Test de conversion XML -> JSON:\n");
                print("Test XML: \n" + xml + "\n");
                print("Résultat JSON: \n" + Xml2Json.conversion(xml) + "\n");
                break;
            case 4:
                String json = "{\"question\":" +
                        "\"name\":" +
                        "\"text\":\"Name of question\"}" +
                        ",\"answer\":[{\"feedback\":{\"text\":\"Correct!\"}" +
                        ",\"text\":\"My Father?\",\"fraction\":100}," +
                        "{\"feedback\":{\"text\":\"Ooops!\"}," +
                        "\"text\":\"GOD?\",\"fraction\":0}]," +
                        "\"questiontext\":{\"text\"" +
                        ":\"Who am I?\",\"format\":\"html\"}}}";
                print("\n Test de conversion JSON -> XML:\n");
                print("Test JSON: \n" + json + "\n");
                print("Résultat XML: \n" + Json2Xml.conversion(json) + "\n");
                break;
            */
            default:
                print("Mauvais Choix.");
                break;
            }
        }
    }
    
    /**
     * Fonction servant à créer un nouveau fichier à partir d'un 
     * fichier qui sera demandé par une dialogue box
     * 
     * Output voulu : JSON	-> input demandé XML
     * Output voulu : XML	-> input demandé JSON
     * 
     * @param choise format de sortie voulue
     * @return true si tout c'est bien passé, false sinon
     */
    private static boolean export(exportFormat choise) {
    	switch (choise) {
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
            //Récuperation du contenu du fichier .xml
            String strToConvert = Xml2Json.selectionnerFichier(fichier);
            //Conversion du XML en JSON
            String strConverted = Xml2Json.conversion(strToConvert);
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
