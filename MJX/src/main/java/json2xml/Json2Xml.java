package json2xml;

import org.json.JSONArray;
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
            if (!(new JSONObject(stringJson).has("quiz"))) {
                return "";
            }
            return makeQuiz(FormatXMLMoodle.check(new JSONObject(stringJson).getJSONObject("quiz")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Erreur";
    }

    private static String makeQuiz(String check) {
        return "<?xml version=\"1.0\" ?><quiz>" + check + "</quiz>";
    }
}
