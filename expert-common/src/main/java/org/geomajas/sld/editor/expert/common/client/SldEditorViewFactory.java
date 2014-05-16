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
package org.geomajas.sld.editor.expert.common.client;

import com.google.gwt.core.client.GWT;
import org.geomajas.annotation.Api;
import org.geomajas.annotation.UserImplemented;
import org.geomajas.sld.editor.expert.common.client.i18n.SldEditorExpertMessages;

/**
 * Sld Editor View Factory class.
 *
 * @author David Debuck
 * @since Version 1.0.0
 *
 */
@Api(allMethods = true)
@UserImplemented
public class SldEditorViewFactory {

	private SldEditorExpertMessages msg = GWT.create(SldEditorExpertMessages.class);

	/**
	 * SldEditorViewFactory constructor.
	 *
	 * @return SldEditorWidgetView
	 */
	public SldEditorWidgetView createSldEditorWidgetView() {

		throw new UnsupportedOperationException(
				msg.sldEditorViewFactoryUnsupportedException()
		);

	}

}
