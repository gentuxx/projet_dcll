/*Copyright [2013] [MJX developers]
*
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
*/

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
