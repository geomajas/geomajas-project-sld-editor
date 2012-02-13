/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2012 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the GNU Affero
 * General Public License. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */

package org.geomajas.sld.client.gin;

import org.geomajas.sld.client.NameTokens;
import org.geomajas.sld.client.SldEditorPlaceManager;
import org.geomajas.sld.client.model.SldManager;
import org.geomajas.sld.client.model.SldManagerImpl;
import org.geomajas.sld.client.view.ViewUtil;
import org.geomajas.sld.editor.client.i18n.SldEditorMessages;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.gwtplatform.mvp.client.googleanalytics.GoogleAnalytics;
import com.gwtplatform.mvp.client.googleanalytics.GoogleAnalyticsImpl;
import com.gwtplatform.mvp.client.proxy.ParameterTokenFormatter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

/**
 * Client module for the common SLD editor part.
 * 
 * @author Jan De Moerloose
 * 
 */
public class ClientModule extends AbstractGinModule {

	protected void configure() {
		// Default implementation of standard resources
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(Singleton.class);
		bind(GoogleAnalytics.class).to(GoogleAnalyticsImpl.class).in(Singleton.class);
		bind(PlaceManager.class).to(SldEditorPlaceManager.class).in(Singleton.class);

		// Constants
		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.HOME_PAGE);

		// Manager
		bind(SldManager.class).to(SldManagerImpl.class).in(Singleton.class);
		
		// i18n
		bind(SldEditorMessages.class).in(Singleton.class);
	}

}
