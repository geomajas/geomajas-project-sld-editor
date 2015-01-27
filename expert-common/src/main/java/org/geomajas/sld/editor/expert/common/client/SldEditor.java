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

import com.google.gwt.core.client.GWT;
import org.geomajas.sld.editor.expert.common.client.model.SldManager;
import org.geomajas.sld.editor.expert.common.client.model.SldManagerImpl;

/**
 * Widget Factory class.
 *
 * @author David Debuck
 *
 */
public final class SldEditor {

	private static SldEditor instance;

	private SldEditorViewFactory viewFactory;

	private SldEditorClientBundleFactory bundleFactory;

	private SldEditorEventBusFactory eventBusFactory;

	private SldEditorServiceFactory sldEditorServiceFactory;

	private SldManager sldManager;

	/**
	 * SldEditor Constructor.
	 */
	private SldEditor() {
	}

	/**
	 * SldEditor Constructor.
	 *
	 * @return instance of SldEditor
	 */
	public static SldEditor getInstance() {
		if (instance == null) {
			instance = new SldEditor();
		}
		return instance;
	}

	/**
	 * Set an instance of this class.
	 *
	 * @param instance
	 */
	public static void setInstance(SldEditor instance) {
		SldEditor.instance = instance;
	}

	/**
	 * Factory for the Sld Editor Client Bundle.
	 *
	 * @return SldEditorClientBundleFactory
	 */
	public SldEditorClientBundleFactory getBundleFactory() {
		if (bundleFactory == null) {
			bundleFactory = GWT.create(SldEditorClientBundleFactory.class);
		}
		return bundleFactory;
	}

	/**
	 * Factory for the Sld Editor View.
	 *
	 * @return SldEditorViewFactory
	 */
	public SldEditorViewFactory getViewFactory() {
		if (viewFactory == null) {
			viewFactory = GWT.create(SldEditorViewFactory.class);
		}
		return viewFactory;
	}

	/**
	 * Factory for the Sld Editor EventBus.
	 *
	 * @return SldEditorEventBusFactory
	 */
	public SldEditorEventBusFactory getSldEditorEventBusFactory() {
		if (eventBusFactory == null) {
			eventBusFactory = GWT.create(SldEditorEventBusFactory.class);
		}
		return eventBusFactory;
	}

	/**
	 * Factory for the Sld Editor Service.
	 *
	 * @return SldEditorServiceFactory
	 */
	public SldEditorServiceFactory getSldEditorServiceFactory() {
		if (sldEditorServiceFactory == null) {
			sldEditorServiceFactory = GWT.create(SldEditorServiceFactory.class);
		}
		return sldEditorServiceFactory;
	}

	/**
	 * Create an instance of the SldManager.
	 *
	 * @return the SldManager
	 */
	public SldManager getSldManager() {
		if (sldManager == null) {
			sldManager = new SldManagerImpl(
					getSldEditorEventBusFactory().createSldEditorWidgetEventBus(),
					getSldEditorServiceFactory().createSldGwtServiceAsync());
		}
		return sldManager;
	}

}
