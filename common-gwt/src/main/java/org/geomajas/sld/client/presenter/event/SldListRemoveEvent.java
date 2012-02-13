package org.geomajas.sld.client.presenter.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;


public class SldListRemoveEvent  extends GwtEvent<SldListRemoveEvent.SldListRemoveHandler> {

	public SldListRemoveEvent() {
		// Possibly for serialization.
	}

	public static void fire(HasHandlers source) {
		SldListRemoveEvent eventInstance = new SldListRemoveEvent();
		source.fireEvent(eventInstance);
	}

	public static void fire(HasHandlers source, SldListRemoveEvent eventInstance) {
		source.fireEvent(eventInstance);
	}

	/**
	 * {@link HasHandlers} indicator for this event.
	 * 
	 * @author Jan De Moerloose
	 * 
	 */
	public interface HasSldListRemoveHandlers extends HasHandlers {

		HandlerRegistration addSldListRemoveHandler(SldListRemoveHandler handler);
	}

	/**
	 * {@link EventHandler} interface for this event.
	 * 
	 * @author Jan De Moerloose
	 * 
	 */
	public interface SldListRemoveHandler extends EventHandler {

		/**
		 * Notifies side content child presenter to reveal itself.
		 * 
		 * @param event the event
		 */
		void onSldListRemove(SldListRemoveEvent event);
	}

	private static final Type<SldListRemoveHandler> TYPE = new Type<SldListRemoveHandler>();

	public static Type<SldListRemoveHandler> getType() {
		return TYPE;
	}

	@Override
	public Type<SldListRemoveHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SldListRemoveHandler handler) {
		handler.onSldListRemove(this);
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
		return "SldListRemoveEvent[" + "]";
	}
}