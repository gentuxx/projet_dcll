package xml2json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Xml2Json {

	
	
	public static void main(String[] args) throws JSONException {
		//Utilisation de la methode statique de XML pour conversion vers JSON
		JSONObject importedJson = XML.toJSONObject("<question><name>"
				    +"    <text>Name of question</text>"
				    +"</name>"
				    +"<questiontext format=\"html\">"
				    +"    <text>Who am I?</text>"
				    +"</questiontext>"
				    +"<answer fraction=\"100\">"
				    +"    <text>My Father?</text>"
				    +"    <feedback>"
				    +"        <text>Correct!</text>"
				    +"    </feedback>"
				    +"</answer>"
				    +"<answer fraction=\"0\">"
				    +"    <text>GOD?</text>"
				    +"    <feedback>"
				    +"        <text>Ooops!</text>"
				    +"    </feedback>"
				    +"</answer></question>");
		//Affichage du code JSON
		System.out.println(importedJson);
	
		//On récupère l'intérieur des balise question
		importedJson = (JSONObject) importedJson.get("question");

		//Debut de reconversion en XML
		String xmlOut = "<question>";

		//Pour le trie dans le bon sens
		ArrayList<String> listForSort = new ArrayList<String>();
		listForSort.add("name");
		listForSort.add("questiontext");
		listForSort.add("answer");
		
		//C'est là dedans qu'il y aura des arrangement à faire
		for(String it:listForSort){
			xmlOut += "<"+it+">\n\t"+XML.toString(importedJson.get(it))+"\n<"+it+">";
		}
		xmlOut += "</question>";
		
		//Code XML depuis JSON
		System.out.println(xmlOut);

	}
}
