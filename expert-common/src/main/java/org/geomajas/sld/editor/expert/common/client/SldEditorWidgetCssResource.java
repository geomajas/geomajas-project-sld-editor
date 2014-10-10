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

import com.google.gwt.resources.client.CssResource;
import org.geomajas.annotation.Api;

/**
 * Interface for the Sld Editor Widget Resource.
 *
 * @author David Debuck
 * @since Version 1.0.0
 *
 */
@Api(allMethods = true)
public interface SldEditorWidgetCssResource extends CssResource {

	/**
	 * Css class.
	 * @return String
	 */
	@ClassName("gm-sldEditorToolStrip")
	String sldEditorToolStrip();

	/**
	 * Css class.
	 * @return String
	 */
	@ClassName("gm-sldEditorCodeMirrorPanel")
	String sldEditorCodeMirrorPanel();

	/**
	 * Css class.
	 * @return String
	 */
	@ClassName("gm-sldEditorSelectTemplateListBox")
	String sldEditorSelectTemplateListBox();

}