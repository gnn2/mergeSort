/**
 * 
 */
package dbsa;

import java.io.BufferedInputStream;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Gabby
 *
 */
public class InputStream2 {
	private FileInputStream is;
	private  DataInputStream ds; 
	private BufferedInputStream bis;
	public InputStream2(){
		
	}
	
	public void open (String fileName) throws FileNotFoundException{
		try{
			if(null == is){
				is = new FileInputStream(new File(fileName) );
				bis = new BufferedInputStream( is );
				ds = new DataInputStream(bis);
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
