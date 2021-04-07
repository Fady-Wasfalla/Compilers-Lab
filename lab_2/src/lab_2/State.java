package lab_2;

import java.util.Hashtable;

public class State {
	
	String name;
	Hashtable <String, String> transitions;
	
	public State(String name,Hashtable <String, String> transitions) {
		this.name=name;
		this.transitions=transitions;
		
	}
	
}
