import java.util.*;

public class EliminateLeftRec {

    public static String LRE(String input){
        String result = "";
        String[] rules = input.split(";",0);
        String variables = "";
        Hashtable<String,ArrayList<String>> ruleHash = new Hashtable<String,ArrayList<String>>();
        for (String rule : rules){
            String[] splitRule = rule.split(",",0);
            variables+=splitRule[0];
            ArrayList<String> list = new ArrayList<String>();
            Collections.addAll(list, splitRule);
            list.remove(0);
            ruleHash.put(splitRule[0],list);
        }
        for (int i=0; i<variables.length(); i++){
//            System.out.println("======================== ITERATION "+i+" "+variables.charAt(i)+" ========================");
            for(int j=0; j<i; j++){
                ArrayList<String> followingRules = new ArrayList<String>();
                ArrayList<String> followingRulesUpdate = new ArrayList<String>();
                //copy the following rules of j
                for (int x = 0 ; x<ruleHash.get(variables.charAt(i)+"").size();x++){
                    followingRules.add(ruleHash.get(variables.charAt(i)+"").get(x)) ;
                    followingRulesUpdate.add(ruleHash.get(variables.charAt(i)+"").get(x)) ;
                }
                char currentJ = variables.charAt(j);
                //eliminate variables
                for(String t : followingRules){
                    if(currentJ==t.charAt(0)){
                        String remaining = t.substring(1);
                        followingRulesUpdate.remove(t);
                        ArrayList<String> JfollowingRules = new ArrayList<String>();
                        JfollowingRules = ruleHash.get(currentJ+"");
                        for(String jt : JfollowingRules){
                            followingRulesUpdate.add(jt+remaining);
                        }
                    }
                }
                ruleHash.put(variables.charAt(i)+"",followingRulesUpdate);
            }
            //check for left recursion
            ArrayList<String> IfollowingRules = new ArrayList<String>();
            IfollowingRules = ruleHash.get(variables.charAt(i)+"");
            Boolean leftRec = false;
            for (String ct: IfollowingRules){
                if(ct.charAt(0)==variables.charAt(i)){
                    leftRec=true;
                    break;
                }
            }
            if(leftRec){
                ArrayList<String> baseFollowingRules = new ArrayList<String>();
                ArrayList<String> dashFollowingRules = new ArrayList<String>();
                String dashVar = variables.charAt(i)+"\'";
                for (String ct: IfollowingRules){
                    if(ct.charAt(0)==variables.charAt(i)){
                        dashFollowingRules.add(ct.substring(1)+dashVar);
                    }
                    else{
                        baseFollowingRules.add(ct+dashVar);
                    }
                }
                dashFollowingRules.add("e");
                //update ruleHash and result
                if(result.length()!=0){
                    result+=";";
                }
                ruleHash.put(variables.charAt(i)+"",baseFollowingRules);
                result+=variables.charAt(i)+",";
                for(int c=0;c<baseFollowingRules.size();c++){
                    if(c == baseFollowingRules.size()-1){
                        result+= baseFollowingRules.get(c);
                    }else{
                        result+= baseFollowingRules.get(c);
                        result+=",";
                    }
                }
                result+=";";
                ruleHash.put(dashVar,dashFollowingRules);
                result+=dashVar+",";
                for(int c=0;c<dashFollowingRules.size();c++){
                    if(c == dashFollowingRules.size()-1){
                        result+= dashFollowingRules.get(c);
                    }else{
                        result+= dashFollowingRules.get(c);
                        result+=",";
                    }
                }
            }else{
                if (result.length()!=0){
                    result+=";";
                }
                result+=variables.charAt(i)+",";
                for(int c=0;c<IfollowingRules.size();c++){
                    if(c == IfollowingRules.size()-1){
                        result+= IfollowingRules.get(c);
                    }else{
                        result+= IfollowingRules.get(c);
                        result+=",";
                    }
                }
            }
        }
//        ruleHash.forEach((k,v)->{
//			System.out.println(k+" ; "+v);
//		});
//        System.out.println();

        return result;
    }
    public static void main(String[]args){
        String [] inputs = {
                "S,aSt,aS,c",
                "S,StS,SxS,a",
                "S,StT,T;T,TxF,F;F,id",
                "S,EF,Fd;E,SF,ES,c;F,SE,c",
                "S,ScT,Sa,T,b;T,aSb,iaLb,i;L,SdL,S"
        };
        for (String input :inputs){
            System.out.println("INPUT : "+input);
            System.out.println();
            System.out.println("OUTPUT : "+LRE(input));
            System.out.println();
            System.out.println();
        }
    }
}
