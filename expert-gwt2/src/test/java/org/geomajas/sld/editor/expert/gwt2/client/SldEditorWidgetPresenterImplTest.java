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
package org.geomajas.sld.editor.expert.gwt2.client;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.geomajas.sld.editor.expert.common.client.SldEditor;
import org.geomajas.sld.editor.expert.common.client.SldEditorWidgetPresenter;
import org.geomajas.sld.editor.expert.common.client.SldEditorWidgetPresenterImpl;
import org.geomajas.sld.editor.expert.common.client.SldEditorWidgetView;
import org.geomajas.sld.editor.expert.common.client.event.SldCancelEvent;
import org.geomajas.sld.editor.expert.common.client.model.SldGwtServiceAsync;
import org.geomajas.sld.editor.expert.common.client.model.SldManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;

import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.web.bindery.event.shared.EventBus;

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

	@Mock(answer=Answers.RETURNS_DEEP_STUBS)
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
		verify(eventBus).fireEvent(any(SldCancelEvent.class));

	}

	@Test
	public void onSaveButtonTest() {
		
		when(view.getSldData()).thenReturn("<a/>");
		presenter.onSaveButton();
		verify(sldManager.getModel().getRawSld()).setXml("<a/>");
		verify(sldManager).validateCurrent(true);

	}

}