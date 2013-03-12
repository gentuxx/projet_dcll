package xml2jsontest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import xml2json.Xml2Json;

public class Xml2JsonTest {

	String stringXML;
	String stringJson;
	
	@Before
	public void setUp() throws Exception {
		stringXML = "<test>ma blalise</test>";
		stringJson = "{\"test\":\"ma blalise\"}";
	}

	@Test
	public void conversionTest() {
		assertTrue(new Xml2Json().conversion(stringXML).equals(stringJson));		
	}
	
	@After
	public void tearDown() throws Exception {
		stringXML = null;
		stringJson = null;
	}

}
