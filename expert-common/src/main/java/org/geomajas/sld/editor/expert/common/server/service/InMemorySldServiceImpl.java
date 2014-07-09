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

package org.geomajas.sld.editor.expert.common.server.service;


import java.io.File;
import java.io.FilenameFilter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.geomajas.sld.NamedLayerInfo;
import org.geomajas.sld.StyledLayerDescriptorInfo;
import org.geomajas.sld.UserLayerInfo;
import org.geomajas.sld.editor.expert.common.client.domain.RawSld;
import org.geomajas.sld.editor.expert.common.client.domain.SldInfo;
import org.geomajas.sld.editor.expert.common.client.domain.SldInfoImpl;
import org.geomajas.sld.service.SldException;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

/**
 * Default implementation of the SLD service using an in-memory map. This service loads a configurable directory of SLD
 * files at startup.
 *
 * @author Jan De Moerloose
 * @author An Buyle
 */
public class InMemorySldServiceImpl implements org.geomajas.sld.editor.expert.common.server.service.SldService {

	private final Logger log = LoggerFactory.getLogger(InMemorySldServiceImpl.class);

	private static final String FILE_ENCODING = "UTF-8";

	private Map<String, RawSld> allSlds = new ConcurrentHashMap<String, RawSld>();

	private Resource directory;

	@PostConstruct
	void init() throws SldException {
		if (getDirectory() != null) {
			try {
				if (getDirectory().getFile().exists()) {
					if (getDirectory().getFile().isDirectory()) {
						File[] sldFiles = getDirectory().getFile().listFiles(new FilenameFilter() {
							public boolean accept(File dir, String name) {
								return name.endsWith(".sld") || name.endsWith(".xml");
							}
						});
						for (File file : sldFiles) {
							RawSld raw = new RawSld();
							raw.setXml(FileUtils.readFileToString(file, FILE_ENCODING));
							String fileName = StringUtils.stripFilenameExtension(file.getName());
							StyledLayerDescriptorInfo tmp = parseXml(fileName, raw.getXml());
							raw.setName(fileName);
							raw.setTitle(tmp.getTitle());
							raw.setVersion(tmp.getVersion());
							log.info("added sld '{}' to service", fileName);
							allSlds.put(raw.getName(), raw);
						}
					}
				}
			} catch (Exception e) { // NOSONAR
				log.warn("Error while initilizing SLD service.", e);
			}
		}
	}

	// ---------------------------------------------------------------

	public Resource getDirectory() {
		return directory;
	}

	public void setDirectory(Resource directory) {
		this.directory = directory;
	}

	public List<SldInfo> findTemplates() throws SldException {
		List<SldInfo> res = new ArrayList<SldInfo>();
		for (RawSld raw : allSlds.values()) {
			res.add(new SldInfoImpl(raw.getName(), raw.getTitle()));
		}
		return res;
	}

	public RawSld findTemplateByName(String name) throws SldException {
		return allSlds.get(name);
	}

	/**
	 * Convert StyledLayerDescriptorInfo to raw xml.
	 *
	 * @param sldi
	 * @return rawSld
	 * @throws SldException
	 */
	public RawSld toXml(StyledLayerDescriptorInfo sldi) throws SldException {
		try {
			if (sldi.getVersion() == null) {
				sldi.setVersion("1.0.0");
			}
			return parseSldI(sldi);
		} catch (JiBXException e) {
			throw new SldException("Validation error", e);
		}
	}

	/**
	 * Convert raw xml to StyledLayerDescriptorInfo.
	 *
	 * @param sld
	 * @return StyledLayerDescriptorInfo
	 * @throws SldException
	 */
	public StyledLayerDescriptorInfo toSldI(RawSld sld) throws SldException {
		try {
			return parseXml(sld.getName(), sld.getXml());
		} catch (JiBXException e) {
			throw new SldException("Validation error", e);
		}
	}

	/**
	 * Test by marshalling.
	 *
	 * @param sld
	 * @throws SldException
	 */
	public void validate(StyledLayerDescriptorInfo sld) throws SldException {
		try {
			parseSldI(sld);
		} catch (JiBXException e) {
			throw new SldException("Validation error", e);
		}
	}

	/**
	 * Test by unmarshalling.
	 *
	 * @param sld
	 * @throws SldException
	 */
	public boolean validate(RawSld sld) throws SldException {
		try {
			parseXml("", sld.getXml());
			return true;
		} catch (JiBXException e) {
			return false;
		}
	}

	// ---------------------------------------------------------------

	private StyledLayerDescriptorInfo parseXml(String name, String raw) throws JiBXException {
		IBindingFactory bfact = BindingDirectory.getFactory(StyledLayerDescriptorInfo.class);
		IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
		Object object = uctx.unmarshalDocument(new StringReader(raw));
		StyledLayerDescriptorInfo sld = (StyledLayerDescriptorInfo) object;
		if (sld.getName() == null) {
			sld.setName(name);
		}
		if (sld.getTitle() == null) {
			sld.setTitle(getTitle(sld, name));
		}
		if (sld.getVersion() == null) {
			sld.setVersion("1.0.0");
		}
		return sld;
	}

	private RawSld parseSldI(StyledLayerDescriptorInfo sld) throws JiBXException {
		RawSld res = new RawSld();
		IBindingFactory bfact;
		bfact = BindingDirectory.getFactory(StyledLayerDescriptorInfo.class);
		IMarshallingContext mctx = bfact.createMarshallingContext();
		StringWriter writer = new StringWriter();
		mctx.setOutput(writer);
		mctx.getXmlWriter().setIndentSpaces(2, "\n", ' ');
		mctx.marshalDocument(sld);
		res.setXml(writer.toString());
		res.setName(sld.getName());
		res.setVersion(sld.getVersion());
		res.setTitle(sld.getTitle() == null ? getTitle(sld, "?") : sld.getTitle());
		return res;
	}

	private String getTitle(StyledLayerDescriptorInfo sld, String fallback) {
		if (sld.getChoiceList() != null && sld.getChoiceList().size() > 0) {
			NamedLayerInfo nli = sld.getChoiceList().get(0).getNamedLayer();
			if (nli != null && nli.getName() != null) { return nli.getName(); }
			UserLayerInfo uli = sld.getChoiceList().get(0).getUserLayer();
			if (uli != null && uli.getName() != null) { return uli.getName(); }
		}
		return fallback;
	}

}
