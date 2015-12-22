package dbsa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class IO_4 {

	public void read_from_file(String readFrom, long bufferSize){
		try {
			RandomAccessFile rafi = new RandomAccessFile( new File(readFrom),"r");
			FileChannel fci = rafi.getChannel();
			MappedByteBuffer mbbi =fci.map(FileChannel.MapMode.READ_ONLY, 0, Math.min(bufferSize,fci.size()));
			//System.out.print(fci.size());
			//System.out.print(mbbi.limit());
			//System.out.print(mbbi.position());
			while(mbbi.hasRemaining()){
				System.out.println((char)mbbi.get());
				//System.out.println(mbbi.position());
			}
			rafi.close();
			fci.close();
		} catch (FileNotFoundException f){
			f.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void read_write_file(String readFrom, String writeTo, long bufferSize){
		try {
			RandomAccessFile rafi = new RandomAccessFile( new File(readFrom),"r");
			FileChannel fci = rafi.getChannel();
			MappedByteBuffer mbbi =fci.map(FileChannel.MapMode.READ_ONLY, 0, Math.min(bufferSize,fci.size()));
			RandomAccessFile rafo = new RandomAccessFile( new File(writeTo),"rw");
			FileChannel fco = rafo.getChannel();
			MappedByteBuffer mbbo =fco.map(FileChannel.MapMode.READ_WRITE, 0, Math.min(bufferSize,fci.size()));			
			//System.out.print(fci.size());
			//System.out.print(mbbi.limit());
			//System.out.print(mbbi.position());
			while(mbbi.hasRemaining()){
				//System.out.println((char)mbbi.get());
				mbbo.put(mbbi.get());
				//System.out.println(mbbi.position());
			}
			rafi.close();
			fci.close();
			rafo.close();
			fco.close();
		} catch (FileNotFoundException f){
			f.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}

