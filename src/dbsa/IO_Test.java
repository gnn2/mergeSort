package dbsa;

import java.io.File;

public class IO_Test {

	public void checkPerformance(int k, int bufferSize){
		long avgFileSize = 0;
		long start = 0;
		double seconds = 0;
		File f = new File("./generated_1.txt");
		String readName = "./generated_";
		String writeName = "./write_to.txt";
		avgFileSize = f.length();
		GenerateData gd = new GenerateData();
		//gd.generateFilesToRead(noOfFiles, 1000, path);
		
		IO_1 io1 = new IO_1();
		start = System.nanoTime();
		for (int i = 1; i <= k; i++) {
			io1.read_from_file(readName + Integer.toString(i) + ".txt");
		}
		seconds = (double)(System.nanoTime()-start) / 1000000000.0;
		gd.writeLogs("io_1", "r", k, bufferSize, seconds, avgFileSize);

		IO_2 io2 = new IO_2();
		start = System.nanoTime();
		for (int i = 1; i <= k; i++) {
			io2.read_from_file(readName  + Integer.toString(i) + ".txt");
		}
		seconds = (double)(System.nanoTime()-start) / 1000000000.0;
		gd.writeLogs("io_2", "r", k, bufferSize, seconds, avgFileSize);

		IO_3 io3 = new IO_3();
		start = System.nanoTime();
		for (int i = 1; i <= k; i++) {
			io3.read_from_file(readName  + Integer.toString(i) + ".txt", bufferSize);
		}
		seconds = (double)(System.nanoTime()-start) / 1000000000.0;
		gd.writeLogs("io_3", "r", k, bufferSize, seconds, avgFileSize);

		IO_4 io4 = new IO_4();
		start = System.nanoTime();
		for (int i = 1; i <= k; i++) {
			io4.read_from_file(readName  + Integer.toString(i) + ".txt", bufferSize);
		}
		seconds = (double)(System.nanoTime()-start) / 1000000000.0;
		gd.writeLogs("io_4", "r", k, bufferSize, seconds, avgFileSize);

		start = System.nanoTime();
		for (int i = 1; i <= k; i++) {
			io1.read_write_file(readName  + Integer.toString(i) + ".txt", writeName);
					}
		seconds = (double)(System.nanoTime()-start) / 1000000000.0;
		gd.writeLogs("io_1", "rw", k, bufferSize, seconds, avgFileSize);
		
		start = System.nanoTime();
		for (int i = 1; i <= k; i++) {
			io2.read_write_file(readName + Integer.toString(i) + ".txt", writeName);
					}
		seconds = (double)(System.nanoTime()-start) / 1000000000.0;
		gd.writeLogs("io_2", "rw", k, bufferSize, seconds, avgFileSize);
		
		start = System.nanoTime();
		for (int i = 1; i <= k; i++) {
			io3.read_write_file(readName  + Integer.toString(i) + ".txt", 
					writeName + Integer.toString(i) + ".txt",bufferSize);
					}
		seconds = (double)(System.nanoTime()-start) / 1000000000.0;
		gd.writeLogs("io_3", "rw", k, bufferSize, seconds, avgFileSize);
		
		start = System.nanoTime();
		for (int i = 1; i <= k; i++) {
			io4.read_write_file(readName  + Integer.toString(i) + ".txt", 
					writeName,bufferSize);
					}
		seconds = (double)(System.nanoTime()-start) / 1000000000.0;
		gd.writeLogs("io_4", "rw", k, bufferSize, seconds, avgFileSize);
				
	}
	
	
}
