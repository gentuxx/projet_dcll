package xml2json;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Xml2Json {
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
}
