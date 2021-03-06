package lab_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;


public class NFA {
	
	
	public static Hashtable<String, State> constructTables(String inputNfa) {
		Hashtable<String, State> nfaStates = new Hashtable<String, State>();
		
		String statesNames = arrangeStateLetters(inputNfa);
		
		String[] transitions = inputNfa.split("#",0);
		String[] zeroTransitions = transitions[0].split(";",0);
		String[] oneTransitions = transitions[1].split(";",0);
		String[] epsilonTransitions = transitions[2].split(";",0);
		
		// initialize the states 
		for (int i=0;i<statesNames.length();i++) {
			if ( nfaStates.get(statesNames.charAt(i)) == null) {
				Hashtable <String, String> epsilonTrans = new Hashtable <String, String>();
				epsilonTrans.put("epsilon", ""+statesNames.charAt(i)); // add the state to itself as an epsilon transition state
				epsilonTrans.put("zero", "");
				epsilonTrans.put("one", "");
				State temp = new State(""+statesNames.charAt(i),epsilonTrans);
				nfaStates.put(temp.name, temp);
			}
		}
		
		
		//epsilon Table
		nfaStates.forEach((k,v)->{
			for (int i=0;i<v.transitions.get("epsilon").length();i++) {
				for (int j=0;j<epsilonTransitions.length;j++) {
					//check if the transition in each state has another transitions EX : 1->2 , 2->3 
					String currentTrans = v.transitions.get("epsilon");
					if( currentTrans.charAt(i) == epsilonTransitions[j].charAt(0) ) {
						if ( currentTrans.indexOf(epsilonTransitions[j].charAt(2)) == -1 )	{
//							System.out.println(k+" before+ "+currentTrans);
							v.transitions.replace("epsilon",currentTrans+epsilonTransitions[j].charAt(2));
//							System.out.println(k+" after+ "+currentTrans+epsilonTransitions[j].charAt(2));
							nfaStates.replace(k, v);
							i=0;
							j=0;
						}
					}
				}
			}			
			nfaStates.replace(k, v);
		});
		
		nfaStates.forEach((k,v)->{
			for (int i=0;i<zeroTransitions.length;i++) {
				if( zeroTransitions[i].charAt(0) == v.name.charAt(0) ) {
					String currentTrans = v.transitions.get("zero");
					v.transitions.replace("zero",arrangeStateLetters(currentTrans+nfaStates.get(zeroTransitions[i].charAt(2)+"").transitions.get("epsilon") ));
					nfaStates.replace(k, v);
				}
			}
			for (int i=0;i<oneTransitions.length;i++) {
				if( oneTransitions[i].charAt(0) == v.name.charAt(0) ) {
					String currentTrans = v.transitions.get("one");
					v.transitions.replace("one",arrangeStateLetters(currentTrans+ nfaStates.get(oneTransitions[i].charAt(2)+"").transitions.get("epsilon") ));
					nfaStates.replace(k, v);
				}
			}
			nfaStates.replace(k, v);
		});
		
//		nfaStates.forEach((k,v)->{
//			System.out.println(v.name+" => "+v.transitions.get("epsilon")+" => "+v.transitions.get("zero")+" => "+v.transitions.get("one"));
//		});
//		System.out.println("==================================================================================");
		return nfaStates;
	}
	
	public static Boolean goalTest(String a,String b) {
		Boolean flag = false;
		String[] strSplitA = a.split("");
        ArrayList<String> strListA1 = new ArrayList<String>(Arrays.asList(strSplitA));
        ArrayList<String> strListA2 = new ArrayList<String>(Arrays.asList(strSplitA));
        
        String[] strSplitB = b.split("");
        ArrayList<String> strListB1 = new ArrayList<String>(Arrays.asList(strSplitB));
        strListA1.removeAll(strListB1);
        
        if ( strListA1.size() != strListA2.size()  ) {
        	flag = true;
        }
		return flag;
	}
	
	public static String arrangeStateLetters(String st) {
		char[] chars = st.toCharArray();
		Arrays.sort(chars);
		String newWord = "" ;
		for (int i=0;i<chars.length;i++) {
			if (newWord.indexOf(chars[i])==-1 && ((""+chars[i]).matches("[A-Za-z]{1}")) ) {
				newWord+=chars[i];
			}
		}
		return newWord;
	}
	
	public static Hashtable<String, DFAState> nfaToDfa(Hashtable<String, State> nfaTable , String startState,String goals) {
		Hashtable<String, DFAState> dfaTable = new Hashtable<String, DFAState>();
		dfaTable.put( nfaTable.get(startState).transitions.get("epsilon") , new DFAState( nfaTable.get(startState).transitions.get("epsilon") ));
		ArrayList<String> dfaStates = new ArrayList<String>();
		dfaStates.add(nfaTable.get(startState).transitions.get("epsilon"));
		for (int i=0;i<dfaStates.size();i++) {
			String toStateWith0 = "";
			String toStateWith1 = "";
			for(int j=0;j<dfaStates.get(i).length();j++) {
				toStateWith0+=nfaTable.get(""+dfaStates.get(i).charAt(j)).transitions.get("zero");
				toStateWith1+=nfaTable.get(""+dfaStates.get(i).charAt(j)).transitions.get("one");
			}
			toStateWith0 = arrangeStateLetters(toStateWith0);
			toStateWith1 = arrangeStateLetters(toStateWith1);
			DFAState updatedState = new DFAState( dfaTable.get(dfaStates.get(i)).name );
				if (dfaTable.get(toStateWith0) == null) {
					dfaStates.add(toStateWith0);
					DFAState temp = new DFAState( toStateWith0 );
					dfaTable.put( toStateWith0 , temp );
					dfaStates.add(toStateWith0);
					updatedState.transition0=temp;
				}
				else{
					updatedState.transition0=dfaTable.get(toStateWith0);
				}
				if ( dfaTable.get(toStateWith1) == null) {
					dfaStates.add(toStateWith1);
					DFAState temp = new DFAState( toStateWith1 );
					dfaTable.put( toStateWith1 , temp );
					dfaStates.add(toStateWith1);
					updatedState.transition1=temp;
				}
				else{
					updatedState.transition1=dfaTable.get(toStateWith1);
				}
			updatedState.goal=goalTest(dfaStates.get(i), goals);
			dfaTable.replace(dfaStates.get(i), updatedState);
		}
//		dfaTable.remove("");
//		dfaTable.forEach((k,v)->{
//			System.out.println(v.name+" -> "+v.transition0.name+" -> "+v.transition1.name+" -> "+v.goal);
//		});
		
		return dfaTable;
	}
	
	public static String mapping(String nfa) {
		String [] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		String[] transitions = nfa.split("#",0);
		String[] zeroTransitions = transitions[0].split(";",0);
		String[] oneTransitions = transitions[1].split(";",0);
		String[] epsilonTransitions = transitions[2].split(";",0);
		String[] goals = transitions[3].split(",",0);
		
		Hashtable<String, Integer> allStates = new Hashtable<String, Integer>();
		
		String output = "";
		
		for (int i=0;i<zeroTransitions.length;i++) {
			String[] split = zeroTransitions[i].split(",",0);
			String x1 = split[0];
			String x2 = split[1];
			if ( allStates.get(x1) == null) {
				allStates.put(x1, Integer.parseInt(x1));
			}
			if ( allStates.get(x2) == null) {
				allStates.put(x2, Integer.parseInt(x2));
			}
			output+=alphabet[allStates.get(x1)]+",";
			output += i==zeroTransitions.length-1 ? alphabet[allStates.get(x2)] : alphabet[allStates.get(x2)]+";";
		}
		output+="#";
		for (int i=0;i<oneTransitions.length;i++) {
			String[] split = oneTransitions[i].split(",",0);
			String x1 = split[0];
			String x2 = split[1];
			if ( allStates.get(x1) == null) {
				allStates.put(x1, Integer.parseInt(x1));
			}
			if ( allStates.get(x2) == null) {
				allStates.put(x2, Integer.parseInt(x2));
			}
			output+=alphabet[allStates.get(x1)]+",";
			output += i==oneTransitions.length-1 ? alphabet[allStates.get(x2)] : alphabet[allStates.get(x2)]+";";
		}
		output+="#";
		for (int i=0;i<epsilonTransitions.length;i++) {
			String[] split = epsilonTransitions[i].split(",",0);
			String x1 = split[0];
			String x2 = split[1];
			if ( allStates.get(x1) == null) {
				allStates.put(x1, Integer.parseInt(x1));
			}
			if ( allStates.get(x2) == null) {
				allStates.put(x2, Integer.parseInt(x2));
			}
			output+=alphabet[allStates.get(x1)]+",";
			output += i==epsilonTransitions.length-1 ? alphabet[allStates.get(x2)] : alphabet[allStates.get(x2)]+";";
		}
		output+="#";
		for (int i=0;i<goals.length;i++) {
			if ( allStates.get(goals[i]) == null) {
				allStates.put(goals[i], Integer.parseInt(goals[i]));
			}
			output += i==goals.length-1 ? alphabet[allStates.get(goals[i])] : alphabet[allStates.get(goals[i])]+",";
		}
		
//		allStates.forEach((k,v)->{
//			System.out.println(k+ " &&& " +v);
//		});
//		System.out.println(output);
		
		
		
		return output;
	}
	
	public static void nfaRun(String inputNfa,String []tests) {
		System.out.println("========= "+inputNfa+" =========");
		inputNfa = mapping(inputNfa);
		Hashtable<String, State> nfaStates = constructTables(inputNfa);
		String[] transitions = inputNfa.split("#",0);
		String goals = transitions[3].replaceAll(",", "");
		Hashtable<String, DFAState> dfaTable = nfaToDfa(nfaStates,"a",goals);
		DFA x = new DFA(dfaTable, tests, nfaStates.get("a").transitions.get("epsilon"));
		x.run();
		System.out.println("++++++++++++++ END ++++++++++++++");
		System.out.println();
	}
	
	public static void main(String[]args) {
//		String inputNfa1 = "0,0;0,1;1,3#1,2;2,4;4,4#0,1;3,4#3,4";
//		String[] tests1 = {
//		"0010",
//		"0111",
//		"1010",
//		"1111",
//		"0110",
//		};
//		nfaRun(inputNfa1,tests1);
//		String[] tests2= {
//				"001011",
//				"011000",
//				"1101001",
//				"011011010",
//				"110010",
//		};
//		String inputNfa2 = "0,0;0,1;0,4;4,4#0,0;1,2;2,3;4,5#3,4;3,1#3,5";
//		nfaRun(inputNfa2,tests2);
		String inputNfa3 = "2,3#4,5;7,8#0,1;0,7;1,2;1,4;3,6;5,6;6,1;6,7#8";
		String[] tests3 = {
				"010101",
				"0111",
				"1100",
				"1",
				"0",
		};
		nfaRun(inputNfa3,tests3);
		
		String inputNfa4 = "1,2;4,5;8,9#3,4;6,7#0,1;0,3;2,1;2,3;5,6;5,8;7,10;9,10#10";
		String[] tests4 = {
				"0010",
				"100",
				"101",
				"1010",
				"0110",
		};
		nfaRun(inputNfa4,tests4);
	}
		
}
	
	

