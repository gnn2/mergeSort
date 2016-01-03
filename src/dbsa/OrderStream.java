package dbsa;

import java.io.IOException;
import java.util.Comparator;

public class OrderStream implements
		Comparable<OrderStream> {

	private InputStream3 o;
	private int value;

	public OrderStream(InputStream3 o, int value) {
		this.o = o;
		this.value = value;
	}

	public int compareTo(OrderStream o2) {
		return (this.value-o2.value);
	}

	public int getValue() {
		return value;
	}

	public InputStream3 getStream() {
		return this.o;
	}
	
	public void setValue(int x){
		this.value = x; 
	}

}
