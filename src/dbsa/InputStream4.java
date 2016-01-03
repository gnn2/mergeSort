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
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Gabby
 *
 */
public class InputStream4 {
	private int buffer; 
	private RandomAccessFile rafi;
	private FileChannel fci ;
	private MappedByteBuffer mbbi ;
	
	public InputStream4(int bufferSize){
		buffer = bufferSize; 
		
	}
	
	public void open (String fileName) throws FileNotFoundException{
		try{
			if(null == rafi){
				rafi = new RandomAccessFile( new File(fileName),"r");
				fci = rafi.getChannel();
				mbbi =fci.map(FileChannel.MapMode.READ_ONLY, 0,buffer);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			
		}
		
		
	}
	
	public int read_next() throws IOException{
		return mbbi.getInt(); 
	}
	
	public boolean isEnd() throws IOException{
	
		return !mbbi.hasRemaining(); 
	}

}
