package json2xml;

import org.json.JSONObject;

/**
 * Classe premettant d'un convertir un quiz JSON en quiz XML
 */
public class Json2Xml {
    /**
     * Méthode effectuant la conversion
     * @param stringJson Chaine du code JSON à modifier
     * @return Chaine convertie au format XML
     * @throws Exception Si le code JSONen paramètre est invalide
     */
    public String conversion(final String stringJson) throws Exception {
        //On crée le formatter
        FormatXMLMoodle formatter = new FormatXMLMoodle();
        //On vérifie la présence la validité du code json
        if (!(new JSONObject(stringJson).has("quiz"))) {
            throw new Exception( "Le code JSON n'est pas valide, " 
            + "il ne contient pasde balise quiz.");
        }
        //On créé le quizz
        return "<?xml version=\"1.0\" ?><quiz>" 
        + formatter.check(new JSONObject(stringJson).getJSONObject("quiz")) 
        + "</quiz>";
    }
}
