package xml2json;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Xml2Json {
	public static String conversion(String stringXML){
		try {
			//Utilisation de la methode statique de XML pour conversion vers JSON
			JSONObject importedJson = XML.toJSONObject(stringXML);
			//Recuperation du code JSON
			String json = importedJson.toString();
			return importedJson.toString();
		} catch (JSONException e) {
			//Erreur
			System.err.println("Erreur lors de la conversion XML->JSON");
			System.err.println("Exception :" + e);
		}
		return "Erreur";
		
	
		/*//On r�cup�re l'int�rieur des balise question
		importedJson = (JSONObject) importedJson.get("question");

		//Debut de reconversion en XML
		String xmlOut = "<question>";

		//Pour le trie dans le bon sens
		ArrayList<String> listForSort = new ArrayList<String>();
		listForSort.add("name");
		listForSort.add("questiontext");
		listForSort.add("answer");
		
		//C'est l� dedans qu'il y aura des arrangement � faire
		for(String it:listForSort){
			xmlOut += "<"+it+">\n\t"+XML.toString(importedJson.get(it))+"\n<"+it+">";
		}
		xmlOut += "</question>";
		
		//Code XML depuis JSON
		System.out.println(xmlOut);
		*/
	}
}
