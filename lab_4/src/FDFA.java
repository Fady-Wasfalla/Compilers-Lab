import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class FDFA {

    String fdfa;
    Hashtable<String, DFA> dfaTable;

    public FDFA(String fdfa){
        this.fdfa=fdfa;
        this.dfaTable=constructDFA(fdfa);
    }

    public static Hashtable<String, DFA> constructDFA(String inp){
        Hashtable<String, DFA> dfaTable = new Hashtable<String, DFA>();
        String [] states = inp.split("#",0)[0].split(";",0);

        List<String> goals = new ArrayList<String>();
        goals = Arrays.asList(inp.split("#",0)[1].split(",",0));

        for (String st : states){
            String [] trans = st.split(",",0);
            if (dfaTable.get(trans[0]) == null ){
                DFA newState = new DFA(trans[0]);
                newState.transition0 = trans[1];
                newState.transition1 = trans[2];
                if ( goals.indexOf(trans[0]) ==-1 ){
                    newState.acceptance=false;
                }else{
                    newState.acceptance=true;
                }
                newState.action = trans[3];
                dfaTable.put(newState.name,newState);
            }
        }
        return dfaTable;
    }
}
