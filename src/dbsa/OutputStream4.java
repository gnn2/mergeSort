/**
 * 
 */
package dbsa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Gabby
 *
 */
public class OutputStream4 {
	RandomAccessFile rafo;
	FileChannel fco ;
	MappedByteBuffer mbbo;
	private int buffer; 
	private int count; 
	public OutputStream4(int bufferSize){
		buffer = bufferSize; 
		count = 0; 
	}
	
	public void create (String fileName) throws FileNotFoundException{
		try{
			rafo = new RandomAccessFile( new File(fileName),"rw");
			fco = rafo.getChannel();
			mbbo =fco.map(FileChannel.MapMode.READ_WRITE, 0, buffer);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
	}
	
	public void write(int x) throws IOException{
		mbbo.putInt(x);
	}
	
	public void close() throws IOException{
		mbbo.force();
		rafo.close();
		fco.close();
		
	}
}
