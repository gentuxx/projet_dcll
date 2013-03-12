package xml2json;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
/**
 * Classe servant à convertir un fichier xml en json.
 * @author nicolas
 *
 */
public class Xml2Json {
    /**
     * Convertit une chaine xml en un objet json.
     * @param stringXML la chaîne à convertir
     * @return Une chaîne json ou Erreur
     */
    public String conversion(final String stringXML) {
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

}

