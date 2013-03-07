package json2xml;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.json.XMLTokener;

import xml2json.Xml2Json;

public class Json2Xml {
	
	public static String conversion(String stringJson){
		//On récupére l'intérieur des balise question
		try {
			return FormatXMLMoodle.check(new JSONObject(stringJson));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "Erreur";
		/*//Debut de reconversion en XML
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
		return stringJson;*/
		
	}

}
