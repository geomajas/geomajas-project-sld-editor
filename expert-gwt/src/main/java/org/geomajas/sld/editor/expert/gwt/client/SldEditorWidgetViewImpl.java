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
package org.geomajas.sld.editor.expert.gwt.client;

import java.util.LinkedHashMap;
import java.util.List;

import org.geomajas.codemirror.client.widget.CodeMirrorPanel;
import org.geomajas.sld.editor.expert.common.client.SldEditor;
import org.geomajas.sld.editor.expert.common.client.SldEditorWidgetPresenter;
import org.geomajas.sld.editor.expert.common.client.SldEditorWidgetResource;
import org.geomajas.sld.editor.expert.common.client.SldEditorWidgetView;
import org.geomajas.sld.editor.expert.common.client.domain.SldInfo;
import org.geomajas.sld.editor.expert.common.client.i18n.SldEditorExpertMessages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.WidgetCanvas;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * Implementation of {@link org.geomajas.sld.editor.expert.common.client.SldEditorWidgetView}.
 *
 * @author Kristof Heirwegh
 * @author David Debuck
 */
public class SldEditorWidgetViewImpl implements SldEditorWidgetView {

	private SldEditorExpertMessages msg = GWT.create(SldEditorExpertMessages.class);

	/* private Window widget; */

	private VLayout widget;

	private SldEditorWidgetPresenter presenter;

	private CodeMirrorPanel codeMirrorPanel;

	private ToolStrip toolStrip;

	private SelectItem selectTemplate;

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

		widget = createSldEditorLayout();

	}

	/**
	 * Create the Sld Editor layout and attach handlers to the actions.
	 *
	 * @return the Sld Editor layout
	 */
	public VLayout createSldEditorLayout() {

		final VLayout vLayout = new VLayout();

		toolStrip = new ToolStrip();
		toolStrip.setWidth100();

		codeMirrorPanel = new CodeMirrorPanel();
		WidgetCanvas canvas = new WidgetCanvas(codeMirrorPanel);
		canvas.setWidth100();
		canvas.setHeight100();

		vLayout.addMember(toolStrip);
		vLayout.addMember(canvas);

		ToolStripButton saveButton = new ToolStripButton();
		saveButton.setIcon("[ISOMORPHIC]/" + "icons/silk/disk.png");
		saveButton.setTitle(msg.saveButtonTitle());
		saveButton.setTooltip(msg.saveButtonTooltip());
		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent clickEvent) {
				presenter.onSaveButton();
			}
		});

		ToolStripButton cancelButton = new ToolStripButton();
		cancelButton.setIcon("[ISOMORPHIC]/" + "icons/silk/cancel.png");
		cancelButton.setTitle(msg.cancelButtonTitle());
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent clickEvent) {
				presenter.onCancelButton();
			}
		});

		ToolStripButton validateButton = new ToolStripButton();
		validateButton.setIcon("[ISOMORPHIC]/" + "icons/silk/tick.png");
		validateButton.setTitle(msg.validateButtonTitle());
		validateButton.setTooltip(msg.validateButtonTooltip());
		validateButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent clickEvent) {
				presenter.onValidateButton();
			}
		});

		ToolStripButton formatBtn = new ToolStripButton();
		formatBtn.setIcon("[ISOMORPHIC]/" + "icons/silk/text_align_left.png");
		formatBtn.setTitle(msg.formatButtonTitle());
		formatBtn.setTooltip(msg.formatButtonTooltip());
		formatBtn.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				presenter.onFormatButton();
			}
		});

		selectTemplate = new SelectItem();
		selectTemplate.setTitle(msg.templateSelectTitle());
		selectTemplate.setTooltip(msg.templateSelectTooltip());
		selectTemplate.setWidth(200);
		selectTemplate.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent changeEvent) {
				presenter.onTemplateSelect((String) changeEvent.getValue());
			}
		});

		toolStrip.addFormItem(selectTemplate);
		toolStrip.addButton(saveButton);
		toolStrip.addButton(validateButton);
		toolStrip.addButton(formatBtn);
		/*
		 * toolStrip.addSeparator(); toolStrip.addButton(saveButton);
		 */
		toolStrip.addFill();
		toolStrip.addButton(cancelButton);

		return vLayout;

	}

	@Override
	public void setPresenter(SldEditorWidgetPresenter presenter) {

		this.presenter = presenter;

	}

	@Override
	public void showMessage(String message) {

		SC.say(message);

	}

	@Override
	public void setSldTemplates(final List<SldInfo> sldTemplates) {

		Scheduler.get().scheduleDeferred(new Command() {

			public void execute() {

				LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
				for (SldInfo info : sldTemplates) {
					data.put(info.getName(), info.getTitle() != null ? info.getTitle() : info.getName());
				}

				selectTemplate.setValueMap(data);

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

		codeMirrorPanel.getEditor().setContent("");
		codeMirrorPanel.getEditor().clearHistory();

	}

	@Override
	public Widget asWidget() {

		return widget;

	}

}