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

/**
 * Interface for the Sld Editor Presenter.
 *
 * @author David Debuck
 *
 */
public interface SldEditorWidgetPresenter {

	/**
	 * Save the sld document.
	 */
	void onSaveButton();

	/**
	 * Cancel editing the sld document.
	 */
	void onCancelButton();

	/**
	 * Validate the current sld document in the editor.
	 */
	void onValidateButton();
	
	/**
	 * Format the current sld document in the editor.
	 */
	void onFormatButton();

	/**
	 * Show the selected sld template in the editor.
	 *
	 * @param template String
	 */
	void onTemplateSelect(String template);

}
