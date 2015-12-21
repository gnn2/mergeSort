package dbsa;

public class main_app {
	public static void main(String[] args) {
		GenerateData gd = new GenerateData();
		gd.generateFilesToRead(2, 10, "src/resources/read_from");
	}
}
