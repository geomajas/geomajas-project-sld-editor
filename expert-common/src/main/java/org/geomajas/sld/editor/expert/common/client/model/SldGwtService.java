/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2014 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the Apache
 * License, Version 2.0. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.sld.editor.expert.common.client.model;

import com.google.gwt.user.client.rpc.RemoteService;
import org.geomajas.sld.StyledLayerDescriptorInfo;
import org.geomajas.sld.editor.expert.common.client.domain.RawSld;
import org.geomajas.sld.editor.expert.common.client.domain.SldInfo;
import org.geomajas.sld.service.SldException;

import java.util.List;

/**
 * GWT-RPC service for SLD retrieval/persistence.
 *
 * @author Jan De Moerloose
 */
public interface SldGwtService extends RemoteService {

	List<SldInfo> findTemplates() throws SldException;

	RawSld findTemplateByName(String name) throws SldException;

	boolean validate(RawSld raw) throws SldException;

	StyledLayerDescriptorInfo convertRawToDescriptor(RawSld raw) throws SldException;

	RawSld convertDescriptorToRaw(StyledLayerDescriptorInfo sldi) throws SldException;

}
