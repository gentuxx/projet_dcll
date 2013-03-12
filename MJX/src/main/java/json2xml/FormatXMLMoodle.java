package json2xml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/**
 * Classe permettant de générer un code XML à partir d'un objet JSON
 * L'objet JSON doit contenir des balises questions
 */
public class FormatXMLMoodle {
    /**
     * Permet de generer un fichier XML compatible moodle
     * depuis un objet JSON.
     * @param json JSONObject du json a convertir
     * @return String code XML du contenu de quiz <question> ... </question>
     */
    public final String check(final JSONObject json) {
        try {
            //On récupère le code à l'intérieur des balise question
            if (!json.has("question")) {
                return "";
            }
            String stringXML = "";
            //On teste si il y a un ou plusieurs questions
            //Si c'est un JSONObject ou un JSONArray
            Object object = json.get("question");
            if (object instanceof JSONObject) {
                //On construit le code de la question
                stringXML += makeQuestion((JSONObject) object);
            } else if (object instanceof JSONArray) {
                //On construit le code des questions
                JSONArray array =  (JSONArray) object;
                for (int i = 0; i < array.length(); i++) {
                    stringXML += makeQuestion(array.getJSONObject(i));
                }
            }
            //On retourne le code XML
            return stringXML;
        } catch (JSONException e) {
            System.out.println("Erreur lors de la conversion.");
            e.printStackTrace();
            return "ERREUR";
        }
    }

    /**
     * Construit la question au format XML,
     * @param contenu JSONObject du contenu d'une balise question
     * @return Question au format XML
     * @throws JSONException Si il y a une erreur lors de l'accès à contenu
     */
    private String makeQuestion(final JSONObject contenu)
    throws JSONException {
        String stringXML = "";
        //On commence le formatage
        //Si la question a un type, on l'ajoute
        if (contenu.has("type")) {
            stringXML = "<question type=\"" + contenu.get("type") + "\">";
            contenu.remove("type");
        } else {
            stringXML += "<question>";
        }
        //On ajoute la partie name
        stringXML += baliseToAttribute(contenu, "name", "");
        //On ajoute la partie catégorie
        stringXML += baliseToAttribute(contenu, "category", "");
        //On ajoute la partie subquestion
        stringXML += baliseToAttribute(contenu, "subquestion", "");
        //On ajoute la partie questiontext
        stringXML += baliseToAttribute(contenu, "questiontext", "format");
        //On ajoute la partie answer
        stringXML += baliseToAttribute(contenu, "answer", "fraction");
        //On ajoute la partie shuffleanswers
        contenu.remove("name");
        contenu.remove("category");
        contenu.remove("subquestion");
        contenu.remove("questiontext");
        contenu.remove("answer");
        stringXML += XML.toString(contenu);
        //On finalise le fichier
        stringXML += "\n</question>";
        return stringXML;
    }

    /**
     * Permet de convertir les balises de contenu en attribut et
     * retourne le code XML correspondant
     * <balise><type> Banzai </type></balise> devient
     * <balise type="Banzai></balise>".
     * @param contenu JSONObject contenant la balise à traiter
     * @param balise Nom de la balise où on doit faire la modification
     *               Si la balise n'existe pas, on renvoie la chaîne vide
     * @param balisesAConvertir Tableau des balises à convertir
     *                          Si la balise n'existe pas, elle est ignorée
     * @return Resultat du traitement au format XML
     * @throws JSONException Si il y a une erreur lors de l'accès à contenu
     */
    private static String baliseToAttribute(final JSONObject contenu,
                                            final String balise,
                                            final String... balisesAConvertir)
    throws JSONException {
        //On vérifie la présence de la balise
        if (!contenu.has(balise)) {
            return "";
        }
        //On récupère le contenu de la balise
        Object contenuTestType = contenu.get(balise);
        //On teste son type, on appelle la seconde fonction si c'est un array
        if (contenuTestType instanceof JSONArray) {
            return baliseToAttribute(contenu.getJSONArray(balise)
                                    , balise
                                    , balisesAConvertir);
        }
        //On récupère le contenu au format JSONObject
        JSONObject contenuTmp = contenu.getJSONObject(balise);
        //On commance la construction de la balise
        String returnString = "<" + balise;
        //On ajoute toutes les balise à convertir en attribut
        //en tant que tel
        for (int i = 0; i < balisesAConvertir.length; i++) {
            if (contenuTmp.has(balisesAConvertir[i])) {
                returnString += " " + balisesAConvertir[i] + "=\""
                + contenuTmp.get(balisesAConvertir[i]) + "\"";
                contenuTmp.remove(balisesAConvertir[i]);
                contenu.remove(balisesAConvertir[i]);
            }
        }
        //On termine la balise
        returnString += ">";
        returnString += "\n" + XML.toString(contenuTmp);
        returnString += "\n</" + balise + ">";
        return returnString;
    }

    /**
     * Même principe que la baliseToAttribute(JSONObject [...]) mais sur un
     * JSONArray, on répète le traitement sur toutes les valeurs du JSONArray
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
