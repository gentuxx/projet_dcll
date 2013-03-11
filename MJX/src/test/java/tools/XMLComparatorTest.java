package tools;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.junit.Test;

public class XMLComparatorTest {
    
    @Test
    public void compareTest() {
        String xml1;
        String xml2;
        
        xml1 = "<question><test>1</test></question>";
        xml2 = "<question><test>1</test></question>";
        assertTrue(XMLComparator.compare(xml1, xml2));
        xml2 = "<question><test>2</test></question>";
        assertFalse(XMLComparator.compare(xml1, xml2));
        xml2 = "<question><test attri=\"banzai\">1</test></question>";
        assertFalse(XMLComparator.compare(xml1, xml2));
        xml1 = "<question><test attri=\"banzai\">1</test></question>";
        assertTrue(XMLComparator.compare(xml1, xml2));
    }
}
