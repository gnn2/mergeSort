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

	public static Random intGenerator = new Random();
	public static String fileName = "./input.txt";
	public static ArrayList<Integer> fileLengths;
	public static ArrayList<Integer> bufferSizes;
	public static int numFiles = 1; // number of files you want to generate
	public static int buffer = 1024; // 1024

	public static void main(String[] args) {
		// Define defaults
		// int bufferSize = 4;
		// int k = 50;
		// GenerateData data = new GenerateData();
		// data.generateFilesToRead(k, 10, "");

		// for(int i=25; i<=30; i++){ //k
		// for(int j=20; j<=20; j=j+10){ //bufferSize
		// k = i;
		// bufferSize= j;
		// IO_Test IO_T = new IO_Test();
		// IO_T.checkPerformance(k, bufferSize);
		// }
		// }

		// Generate files
		// GenerateData gd = new GenerateData();
		// gd.generateFilesToRead(k, 100000, "src/resources/read_from");

		// Read from the generated files
		// IO_1 io1 = new IO_1();
		// io1.read_from_file("src/resources/read_from/generated_1.txt");

		// Read from generated files and write to file
		// io1.read_write_file("src/resources/read_from/generated_0.txt",
		// "src/resources/write_to.txt");

		// Read from the generated files
		// IO_2 io2 = new IO_2();
		// io2.read_from_file("src/resources/read_from/generated_0.txt");

		// Read from generated files and write to file
		// io2.read_write_file("src/resources/read_from/generated_0.txt",
		// "src/resources/write_to.txt");

		// Read from the generated files
		// IO_3 io3 = new IO_3();
		// io3.read_from_file("src/resources/read_from/generated_0.txt", 10);

		// Read from generated files and write to file
		// io3.read_write_file("src/resources/read_from/generated_1.txt",
		// "src/resources/write_to2.txt",10);

		// Read from the generated files
		// IO_4 io4 = new IO_4();
		// io4.read_from_file("src/resources/read_from/generated_0.txt", 10);

		// Read from generated files and write to file
		// io4.read_write_file("src/resources/read_from/generated_0.txt",
		// "src/resources/write_to.txt",20);
		fileLengths = new ArrayList<Integer>();
		fileLengths.add(100);
		fileLengths.add(10000);
		fileLengths.add(100000);
		fileLengths.add(1000000);
		fileLengths.add(10000000);
		//part1();
		// part2();
		ArrayList<String> generated = new ArrayList<String>();
		for(int i = 0; i < fileLengths.size(); i++){
			buffer = 131072;
			 ArrayList<String> files= generateRandomType3("type3stream_"
					+ Integer.toString(i), numFiles, fileLengths.get(i), buffer);
			 for(int k = 0; k < files.size();  k++){
				 generated.add(files.get(k));
			 }
		}
		
		part3(generated);

	}

	public static void part1() {

		test();
		System.out
				.println("******Test output stream 3 with different size buffers*******");
		testBufferSize();
		System.out
				.println("******Test output stream 4 with different size buffers*******");
		testBufferSizeType4();

	}

	/**
	 * Example of part 1.2 Implemented to use I/O type 3 changed to any of the 4
	 * types
	 */
	public static void part2() {

		String base = "./output";
		String sortedFileName = "./sortedFile.txt";
		int buf = 1024;
		ArrayList<String> files = generateRandomType3(base, 3, 1000, buf);
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
		s = merge(streams, s);
		// writePriorityToFile(q, sortedFileName);
		printFile(sortedFileName);
	}

	/**
	 * part 3 of the assignment
	 */
	public static void part3(ArrayList<String> files) {
		System.out.println("*******Test external merge*********");
		// ArrayList<String> files = new ArrayList<String>();
		// files.add("./type3stream_01.txt"); // size 100
		// files.add("./type3stream_11.txt"); // size 1000
		// files.add("./type3stream_21.txt"); // size 100000
		// files.add("./type3stream_31.txt"); // size 1000000
		// files.add("./type3stream_41.txt"); // size 10000000
		// int mSize = 10;
		// int d = 2;
		// d size
		for (int i = 0; i < files.size(); i++) {
			int fileSize = fileLengths.get(i);
			System.out.println(files.get(i));
			// int start = fileSize/5;
			for (int d = 5; d <= 100; d *= 2) { // dont want to open too many
													// streams at a time
				for (int mSize = 10; mSize <= fileSize; mSize *= 10) {

					if (mSize >= fileSize && d == 5) {
						long startTime = System.currentTimeMillis();
						externalMergeSort(files.get(i), mSize, d);
						long endTime = System.currentTimeMillis();

						System.out
								.println("External merge-sort for file size: "
										+ fileSize
										+ " main memory available: "
										+ mSize
										+ ". File was able to fit in memory so only sort was appiled."
										+ "  Took:" + (endTime - startTime)
										+ " milliseconds");
						mSize = mSize + 200; // try with buffer a little bigger
												// than the size
						startTime = System.currentTimeMillis();
						externalMergeSort(files.get(i), mSize + 200, d);
						endTime = System.currentTimeMillis();

						System.out
								.println("External merge-sort for file size: "
										+ fileSize
										+ " main memory available: "
										+ mSize
										+ ". File was able to fit in memory so only sort was appiled."
										+ "  Took:" + (endTime - startTime)
										+ " milliseconds");
					} else {
						// dont want to generate more than 100 files when
						// splitting
						if ((fileSize / mSize <= 100)
								&& (fileSize / mSize >= d)) { // don't
																// need to test
																// for d sizes
																// bigger than
																// the number of
																// streams that
																// will be
																// generated

							long startTime = System.currentTimeMillis();
							externalMergeSort(files.get(i), mSize, d);
							long endTime = System.currentTimeMillis();
							System.out
									.println("External merge-sort for file size: "
											+ fileSize
											+ " main memory available: "
											+ mSize
											+ " d size : "
											+ d
											+ "  took:"
											+ (endTime - startTime)
											+ " milliseconds");
						}
					}
				}

			}

		}

	}

	/**
	 * for each file length test how long it takes to write with each type of
	 * input stream
	 */
	public static void test() {

		for (int i = 0; i < fileLengths.size(); i++) {
			System.out.println("******Test all output streams for writting "
					+ "file of size " + fileLengths.get(i) + " .*******");
			long startTime = System.currentTimeMillis();
			ArrayList<String> files = generateRandomType1("./type1stream_"
					+ Integer.toString(i), numFiles, fileLengths.get(i));
			long endTime = System.currentTimeMillis();
			System.out.println("To generate a file with " + fileLengths.get(i)
					+ " integers " + " using output stream type 1 took:"
					+ (endTime - startTime) + " milliseconds");

			startTime = System.currentTimeMillis();
			ArrayList<String> files2 = generateRandomType2("type2stream_"
					+ Integer.toString(i), numFiles, fileLengths.get(i));
			endTime = System.currentTimeMillis();
			System.out.println("To generate a file with " + fileLengths.get(i)
					+ " integers " + " using output stream type 2 took:"
					+ (endTime - startTime) + " milliseconds");

			startTime = System.currentTimeMillis();
			ArrayList<String> files3 = generateRandomType3("type3stream_"
					+ Integer.toString(i), numFiles, fileLengths.get(i), buffer);
			endTime = System.currentTimeMillis();
			System.out.println("To generate a file with " + fileLengths.get(i)
					+ " integers " + " using output stream type 3" + " took:"
					+ (endTime - startTime) + " milliseconds");

			int buffer4 = fileLengths.get(i) * 4; // if the buffer is smaller
													// than the number of bytes
													// we are going to write a
													// BufferOverFlow Exception
													// is thrown
			startTime = System.currentTimeMillis();
			ArrayList<String> files4 = generateRandomType4(
					("type4stream_" + Integer.toString(i)), numFiles,
					fileLengths.get(i), buffer4);
			endTime = System.currentTimeMillis();
			System.out.println("To generate a file with " + fileLengths.get(i)
					+ " integers " + " using output stream type 4" + " took:"
					+ (endTime - startTime) + " milliseconds");

			System.out.println("******Test all input streams for reading "
					+ "file of size " + fileLengths.get(i) + " .*******");

			testRead(files, fileLengths.get(i));
			testRead2(files2, fileLengths.get(i));
			testRead3(files3, fileLengths.get(i), buffer);
			testRead4(files4, fileLengths.get(i), buffer4);
			System.out.println();
		}
		System.out.println();

	}

	public static void testRead(ArrayList<String> fileNames, int size) {
		long startTime;
		long endTime;
		for (int i = 0; i < fileNames.size(); i++) {
			startTime = System.currentTimeMillis();
			size = readFileStream1(fileNames.get(i));
			endTime = System.currentTimeMillis();
			System.out.println("To read a file with " + size + " integers "
					+ " using input stream type 1 took:"
					+ (endTime - startTime) + " milliseconds");
		}

	}

	public static void testRead2(ArrayList<String> fileNames, int size) {
		for (int i = 0; i < fileNames.size(); i++) {
			long startTime = System.currentTimeMillis();
			readFileStream2(fileNames.get(i));
			long endTime = System.currentTimeMillis();
			System.out.println("To read a file with " + size + " integers "
					+ " using input stream type 2 took:"
					+ (endTime - startTime) + " milliseconds");
		}

	}

	public static void testRead3(ArrayList<String> fileNames, int size,
			int buffer) {
		for (int i = 0; i < fileNames.size(); i++) {
			long startTime = System.currentTimeMillis();
			size = readFileStream3(fileNames.get(i), buffer);
			long endTime = System.currentTimeMillis();
			System.out.println("To read a file with " + size + " integers "
					+ " buffer size: " + buffer
					+ " using input stream type 3 took:"
					+ (endTime - startTime) + " milliseconds");
		}
	}

	public static void testRead4(ArrayList<String> fileNames, int size,
			int buffer) {
		for (int i = 0; i < fileNames.size(); i++) {
			long startTime = System.currentTimeMillis();
			readFileStream4(fileNames.get(i), buffer);
			long endTime = System.currentTimeMillis();
			System.out.println("To read a file with " + size + " integers "
					+ " buffer size: " + buffer
					+ " using input stream type 4 took:"
					+ (endTime - startTime) + " milliseconds");
		}
	}

	/**
	 * Test how long it takes to generate file of particular size and differing
	 * buffer size
	 */
	public static void testBufferSize() {
		// for(int i = 0; i < fileLengths.size(); i ++){
		int fileSize = fileLengths.get(4);
		int buffer;
		for (int i = 1; i <= 29; i++) {
			long startTime = System.currentTimeMillis();
			buffer = (int) Math.pow(2, i);
			ArrayList<String> files3 = generateRandomType3("type3stream_",
					numFiles, fileSize, buffer);
			long endTime = System.currentTimeMillis();
			System.out.println("To generate a file with " + fileSize
					+ " integers " + " using output stream type 3"
					+ " and buffer size of: " + buffer + " took: "
					+ (endTime - startTime) + " milliseconds");

			// System.out
			// .println("******Test all input streams for reading "+
			// "file of size " + fileLengths.get(i) + " .*******");

			testRead3(files3, fileSize, buffer);

			System.out.println();
		}
		// }

	}

	/**
		 * 
		 */
	public static void testBufferSizeType4() {
		int fileSize = fileLengths.get(4);

		for (int i = 1; i <= 29; i++) {

			int buffer = (int) Math.pow(2, i);
			if (buffer >= fileSize * 4) { // if the buffer is smaller
											// than the number of bytes
											// we are going to write an
											// BufferOverFlow Exception
											// is thrown
				long startTime = System.currentTimeMillis();
				ArrayList<String> files4 = generateRandomType4("type4stream_",
						numFiles, fileSize, buffer);
				long endTime = System.currentTimeMillis();
				System.out.println("To generate a file with " + fileSize
						+ " integers " + " using output stream type 4"
						+ " and buffer size of: " + buffer + " took: "
						+ (endTime - startTime) + " milliseconds");

				// System.out
				// .println("******Test all input streams for reading "+
				// "file of size " + fileLengths.get(i) + " .*******");

				testRead4(files4, fileSize, buffer);
				System.out.println();
			}

		}
	}

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

	/**
	 * merge intput streams into one priority queue
	 * 
	 * @param streams
	 */
	public static OutputStream3 merge(ArrayList<InputStream3> streams,
			OutputStream3 out) {
		PriorityQueue<OrderStream> q = new PriorityQueue<OrderStream>();
		int value;
		OrderStream o;
		// load first value from every stream
		for (int i = 0; i < streams.size(); i++) {
			try {
				InputStream3 s = streams.get(i);
				if (!s.isEnd()) {
					value = s.read_next();
					// System.out.println(value);
					o = new OrderStream(s, value);
					q.add(o);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/**
		 * take out the smallest value and write it to the file
		 * 
		 */
		int count = 0;
		while (!q.isEmpty()) {
			count++;
			o = q.poll();
			value = o.getValue();
			InputStream3 s = (InputStream3) o.getStream();
			try {
				out.write(value);
				// System.out.println(value);
				if (!s.isEnd()) {
					value = s.read_next();
					o.setValue(value);
					q.add(o);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// System.out.println();
		// System.out.println(count);
		// System.out.println();

		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}

	/**
	 * write priority q to file
	 * 
	 * @param q
	 */
	public static void writePriorityToFile(PriorityQueue<Integer> q,
			String fileName) {
		ProjectOutputStream out = new ProjectOutputStream();
		try {
			out.create(fileName);
			while (!q.isEmpty()) {
				int n = q.poll();
				out.write(n);
			}
			out.close();

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

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

	public static int readFileStream1(String fileName) {
		ProjectInputStream in = new ProjectInputStream();
		int count = 0;
		try {
			in.open(fileName);
			while (!in.isEnd()) {
				int i = in.read_next(); // do something with read ints
				// System.out.println(i);
				count++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public static int readFileStream2(String fileName) {
		InputStream2 in = new InputStream2();
		int count = 0;
		try {
			in.open(fileName);
			while (!in.isEnd()) {
				int i = in.read_next(); // do something with read ints
				// System.out.println(i);
				count++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public static int readFileStream3(String fileName, int buf) {
		InputStream3 in = new InputStream3(buf);
		int count = 0;
		try {
			in.open(fileName);
			while (!in.isEnd()) {
				int i = in.read_next(); // do something with read ints
				// System.out.println(i);
				count++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public static int readFileStream4(String fileName, int buf) {
		InputStream4 in = new InputStream4(buf);
		int count = 0;
		try {
			in.open(fileName);
			while (!in.isEnd()) {
				int i = in.read_next(); // do something with read ints
				// System.out.println(i);
				count++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * do merge-sort on file
	 * 
	 * @param file
	 * @param mSize
	 *            size of main memory in ints
	 * @param d
	 *            number of streams
	 */
	public static void externalMergeSort(String file, int mSize, int d) {
		// int buf = 1024;
		int buf = mSize * 4; // number of ints * 4 bytes
		String base = "chunk";
		String end = ".txt";
		InputStream3 f = new InputStream3(buf);
		int count = 0;
		int numFiles = 0;
		Queue<String> files = new LinkedList<String>();
		int totalSize = 0;
		String mergeOutput = "merge";
		String outputName = "";
		int mergeCount = 0;
		try {

			f.open(file);
			while (!f.isEnd()) {
				OutputStream3 out = new OutputStream3(buf);
				ArrayList<Integer> nums = new ArrayList<Integer>();
				numFiles++;
				String name = base + Integer.toString(numFiles) + end;
				files.add(name);
				out.create(name);
				// System.out.println(name);
				while (count < mSize) {
					count++;
					if (!f.isEnd()) {
						totalSize++;
						int x = f.read_next();
						nums.add(x);

					}
				}
				// sort ints and write them to stream
				Collections.sort(nums);
				for (int i = 0; i < nums.size(); i++) {
					out.write(nums.get(i));
				}
				out.close();
				count = 0;
			}

			// System.out.println("Generated " + files.size() +
			// " sorted files");

			// split the queue into d sized parts and merge those streams into
			// an output file
			// add that file to the end of the queue
			ArrayList<InputStream3> part;

			while (files.size() > 1) {
				buf = (mSize * 4) / (files.size() + 1); // buffer =
														// mSize*4/(number of
														// files + 1(output file
														// buffer))

				part = new ArrayList<InputStream3>();
				InputStream3 curr;
				int limit = (files.size() >= d) ? d : files.size();
				for (int k = 0; k < limit; k++) {
					curr = new InputStream3(buf);
					// System.out.println("buffer :" + buf);
					String n = files.poll();
					curr.open(n);
					part.add(curr);
				}
				mergeCount++;
				outputName = mergeOutput + Integer.toString(mergeCount) + end;
				OutputStream3 out = new OutputStream3(buf);
				out.create(outputName);
				// add new file to queue
				files.add(outputName);
				out = merge(part, out);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// printFile(outputName);
	}

}
