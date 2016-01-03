/**
 * 
 */
package dbsa;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Gabby
 *
 */
public class OutputStream3 {
	private FileOutputStream out;
	private  DataOutputStream ds; 
	private BufferedOutputStream bos;
	private int buffer; 
	public OutputStream3(int bufferSize){
		buffer = bufferSize; 
	}
	
	public void create (String fileName) throws FileNotFoundException{
		try{
			out = new FileOutputStream(new File(fileName) );
			bos = new BufferedOutputStream(out, buffer);
			ds = new DataOutputStream(bos);
		} catch (Exception e) {
			System.out.println(e.toString());
			throw e; 
		}
		
		
	}
	
	public void write(int x) throws IOException{
		ds.writeInt(x);
	}
	
	public void close() throws IOException{
		ds.flush();
		bos.close();
		ds.close();
		out.close();
	}


}
