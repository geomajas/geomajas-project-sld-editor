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
import org.geomajas.annotation.Api;
import org.geomajas.annotation.UserImplemented;

/**
 * Sld Editor Resource Factory class.
 *
 * @author David Debuck
 * @since Version 1.0.0
 *
 */
@Api(allMethods = true)
@UserImplemented
public class SldEditorClientBundleFactory {

	/**
	 * SldEditorClientBundleFactory constructor.
	 *
	 * @return SldEditorWidgetResource
	 */
	public SldEditorWidgetResource createSldEditorWidgetResource() {
		return GWT.create(SldEditorWidgetResource.class);
	}

}
