//package com.pk.hack;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//public class sam {
//
//	public static void main(String args[]) throws Exception {
//
//		String filepath = "C:\\Users\\Prokarma\\Desktop\\";
//		File file = new File(filepath + "abc.xml");
//		String strText = null;
//		List<String> catList = new ArrayList<String>();
//		List<String> pageTitleList = new ArrayList<String>();
//		List<String> pageIdList = new ArrayList<String>();
//
//		int catCount = 0;
//
//		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
//				.newInstance();
//		DocumentBuilder documentBuilder = documentBuilderFactory
//				.newDocumentBuilder();
//		Document doc = documentBuilder.parse(file);
//
//		// System.out.println("Root element :" +
//		// doc.getDocumentElement().getNodeName());
//		NodeList nodeList = doc.getChildNodes();
//		try {
//			for (int i = 0; i < nodeList.getLength(); i++) {
//				Node node = nodeList.item(i);
//				// System.out.println(node.getNodeName());
//				// System.out.println(node.getNodeValue());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		NodeList nList = doc.getElementsByTagName("page");
//		// System.out.println("===============================================================");
//		Element eElement = null;
//		for (int itr = 0; itr < nodeList.getLength(); itr++) {
//			Node node = nodeList.item(itr);
//			// System.out.println("\nNode Name :" + node.getNodeName());
//			if (node.getNodeType() == Node.ELEMENT_NODE) {
//				eElement = (Element) node;
//
//				NodeList nList2 = eElement.getElementsByTagName("revision")
//						.item(0).getChildNodes();
//				for (int i = 0; itr < nList2.getLength(); itr++) {
//					Node node1 = nList2.item(itr);
//					// System.out.println("\nNode Name :" +
//					// node1.getNodeName());
//
//					if (node1.getNodeType() == Node.ELEMENT_NODE) {
//						Element eElement1 = (Element) node1;
//
//						strText = eElement.getElementsByTagName("text").item(0)
//								.getTextContent();
//					}
//
//				}
//			}
//			catList = processText(strText);
//
//			for (String category : catList) {
//				if (category.trim().matches("Fertility")) {
//					catCount++;
//					String pageTitle = eElement.getElementsByTagName("title")
//							.item(0).getTextContent();
//					pageTitleList.add(pageTitle);
//					String pageId = eElement.getElementsByTagName("id").item(0)
//							.getTextContent();
//					pageIdList.add(pageId);
//				}
//			}
//		}
//
//		System.out.println(pageTitleList);
//		System.out.println(pageIdList);
//		System.out.println(catCount);
//	}
//
//	private static List<String> processText(String strText) {
//
//		String subString = strText.substring(strText.indexOf("[[Category:"));
//
//		// subString.replaceAll("", "");
//		// String text = subString.replaceAll("]]", "");
//		String s = subString.replaceAll("\\[\\[Category:", "");
//
//		// s.substring(s.indexOf("{"));
//		// System.out.println(s);
//
//		String[] catArr = s.split("]]");
//
//		List<String> strList = Arrays.asList(catArr);
//
//		System.out.println(strList);
//
//		return strList;
//
//	}
//
//}
