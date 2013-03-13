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

/**
 * Classe de test de la classe formatXMLMoodleTest
 */
public class FormatXMLMoodleTest {
    
    JSONObject json;
    String xmlString;
    String jsonString;
    FormatXMLMoodle formatter;
	@Before
	public void setUp() throws Exception {
	    json = new JSONObject();
	    xmlString = "";
	    jsonString = "";
	    formatter = new FormatXMLMoodle();
	}

	@After
	public void tearDown() throws Exception {
	    json = null;
        xmlString = null;
        jsonString = null;
        formatter = null;
	}
	
	@Test
	public void testCheck() throws JSONException {
	    //Test de base
        xmlString = "<question type=\"category\">"
        + "<category>"
        + "<text>$course$/XXXX</text>"
        + "</category>"
        + "</question>";
        json = XML.toJSONObject(xmlString);
         jsonString = formatter.check(json);
        
	    //Test avec name
	    xmlString = "<question>" 
		+ "     <name>"
		+ "         <text>Aire du cercle</text>"
	    + "      </name>"
	    + "</question>";
	    json = XML.toJSONObject(xmlString);
	    jsonString = formatter.check(json);
	    
	    //Test
	    assertTrue(XMLComparator.compare(xmlString, jsonString));
	    
	    //Test avec questiontext simple
        xmlString = "<question>" 
        + "     <questiontext format=\"moodle_auto_format\">"
        + "         <text>Calcul de l'aire du cercle ayant pour rayon {R}</text>"
        + "     </questiontext>"
        + "</question>";
        json = XML.toJSONObject(xmlString);
        jsonString = formatter.check(json);
        
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
        jsonString = formatter.check(json);
        
        //Test
        assertTrue(XMLComparator.compare(xmlString, jsonString));
        
        //Test Multiple choice
        xmlString = "<question type=\"multichoice\">"
        + "<answer fraction=\"100\">"
        + "<text>The correct answer</text>"
        + "<feedback><text>Correct!</text></feedback>"
        + "</answer>"
        + "<answer fraction=\"0\">"
        + "<text>A distractor</text>"
        + "<feedback><text>Ooops!</text></feedback>"
        + "</answer>"
        + "<answer fraction=\"0\">"
        + "<text>Another distractor</text>"
        + "<feedback><text>Ooops!</text></feedback>"
        + "</answer>"
        + "<shuffleanswers>1</shuffleanswers>"
        + "<single>true</single>"
        + "<answernumbering>abc</answernumbering></question>";
        json = XML.toJSONObject(xmlString);
        jsonString = formatter.check(json);
        
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
        jsonString = formatter.check(json);
        
        //Test
        assertTrue(XMLComparator.compare(xmlString, jsonString));
        
        //Test avec shortanswer
        xmlString = "" 
		+ "    <question type=\"shortanswer\">"
		+ "        <answer fraction=\"100\">"
		+ "            <text>The correct answer</text>"
		+ "        <feedback>"
		+ "         <text>Correct!</text></feedback>"
		+ "    </answer>"
		+ "    <answer fraction=\"100\">"
		+ "        <text>An alternative answer</text>"
		+ "        <feedback>"
		+ "             <text>Correct!</text>"
		+ "         </feedback>"
		+ "    </answer>"
        + "</question>";
        json = XML.toJSONObject(xmlString);
        jsonString = formatter.check(json);
        
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
        jsonString = formatter.check(json);
        
        //Test
        assertTrue(XMLComparator.compare(xmlString, jsonString));
        
        //Test avec matching
        xmlString = "<question type=\"matching\">"
        		+ "   <subquestion>"
        		+ "       <text>This is the 1st item in the 1st pair.</text>"
        		+ "       <answer>"
        		+ "           <text>This is the 2nd item in the 1st pair.</text>"
        		+ "       </answer>"
        		+ "   </subquestion>"
        		+ "   <subquestion>"
        		+ "       <text>This is the 1st item in the 2nd pair.</text>"
        		+ "       <answer>"
        		+ "           <text>This is the 2nd item in the 2nd pair.</text>"
        		+ "       </answer>"
        		+ "   </subquestion>"
        		+ "   <shuffleanswers>true</shuffleanswers>"
                + "</question>";
        json = XML.toJSONObject(xmlString);
        jsonString = formatter.check(json);
        
        //Test
        assertTrue(XMLComparator.compare(xmlString, jsonString));

        
        //Test avec essay
        xmlString = "<question type=\"essay\">" +
        			"<answer fraction=\"0\">" +
        				"<text></text>" +
        			"</answer>" +
        			"</question>";
        json = XML.toJSONObject(xmlString);
        jsonString = formatter.check(json);
        
        //Test
        assertTrue(XMLComparator.compare(xmlString, jsonString));
       
	}
}
