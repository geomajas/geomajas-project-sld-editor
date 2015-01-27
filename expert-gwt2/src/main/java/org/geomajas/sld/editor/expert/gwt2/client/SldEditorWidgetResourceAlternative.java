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

import com.google.gwt.resources.client.ImageResource;
import org.geomajas.sld.editor.expert.common.client.SldEditorWidgetResource;

/**
 * Interface for the Sld Editor Resource.
 *
 * @author David Debuck
 *
 */
public interface SldEditorWidgetResourceAlternative extends SldEditorWidgetResource {

	/**
	 * Css Resource for this widget.
	 *
	 * @return SldEditorWidgetCssResource
	 */
	@Source("alternative-sldEditor-widget.css")
	SldEditorWidgetCssResourceAlternative css();

	@Source("public/sc/icons/silk/cancel.png")
	ImageResource sldCancelButton();

	@Source("public/sc/icons/silk/disk.png")
	ImageResource sldSaveButton();

	@Source("public/sc/icons/silk/tick.png")
	ImageResource sldValidateButton();

}