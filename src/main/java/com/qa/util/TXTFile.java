package com.qa.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TXTFile {

	FileWriter myWriter;


	public boolean  txtFileCreation(String folderName, String fileName) {
		boolean isFileCreated = false;
		try {
			File theDir = new File(folderName);
			if (!theDir.exists()){
			    theDir.mkdirs();
			}
			
			
			File myObj = new File(folderName+"/"+fileName);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
				isFileCreated  = true;
			} else {
				System.out.println("File already exists.");
			}
			myWriter = new FileWriter(folderName+"/"+fileName,false);
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return isFileCreated;
	}

	public void writeTxtFile( String fileName, String message) {
		try {
			myWriter.write(message);
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public void closeTxtFile() {
		try {
			myWriter.close();
			System.out.println("Successfully closed the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
