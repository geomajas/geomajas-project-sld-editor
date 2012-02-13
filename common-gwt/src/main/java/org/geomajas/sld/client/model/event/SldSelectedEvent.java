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
package org.geomajas.sld.client.model.event;

import org.geomajas.sld.StyledLayerDescriptorInfo;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class SldSelectedEvent extends GwtEvent<SldSelectedEvent.SldSelectedHandler> {

	private StyledLayerDescriptorInfo sld;

	public SldSelectedEvent(StyledLayerDescriptorInfo sld) {
		this.sld = sld;
	}

	public static void fire(HasHandlers source, StyledLayerDescriptorInfo sld) {
		SldSelectedEvent eventInstance = new SldSelectedEvent(sld);
		source.fireEvent(eventInstance);
	}

	public static void fire(HasHandlers source, SldSelectedEvent eventInstance) {
		source.fireEvent(eventInstance);
	}
	
	public StyledLayerDescriptorInfo getSld() {
		return sld;
	}

	/**
	 * {@link HasHandlers} indicator for this event.
	 * 
	 * @author Jan De Moerloose
	 * 
	 */
	public interface HasSldSelectedHandlers extends HasHandlers {

		HandlerRegistration addSldSelectedHandler(SldSelectedHandler handler);
	}

	/**
	 * {@link EventHandler} interface for this event.
	 * 
	 * @author Jan De Moerloose
	 * 
	 */
	public interface SldSelectedHandler extends EventHandler {

		/**
		 * Called when the SLD list has changed.
		 * 
		 * @param event event
		 */
		void onSldSelected(SldSelectedEvent event);
	}

	private static final Type<SldSelectedHandler> TYPE = new Type<SldSelectedHandler>();

	public static Type<SldSelectedHandler> getType() {
		return TYPE;
	}

	@Override
	public Type<SldSelectedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SldSelectedHandler handler) {
		handler.onSldSelected(this);
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
		return "SldSelectedEvent[" + "]";
	}
}