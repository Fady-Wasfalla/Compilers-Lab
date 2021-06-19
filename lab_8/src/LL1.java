import javax.swing.*;
import java.util.*;

public class LL1 {

    public static Hashtable< HashSet<String> , String> llTable;
    public static Hashtable<String, ArrayList<String>> cfg;
    public static Hashtable<String, ArrayList<String>> first;
    public static Hashtable<String, ArrayList<String>> follow;

    public static Boolean checkTerminal(char inp){
        Boolean res = (inp+"").matches("[A-Z]+") ? false : true;
        return res;
    }

    public static String returnRule(String key,String terminal){
        ArrayList<String> rules =  cfg.get(key);
        for(int i=0;i<rules.size();i++){
            String curRule = rules.get(i);
            for(int j=0;j<curRule.length();j++){
                if( (curRule.charAt(0) + "").equals("e") ){
                    continue;
                }
                Boolean b = (key.equals("Y") && terminal.equals("$"))?true:false;
//                if(b)
//                    System.out.println("##########################################1 "+curRule.charAt(0));
                if (checkTerminal(curRule.charAt(0)) ) { // if first char is terminal or epsilon
                    if ((curRule.charAt(0) + "").equals(terminal)) {
                        return curRule;
                    }
                }else{ // if first char is variable
                    if (checkTerminal(curRule.charAt(j))){ // targer i case AiB
                        if ((curRule.charAt(j)+"").equals(terminal)){
                            return curRule;
                        }
                        else{
                            if(i==rules.size()-1){
                                return "NA";
                            }
                            break;
                        }
                    }else{
                        if(first.get(curRule.charAt(j)+"").contains(terminal)){
                            return curRule;
                        }
                        if(!first.get(curRule.charAt(j)+"").contains("e") && i==rules.size()-1){
                            return "NA";
                        }
                    }
                    if(j==curRule.length()-1 && first.get(curRule.charAt(j)+"").contains("e")){
                        if(follow.get(key).contains(terminal)){
//                            System.out.println("****************");
                            return curRule;
                        }
                    }
                }
            }
        }
        if (first.get(key).contains("e") && follow.get(key).contains(terminal)){
            return "e";
        }
        return "NA";
    }
    public static void constructTable(String input){
        llTable = new Hashtable< HashSet<String> , String>();
        cfg = new Hashtable<String, ArrayList<String>>();
        first = new Hashtable<String, ArrayList<String>>();
        follow = new Hashtable<String, ArrayList<String>>();
        String [] lang = input.split("#");
        String cfgS = lang[0];
        String firS = lang[1];
        String folS = lang[2];
        for(int i=0;i<cfgS.split(";").length;i++){
            ArrayList fills =  new ArrayList<>(Arrays.asList( cfgS.split(";")[i].split(",") ));
            cfg.put( (String)fills.remove(0), fills);
        }

        for(int i=0;i<firS.split(";").length;i++){
            ArrayList<String> fills =  new ArrayList<String>(Arrays.asList( firS.split(";")[i].split(",") ));
            String keyT = fills.remove(0);
            ArrayList f =  new ArrayList<>();
            for(int j=0;j<fills.size();j++){
                if((fills.get(j)+"").length()>1){
                    for (int k=0;k<(fills.get(j)+"").length();k++){
                        f.add(fills.get(j).charAt(k)+"");
                    }
                }else{
                    f.add(fills.get(j)+"");
                }
            }
            first.put( keyT, f);
        }

        for(int i=0;i<folS.split(";").length;i++){
            ArrayList fills =  new ArrayList<>(Arrays.asList( folS.split(";")[i].split(",") ));
            String keyT = (String) fills.remove(0);
            String fs =  (String) fills.remove(0);
            ArrayList f =  new ArrayList<>();
            for(int j=0;j<fs.length();j++){
                f.add(fs.charAt(j)+"");
            }
            follow.put( keyT, f);
        }
        String charss = input.replaceAll("[,#;eA-Z]*", "");
        ArrayList<String> terminals = new ArrayList<String>();

        for (int i=0;i<charss.length();i++){
            if(!terminals.contains(charss.charAt(i)+"")){
                terminals.add(charss.charAt(i)+"");
            }
        }
        cfg.forEach((k,v)->{
            for (int j=0;j<terminals.size();j++){
                HashSet<String> llKey = new HashSet<String>();
                llKey.add(k);
                llKey.add(terminals.get(j));
                String fillTable = returnRule(k,terminals.get(j));
//                System.out.println("++++++++++++++++++++ Rules "+k+" "+terminals.get(j)+" return: "+fillTable);
                llTable.put(llKey,fillTable);

            }
        });

//        System.out.println("RULES TABLE");
//        cfg.forEach((k,v)->{
//            System.out.println(k+" ; "+v);
//        });
//        System.out.println("FIRST TABLE");
//        first.forEach((k,v)->{
//            System.out.println(k+" ; "+v);
//        });
//        System.out.println("FOLLOW TABLE");
//        follow.forEach((k,v)->{
//            System.out.println(k+" ; "+v);
//        });
//        System.out.println("LL1 TABLE");
//        for (Map.Entry<HashSet<String>, String> e : llTable.entrySet()) {
//            System.out.println(Arrays.toString(e.getKey().toArray()) + " ==> " +  e.getValue());
//        }

    }

    public static String parse(String input,String test){
        String result = "";
        ArrayList<String> print = new ArrayList<String>();
        constructTable(input);
        char [] inp = (test+"$").toCharArray();
        Stack<String> st = new Stack<String>();
        st.push("$");
        st.push(input.charAt(0)+"");
        print.add(input.charAt(0)+"");
        Boolean flag = true;
        int index=0;
        while (flag){
            String pop = st.pop();
            if(pop.equals("$") && (inp[index]+"").equals(pop)){
                flag=false;
            }
            if(pop.equals("$") && !(inp[index]+"").equals(pop)){
                flag=false;
                print.add("ERROR");
                break;
            }
            if( !checkTerminal(pop.charAt(0)) ) {
                HashSet<String> key = new HashSet<String>();
                key.add(pop);
                key.add(inp[index] + "");
                String cell = llTable.get(key);
//                System.out.println("CELL: "+cell);
                if(cell.equals("NA")){
//                    System.out.println("H1");
                    print.add("ERROR");
                    break;
                }
                if (!cell.equals("e")) {
                    for (int i = cell.length() - 1; i > -1; i--) {
                        st.push(cell.charAt(i) + "");
                    }
                    print.add(print.get(print.size()-1).replaceFirst(pop,cell));
                }
                else{
//                    System.out.println("HEREEE");
                    print.add(print.get(print.size()-1).replaceFirst(pop,""));
                    continue;
                }
            }
            if(checkTerminal(pop.charAt(0)) && inp[index]==pop.charAt(0)){ // pop same terminal
                index++;
            }
            else {
                if (checkTerminal(pop.charAt(0)) && inp[index] != pop.charAt(0)) { // pop diff terminal
                    flag = false;
//                    System.out.println("H2 " + pop + " " + inp[index]);
                    print.add("ERROR");
                    break;
                }
            }
        }
//        System.out.println();
        for(String x: print){
            result+=x+",";
        }
        result = result.substring(0,result.length()-1);
        return result;
    }
    public static void main(String[] args) {
        String input1 = "S,aX,xX;X,bY,cY,yS;Y,X,x#S,a,x;X,b,c,y;Y,bcy,x#S,$;X,$;Y,$";
        String [] tests = {
                "ayacx",
                "xcbx",
                "xbyxcx",
                "acyxcbx",
                "abc"
        };
        System.out.println("========= " + input1 + " =========");
        for (String t : tests){
            System.out.println(t+" -> "+parse(input1,t));
        }
        System.out.println();
        String input2 = "S,oLc,a;L,oLcD,aD;D,mSD,e#S,o,a;L,o,a;D,m,e#S,cm$;L,c;D,c";
        System.out.println("========= " + input2 + " =========");

        String [] tests1 = {
                "oamac",
                "ooacc",
                "oamamac",
                "oamoamacc",
                "oacoc"
        };
        for (String t : tests1){
            System.out.println(t+" -> "+parse(input2,t));
        }

        System.out.println(checkTerminal('x'));
    }
}