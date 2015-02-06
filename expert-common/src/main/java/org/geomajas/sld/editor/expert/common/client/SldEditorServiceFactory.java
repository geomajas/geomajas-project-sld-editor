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
package org.geomajas.sld.editor.expert.common.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import org.geomajas.annotation.Api;
import org.geomajas.annotation.UserImplemented;
import org.geomajas.sld.editor.expert.common.client.model.SldGwtService;
import org.geomajas.sld.editor.expert.common.client.model.SldGwtServiceAsync;

/**
 * Sld Editor Service Factory class.
 *
 * @author David Debuck
 * @since Version 1.0.0
 *
 */
@Api(allMethods = true)
@UserImplemented
public class SldEditorServiceFactory {

	private SldGwtServiceAsync service;

	/**
	 * SldEditorServiceFactory constructor.
	 *
	 * @return SldGwtServiceAsync
	 */
	public SldGwtServiceAsync createSldGwtServiceAsync() {

		this.service = GWT.create(SldGwtService.class);

		ServiceDefTarget endpoint = (ServiceDefTarget) service;
		endpoint.setServiceEntryPoint(GWT.getHostPageBaseURL() + "d/sldTemplates");

		return service;

	}

}
