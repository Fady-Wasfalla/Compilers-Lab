package lab_2;

import java.util.Hashtable;

public class DFAState {

	String name;
	DFAState transition0;
	DFAState transition1;
	Boolean goal;
	
	public DFAState(String name) {
		this.name=name;
	}
	
}
