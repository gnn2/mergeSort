package dbsa;

import java.io.DataInputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
/**
 * part 1.1
 * reading and writing to file 
 */

public class ProjectInputStream {
	private FileInputStream is;
	private static DataInputStream ds; 
	
	public ProjectInputStream(){
		
	}
	
	public void open (String fileName) throws FileNotFoundException{
		try{
			if(null == is){
				is = new FileInputStream(new File(fileName) );
				ds = new DataInputStream(is);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			throw e; 
		}
		
		
	}
	
	public int read_next() throws IOException{
		return ds.readInt(); 
	}
	
	public boolean isEnd() throws IOException{
		return ds.available() <= 0; 
	}
}
