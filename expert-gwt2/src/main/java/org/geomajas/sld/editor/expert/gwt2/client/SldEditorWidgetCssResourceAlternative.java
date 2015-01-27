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

import org.geomajas.sld.editor.expert.common.client.SldEditorWidgetCssResource;

/**
 * Interface for the Sld Editor Widget Resource.
 *
 * @author David Debuck
 *
 */
public interface SldEditorWidgetCssResourceAlternative extends SldEditorWidgetCssResource {

	@ClassName("gm-toolbar")
	String sldEditorToolbar();

	@ClassName("gm-toolbarElementTitle")
	String sldEditorToolbarElementTitle();

	@ClassName("gm-toolbarElement")
	String sldEditorToolbarElement();

	@ClassName("gm-biggerText")
	String sldEditorBiggerText();

	@ClassName("gm-codeMirror")
	String sldEditorCodeMirror();

}