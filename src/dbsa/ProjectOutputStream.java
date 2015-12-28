/**
 * 
 */
package dbsa;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Gabby
 *
 */
public class ProjectOutputStream {
	private FileOutputStream out;
	private static DataOutputStream ds; 
	
	public ProjectOutputStream(){
		
	}
	
	public void create (String fileName) throws FileNotFoundException{
		try{
			out = new FileOutputStream(new File(fileName) );
			ds = new DataOutputStream(out);
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
