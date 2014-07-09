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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import org.geomajas.gwt2.example.base.client.sample.SamplePanel;
import org.geomajas.gwt2.example.base.client.sample.SamplePanelRegistry;
import org.geomajas.gwt2.example.base.client.sample.ShowcaseSampleDefinition;
import org.geomajas.sld.editor.expert.gwt2.client.i18n.Messages;

/**
 * Entry point and main class for GWT application. This class defines the layout and functionality of this application.
 *
 * @author David Debuck
 */
public class SldEditorExample implements EntryPoint {

	public static final Messages MSG = GWT.create(Messages.class);

	public static final String CATEGORY_GENERAL = "General Samples";

	public void onModuleLoad() {

		// Register the gwt 2 example to the showcase.
		SamplePanelRegistry.registerCategory(CATEGORY_GENERAL, 100);
		SamplePanelRegistry.registerFactory(CATEGORY_GENERAL, new ShowcaseSampleDefinition() {

			public SamplePanel create() {
				return new SldEditorPanel();
			}

			public String getTitle() {
				return "SLD Editor";
			}

			public String getShortDescription() {
				return "This example show an implementation of the SLD editor.";
			}

			public String getDescription() {
				return "This example show an implementation of the SLD editor.";
			}

			public String getCategory() {
				return CATEGORY_GENERAL;
			}

		});

	}
}
