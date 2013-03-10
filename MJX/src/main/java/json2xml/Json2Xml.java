package json2xml;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * TODO.
 * @author nicolas
 *
 */
public class Json2Xml {
    /**
     * TODO.
     * @param stringJson
     * @return
     */
    public static String conversion(final String stringJson) {
        //On récupére l'intérieur des balise question
        try {
            return FormatXMLMoodle.check(new JSONObject(stringJson));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Erreur";
    }
}
