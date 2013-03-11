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
