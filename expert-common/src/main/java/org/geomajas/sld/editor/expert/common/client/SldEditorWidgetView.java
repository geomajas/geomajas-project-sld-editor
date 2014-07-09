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

import com.google.gwt.user.client.ui.IsWidget;
import org.geomajas.annotation.Api;
import org.geomajas.annotation.UserImplemented;
import org.geomajas.sld.editor.expert.common.client.domain.SldInfo;

import java.util.List;

/**
 * Interface for the Sld Editor Widget View.
 *
 * @author David Debuck
 * @since Version 1.0.0
 *
 */
@Api(allMethods = true)
@UserImplemented
public interface SldEditorWidgetView extends IsWidget {

	/**
	 * Sets the presenter for callback.
	 *
	 * @param presenter SldEditorWidgetPresenter
	 */
	void setPresenter(SldEditorWidgetPresenter presenter);

	/**
	 * Show a message to the user.
	 *
	 * @param message String
	 */
	void showMessage(String message);

	/**
	 * Set the sld template list.
	 *
	 * @param sldTemplates Sld templates
	 */
	void setSldTemplates(List<SldInfo> sldTemplates);

	/**
	 * Set the data in the editor.
	 *
	 * @param xmlData xml String
	 */
	void setSldData(String xmlData);

	/**
	 * Get the data from the editor.
	 *
	 * @return xml String
	 */
	String getSldData();

	/**
	 * Execute when a cancel Event has been received.
	 */
	void cancelButtonEvent();

}
