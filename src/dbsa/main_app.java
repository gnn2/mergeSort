package dbsa;

import java.io.File;

public class main_app {
	public static void main(String[] args) {
		// Generate files
		GenerateData gd = new GenerateData();
		gd.generateFilesToRead(2, 10, "src/resources/read_from");

		// Read from the generated files
		IO_1 io1 = new IO_1();
		//io1.read_from_file("src/resources/read_from/generated_0.txt");

		// Read from generated files and write to file
		//io1.read_write_file("src/resources/read_from/generated_0.txt", "src/resources/write_to.txt");

		// Read from the generated files
		IO_2 io2 = new IO_2();
		io2.read_from_file("src/resources/read_from/generated_0.txt");

		// Read from generated files and write to file
		io2.read_write_file("src/resources/read_from/generated_0.txt", "src/resources/write_to.txt");


	}
}
