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

import com.google.gwt.event.shared.SimpleEventBus;
import org.geomajas.annotation.Api;
import org.geomajas.annotation.UserImplemented;

/**
 * Sld Editor EventBus Factory class.
 *
 * @author David Debuck
 * @since Version 1.0.0
 *
 */
@Api(allMethods = true)
@UserImplemented
public class SldEditorEventBusFactory {

	/**
	 * SldEditorEventBusFactory constructor.
	 *
	 * @return SimpleEventBus
	 */
	public SimpleEventBus createSldEditorWidgetEventBus() {

		return new SimpleEventBus();

	}

}
