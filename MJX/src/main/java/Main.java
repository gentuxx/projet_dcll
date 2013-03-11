//package

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import json2xml.Json2Xml;
import xml2json.Xml2Json;

/**
 * TODO.
 * @author nicolas
 *
 */
public class Main {
    /**
     * TODO.
     * @param string
     */
    public static void print(final String string) {
        System.out.println(string);
    }
    /**
     * TODO.
     * @param args
     */
    public static void main(final String[] args) {
        int choice = 100;

        while (choice != 0) {
            print("Que voulez vous faire?");
            print("1 : Conversion XML -> JSON (A VERIFIER)");
            print("2 : Conversion JSON -> XML (A FAIRE)");
            print("3 : Test XML -> JSON (A FAIRE)");
            print("4 : Test JSON -> XML (A FAIRE)");
            print("0 : Quitter");
            choice = new Scanner(System.in).nextInt();
            switch(choice) {
            case 0:
                System.exit(0);
                break;
            case 1:
                //selectionner fichier
                File fichier = null;
                JFileChooser dialogue = new JFileChooser();
                //On autorise seulement les fichiers xml
                FileNameExtensionFilter filter =
                       new FileNameExtensionFilter("Fichier xml", "xml");
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
                File jsonResult =
                    new File(
                        fichier.getAbsolutePath().substring(
                               0, fichier.getAbsolutePath().length() - 4)
                               + ".json");
                try {
                      jsonResult.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
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
                }
                print("Conversion terminée, résultat dans "
                       + jsonResult.getAbsolutePath());
                break;
            case 2:
                print("\nA FAIRE\n");
                break;
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
                        {\"name\":" +
                        {\"text\":\"Name of question\"}" +
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
            default:
                print("Mauvais Choix.");
                break;
            }

        }
    }
}
