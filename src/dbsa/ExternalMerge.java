/**
 * 
 */
package dbsa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Gabby
 *
 */
public class ExternalMerge {

	/**
	 * splits file into sorted chuncks then performs merge until the file is sorted  
	 * 
	 * @param file
	 * @param mSize
	 *            size of main memory in ints
	 * @param d
	 *            number of streams
	 */
	public static void externalMergeSort(String file, int mSize, int d) {
		// int buf = 1024;
		int buf = mSize * 4; // number of ints * 4 bytes
		String base = "chunk";
		String end = ".txt";
		InputStream3 f = new InputStream3(buf);
		int count = 0;
		int numFiles = 0;
		Queue<String> files = new LinkedList<String>();
		int totalSize = 0;
		String mergeOutput = "merge";
		String outputName = "";
		int mergeCount = 0;
		try {

			f.open(file);
			while (!f.isEnd()) {
				OutputStream3 out = new OutputStream3(buf);
				ArrayList<Integer> nums = new ArrayList<Integer>();
				numFiles++;
				String name = base + Integer.toString(numFiles) + end;
				files.add(name);
				out.create(name);
				// System.out.println(name);
				while (count < mSize) {
					count++;
					if (!f.isEnd()) {
						totalSize++;
						int x = f.read_next();
						nums.add(x);

					}
				}
				// sort ints and write them to stream
				Collections.sort(nums);
				for (int i = 0; i < nums.size(); i++) {
					out.write(nums.get(i));
				}
				out.close();
				count = 0;
			}

			// System.out.println("Generated " + files.size() +
			// " sorted files");

			// split the queue into d sized parts and merge those streams into
			// an output file
			// add that file to the end of the queue
			ArrayList<InputStream3> part;

			while (files.size() > 1) {
				buf = (mSize * 4) / (files.size() + 1); // buffer =
														// mSize*4/(number of
														// files + 1(output file
														// buffer))

				part = new ArrayList<InputStream3>();
				InputStream3 curr;
				int limit = (files.size() >= d) ? d : files.size();
				for (int k = 0; k < limit; k++) {
					curr = new InputStream3(buf);
					// System.out.println("buffer :" + buf);
					String n = files.poll();
					curr.open(n);
					part.add(curr);
				}
				mergeCount++;
				outputName = mergeOutput + Integer.toString(mergeCount) + end;
				OutputStream3 out = new OutputStream3(buf);
				out.create(outputName);
				// add new file to queue
				files.add(outputName);
				out = merge(part, out);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// printFile(outputName);
	}
	/**
	 * merge intput streams into one priority queue
	 * 
	 * @param streams
	 */
	public static OutputStream3 merge(ArrayList<InputStream3> streams,
			OutputStream3 out) {
		PriorityQueue<OrderStream> q = new PriorityQueue<OrderStream>();
		int value;
		OrderStream o;
		// load first value from every stream
		for (int i = 0; i < streams.size(); i++) {
			try {
				InputStream3 s = streams.get(i);
				if (!s.isEnd()) {
					value = s.read_next();
					// System.out.println(value);
					o = new OrderStream(s, value);
					q.add(o);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/**
		 * take out the smallest value and write it to the file
		 * 
		 */
		int count = 0;
		while (!q.isEmpty()) {
			count++;
			o = q.poll();
			value = o.getValue();
			InputStream3 s = (InputStream3) o.getStream();
			try {
				out.write(value);
				// System.out.println(value);
				if (!s.isEnd()) {
					value = s.read_next();
					o.setValue(value);
					q.add(o);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// System.out.println();
		// System.out.println(count);
		// System.out.println();

		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}

}
