package json2xml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Formatage {
	public static String check(JSONObject json){
		try {
			JSONObject contenu = json.getJSONObject("question");
			//On commence le formatage
			String stringXML = "<question>";
			//On ajoute la partie name
			stringXML += namePart(contenu);
			stringXML += questiontextPart(contenu);
			stringXML += answerPart(contenu);
			//On finalise le fichier
			stringXML += "\n</question>";
			return stringXML;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static String answerPart(JSONObject contenu) throws JSONException {
		JSONArray answer = contenu.getJSONArray("answer");
		String stringAnswer = "\n<answer ";
		for(int i = 0; i < answer.length(); i++){
			stringAnswer += baliseToAttribute(answer.getJSONObject(i),"fraction");
			answer.getJSONObject(i).remove("fraction");
			stringAnswer += ">";
			stringAnswer += "\n" + XML.toString(answer.getJSONObject(i));
			stringAnswer += "\n</answer>";
		}
		//On écrit la partie name
		return stringAnswer;
	}

	public static String namePart(JSONObject contenu) throws JSONException{
		//On écrit la partie name
		String stringName = "\n<name>";
		stringName += XML.toString(contenu.get("name"));
		stringName += "\n</name>";
		return stringName;		
	}
	
	public static String questiontextPart(JSONObject contenu) throws JSONException{
		JSONObject questiontext = contenu.getJSONObject("questiontext");
		//On écrit la partie questiontext
		String stringName = "\n<questiontext ";
		stringName += baliseToAttribute(questiontext,"format");
		questiontext.remove("format");
		stringName += ">";
		
		stringName += "\n" + XML.toString(contenu.get("questiontext"));
		stringName += "\n</questiontext>";
		return stringName;		
	}
	
	private static String baliseToAttribute(JSONObject contenu, String... balise) throws JSONException{
		String returnString = "";
		for(int i = 0; i < balise.length; i++){
			if(contenu.has(balise[i])){
				returnString += " " + balise[i] + "=\"" + contenu.get(balise[i]) +"\"";
			}
		}
		return returnString;
	}
}
