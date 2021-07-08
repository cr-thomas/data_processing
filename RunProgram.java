package com.pk.hack;

import java.io.File;

public class RunProgram {

	public static void listFilesForFolder(final File folder, String categoryName) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry, categoryName);
			} else {
				ProcessFile.readXML(fileEntry.getName(), categoryName);
			}
		}
	}

	public static void main(String[] args) throws Exception {

		SplitXmlFile runnable1 = new SplitXmlFile();
		runnable1
				.split("C:\\Users\\Prokarma\\Desktop\\enwiki_sample_dumptest.xml",
						100);
		String inputFolder = "C:\\Users\\Prokarma\\Desktop\\Cassian\\";
		final File folder = new File(inputFolder);
		listFilesForFolder(folder, "Notary");

		// runnable1.split("C:\\Users\\Prokarma\\Desktop\\pk-codathon-en-wiki-7g_dump.xml",
		// 1000);

		// new Thread(runnable1).start();
		// new Thread(runnable2).start();
		// System.out.println("this might print first!");
	}

}
