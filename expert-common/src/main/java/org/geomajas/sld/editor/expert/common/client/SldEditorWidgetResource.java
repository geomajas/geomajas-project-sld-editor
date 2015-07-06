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

import org.geomajas.annotation.Api;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Interface for the Sld Editor Resource.
 *
 * @author David Debuck
 * @since Version 1.0.0
 *
 */
@Api(allMethods = true)
public interface SldEditorWidgetResource extends ClientBundle {

	/**
	 * Css Resource for this widget.
	 *
	 * @return SldEditorWidgetCssResource
	 */
	@Source("sldEditor-widget.css")
	SldEditorWidgetCssResource css();

	/**
	 * Cancel image.
	 * 
	 * @return
	 */
	@Source("public/sc/icons/silk/cancel.png")
	ImageResource sldCancelButton();

	/**
	 * Format SLD text image.
	 * 
	 * @return
	 */
	@Source("public/sc/icons/silk/text_align_left.png")
	ImageResource sldFormatButton();

	/**
	 * Save SLD text image.
	 * 
	 * @return
	 */
	@Source("public/sc/icons/silk/disk.png")
	ImageResource sldSaveButton();

	/**
	 * Validate SLD text image.
	 * 
	 * @return
	 */
	@Source("public/sc/icons/silk/tick.png")
	ImageResource sldValidateButton();

}