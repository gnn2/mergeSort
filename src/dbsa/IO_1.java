package dbsa;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IO_1 {

	public void read_from_file(String fileName){
		try {
			InputStream is = new FileInputStream( new File(fileName));
			DataInputStream dis = new DataInputStream(is);

			while (dis.available() > 3){  //there could be missing values at the end
				System.out.println(dis.readInt());
			}
			dis.close();
		} catch (FileNotFoundException f){
			f.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	
	}
	
	public void read_write_file(String readFrom, String writeTo){
		try {
			InputStream ins = new FileInputStream( new File(readFrom));
			DataInputStream dis = new DataInputStream(ins);
			
			OutputStream outs = new FileOutputStream( new File(writeTo));
			DataOutputStream dos = new DataOutputStream(outs);

			while (dis.available() > 3){ //there could be missing values at the end
				dos.writeInt(dis.readInt());
			}
			dis.close();
			dos.close();

		} catch (FileNotFoundException f){
			f.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
