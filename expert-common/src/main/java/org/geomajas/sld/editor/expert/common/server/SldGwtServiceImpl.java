/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2015 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the Apache
 * License, Version 2.0. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.sld.editor.expert.common.server;

import java.util.List;

import org.geomajas.sld.StyledLayerDescriptorInfo;
import org.geomajas.sld.editor.expert.common.client.domain.RawSld;
import org.geomajas.sld.editor.expert.common.client.domain.SldInfo;
import org.geomajas.sld.editor.expert.common.client.model.SldGwtService;
import org.geomajas.sld.editor.expert.common.server.service.SldService;
import org.geomajas.sld.service.SldException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementation of {@link SldGwtService} that exposes some methods of the {@link SldService}.
 *
 * @author Kristof Heirwegh
 */
public class SldGwtServiceImpl implements SldGwtService {

	@Autowired
	private SldService sldService;

	public List<SldInfo> findTemplates() throws SldException {
		return sldService.findTemplates();
	}

	public RawSld findTemplateByName(String name) throws SldException {
		return sldService.findTemplateByName(name);
	}

	public boolean validate(RawSld raw) throws SldException {
		return sldService.validate(raw);
	}

	public RawSld format(RawSld raw) throws SldException {
		return sldService.format(raw);
	}

	public StyledLayerDescriptorInfo convertRawToDescriptor(RawSld raw) throws SldException {
		return sldService.toSldI(raw);
	}

	public RawSld convertDescriptorToRaw(StyledLayerDescriptorInfo sldi) throws SldException {
		return sldService.toXml(sldi);
	}

}
