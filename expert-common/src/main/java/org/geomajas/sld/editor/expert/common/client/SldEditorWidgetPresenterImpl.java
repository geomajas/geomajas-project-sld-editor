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

import org.geomajas.sld.editor.expert.common.client.event.SldCancelEvent;
import org.geomajas.sld.editor.expert.common.client.event.SldFormattedEvent;
import org.geomajas.sld.editor.expert.common.client.event.SldValidatedEvent;
import org.geomajas.sld.editor.expert.common.client.event.TemplateLoadedEvent;
import org.geomajas.sld.editor.expert.common.client.event.TemplateNamesLoadedEvent;
import org.geomajas.sld.editor.expert.common.client.i18n.SldEditorExpertMessages;

import com.google.gwt.core.client.GWT;

/**
 * Implementation of the interface {@link org.geomajas.sld.editor.expert.common.client.SldEditorWidgetPresenter}.
 *
 * @author David Debuck.
 */
public class SldEditorWidgetPresenterImpl implements
		SldEditorWidgetPresenter,
		TemplateNamesLoadedEvent.TemplateNamesLoadedHandler,
		TemplateLoadedEvent.TemplateLoadedHandler,
		SldValidatedEvent.SldValidatedHandler,
		SldFormattedEvent.SldFormattedHandler {

	private SldEditorExpertMessages msg = GWT.create(SldEditorExpertMessages.class);

	private SldEditorWidgetView view;

	/**
	 * SldEditorWidgetPresenterImpl constructor.
	 *
	 * @param view SldEditorWidgetView
	 */
	public SldEditorWidgetPresenterImpl(SldEditorWidgetView view) {

		this.view = view;
		this.view.setPresenter(this);

		// Register the implemented Event Handlers to the EventBus.
		SldEditor.getInstance().getSldManager().getEventBus().addHandler(TemplateNamesLoadedEvent.getType(), this);
		SldEditor.getInstance().getSldManager().getEventBus().addHandler(TemplateLoadedEvent.getType(), this);
		SldEditor.getInstance().getSldManager().getEventBus().addHandler(SldValidatedEvent.getType(), this);
		SldEditor.getInstance().getSldManager().getEventBus().addHandler(SldFormattedEvent.getType(), this);

		// This will fetch the available templates and fire a TemplateNamesLoaded event.
		SldEditor.getInstance().getSldManager().fetchTemplateNames();

	}

	@Override
	public void onSaveButton() {
		// We have to set the RawSld in the model before we do a validation.
		SldEditor.getInstance().getSldManager().getModel().getRawSld().setXml(
				view.getSldData()
		);
		SldEditor.getInstance().getSldManager().validateCurrent(true);
	}

	@Override
	public void onCancelButton() {
		SldEditor.getInstance().getSldManager().getEventBus().fireEvent(new SldCancelEvent());
	}

	@Override
	public void onValidateButton() {

		// We have to set the RawSld in the model before we do a validation.
		SldEditor.getInstance().getSldManager().getModel().getRawSld().setXml(
				view.getSldData()
		);

		// Validate but do not save the data.
		SldEditor.getInstance().getSldManager().validateCurrent(false);

	}

	@Override
	public void onFormatButton() {

		// We have to set the RawSld in the model before we format.
		SldEditor.getInstance().getSldManager().getModel().getRawSld().setXml(
				view.getSldData()
		);

		// Format but do not save the data.
		SldEditor.getInstance().getSldManager().formatCurrent();

	}

	@Override
	public void onTemplateSelect(String template) {

		SldEditor.getInstance().getSldManager().fetchTemplate(template);

	}

	@Override
	public void onTemplateNamesLoaded(TemplateNamesLoadedEvent event) {

		view.setSldTemplates(
			SldEditor.getInstance().getSldManager().getModel().getTemplateNames()
		);

	}

	@Override
	public void onTemplateLoaded(TemplateLoadedEvent event) {

		view.setSldData(
			SldEditor.getInstance().getSldManager().getModel().getTemplate().getXml()
		);

	}

	@Override
	public void onSldValidated(SldValidatedEvent event) {

		if (event.isValid()) {
			view.showMessage(msg.sldValidMessage());
		} else {
			view.showMessage(msg.sldInvalidMessage());
		}

	}

	@Override
	public void onSldFormatted(SldFormattedEvent event) {
		
		view.setSldData(
				SldEditor.getInstance().getSldManager().getModel().getRawSld().getXml()
			);

		if (event.isFormatted()) {
			view.showMessage(msg.formatSucceeded());
		} else {
			view.showMessage(msg.formatFailed());
		}
		
	}
	
}
