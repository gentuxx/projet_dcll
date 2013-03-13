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

package tools;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class XMLComparatorTest {
	
	String xml1;
    String xml2;
    
    @Before
    public void setUp() throws Exception {
    	xml1 = "<question><test>1</test></question>";
        xml2 = "<question><test>1</test></question>";
    }
    
    @Test
    public void compareTest() { 
        assertTrue(XMLComparator.compare(xml1, xml2));
        xml2 = "<question><test>2</test></question>";
        assertFalse(XMLComparator.compare(xml1, xml2));
        xml2 = "<question><test attri=\"banzai\">1</test></question>";
        assertFalse(XMLComparator.compare(xml1, xml2));
        xml1 = "<question><test attri=\"banzai\">1</test></question>";
        assertTrue(XMLComparator.compare(xml1, xml2));
    }
    
    @After
	public void tearDown() throws Exception {
    	xml1 = null;
    	xml2 = null;
    }
}
