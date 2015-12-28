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
public class OutputStream2 {
	private FileOutputStream out;
	private static DataOutputStream ds; 
	private BufferedOutputStream bos;
	public OutputStream2(){
		
	}
	
	public void create (String fileName) throws FileNotFoundException{
		try{
			out = new FileOutputStream(new File(fileName) );
			bos = new BufferedOutputStream(out);
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
		ds.close();
	}

}
