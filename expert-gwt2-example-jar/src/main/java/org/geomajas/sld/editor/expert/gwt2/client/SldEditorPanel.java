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

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import org.geomajas.gwt2.example.base.client.sample.SamplePanel;
import org.geomajas.sld.editor.expert.common.client.SldEditor;
import org.geomajas.sld.editor.expert.common.client.SldEditorWidget;
import org.geomajas.sld.editor.expert.common.client.event.SldCancelEvent;
import org.geomajas.sld.editor.expert.common.client.event.SldSaveEvent;
import org.geomajas.sld.editor.expert.common.client.i18n.SldEditorExpertMessages;

/**
 * Entry point of Gwt2 SLD editor.
 *
 * @author David Debuck
 */
public class SldEditorPanel implements SamplePanel {
	

	private SldEditorExpertMessages msg = GWT.create(SldEditorExpertMessages.class);

	/**
	 * UI binder for this widget.
	 *
	 * @author David Debuck
	 */
	interface MyUiBinder extends UiBinder<Widget, SldEditorPanel> {
	}

	private static final MyUiBinder UI_BINDER = GWT.create(MyUiBinder.class);

	@UiField
	protected SimpleLayoutPanel panel;

	private Widget layout;

	/**
	 * Constructor.
	 */
	public SldEditorPanel() {

		layout = UI_BINDER.createAndBindUi(this);
		final SldEditorWidget widget = new SldEditorWidget();
		SldEditor.getInstance().getEventBus().addHandler(SldSaveEvent.getType(), new SldSaveEvent.SldSaveHandler() {
			
			@Override
			public void onSldSave(SldSaveEvent event) {
				widget.getView().showMessage(msg.sldSaveMessage());			
			}
		});
		
		SldEditor.getInstance().getEventBus()
				.addHandler(SldCancelEvent.getType(), new SldCancelEvent.SldCancelHandler() {

					@Override
					public void onSldCancel(SldCancelEvent event) {
						widget.getView().showMessage(msg.sldCancelMessage());
						widget.getView().cancelButtonEvent();
					}
				});

		panel.setWidget(widget);

	}

	@Override
	public Widget asWidget() {
		return layout;
	}

}
