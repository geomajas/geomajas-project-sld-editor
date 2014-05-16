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
package org.geomajas.sld.editor.expert.gwt.client;

import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.web.bindery.event.shared.EventBus;
import org.geomajas.sld.editor.expert.common.client.SldEditor;
import org.geomajas.sld.editor.expert.common.client.SldEditorWidgetPresenter;
import org.geomajas.sld.editor.expert.common.client.SldEditorWidgetPresenterImpl;
import org.geomajas.sld.editor.expert.common.client.SldEditorWidgetView;
import org.geomajas.sld.editor.expert.common.client.model.SldGwtServiceAsync;
import org.geomajas.sld.editor.expert.common.client.model.SldManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Sld Editor Widget Presenter Unit Test class.
 *
 * @author David Debuck
 *
 */
@RunWith(GwtMockitoTestRunner.class)
public class SldEditorWidgetPresenterImplTest {

	@Mock
	private SldEditorWidgetView view;

	@Mock
	private SldGwtServiceAsync sldGwtServiceAsync;

	@Mock
	private SldEditor sldEditor;

	@Mock
	private EventBus eventBus;

	@Mock
	private SldManager sldManager;

	private SldEditorWidgetPresenter presenter;

	@Before
	public void PrepareTest() {

		SldEditor.setInstance(sldEditor);
		when(sldEditor.getSldManager()).thenReturn(sldManager);
		when(sldManager.getEventBus()).thenReturn(eventBus);

		presenter = new SldEditorWidgetPresenterImpl(view);

	}

	@Test
	public void onCancelButtonTest() {

		presenter.onCancelButton();
		verify(view).showMessage("sldCancelMessage");

	}

	@Test
	public void onSaveButtonTest() {

		presenter.onSaveButton();
		verify(view).showMessage("sldSaveMessage");

	}

}