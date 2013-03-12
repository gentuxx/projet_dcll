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

