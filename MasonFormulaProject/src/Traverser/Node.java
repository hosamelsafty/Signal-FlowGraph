package Traverser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Node {

	private String value;
	private int gain;

	public Node(String str, int gain) {
		value = str;
		this.gain = gain;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getGain() {
		return gain;
	}

	public void setGain(int gain) {
		this.gain = gain;
	}

}