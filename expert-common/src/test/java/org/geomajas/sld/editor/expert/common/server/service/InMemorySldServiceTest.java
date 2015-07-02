package org.geomajas.sld.editor.expert.common.server.service;

import junit.framework.Assert;

import org.geomajas.sld.editor.expert.common.client.domain.RawSld;
import org.geomajas.sld.service.SldException;
import org.junit.Test;


public class InMemorySldServiceTest {

	@Test
	public void testFormat() throws SldException {
		InMemorySldServiceImpl service = new InMemorySldServiceImpl();
		RawSld sld = new RawSld();
		sld.setXml("<a att1=\"a1\"  att2=\"a2\"    ><b></b></a>");
		sld = service.format(sld);
		// header + attributes wrapped + indentation !!!
		Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
				"<a\n" + 
				"    att1=\"a1\"\n" + 
				"    att2=\"a2\">\n" + 
				"  <b/>\n" + 
				"</a>", sld.getXml());
		
	}
}
