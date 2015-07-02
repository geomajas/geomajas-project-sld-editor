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
package org.geomajas.sld.editor.expert.common.client.model;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.geomajas.sld.StyledLayerDescriptorInfo;
import org.geomajas.sld.editor.expert.common.client.domain.RawSld;
import org.geomajas.sld.editor.expert.common.client.domain.SldInfo;
import org.geomajas.sld.editor.expert.common.client.event.SldFormattedEvent;
import org.geomajas.sld.editor.expert.common.client.event.SldSaveEvent;
import org.geomajas.sld.editor.expert.common.client.event.SldSaveEvent.HasSldSaveHandlers;
import org.geomajas.sld.editor.expert.common.client.event.SldSaveEvent.SldSaveHandler;
import org.geomajas.sld.editor.expert.common.client.event.SldValidatedEvent;
import org.geomajas.sld.editor.expert.common.client.event.SldValidatedEvent.HasSldValidatedHandlers;
import org.geomajas.sld.editor.expert.common.client.event.SldValidatedEvent.SldValidatedHandler;
import org.geomajas.sld.editor.expert.common.client.event.TemplateLoadedEvent;
import org.geomajas.sld.editor.expert.common.client.event.TemplateLoadedEvent.HasTemplateLoadedHandlers;
import org.geomajas.sld.editor.expert.common.client.event.TemplateLoadedEvent.TemplateLoadedHandler;
import org.geomajas.sld.editor.expert.common.client.event.TemplateNamesLoadedEvent;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * Default implementation of {@link SldManager}.
 *
 * @author Jan De Moerloose
 * @author Kristof Heirwegh
 */
public class SldManagerImpl implements SldManager, HasTemplateLoadedHandlers, HasSldValidatedHandlers,
		HasSldSaveHandlers {

	private final Logger log = Logger.getLogger(SldManagerImpl.class.getName());

	private final SldGwtServiceAsync service;

	private final EventBus eventBus;

	private SldModel model = new SldModelImpl();

	public SldManagerImpl(EventBus eventBus, SldGwtServiceAsync service) {
		this.eventBus = eventBus;
		this.service = service;
	}

	public void fireEvent(GwtEvent<?> event) {
		eventBus.fireEvent(event);
	}

	public void fetchTemplateNames() {
		model.getTemplateNames().clear();
		service.findTemplates(new AsyncCallback<List<SldInfo>>() {
			public void onSuccess(List<SldInfo> result) {
				for (SldInfo template : result) {
					model.getTemplateNames().add(template);
				}
				TemplateNamesLoadedEvent.fire(SldManagerImpl.this);
			}

			public void onFailure(Throwable caught) {
				log.log(Level.SEVERE, "fetchTemplateNames failed", caught);
			}
		});
	}

	public void fetchTemplate(String name) {
		model.setTemplate(null);
		service.findTemplateByName(name, new AsyncCallback<RawSld>() {
			public void onSuccess(RawSld result) {
				model.setTemplate(result);
				TemplateLoadedEvent.fire(SldManagerImpl.this);
			}

			public void onFailure(Throwable caught) {
				log.log(Level.SEVERE, "findTemplateByName failed", caught);
			}
		});
	}

	public void validateCurrent(final boolean saveAfterValidation) {
		service.validate(model.getRawSld(), new AsyncCallback<Boolean>() {
			public void onFailure(Throwable caught) {
				SldValidatedEvent.fireInValid(SldManagerImpl.this);
				model.setValid(false);
			}

			public void onSuccess(Boolean result) {
				if (result != null && result) {
					model.setValid(true);
					if (saveAfterValidation) {
						service.convertRawToDescriptor(model.getRawSld(),
								new AsyncCallback<StyledLayerDescriptorInfo>() {
									public void onSuccess(StyledLayerDescriptorInfo result) {
										model.setSldDescriptor(result);
										SldSaveEvent.fire(SldManagerImpl.this);
									}

									public void onFailure(Throwable caught) {
										// should not happen as validation was OK.
										SldValidatedEvent.fireInValid(SldManagerImpl.this);
										model.setValid(false);
									}
								});
					} else {
						SldValidatedEvent.fireValid(SldManagerImpl.this);
					}
				} else {
					onFailure(null);
				}
			}
		});
	}

	public void formatCurrent() {
		service.format(model.getRawSld(), new AsyncCallback<RawSld>() {

			public void onFailure(Throwable caught) {
				SldFormattedEvent.fireNotFormatted(SldManagerImpl.this);
			}

			public void onSuccess(RawSld result) {
				model.setRawSld(result);
				SldFormattedEvent.fireFormatted(SldManagerImpl.this);
			}
		});
	}

	// ---------------------------------------------------------------

	public HandlerRegistration addTemplateLoadedHandler(TemplateLoadedHandler handler) {
		return eventBus.addHandler(TemplateLoadedEvent.getType(), handler);
	}

	public HandlerRegistration addSldValidatedHandler(SldValidatedHandler handler) {
		return eventBus.addHandler(SldValidatedEvent.getType(), handler);
	}

	public HandlerRegistration addSldSaveHandler(SldSaveHandler handler) {
		return eventBus.addHandler(SldSaveEvent.getType(), handler);
	}

	// ---------------------------------------------------------------

	public SldModel getModel() {
		return model;
	}

	public void convertToRawSld(StyledLayerDescriptorInfo sldi, AsyncCallback<RawSld> callback) {
		service.convertDescriptorToRaw(sldi, callback);
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}
}
