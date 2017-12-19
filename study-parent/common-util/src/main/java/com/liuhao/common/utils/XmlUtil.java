package com.liuhao.common.utils;

import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlUtil {

	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(XmlUtil.class);

	/**
	 * 
	 * @param path
	 * @return
	 */
	public static Element getXmlRoot(String path) {
		File xmlfile = new File(Thread.currentThread().getContextClassLoader().getResource(path).getFile());
		Element root = null;
		try {
			SAXReader reader = new SAXReader();
			Document document = (Document) reader.read(xmlfile);
			root = document.getRootElement();
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("{} getXmlRoot element with Exception :{}", new Object[] { path, e });
			throw new RuntimeException(e);
		}
		return root;
	}

	public static Element getXmlRoot(InputStream is) {
		Element root = null;
		try {
			SAXReader reader = new SAXReader();
			Document document = (Document) reader.read(is);
			root = document.getRootElement();
		} catch (Exception e) {
			logger.error("{} getXmlRoot element with Exception :{}", e);
			throw new RuntimeException(e);
		}
		return root;
	}

	public static Document createDocument(String rootname, Map<String, String> params) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement(rootname);
		for (Entry<String, String> en : params.entrySet()) {
			// System.out.println("key" + en.getKey() +": value"+en.getValue());
			if (en.getValue() != null) {
				root.addElement(en.getKey()).addText(en.getValue());
			}
		}
		return document;
	}

	/**
	 * xml转换成JavaBean
	 * 
	 * @param xml
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T parseToJavaBean(String xml, Class<T> c) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return t;
	}

	/**
	 * JavaBean转换成xml
	 * 
	 * @param obj
	 * @param encoding
	 * @return
	 */
	public static String convertToXml(Object obj, String encoding, boolean outPut) {
		String result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, outPut);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * JavaBean转换成xml 默认编码UTF-8
	 * 
	 * @param obj
	 * @param writer
	 * @return
	 */
	public static String convertToXml(Object obj) {
		return convertToXml(obj, "UTF-8");
	}

	/**
	 * JavaBean转换成xml
	 * 
	 * @param obj
	 * @param encoding
	 * @return
	 */
	public static String convertToXml(Object obj, String encoding) {
		String result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 格式化xml
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
