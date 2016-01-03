/**
 * 
 */
package dbsa;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Gabby
 *
 */
public class GenerateData {
	public static  Random intGenerator = new Random();
	/**
	 * generates random files using ProjectOutputStream
	 * 
	 * @param baseFileName
	 * @param numFiles
	 * @param maxLength
	 * @return
	 */
	public static ArrayList<String> generateRandomType1(String baseFileName,
			int numFiles, int maxLength) {
		ArrayList<String> fileNames = new ArrayList<String>();
		for (int i = 1; i <= numFiles; i++) {
			String outputFile = baseFileName + Integer.toString(i) + ".txt";
			ProjectOutputStream out = new ProjectOutputStream();
			try {
				out.create(outputFile);
				fileNames.add(outputFile);
				// for each int in int buffer
				for (int x = 0; x < maxLength; x++) {
					// Generate random ( unsorted ) int 0 -1000
					int next = intGenerator.nextInt(1000);
					// write int to data output stream
					out.write(next);
				}
				out.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return fileNames;
	}

	/**
	 * generates random files using OutputStream2
	 * 
	 * @param baseFileName
	 * @param numFiles
	 * @param maxLength
	 * @return
	 */
	public static ArrayList<String> generateRandomType2(String baseFileName,
			int numFiles, int maxLength) {
		ArrayList<String> fileNames = new ArrayList<String>();
		for (int i = 1; i <= numFiles; i++) {
			String outputFile = baseFileName + Integer.toString(i) + ".txt";
			OutputStream2 out = new OutputStream2();
			try {
				out.create(outputFile);
				fileNames.add(outputFile);
				// for each int in int buffer
				for (int x = 0; x < maxLength; x++) {
					// Generate random ( unsorted ) int 0 -1000
					int next = intGenerator.nextInt(1000);
					// write int to data output stream
					out.write(next);
				}
				out.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return fileNames;
	}

	/**
	 * generates random files using OutputStream3
	 * 
	 * @param baseFileName
	 * @param numFiles
	 * @param maxLength
	 * @param buffer
	 * @return
	 */
	public static ArrayList<String> generateRandomType3(String baseFileName,
			int numFiles, int maxLength, int buffer) {
		ArrayList<String> fileNames = new ArrayList<String>();
		for (int i = 1; i <= numFiles; i++) {
			String outputFile = baseFileName + Integer.toString(i) + ".txt";
			OutputStream3 out = new OutputStream3(buffer);
			try {
				out.create(outputFile);
				fileNames.add(outputFile);
				// for each int in int buffer
				for (int x = 0; x < maxLength; x++) {
					// Generate random ( unsorted ) int 0 -1000
					int next = intGenerator.nextInt(1000);
					// write int to data output stream
					out.write(next);
				}
				out.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return fileNames;
	}

	/**
	 * generates random files using OutputStream4
	 * 
	 * @param baseFileName
	 * @param numFiles
	 * @param maxLength
	 * @param buffer
	 * @return
	 */
	public static ArrayList<String> generateRandomType4(String baseFileName,
			int numFiles, int maxLength, int b) {
		ArrayList<String> fileNames = new ArrayList<String>();
		for (int i = 1; i <= numFiles; i++) {
			String outputFile = baseFileName + Integer.toString(i) + ".txt";
			// buffer = maxLength * 4;
			OutputStream4 out = new OutputStream4(b);
			try {
				out.create(outputFile);
				fileNames.add(outputFile);
				// for each int in int buffer
				for (int x = 0; x < maxLength; x++) {
					// Generate random ( unsorted ) int 0 -1000
					int next = intGenerator.nextInt(1000);
					// write int to data output stream
					out.write(next);
				}
				out.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return fileNames;
	}
}
