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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import org.geomajas.gwt.example.base.SampleTreeNode;
import org.geomajas.gwt.example.base.SampleTreeNodeRegistry;
import org.geomajas.sld.editor.expert.gwt.client.i18n.Messages;

/**
 * Entry point and main class for GWT application. This class defines the layout and functionality of this application.
 *
 * @author Kristof Heirwegh
 * @author David Debuck
 */
public class SldEditorExample implements EntryPoint {

	public static final Messages MSG = GWT.create(Messages.class);

	public void onModuleLoad() {

		// Register the gwt to the showcase.
		SampleTreeNodeRegistry.addSampleTreeNode(new SampleTreeNode(
				MSG.sampleTitle(),
				"[ISOMORPHIC]/geomajas/osgeo/layer-raster.png",
				"ExpertSldEditor",
				"SldEditor",
				SldEditorPanel.FACTORY
		));

	}
}
