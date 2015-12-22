package dbsa;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
}
