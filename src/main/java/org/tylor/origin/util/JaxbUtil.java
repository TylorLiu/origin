package org.tylor.origin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Jaxb2工具类 ,xml转换类
 *
 * @author zhuc
 * @create 2013-3-29 下午2:40:14
 */
public class JaxbUtil {

	public static String convertToXml(Object obj) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(obj.getClass());

		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// //编码格式
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// 是否省略xm头声明信息

		StringWriter out = new StringWriter();
		OutputFormat format = new OutputFormat();
		format.setIndent(true);
		format.setNewlines(true);
		format.setNewLineAfterDeclaration(false);
		XMLWriter writer = new XMLWriter(out, format);

		XMLFilterImpl nsfFilter = new XMLFilterImpl() {
			private boolean ignoreNamespace = false;
			private String rootNamespace = null;
			private boolean isRootElement = true;

			@Override
			public void startDocument() throws SAXException {
				super.startDocument();
			}

			@Override
			public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
				if (this.ignoreNamespace)
					uri = "";
				if (this.isRootElement)
					this.isRootElement = false;
				else if (!uri.equals("") && !localName.contains("xmlns"))
					localName = localName + " xmlns=\"" + uri + "\"";

				super.startElement(uri, localName, localName, atts);
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				if (this.ignoreNamespace)
					uri = "";
				super.endElement(uri, localName, localName);
			}

			@Override
			public void startPrefixMapping(String prefix, String url) throws SAXException {
				if (this.rootNamespace != null)
					url = this.rootNamespace;
				if (!this.ignoreNamespace)
					super.startPrefixMapping("", url);

			}
		};
		nsfFilter.setContentHandler(writer);
		marshaller.marshal(obj, nsfFilter);
		return out.toString();
	}

	public static <T> T convertToJavaBean(String xml, Class<T> valueType) throws JAXBException {

		JAXBContext context = null;

		context = JAXBContext.newInstance(valueType);

		Unmarshaller unmarshaller = context.createUnmarshaller();
		// return (T) unmarshaller.unmarshal(new StringReader(xml));
		// SerializeUtil obj = new SerializeUtil();

		return (T) unmarshaller.unmarshal(getSource(xml));
	}

	public static SAXSource getSource(StreamSource source) {
		InputStream inputStream = source.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuffer sb = new StringBuffer();
		String line = null;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
		return getSource(sb.toString());
	}

	public static SAXSource getSource(String xml) {
		XMLReader reader = null;
		try {
			reader = XMLReaderFactory.createXMLReader();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		XMLFilterImpl nsfFilter = new XMLFilterImpl() {
			// 忽略namespace
			private boolean ignoreNamespace = true;

			@Override
			public void startDocument() throws SAXException {
				super.startDocument();
			}

			@Override
			public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
				if (this.ignoreNamespace)
					uri = "";
				super.startElement(uri, localName, qName, atts);
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				if (this.ignoreNamespace)
					uri = "";
				super.endElement(uri, localName, localName);
			}

			@Override
			public void startPrefixMapping(String prefix, String url) throws SAXException {
				if (!this.ignoreNamespace)
					super.startPrefixMapping("", url);
			}
		};
		nsfFilter.setParent(reader);
		InputSource input = new InputSource(new StringReader(xml));
		return new SAXSource(nsfFilter, input);
	}
}