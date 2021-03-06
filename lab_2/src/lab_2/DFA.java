package lab_2;

import java.util.Hashtable;

public class DFA {

	Hashtable<String, DFAState> dfaTable;
	String [] testCases;
	String startState;
	
	
	public DFA(Hashtable<String, DFAState> dfaTable,String [] testCases,String startState) {
		this.dfaTable=dfaTable;
		this.testCases=testCases;
		this.startState=startState;
	}
	
	public void run() {
//		System.out.println("DFA Table : ");
//		dfaTable.forEach((k,v)->{
//			System.out.println(v.name+" -> "+v.transition0.name+" -> "+v.transition1.name+" -> "+v.goal);
//		});
		System.out.println();
		System.out.println("Test Cases : ");
		for (int i=0;i<testCases.length;i++) {
			DFAState currentState= dfaTable.get(startState);
			System.out.print(testCases[i]+" ==> ");
			boolean flag = false;
			for (int j=0;j<testCases[i].length();j++) {
				if(testCases[i].charAt(j)=='0'){
					currentState = dfaTable.get(currentState.transition0.name);
				}
				else{
					currentState = dfaTable.get(currentState.transition1.name);
				}
			}
			flag = currentState.goal;
			System.out.println(flag);
		}
	}


}