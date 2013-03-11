package json2xmltest;

import static org.junit.Assert.*;
import json2xml.FormatXMLMoodle;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tools.XMLComparator;

public class FormatXMLMoodleTest {
    
    JSONObject json;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCheck() throws JSONException {
	    //Test avec name
	    String xmlString = "<question>" 
		+ "     <name>"
		+ "         <text>Aire du cercle</text>"
	    + "      </name>"
	    + "</question>";
	    JSONObject json = XML.toJSONObject(xmlString);
	    String jsonString = FormatXMLMoodle.check(json);
	    
	    //Test
	    assertTrue(XMLComparator.compare(xmlString, jsonString));
	    
	    //Test avec questiontext simple
        xmlString = "<question>" 
        + "     <questiontext format=\"moodle_auto_format\">"
        + "         <text>Calcul de l'aire du cercle ayant pour rayon {R}</text>"
        + "     </questiontext>"
        + "</question>";
        json = XML.toJSONObject(xmlString);
        jsonString = FormatXMLMoodle.check(json);
        
        //Test
        assertTrue(XMLComparator.compare(xmlString, jsonString));
        
        //Test avec answer simple
        xmlString = "<question>"
        + "<answer fraction=\"100\">"
        + "<text>3.14 * {R}*{R}</text>"
        + "<tolerance>0.01</tolerance>"
        + "<tolerancetype>1</tolerancetype>"
        + "<correctanswerformat>1</correctanswerformat>"
        + "<correctanswerlength>1</correctanswerlength>"
        + "<feedback>"
        + "<text/>"
        + "</feedback>"
        + "</answer>"
        + "</question>";
        json = XML.toJSONObject(xmlString);
        jsonString = FormatXMLMoodle.check(json);
        
        //Test
        assertTrue(XMLComparator.compare(xmlString, jsonString));
        
        //Test avec true/false
        xmlString = "<question type=\"truefalse\">"		
		+"<answer fraction=\"100\">"
		+"<text>true</text>"
		+"<feedback><text>Correct!</text></feedback>"
		+"</answer>"
		+"<answer fraction=\"0\">"
		+"<text>false</text>"
		+"<feedback><text>Ooops!</text></feedback>"
		+"</answer>"
        +"</question>";
        json = XML.toJSONObject(xmlString);
        jsonString = FormatXMLMoodle.check(json);
        
        //Test
        assertTrue(XMLComparator.compare(xmlString, jsonString));
        
        //Test avec shortanswer
        xmlString = "<question type=\"shortanswer\">"
		+"<answer fraction=\"100\">"
		+"<text>The correct answer</text>"
		+"<feedback><text>Correct!</text></feedback>"
		+"</answer>"
		+"<question type=\"shortanswer\">"
		+"<answer fraction=\"100\">"
		+"<text>The correct answer</text>"
		+"<feedback><text>Correct!</text></feedback>"
		+"</answer>"
		+"<answer fraction=\"100\">"
		+"<text>An alternative answer</text>"
		+"<feedback><text>Correct!</text></feedback>"
		+"</answer>"
        +"</question>";
        json = XML.toJSONObject(xmlString);
        jsonString = FormatXMLMoodle.check(json);
        
        //Test
        assertTrue(XMLComparator.compare(xmlString, jsonString));
        
        //Test avec numerical
        xmlString = "<question type=\"numerical\">" +
        		"<answer fraction=\"100\">" +
	        		"<text>23</text>" +
	        		"<feedback>" +
	        			"<text>Feedback</text>" +
	        		"</feedback>" +
        		"</answer>" +
                "</question>";
        json = XML.toJSONObject(xmlString);
        jsonString = FormatXMLMoodle.check(json);
        
        //Test
        assertTrue(XMLComparator.compare(xmlString, jsonString));
        
        //Test avec matching
        xmlString = "<question type=\"matching\">" +
        		"<subquestion>" +
        			"<text>This is the 1st item in the 1st pair.</text>" +
        			"<answer>" +
        				"<text>This is the 2nd item in the 1st pair.</text>" +
        			"</answer>" +
        		"</subquestion>" +
        		"<subquestion>" +
        			"<text>This is the 1st item in the 2nd pair.</text>" +
        			"<answer>" +
        				"<text>This is the 2nd item in the 2nd pair.</text>" +
        			"</answer>" +
        		"</subquestion>" +
        		"<shuffleanswers>true</shuffleanswers>" +
                "</question>";
        json = XML.toJSONObject(xmlString);
        jsonString = FormatXMLMoodle.check(json);
        
        //Test
        assertTrue(XMLComparator.compare(xmlString, jsonString));

        
        //Test avec essay
        xmlString = "<question type=\"essay\">" +
        			"<answer fraction=\"0\">" +
        				"<text></text>" +
        			"</answer>" +
        			"</question>";
        json = XML.toJSONObject(xmlString);
        jsonString = FormatXMLMoodle.check(json);
        
        //Test
        assertTrue(XMLComparator.compare(xmlString, jsonString));
       
	}
}
