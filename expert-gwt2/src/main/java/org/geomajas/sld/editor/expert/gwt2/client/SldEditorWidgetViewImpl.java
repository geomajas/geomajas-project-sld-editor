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
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import org.geomajas.codemirror.client.widget.CodeMirrorPanel;
import org.geomajas.sld.editor.expert.common.client.SldEditor;
import org.geomajas.sld.editor.expert.common.client.SldEditorWidgetPresenter;
import org.geomajas.sld.editor.expert.common.client.SldEditorWidgetResource;
import org.geomajas.sld.editor.expert.common.client.SldEditorWidgetView;
import org.geomajas.sld.editor.expert.common.client.domain.SldInfo;
import org.geomajas.sld.editor.expert.common.client.i18n.SldEditorExpertMessages;

import java.util.List;

/**
 * Implementation of {@link org.geomajas.sld.editor.expert.common.client.SldEditorWidgetView}.
 *
 * @author David Debuck
 */
public class SldEditorWidgetViewImpl implements SldEditorWidgetView {

	private SldEditorExpertMessages msg = GWT.create(SldEditorExpertMessages.class);

	@UiField
	protected ListBox selectTemplate;

	@UiField
	protected SldEditorCustomToolBarButton validateSld;

/*	@UiField
	protected SldEditorCustomToolBarButton saveSld;*/

	@UiField
	protected SldEditorCustomToolBarButton cancelSld;

	@UiField
	protected CodeMirrorPanel codeMirrorPanel;

	private SldEditorWidgetPresenter presenter;

	private HTMLPanel widget;

	private static final MyUiBinder UIBINDER = GWT.create(MyUiBinder.class);

	/**
	 * UI binder interface.
	 *
	 * @author David Debuck
	 *
	 */
	interface MyUiBinder extends UiBinder<Widget, SldEditorWidgetViewImpl> {
	}

	/**
	 * SldEditorWidgetViewImpl constructor.
	 */
	public SldEditorWidgetViewImpl() {
		this(SldEditor.getInstance().getBundleFactory().createSldEditorWidgetResource());
	}

	/**
	 * SldEditorWidgetViewImpl constructor.
	 *
	 * @param resource SldEditorWidgetResource
	 */
	public SldEditorWidgetViewImpl(SldEditorWidgetResource resource) {

		resource.css().ensureInjected();
		widget = (HTMLPanel) UIBINDER.createAndBindUi(this);

		selectTemplate.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {

				updateViewOnAction();

				// Only fetch the template when the selected index is not the tooltip.
				if (selectTemplate.getSelectedIndex() != 0) {

					/*saveSld.setEnabled(true);*/

					presenter.onTemplateSelect(
							selectTemplate.getValue(selectTemplate.getSelectedIndex())
					);

				}

			}
		});

		validateSld.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				presenter.onValidateButton();

			}
		});

/*		saveSld.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				presenter.onSaveButton();

			}
		});*/

		cancelSld.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				presenter.onCancelButton();

			}
		});

	}

	/**
	 * Update the view action elements.
	 */
	private void updateViewOnAction() {

		/*saveSld.setEnabled(false);*/
		codeMirrorPanel.getEditor().setContent("");
		codeMirrorPanel.getEditor().clearHistory();

	}

	@Override
	public void setPresenter(SldEditorWidgetPresenter presenter) {

		this.presenter = presenter;

	}

	@Override
	public void showMessage(String message) {

		Window.alert(message);

	}

	@Override
	public void setSldTemplates(final List<SldInfo> sldTemplates) {

		Scheduler.get().scheduleDeferred(new Command() {
			public void execute() {

				selectTemplate.addItem(msg.templateSelectTooltip());

				for (SldInfo sld : sldTemplates) {

					selectTemplate.addItem(sld.getTitle(), sld.getName());

				}

			}
		});

	}

	@Override
	public void setSldData(String xmlData) {

		codeMirrorPanel.getEditor().setContent(xmlData);

	}

	@Override
	public String getSldData() {

		return codeMirrorPanel.getEditor().getContent();

	}

	@Override
	public void cancelButtonEvent() {

		updateViewOnAction();
		selectTemplate.setSelectedIndex(0);

	}

	@Override
	public Widget asWidget() {
		return widget;
	}

}