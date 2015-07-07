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
package org.geomajas.sld.editor.expert.common.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * Event that returns the result of a formattingRequest.
 * 
 * @author Jan De Moerloose
 */
public class SldFormattedEvent extends GwtEvent<SldFormattedEvent.SldFormattedHandler> {

	private final boolean formatted;
	
	public SldFormattedEvent(boolean formatted) {
		this.formatted = formatted;
	}
	
	public boolean isFormatted() {
		return formatted;
	}

	public static void fireFormatted(HasHandlers source) {
		SldFormattedEvent eventInstance = new SldFormattedEvent(true);
		source.fireEvent(eventInstance);
	}

	public static void fireNotFormatted(HasHandlers source) {
		SldFormattedEvent eventInstance = new SldFormattedEvent(false);
		source.fireEvent(eventInstance);
	}

	public static void fire(HasHandlers source, SldFormattedEvent eventInstance) {
		source.fireEvent(eventInstance);
	}

	/**
	 * {@link HasHandlers} indicator for this event.
	 * 
	 * @author Jan De Moerloose
	 */
	public interface HasSldFormattedHandlers extends HasHandlers {
		HandlerRegistration addSldFormattedHandler(SldFormattedHandler handler);
	}

	/**
	 * {@link EventHandler} interface for this event.
	 * 
	 * @author Jan De Moerloose
	 */
	public interface SldFormattedHandler extends EventHandler {

		/**
		 * Called when the SLD was formatted.
		 * 
		 * @param event event
		 */
		void onSldFormatted(SldFormattedEvent event);
	}

	private static final Type<SldFormattedHandler> TYPE = new Type<SldFormattedHandler>();

	public static Type<SldFormattedHandler> getType() {
		return TYPE;
	}

	@Override
	public Type<SldFormattedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SldFormattedHandler handler) {
		handler.onSldFormatted(this);
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return "SldFormattedEvent[" + "]";
	}
}