package com.pk.hack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ProcessFile {

	public static void readXML(String xmlFileName, String categoryName) {

		String filepath = "C:\\Users\\Prokarma\\Desktop\\Cassian\\";
		File file = new File(filepath + xmlFileName);
		InputStream inputStream = null;
		String strText = null;
		List<String> catList = new ArrayList<String>();
		List<String> pageTitleList = new ArrayList<String>();
		List<String> pageIdList = new ArrayList<String>();

		int catCount = 0;

		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Reader reader = null;
		try {
			reader = new InputStreamReader(inputStream, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		InputSource is = new InputSource(reader);
		is.setEncoding("UTF-8");

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document tDoc = documentBuilder.parse(is);
			// NodeList nodeList = tDoc.getChildNodes();

			NodeList nList = tDoc.getElementsByTagName("page");
			// System.out.println("===============================================================");
			Element eElement = null;
			for (int itr = 0; itr < nList.getLength(); itr++) {
				Node node = nList.item(itr);
				// System.out.println("\nNode Name :" + node.getNodeName());
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					eElement = (Element) node;

					NodeList nList2 = eElement.getElementsByTagName("revision")
							.item(0).getChildNodes();
					for (int i = 0; itr < nList2.getLength(); itr++) {
						Node node1 = nList2.item(itr);
						// System.out.println("\nNode Name :" +
						// node1.getNodeName());

						if (node1.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement1 = (Element) node1;

							strText = eElement.getElementsByTagName("text")
									.item(0).getTextContent();
						}

					}
				}
				catList = processText(strText);

				for (String category : catList) {
					if (category.trim().matches(categoryName)) {
						catCount++;
						String pageTitle = eElement
								.getElementsByTagName("title").item(0)
								.getTextContent();
						pageTitleList.add(pageTitle);
						String pageId = eElement.getElementsByTagName("id")
								.item(0).getTextContent();
						pageIdList.add(pageId);
					}
				}
				
				System.out.println(pageTitleList);
				System.out.println(pageIdList);
				System.out.println(catCount);
			}			

			// for (int i = 0; i < nodeList.getLength(); i++) {
			// Node node = nodeList.item(i);
			// System.out.println(node.getNodeName());
			// System.out.println(node.getNodeValue());
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static List<String> processText(String strText) {

		String subString = strText.substring(strText.indexOf("[[Category:"));

		// subString.replaceAll("", "");
		// String text = subString.replaceAll("]]", "");
		String s = subString.replaceAll("\\[\\[Category:", "");

		// s.substring(s.indexOf("{"));
		// System.out.println(s);

		String[] catArr = s.split("]]");

		List<String> strList = Arrays.asList(catArr);

//		System.out.println(strList);

		return strList;

	}
}
