package dbsa;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class GenerateData {
	public void generateFilesToRead(int noOfFiles, int noOfRows, String path){
		Charset charset = Charset.forName("UTF-8");

		Random rand = new Random();
		String current_val;

		for (int i = 1; i <= noOfFiles; i++) { // Generate noOfFiles files
			String new_file = path + "/generated_" + Integer.toString(i) + ".txt";
			Path file = Paths.get(new_file);
			try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
				for (int j = 1; j <= noOfRows; j++) { // Generate noOfRows per each file
					current_val = Integer.toString(rand.nextInt(1000000000));
					writer.write(current_val);
					writer.newLine();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	/*
	 * Wrýte logs for each of the called functýons
	 * int k: number of streams
	 * int bufferSize: sýze of the buffer
	 * TODO: Explaýn all params !!!!
	 */
	public void writeLogs(String methodType, String jobType, int k, int bufferSize, double elapsedTime, long avgFileSize){
		Charset charset = Charset.forName("UTF-8");
		String path = "src/resources/logs.txt";
		Path file = Paths.get(path);

		// Method type;Job type;K steams;Elapsed time; Avg file size
		try (BufferedWriter writer = Files.newBufferedWriter(file, charset, StandardOpenOption.APPEND)) {
			writer.write(methodType + ";" + jobType + ";" + Integer.toString(k)
						+ ";" + Integer.toString(bufferSize)
						+ ";" + Double.toString(elapsedTime)
						+ ";" + Long.toString(avgFileSize));
			writer.newLine();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
