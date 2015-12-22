package dbsa;

import java.io.File;

public class main_app {
	public static void main(String[] args) {
		// Define defaults
		int bufferSize = 4;
		long avgFileSize = 0;
		long start = 0;
		double seconds = 0;
		int k = 10;

		// Generate files
		GenerateData gd = new GenerateData();
		// gd.generateFilesToRead(k, 10000, "src/resources/read_from");
		File f = new File("src/resources/read_from/generated_1.txt");
		avgFileSize = f.length();

		// Read from the generated files
		// IO_1 io1 = new IO_1();
		// io1.read_from_file("src/resources/read_from/generated_1.txt");

		// Read from generated files and write to file
		//io1.read_write_file("src/resources/read_from/generated_0.txt", "src/resources/write_to.txt");

		// Read from the generated files
		// IO_2 io2 = new IO_2();
		//io2.read_from_file("src/resources/read_from/generated_0.txt");

		// Read from generated files and write to file
		//io2.read_write_file("src/resources/read_from/generated_0.txt", "src/resources/write_to.txt");

		// Read from the generated files
		// IO_3 io3 = new IO_3();
		//io3.read_from_file("src/resources/read_from/generated_0.txt", 10);

		// Read from generated files and write to file
		//io3.read_write_file("src/resources/read_from/generated_1.txt", "src/resources/write_to2.txt",10);

		// Read from the generated files
		// IO_4 io4 = new IO_4();
		//io4.read_from_file("src/resources/read_from/generated_0.txt", 10);

		// Read from generated files and write to file
		//io4.read_write_file("src/resources/read_from/generated_0.txt", "src/resources/write_to.txt",20);

		/* READ WRITE from multýple fýles*/
		IO_1 io1 = new IO_1();
		start = System.nanoTime();
		for (int i = 1; i <= k; i++) {
			io1.read_from_file("src/resources/read_from/generated_" + Integer.toString(i) + ".txt");
		}
		seconds = (double)(System.nanoTime()-start) / 1000000000.0;
		gd.writeLogs("io_1", "r", k, bufferSize, seconds, avgFileSize);

		IO_2 io2 = new IO_2();
		start = System.nanoTime();
		for (int i = 1; i <= k; i++) {
			io2.read_from_file("src/resources/read_from/generated_" + Integer.toString(i) + ".txt");
		}
		seconds = (double)(System.nanoTime()-start) / 1000000000.0;
		gd.writeLogs("io_2", "r", k, bufferSize, seconds, avgFileSize);

		IO_3 io3 = new IO_3();
		start = System.nanoTime();
		for (int i = 1; i <= k; i++) {
			io3.read_from_file("src/resources/read_from/generated_" + Integer.toString(i) + ".txt", bufferSize);
		}
		seconds = (double)(System.nanoTime()-start) / 1000000000.0;
		gd.writeLogs("io_3", "r", k, bufferSize, seconds, avgFileSize);

		IO_4 io4 = new IO_4();
		start = System.nanoTime();
		for (int i = 1; i <= k; i++) {
			io4.read_from_file("src/resources/read_from/generated_" + Integer.toString(i) + ".txt", bufferSize);
		}
		seconds = (double)(System.nanoTime()-start) / 1000000000.0;
		gd.writeLogs("io_4", "r", k, bufferSize, seconds, avgFileSize);

	}
}
