package json2xml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
/**
 * TODO.
 * @author nicolas
 *
 */
public class FormatXMLMoodle {
    /**
     * Permet de generer un fichier XML compatible moodle
     * depuis un fichier JSON.
     * @param json JSONObject du json a convertir
     * @return String contenant le code XML
     */
    public static String check(final JSONObject json) {
        try {
            //On récupère le code à l'intérieur des balise question
            JSONObject contenu = json.getJSONObject("question");
            //On commence le formatage
            String stringXML = "<question>";
            //On ajoute la partie name
            stringXML += namePart(contenu);
            //On ajoute la partie questiontext
            stringXML += questiontextPart(contenu);
            //On ajoute la partie answer
            stringXML += answerPart(contenu);
            //On finalise le fichier
            stringXML += "\n</question>";
            return stringXML;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Formate la partie answer.
     * @param contenu
     * @return Partie answer formatté
     * @throws JSONException
     */
    private static String answerPart(final JSONObject contenu)
    throws JSONException {
        return "\n" + baliseToAttribute(contenu, "answer", "fraction");
    }

    /**
     * Formate la partie name.
     * @param contenu
     * @return Partie name formatée
     * @throws JSONException
     */
    public static String namePart(final JSONObject contenu)
    throws JSONException {
        if (!contenu.has("name")) {
            return "";
        }
        //On écrit la partie name
        String stringName = "\n<name>";
        stringName += XML.toString(contenu.get("name"));
        stringName += "\n</name>";
        return stringName;
    }

    /**
     * Formate la partie questiontext.
     * @param contenu
     * @return Partie questiontext formatté
     * @throws JSONException
     */
    public static String questiontextPart(final JSONObject contenu)
    throws JSONException {
        //On écrit la partie questiontext
        return "\n" + baliseToAttribute(contenu, "questiontext", "format");
    }

    /**
     * Permet de convertir les balises de contenu en attribut et
     * retourne le code XML correspondant
     * <balise><type> Banzai </type></balise> devient
     * <balise type="Banzai></balise>".
     * @param contenu JSONObject contenant le contenu
     * @param balise nom de la balise ou on doit faire la modification
     * @param balisesAConvertir tableau des balises à convertir
     * @return Code XML Formaté
     * @throws JSONException
     */
    private static String baliseToAttribute(final JSONObject contenu,
                                            final String balise,
                                            final String... balisesAConvertir)
    throws JSONException {
        if (!contenu.has(balise)) {
            return "";
        }
        Object contenuTestType = contenu.get(balise);
        if (contenuTestType instanceof JSONArray) {
            return baliseToAttribute(contenu.getJSONArray(balise)
                                    , balise
                                    , balisesAConvertir);
        }
        JSONObject contenuTmp = contenu.getJSONObject(balise);
        String returnString = "<" + balise;
        for (int i = 0; i < balisesAConvertir.length; i++) {
            if (contenuTmp.has(balisesAConvertir[i])) {
                returnString += " " + balisesAConvertir[i] + "=\""
                + contenuTmp.get(balisesAConvertir[i]) + "\"";
                contenuTmp.remove(balisesAConvertir[i]);
            }
        }
        returnString += ">";
        returnString += "\n" + XML.toString(contenuTmp);
        returnString += "\n</" + balise + ">";
        return returnString;
    }

/**
 *
 * @param contenu
 * @param balise
 * @param balisesAConvertir
 * @return
 * @throws JSONException
 */
    private static String baliseToAttribute(final JSONArray contenu,
                                            final String balise,
                                            final String... balisesAConvertir)
    throws JSONException {
        String returnString = "";
        for (int j = 0; j < contenu.length(); j++) {
        returnString += "\n<" + balise;
            JSONObject contenuTmp = contenu.getJSONObject(j);
            for (int i = 0; i < balisesAConvertir.length; i++) {
                if (contenuTmp.has(balisesAConvertir[i])) {
                    returnString += " " + balisesAConvertir[i] + "=\""
                    + contenuTmp.get(balisesAConvertir[i]) + "\"";
                    contenuTmp.remove(balisesAConvertir[i]);
                }
            }
            returnString += ">";
            returnString += "\n" + XML.toString(contenuTmp);
            returnString += "\n</" + balise + ">";
        }
        return returnString;
    }
}
