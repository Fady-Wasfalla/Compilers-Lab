import java.util.*;

public class FBDFA {

    public static String popDFA(Stack<DFA> stk , String testCase){
        DFA top = stk.pop();
        if (top.acceptance){
            return top.action;
        }
        return "";
    }

    public static String runTestCase(Hashtable<String, DFA> dfaTable , String testCase ){
        Stack<DFA> stk= new Stack<>();
        stk.push(dfaTable.get("0"));
        DFA currentState = dfaTable.get("0");

        for (int i=0;i<testCase.length();i++){
            DFA nextState;
            if ( testCase.charAt(i) =='0'){
                nextState = dfaTable.get(currentState.transition0);
            }else{
                nextState = dfaTable.get(currentState.transition1);
            }
            stk.push(nextState);
            currentState = nextState;
        }
        DFA firstElem = stk.lastElement();
        DFA top = stk.pop();
        String popString = "";
        while(!top.acceptance && !stk.empty()){
            top = stk.pop();
            popString = testCase.charAt(testCase.length()-1) + popString;
            testCase = testCase.substring(0, testCase.length() - 1);
//            System.out.println("testCase ==>"+testCase);
        }
        if (stk.empty()){
//            System.out.println("firstElem.action ==> "+firstElem.name+" - "+firstElem.action);
            return firstElem.action;
        }
        if (popString.length()==0){
//            System.out.println("popString.length()==0 ==>" + top.action);
            return top.action;
        }
//        System.out.println("top.action ==>" + top.action);
        return top.action + runTestCase(dfaTable , popString );
    }

    public static void run(String inp,String [] testCases){
        FDFA fbDfa= new FDFA(inp);
        System.out.println("========= "+inp+" =========");
        for (String test : testCases){
            System.out.println("$$ TEST CASE "+test + " ==> "+runTestCase(fbDfa.dfaTable,test));
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        run("0,1,0,00;1,1,2,01;2,1,3,10;3,1,0,11#3", new String[]{"0100", "10011","1000011011","011001","1001111010"});
        run("0,1,3,000;1,2,3,001;2,2,4,010;3,1,4,011;4,2,4,100#2,4",new String[]{"01110110","0101001","1010","101011001","11110"});
        run("0,1,0,00;1,1,2,01;2,2,2,10#2",new String[]{"00"});
    }

}
