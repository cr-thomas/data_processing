package com.pk.hack;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class SplitXmlFile {

	// private volatile String xmlResource;
	// private volatile int condition;

	// public SplitXmlFile(String xmlResource, int condition) {
	// this.xmlResource = xmlResource;
	// this.condition = condition;
	// }

	// public void run() {
	// try {
	// System.out.println(xmlResource + " is running");
	// split(xmlResource, condition);
	// } catch (XMLStreamException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	public void split(String xmlResource, int condition)
			throws XMLStreamException, IOException {

		XMLEventFactory xef = XMLEventFactory.newFactory();
		XMLInputFactory xif = XMLInputFactory.newInstance();
		XMLOutputFactory xof = XMLOutputFactory.newFactory();

		XMLEventReader xer = xif.createXMLEventReader(new FileReader(
				xmlResource));
		StartElement rootStartElement = xer.nextTag().asStartElement();
		StartDocument startDocument = xef.createStartDocument();
		StartElement breakStartElement = null;
		XMLEvent xmlEvent = null;
		EndDocument endDocument = xef.createEndDocument();
		int countOfPage = 0;
		long startTime = 0;
		long endTime = 0;
		long totalTime = 0;

		try {
			startTime = System.currentTimeMillis();
			while (xer.hasNext() && !xer.peek().isEndDocument()) {
				boolean isLimitAchieved;

				xmlEvent = xer.nextTag();
				if (!xmlEvent.isStartElement()) {
					break;
				}

				breakStartElement = xmlEvent.asStartElement();
				List<XMLEvent> cachedXMLEvents = new ArrayList<XMLEvent>();

				if (countOfPage > condition) {
					cachedXMLEvents.add(breakStartElement);
					isLimitAchieved = true;
				} else {
					cachedXMLEvents.add(breakStartElement);
					xmlEvent = xer.nextEvent();
					isLimitAchieved = false;
					while (xer.hasNext()) {
						xmlEvent = xer.nextEvent();
						if (xmlEvent.isEndElement()
								&& xmlEvent.asEndElement().getName()
										.getLocalPart()
										.equalsIgnoreCase("page")) {
							countOfPage++;
						}
						cachedXMLEvents.add(xmlEvent);

						if (countOfPage == condition) {
							isLimitAchieved = true;
							break;
						}
					}
				}

				if (isLimitAchieved) {
					// Create a file for the fragment, the name is derived from
					// the
					// value of the id attribute
					FileWriter fileWriter = null;
					fileWriter = new FileWriter(new File(
							"C:\\Users\\Prokarma\\Desktop\\Cassian\\"
									+ condition + "-"
									+ System.currentTimeMillis() + ".xml"));

					// A StAX XMLEventWriter will be used to write the XML
					// fragment
					XMLEventWriter xew = xof.createXMLEventWriter(fileWriter);
					// xew.add(startDocument);

					xew.add(rootStartElement);

					// Write the XMLEvents that were cached while when we were
					// checking the fragment to see if it matched our criteria.
					for (XMLEvent cachedEvent : cachedXMLEvents) {
						xew.add(cachedEvent);
					}

					xew.add(xef.createEndElement(rootStartElement.getName(),
							null));

					// xew.add(endDocument);

					// Close everything we opened
					fileWriter.close();

				}
				countOfPage = 0;
			}
		} catch (XMLStreamException xex) {
			xex.printStackTrace();
		} finally {
			endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			System.out.println("Total Execution time: " + totalTime
					+ "milliseconds");
		}
	}
}
