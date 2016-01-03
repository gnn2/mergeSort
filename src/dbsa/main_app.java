package dbsa;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class main_app {

	
	public static String fileName = "./input.txt";
	public static ArrayList<Integer> fileLengths;
	public static ArrayList<Integer> bufferSizes;
	public static int numFiles = 1; // number of files you want to generate
	public static int buffer = 1024; // 1024
	public static Test tester; 
	public static GenerateData gen = new GenerateData(); 
	public static void main(String[] args) {
		fileLengths = new ArrayList<Integer>();
		fileLengths.add(100);
		fileLengths.add(10000);
		fileLengths.add(100000);
		fileLengths.add(1000000);
		fileLengths.add(10000000);
		tester = new Test (fileLengths,numFiles,buffer); 
		part1();
		// part2();
		part3();

	}

	public static void part1() {

		tester.test();
		System.out
				.println("******Test output stream 3 with different size buffers*******");
		tester.testBufferSize();
		System.out
				.println("******Test output stream 4 with different size buffers*******");
		tester.testBufferSizeType4();

	}

	/**
	 * D way merging algorithm given d number of streams
	 * Implemented to use I/O type 3 
	 * types
	 */
	public static void part2() {

		String base = "./output";
		String sortedFileName = "./sortedFile.txt";
		int buf = 1024;
		ExternalMerge eM = new ExternalMerge(); 
		ArrayList<String> files = gen.generateRandomType3(base, 3, 1000, buf);
		ArrayList<InputStream3> streams = new ArrayList<InputStream3>();

		for (int i = 0; i < files.size(); i++) {
			try {
				InputStream3 in = new InputStream3(buf);
				in.open(files.get(i));
				streams.add(in);
			} catch (Exception e) {
				System.out.println("Exception: " + e);

			}
		}
		OutputStream3 s = new OutputStream3(buf);
		try {
			s.create(sortedFileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s = eM.merge(streams, s);
		// writePriorityToFile(q, sortedFileName);
		printFile(sortedFileName);
	}

	/**
	 *  external merge for generated files 
	 */
	public static void part3() {
		ArrayList<String> generated = new ArrayList<String>();
		for(int i = 0; i < fileLengths.size(); i++){
			buffer = 131072;
			 ArrayList<String> files= gen.generateRandomType3("type3stream_"
					+ Integer.toString(i), numFiles, fileLengths.get(i), buffer);
			 for(int k = 0; k < files.size();  k++){
				 generated.add(files.get(k));
			 }
		}
		
		tester.testExternalMerge(generated);
	

	}

	/**
	 * prints file to console
	 * @param fileName
	 */
	public static void printFile(String fileName) {
		ProjectInputStream in = new ProjectInputStream();
		try {
			in.open(fileName);
			while (!in.isEnd()) {
				System.out.println(in.read_next());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
