package dbsa;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Random;

public class IO_1_Simple {

	public void read_from_file(){
		try {
			/*File write_to = new File("src/resources/write_to.txt" );
			if (!write_to.exists()) {
				write_to.createNewFile();
			}
			OutputStream outs = new FileOutputStream(write_to);
			DataOutputStream dsout = new DataOutputStream(outs);*/

			BufferedWriter bfr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/resources/write_to.txt"), "UTF-8"));
			Random rand = new Random();
			bfr.write(rand.nextInt(10));

			InputStream is = new FileInputStream( new File("src/resources/read_from/file_1.txt" ) );
			DataInputStream ds = new DataInputStream(is);
			for(int i=0; i<=0; i++){
			//while (ds.available() > 0){
				System.out.println(ds.readInt());
				//dsout.writeInt(ds.readInt());
			}
		} catch (FileNotFoundException f){
			// TODO Auto-generated catch block
			f.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
