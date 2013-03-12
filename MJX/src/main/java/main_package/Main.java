package main_package;

//Made in java
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

//Nos fonctions spéciale
import json2xml.Json2Xml;
import xml2json.Xml2Json;

/** Liste des formats d'export supportés. */
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
            print("1 : Conversion XML -> JSON");
            print("2 : Conversion JSON -> XML");
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
                if (!export(exportFormat.XML)) {
                    print("Un problème est survenu lors de la conversion JSON -> XML");
                }
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
    private static boolean export(exportFormat choise) {
    	//Variable utilisées dans tout les cas
    	//Pour la dialogue
        JFileChooser dialogue;
        FileNameExtensionFilter filter;
        //Pour les opération de fichier
        File fichier;
        String strToConvert;
        String strConverted;
        PrintWriter pr;
        
    	switch (choise) {
    	case JSON:
    		dialogue = new JFileChooser();
            //On autorise seulement les fichiers xml
    		filter = new FileNameExtensionFilter("Fichiers xml", "xml");
            dialogue.setFileFilter(filter);
            dialogue.setAcceptAllFileFilterUsed(false);
            dialogue.setDialogTitle("Importation du fichier XML Moodle");
            //selectionner fichier
            if (dialogue.showOpenDialog(null)
                == JFileChooser.APPROVE_OPTION) {
                fichier = dialogue.getSelectedFile();
            } else {
            	print("Export annulé.\n");
            	return true;
            }
            //objet pour la conversion
            Xml2Json xml_json_conv = new Xml2Json();
            //Récuperation du contenu du fichier .xml
            strToConvert = recupDataFichier(fichier);
            //Conversion du XML en JSON
            strConverted = xml_json_conv.conversion(strToConvert);
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
                   + "\n");
    		return true;
    	case XML:
    		dialogue = new JFileChooser();
            //On autorise seulement les fichiers json
            filter = new FileNameExtensionFilter("Fichiers json", "json");
            dialogue.setFileFilter(filter);
            dialogue.setAcceptAllFileFilterUsed(false);
            dialogue.setDialogTitle("Importation du fichier JSON");
            if (dialogue.showOpenDialog(null)
                == JFileChooser.APPROVE_OPTION) {
                fichier = dialogue.getSelectedFile();
            } else {
            	print("Export annulé.\n");
            	return true;
            }
    		//objet pour la conversion
    		Json2Xml json_xml_conv = new Json2Xml();
            //Récuperation du contenu du fichier .json
            strToConvert = recupDataFichier(fichier);
            //Conversion du JSON en XML
            try {
				strConverted = json_xml_conv.conversion(strToConvert);
			} catch (Exception e1) {
				e1.printStackTrace();
        		return false;
			}
            //Création du fichier .xml
            File xmlResult = new File(
                    fichier.getAbsolutePath().substring(
                           0, fichier.getAbsolutePath().length() - 5)
                           + ".xml");
            try {
            	xmlResult.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
        		return false;
            }
            //Ecriture de la chaine xml dans le fichier .xml
            try {
            	pr = new PrintWriter(xmlResult);
	            for (int i = 0; i < strConverted.length(); i++) {
	            	pr.print(strConverted.charAt(i));
	            }
	            pr.close();
            } catch (FileNotFoundException e) {
            	e.printStackTrace();
        		return false;
            }
            print("Conversion terminée, résultat dans "
                   + xmlResult.getAbsolutePath()
                   + "\n");
    		return true;
    	default :
    		print("Format d'export demandé invalide.\n");
    		break;
    	}
		return false;
    }
    
    /**
     * Récupère le contenu d'un fichier xml et le retourne sous forme de chaîne.
     * 
     * @param f le fichier dont on veut récupérer le contenu
     * @return le contenu du fichier
     */
    private static String recupDataFichier(final File f) {
        StringWriter strw = new StringWriter();
        try {
              BufferedInputStream buff =
              new BufferedInputStream(new FileInputStream(f));
               int b;
               while ((b = buff.read()) != -1) {
                   strw.write(b);
               }
               strw.flush();
               strw.close();
               buff.close();
            } catch (IOException ie) {
                 ie.printStackTrace();
            }
        return strw.toString();
    }
}
