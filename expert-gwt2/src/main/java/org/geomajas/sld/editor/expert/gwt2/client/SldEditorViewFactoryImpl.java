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

import org.geomajas.sld.editor.expert.common.client.SldEditorViewFactory;
import org.geomajas.sld.editor.expert.common.client.SldEditorWidgetView;

/**
 * Sld Editor View Factory class.
 *
 * @author David Debuck
 *
 */
public class SldEditorViewFactoryImpl extends SldEditorViewFactory {

	/**
	 * SldEditorViewFactory constructor.
	 *
	 * @return SldEditorWidgetView
	 */
	public SldEditorWidgetView createSldEditorWidgetView() {
		return new SldEditorWidgetViewImpl();
	}

}
