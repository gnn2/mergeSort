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
public class Test {

	private static ArrayList<Integer> fileLengths;
	private static int numFiles;
	private static int buffer; // default buffer
	private static GenerateData gen = new GenerateData();

	public Test(ArrayList<Integer> fl, int numF, int buf) {
		fileLengths = fl;
		numFiles = numF;
		buffer = buf;

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
			ArrayList<String> files = gen.generateRandomType1("./type1stream_"
					+ Integer.toString(i), numFiles, fileLengths.get(i));
			long endTime = System.currentTimeMillis();
			System.out.println("To generate a file with " + fileLengths.get(i)
					+ " integers " + " using output stream type 1 took:"
					+ (endTime - startTime) + " milliseconds");

			startTime = System.currentTimeMillis();
			ArrayList<String> files2 = gen.generateRandomType2("type2stream_"
					+ Integer.toString(i), numFiles, fileLengths.get(i));
			endTime = System.currentTimeMillis();
			System.out.println("To generate a file with " + fileLengths.get(i)
					+ " integers " + " using output stream type 2 took:"
					+ (endTime - startTime) + " milliseconds");

			startTime = System.currentTimeMillis();
			ArrayList<String> files3 = gen
					.generateRandomType3("type3stream_" + Integer.toString(i),
							numFiles, fileLengths.get(i), buffer);
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
			ArrayList<String> files4 = gen.generateRandomType4(
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

	/**
	 * test how long it takes to read files of type 1
	 * 
	 * @param fileNames
	 * @param size
	 */
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

	/**
	 * test how long it takes to read files of i/o type two
	 * 
	 * @param fileNames
	 * @param size
	 */
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

	/**
	 * test how long it takes to read files of i/o type 3
	 * 
	 * @param fileNames
	 * @param size
	 * @param buffer
	 */
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

	/**
	 * test how long it takes to read files of type i/o 4
	 * 
	 * @param fileNames
	 * @param size
	 * @param buffer
	 */
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
	 * Test how long it takes to generate files of type i/o 3 of particular size
	 * and differing buffer sizes
	 */
	public static void testBufferSize() {
		// for(int i = 0; i < fileLengths.size(); i ++){
		int fileSize = fileLengths.get(4);
		int buffer;
		for (int i = 1; i <= 29; i++) {
			long startTime = System.currentTimeMillis();
			buffer = (int) Math.pow(2, i);
			ArrayList<String> files3 = gen.generateRandomType3("type3stream_",
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
	 * Test i/o type 4 with different buffer sizes
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
				ArrayList<String> files4 = gen.generateRandomType4(
						"type4stream_", numFiles, fileSize, buffer);
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
	 * test external merge algorithm with different sized files, available
	 * memory and d number of streams to merge
	 * 
	 * @param files
	 */
	public void testExternalMerge(ArrayList<String> files) {
		System.out.println("*******Test external merge*********");
		ExternalMerge eM = new ExternalMerge();
		for (int i = 0; i < files.size(); i++) {
			int fileSize = fileLengths.get(i);
			System.out.println(files.get(i));
			// int start = fileSize/5;
			for (int d = 5; d <= 100; d *= 2) { // dont want to open too many
												// streams at a time
				for (int mSize = 10; mSize <= fileSize; mSize *= 10) {

					if (mSize >= fileSize && d == 5) {
						long startTime = System.currentTimeMillis();
						eM.externalMergeSort(files.get(i), mSize, d);
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
						eM.externalMergeSort(files.get(i), mSize + 200, d);
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
							eM.externalMergeSort(files.get(i), mSize, d);
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
	 * read entire file of type 1
	 * @param fileName
	 * @return
	 */
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

	/**
	 * read entire file of type 2
	 * @param fileName
	 * @return
	 */
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

	/**
	 * read entire file of type 3 with buffer 
	 * @param fileName
	 * @param buf buffer size
	 * @return
	 */
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

	/**
	 * read entire file of type 4 with buffer size
	 * @param fileName
	 * @param buf buffer size 
	 * @return
	 */
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

}
