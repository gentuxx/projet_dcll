package xml2json;


import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
/**
 * TODO.
 * @author nicolas
 *
 */
public class Xml2Json {
    /**
     * TODO.
     * @param stringXML
     * @return
     */
    public static String conversion(final String stringXML) {
        try {
            //Utilisation de la methode statique de XML
            //pour conversion vers JSON
            JSONObject importedJson = XML.toJSONObject(stringXML);
            //Recuperation du code JSON
            return importedJson.toString();
        } catch (JSONException e) {
            //Erreur
            System.err.println("Erreur lors de la conversion XML->JSON");
            System.err.println("Exception :" + e);
        }
        return "Erreur";
    }
    /**
     * TODO.
     * @param f
     * @return
     */
    //Ouvre un fichier et met son contenu dans une string que
    //l'on utilisera pour la conversion
    public static String selectionnerFichier(final File f) {
        StringWriter strw = new StringWriter();
        try {
           BufferedReader buff = new BufferedReader(new FileReader(f));

           String ligne;
           while ((ligne = buff.readLine()) != null) {
               strw.write(ligne);
           strw.flush();
           strw.close();
           buff.close();
           }
           }
        catch (IOException ie) {
             ie.printStackTrace();
        }
        return strw.toString();
    }

}
