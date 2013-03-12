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
            if (!json.has("question")) {
                return "";
            }
            String stringXML = "";
            Object object = json.get("question");
            if (object instanceof JSONObject) {
                stringXML += makeQuestion((JSONObject) object);
            }
            else if (object instanceof JSONArray) {
                JSONArray array =  (JSONArray) object;
                for(int i = 0; i < array.length(); i++) {
                    stringXML += makeQuestion(array.getJSONObject(i));
                }
            }
            return stringXML;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object makeQuestion(JSONObject contenu) 
    throws JSONException {
        String stringXML = "";
        //On commence le formatage
        if(contenu.has("type")){
            stringXML ="<question type=\"" + contenu.get("type") + "\">";
            contenu.remove("type");
        }
        else{
            stringXML += "<question>";
        }
        //On ajoute la partie name
        stringXML += baliseToAttribute(contenu, "name", "");
        //On ajoute la partie catégorie
        stringXML += baliseToAttribute(contenu, "category", "");
        //On ajoute la partie subquestion
        stringXML += baliseToAttribute(contenu,"subquestion","");
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
                contenu.remove(balisesAConvertir[i]);
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
    
    /**
     * Permet de convertir une donnée depuis un code json en balise
     * @param contenu Object d'où on prend les données
     * @param données à convertir
     * @return string xml des données converties
     * @throws JSONException 
     */
    private static String jsonToBalise(final JSONObject contenu,
                                       final String... donnees) 
    throws JSONException{
        String balises = "";
        for(int i = 0; i < donnees.length; i++){
            if(contenu.has(donnees[i])){
                balises += "<" + donnees[i] + ">" 
                        + contenu.get(donnees[i])
                        + "</" + donnees[i] + ">";
            }
        }
        return balises;
    }
}
