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

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.Window;
import org.geomajas.gwt.example.base.SamplePanel;
import org.geomajas.gwt.example.base.SamplePanelFactory;
import org.geomajas.sld.editor.expert.common.client.SldEditorWidget;

/**
 * Entry point of SmartGWT SLD editor.
 *
 * @author Kristof Heirwegh
 * @author David Debuck
 */
public class SldEditorPanel extends SamplePanel {

	/**
	 * This is called by the showcase when clicking on the tree node.
	 */
	public static final SamplePanelFactory FACTORY = new SamplePanelFactory() {
		public SamplePanel createPanel() {
			return new SldEditorPanel();
		}
	};

	/**
	 * Create background content decoration for the widget tab.
	 *
	 * @return HTMLFlow background content.
	 */
	private HTMLFlow getBackgroundDecoration() {

		// Show some background content in the tab.
		HTMLFlow htmlFlow = new HTMLFlow();
		htmlFlow.setWidth100();
		htmlFlow.setHeight100();
		String contents =
			"<div style='margin-left: 5px; font-size: 100pt; font-weight: bold; color:#DDFFDD'>GEOMAJAS</div>"
			+ "<div style='margin-left: 10px; margin-top:-70px; font-size: 50pt; color:#CCCCCC'>SLD-Editor</div>"
			+ "<div style='margin-left: 10px; margin-top:-15px; font-size: 28pt; color:#DDDDDD'>EXPERT-mode</div>";
		htmlFlow.setContents(contents);

		return htmlFlow;

	}

	@Override
	public Canvas getViewPanel() {

		Window window = new Window();

		window.setAutoCenter(true);
		window.setShowMinimizeButton(false);
		window.setShowCloseButton(true);
		window.setAutoSize(true);

		// Create a new Sld Editor Widget.
		SldEditorWidget widget = new SldEditorWidget();

		window.addItem(widget.asWidget());

		window.show();

		return getBackgroundDecoration();

	}

	@Override
	public String getDescription() {
		return SldEditorExample.MSG.sampleDescription();
	}

	@Override
	public String[] getConfigurationFiles() {
		return new String[] {"classpath:org/geomajas/sld/editor/expert/gwt/context/application.xml"};
	}
}
