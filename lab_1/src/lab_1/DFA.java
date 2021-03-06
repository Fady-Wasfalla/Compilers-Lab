package lab_1;

public class DFA {

	String[][] Dfa;
	
	public DFA(String dfa) {
		String[] arrOfStr = dfa.split("#",0);
		String[] states = arrOfStr[0].split(";",0);
		Dfa = new String[states.length][];
		String[] goals = arrOfStr[1].split(",",0);
		for (int i=0 ;i<states.length;i++) {
			String fl = ",false";
			for (String g : goals) {
				if ( g.charAt(0) == states[i].charAt(0) ) {
					fl = ",true";
					break;
				}
			}
			states[i]+=fl;
			Dfa[i]=states[i].split(",",0);
		}
		
	}
	
	public static boolean run(String test , DFA inp) {
		int position = 0 ;
		for (int i=0; i<test.length();i++) {
			if (test.charAt(i) == '0') {
				position = Integer.parseInt(inp.Dfa[position][1]);
			}
			else{
				position = Integer.parseInt(inp.Dfa[position][2]);
			}
		}
		return Boolean.parseBoolean(inp.Dfa[position][3]);

	}
	
	 public static void main(String args[]){
		DFA one = new DFA("0,0,1;1,2,1;2,0,3;3,3,3#1,3");
		String[] listOne = {"0101","1101","0010","1000","1100"};
		for(String l : listOne) {
			boolean f = run(l,one);
			System.out.println(l +" -> "+f);
		}
		System.out.println();
		DFA two = new DFA("0,3,1;1,2,1;2,2,1;3,3,3#2");
		String[] listTwo = {"010","10101010","10010","100010011","010010"};
		for(String l : listTwo) {
			boolean f = run(l,two);
//			System.out.println(l +" -> "+f);
		}
		System.out.println();
		DFA three = new DFA("0,0,1;1,1,2;2,2,2#2");
		String[] listThree = {"0101","010","0000","0100","1101"};
		for(String l : listThree) {
			boolean f = run(l,three);
			System.out.println(l +" -> "+f);
		}
	 }


}