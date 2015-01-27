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
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;

/**
 * Custom button for the Sld Editor Toolbar.
 *
 * @author David Debuck
 *
 */
public class SldEditorCustomToolBarButton extends Button {

	private String text;

	/**
	 * Constructor.
	 */
	public SldEditorCustomToolBarButton() {
		super();
	}

	/**
	 * Attach an ImageResource to the button.
	 *
	 * @param imageResource imageResource
	 */
	public void setResource(ImageResource imageResource) {

		Image img = new Image(imageResource);
		DOM.insertChild(getElement(), img.getElement(), 0);

	}

	@Override
	public void setText(String text) {

		this.text = text;
		Element span = DOM.createElement("span");
		span.setInnerText(text);
		DOM.insertChild(getElement(), span, 1);

	}

	@Override
	public String getText() {

		return this.text;

	}

}
