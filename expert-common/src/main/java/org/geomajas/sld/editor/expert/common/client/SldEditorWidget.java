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

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.geomajas.annotation.Api;

/**
 * Entry point for the SldEditorWidget.
 *
 * @author David Debuck
 * @since Version 1.0.0
 *
 */
@Api
public class SldEditorWidget implements IsWidget {

	private SldEditorWidgetView view;

	private SldEditorWidgetPresenter presenter;

	/**
	 * SldEditorWidget constructor.
	 */
	@Api
	public SldEditorWidget() {
		this(SldEditor.getInstance().getViewFactory().createSldEditorWidgetView());
	}

	/**
	 * SldEditorWidget constructor.
	 *
	 * @param view SldEditorWidgetView
	 */
	public SldEditorWidget(SldEditorWidgetView view) {
		this.view = view;
		presenter = new SldEditorWidgetPresenterImpl(view);
	}

	@Override
	public Widget asWidget() {
		return view.asWidget();
	}

}
