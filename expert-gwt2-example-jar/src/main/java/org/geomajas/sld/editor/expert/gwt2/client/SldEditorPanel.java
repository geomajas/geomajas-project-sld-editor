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
package org.geomajas.sld.editor.expert.gwt2.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import org.geomajas.gwt2.example.base.client.sample.SamplePanel;
import org.geomajas.sld.editor.expert.common.client.SldEditorWidget;

/**
 * Entry point of Gwt2 SLD editor.
 *
 * @author David Debuck
 */
public class SldEditorPanel implements SamplePanel {

	/**
	 * UI binder for this widget.
	 *
	 * @author David Debuck
	 */
	interface MyUiBinder extends UiBinder<Widget, SldEditorPanel> {
	}

	private static final MyUiBinder UI_BINDER = GWT.create(MyUiBinder.class);

	@UiField
	protected HTMLPanel panel;

	private Widget layout;

	/**
	 * Constructor.
	 */
	public SldEditorPanel() {

		layout = UI_BINDER.createAndBindUi(this);

		panel.add(new SldEditorWidget());

	}

	@Override
	public Widget asWidget() {
		return layout;
	}

}
