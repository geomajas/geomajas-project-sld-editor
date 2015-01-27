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
package org.geomajas.sld.editor.expert.gwt2.client;

import com.google.gwt.core.client.GWT;
import org.geomajas.sld.editor.expert.common.client.SldEditorClientBundleFactory;
import org.geomajas.sld.editor.expert.common.client.SldEditorWidgetResource;

/**
 * Sld Editor Resource Factory Alternative class.
 *
 * @author David Debuck
 *
 */
public class SldEditorClientBundleFactoryAlternative extends SldEditorClientBundleFactory {

	/**
	 * SldEditorClientBundleFactoryAlternative constructor.
	 *
	 * @return SldEditorWidgetResource
	 */
	public SldEditorWidgetResource createSldEditorWidgetResource() {
		return GWT.create(SldEditorWidgetResourceAlternative.class);
	}

}
