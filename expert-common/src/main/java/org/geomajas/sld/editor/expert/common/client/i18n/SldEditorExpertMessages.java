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

package org.geomajas.sld.editor.expert.common.client.i18n;

import com.google.gwt.i18n.client.Messages;

/**
 * Localization constants for the GWT Expert SldEditor.
 *
 * @author Kristof Heirwegh
 */
public interface SldEditorExpertMessages extends Messages {

	String windowTitle();

	String saveButtonTitle();
	String saveButtonTooltip();
	String validateButtonTitle();
	String validateButtonTooltip();
	String validationFailed();
	String validationSucceeded();
	String cancelButtonTitle();

	String templateSelectTitle();
	String templateSelectTooltip();

	String confirmLoseDirtyData();

	String failedToParseSldObject();

	// Sld Editor Widget.
	String sldCancelMessage();
	String sldValidMessage();
	String sldInvalidMessage();
	String sldSaveMessage();

	String sldEditorViewFactoryUnsupportedException();

}
