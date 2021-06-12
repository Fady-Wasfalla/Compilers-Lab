import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class CFG {

    String[] states;
    ArrayList<ArrayList<String>> statesArray;
    String[] leftHandSides;
    ArrayList<String> terminals;
    ArrayList<String> firstArraylist;

    Hashtable<String, ArrayList<String>> firstTable;
    Hashtable<String, ArrayList<String>> rules;

    int statesLength;

    public CFG(String input) {
        states = input.split(";");
        statesLength = states.length;
        leftHandSides = new String[statesLength];
        statesArray = new ArrayList<>();

        for(int i=0; i< statesLength;i++) {
            leftHandSides[i]=states[i].split(",")[0];
        }
        for(int i=0; i< statesLength;i++) {
            String[] s = states[i].split(",");
            ArrayList<String> arr = new ArrayList<>();
            for(int j=1;j<s.length;j++) {
                arr.add(s[j]);
            }
            statesArray.add(arr);
        }

        rules = new Hashtable<>();
        firstTable=new Hashtable<>();
        terminals = new ArrayList<String>();
        firstArraylist = new ArrayList<String>();
        boolean nonTerminal = true;

        for(int i=0;i<statesLength;i++) {
            rules.put(leftHandSides[i], statesArray.get(i));
            firstTable.put(leftHandSides[i],new ArrayList<String>());
        }

        System.out.println(rules);
        System.out.println(firstTable);


        for(int i=0;i<statesLength;i++) {
            for(int j=0;j<statesArray.get(i).size();j++) {
                for(int c=0;c<statesArray.get(i).get(j).length();c++) {
                    for(int l=0;l<leftHandSides.length;l++) {
                        if(((statesArray.get(i).get(j).charAt(c)+"").equals(leftHandSides[l]))) {
                            nonTerminal=false;
                        }
                    }
                    if(nonTerminal && !terminals.contains(statesArray.get(i).get(j).charAt(c)+"") && statesArray.get(i).get(j).charAt(c)!='e' )
                        terminals.add(statesArray.get(i).get(j).charAt(c)+"");
                    nonTerminal=true;
                }
            }
        }

        //first(statesArray);
        firstTrial3(statesArray);
    }
    public String first(ArrayList<ArrayList<String>> input) {
        //Set<String> first_Set = new HashSet<String>();
        String[] firstArray = new String[statesLength];
        boolean[] epsilons = new boolean[statesLength];


        boolean change = true;
        boolean nonTerminal = true;
        boolean caseDone = false;

        //while(change) {
        //change = false;
        for(int i=0;i<statesLength;i++) {
            for(int j=0;j<statesArray.get(i).size();j++) {
                if(statesArray.get(i).get(j).equals("e")) {
                    firstArray[i]="e";
                    epsilons[i]=true;
                }
                for(int c=0;c<statesArray.get(i).get(j).length();c++) {
                    for(int l=0;l<leftHandSides.length;l++) {
                        if(((statesArray.get(i).get(j).charAt(c)+"").equals(leftHandSides[l]))) {
                            nonTerminal=false;
                        }
                    }
                    if(nonTerminal && !terminals.contains(statesArray.get(i).get(j).charAt(c)+"") && statesArray.get(i).get(j).charAt(c)!='e' )
                        terminals.add(statesArray.get(i).get(j).charAt(c)+"");
                    nonTerminal=true;
                }
            }
        }
        //		for(String b:terminals) {
        //			System.out.println("nonTerminslas: " +b);
        //		}
        for(int i=0;i<statesLength;i++) {
            for(int j=0;j<statesArray.get(i).size();j++) {

                caseDone=false;

                //if it is a terminal case
                for(int t=0;t<terminals.size();t++) {
                    if((statesArray.get(i).get(j).charAt(0)+"").equals(terminals.get(t))) {
                        if(firstArray[i]!=null)
                            firstArray[i]= firstArray[i]+ statesArray.get(i).get(j).charAt(0);
                        else
                            firstArray[i]= statesArray.get(i).get(j).charAt(0)+"";
                        caseDone = true;
                    }
                }

                if(!caseDone) {
                    int lengthForOne = statesArray.get(i).get(j).length();
                    for(int le=0;le<lengthForOne;le++) {
                        char firstLetter = statesArray.get(i).get(j).charAt(le);

                        //S -> S | ...
                        if((firstLetter+"").equals(statesArray.get(i).get(0)+""))
                        {
                            if(!epsilons[i])
                                caseDone=true;
                            else {
                                //feeha epsilon
                                firstArray[i]=firstArray[i]+firstArray[statesArray.get(i).get(j).charAt(le)];
                            }
                        }

                        if(!caseDone) {
                            for(int l=0;l<leftHandSides.length;l++) {
                                if(((statesArray.get(i).get(j).charAt(le)+"").equals(leftHandSides[l]))) {
                                    if(epsilons[le]) {
                                        //feeha epsilon
                                        firstArray[i]+=firstArray[le];
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for(String b:firstArray) {
            System.out.println("firsts: " +b);
        }
        return "";
    }
    public void addElement(Hashtable<String, ArrayList<String>> hash,String location,String input){
        ArrayList<String> temp = hash.get(location);
        temp.add(input);
        hash.put(location, temp);
    }
    public void addSet(Hashtable<String, ArrayList<String>> hash,String location,ArrayList<String> input){
        ArrayList<String> temp = hash.get(location);
        for(int i=0;i<input.size();i++) {
            temp.add(input.get(i));
            System.out.println(input.size());
        }
        hash.put(location, temp);
    }

    public void firstTrial2(ArrayList<ArrayList<String>> input) {

        boolean change = true;
        boolean terminalFound=false;
        boolean epsilonIntersection = true;

        while(change) {

            change=false;
            epsilonIntersection=true;
            terminalFound=false;

            for(int i=0; i<leftHandSides.length;i++) {

                //				System.out.println(rules.get("S").size());
                //				System.out.println(rules.get("S").get(i));

                for(int j=0;j<rules.get(leftHandSides[i]).size();j++) {
                    for(int c=0;c<rules.get(leftHandSides[i]).get(j).length();c++) {

                        if(firstTable.get(leftHandSides[i]).size()!=0 ) {
                            System.out.println("length " + firstTable);
                            if(!((rules.get(leftHandSides[i]).get(j).charAt(c)+"").contains("e"))) {
                                epsilonIntersection=false;
                            }
                        }
                    }
                    if(epsilonIntersection && !firstTable.get(leftHandSides[i]).contains("e")) {
                        addElement(firstTable,leftHandSides[i],"e");
                        change =true;
                    }
                    for(int t=0;t<terminals.size();t++) {
                        if((rules.get(leftHandSides[i]).get(j).charAt(0)+"").equals(terminals.get(t))) {
                            addElement(firstTable,leftHandSides[i],terminals.get(t));
                            //							change = true;
                            terminalFound=true;
                            break;
                        }
                        if((rules.get(leftHandSides[i]).get(j).charAt(0)+"").equals("e")) {
                            addElement(firstTable,leftHandSides[i],"e");
                            //change = true;
                            terminalFound=true;
                            break;
                        }
                    }
                    //					if(!epsilonIntersection && !terminalFound) {
                    for(int c=0;c<rules.get(leftHandSides[i]).get(j).length();c++) {
                        // 1 to k
                        String character = rules.get(leftHandSides[i]).get(j).charAt(c)+"";
                        System.out.println("char  "+character);

                        //						if((rules.get(leftHandSides[i]).get(j).charAt(c)+"").contains("e")) {
                        //							addSet(firstTable, leftHandSides[i], firstTable.get(rules.get(leftHandSides[i]).get(j).charAt(c+1)+""));
                        //						}
                        if(c!=0 && j!=0 &&firstTable.get(character)!=null && (rules.get(leftHandSides[i]).get(j).charAt(c-1)+"").contains("e") ) {
                            if(!(firstTable.get(leftHandSides[i]).contains(firstTable.get(character)+""))) {
                                System.out.println("DPPP");
                                addSet(firstTable, leftHandSides[i], firstTable.get(rules.get(leftHandSides[i]).get(j).charAt(c+1)+""));
                                //change=true;
                            }
                        }


                        if(c==0 && !character.equals(leftHandSides[i])) {
                            if(firstTable.get(character)!=null && !firstTable.get(leftHandSides[i]).contains(firstTable.get(character)+"")) {
                                addSet(firstTable, leftHandSides[i], firstTable.get(character));
                                //change=true;
                            }
                        }

                    }
                }
            }
            //			}
        }
    }

    public void firstTrial3(ArrayList<ArrayList<String>> input) {


        boolean change = true;
        boolean terminalFound=false;
        boolean epsilonIntersection = true;

        while(change) {

            change=false;
            epsilonIntersection=true;
            terminalFound=false;

            for(int i=0; i<leftHandSides.length;i++) {
                for(int j=0;j<rules.get(leftHandSides[i]).size();j++) {
                    for(int c=0;c<rules.get(leftHandSides[i]).get(j).length();c++) {
                        if(!(rules.get(leftHandSides[i]).get(j).charAt(c)+"").contains("e")) {
                            epsilonIntersection=false;
                        }
                        if((rules.get(leftHandSides[i]).get(j).charAt(c)+"").equals("e")) {
                            System.out.println( "PRINT "+rules.get(leftHandSides[i]).get(j).charAt(c));
                            addElement(firstTable,leftHandSides[i],"e");
//							change =true;
                        }
                    }

                    if(epsilonIntersection && !firstTable.get(leftHandSides[i]).contains("e")) {
                        System.out.println( "PRINT2 "+rules.get(leftHandSides[i]).get(j));
                        addElement(firstTable,leftHandSides[i],"e");
                        //change =true;
                    }

                    for(int t=0;t<terminals.size();t++) {
                        if((rules.get(leftHandSides[i]).get(j).charAt(0)+"").equals(terminals.get(t))) {
                            addElement(firstTable,leftHandSides[i],terminals.get(t));
//							change = true;
                            terminalFound=true;
//							break;
                        }
                    }
                    System.out.println(rules.get(leftHandSides[i]).get(j));
                    System.out.println(rules.get(leftHandSides[i]).get(j).length());

                    for(int c=0;c<rules.get(leftHandSides[i]).get(j).length();c++) {
                        String before = rules.get(leftHandSides[i]).get(j);
                        if(c>0 &&firstTable.get(before.charAt(c)+"")!=null&& firstTable.get(before.charAt(c)+"").contains("e")) {
                            if(!firstTable.get(before.charAt(c)+"").contains(firstTable.get(leftHandSides[i])+"")) {
                                System.out.println("INN");
                                addSet(firstTable, leftHandSides[i], firstTable.get(rules.get(leftHandSides[i]).get(j).charAt(c)+""));
                                change=true;
                            }
                        }
                        else if(c==0){
                            if(firstTable.get(rules.get(leftHandSides[i]).get(j)+"")!=null) {
                                addSet(firstTable, leftHandSides[i], firstTable.get(rules.get(leftHandSides[i]).get(j).charAt(0)+""));
                                //change =true;
                                System.out.println("D"+firstTable.get(rules.get(leftHandSides[i]).get(j)+""));
                            }
                        }
                    }

                }
            }
            System.out.println(rules);
            System.out.println(firstTable);
        }
    }

    public static void main(String[] args) {
        CFG cfg = new CFG("S,ScT,T;T,aSb,iaLb,e;L,SdL,S");

    }
}
