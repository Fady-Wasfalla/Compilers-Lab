import java.util.*;

public class FirstAndFollow {
    public static Hashtable<String, ArrayList<String>> ruleHash;
    public static Hashtable<String, ArrayList<String>> firstHash;
    public static Hashtable<String, ArrayList<String>> followHash;
    public static ArrayList<String> variables;

    public static String calculateFirAndFol(String input){
        String result = "";
        String[] rules = input.split(";",0);
        ruleHash = new Hashtable<String,ArrayList<String>>();
        variables = new ArrayList<String>();
        for (String rule : rules){
            String[] splitRule = rule.split(",",0);
            ArrayList<String> list = new ArrayList<String>();
            Collections.addAll(list, splitRule);
            list.remove(0);
            variables.add(splitRule[0]);
            ruleHash.put(splitRule[0],list);
        }
        firstHash = new Hashtable<String,ArrayList<String>>();
        followHash = new Hashtable<String,ArrayList<String>>();
        for (String key : variables){
            ArrayList<String> list = new ArrayList<String>();
            firstHash.put(key,list);
            followHash.put(key,list);
        }

        calculateFirst(input);
        calculateFollow(input);

        return result;
    }


    public static String calculateFirst(String input){
        String result="";

        Boolean change = true;
        while (change) {
            change = false;
            for (String key : variables) {
                ArrayList<String> currntKeyList = ruleHash.get(key);
                Boolean change1 = epsilonIntersection(key,currntKeyList) ;
                Boolean change2 = firstSubset(key,currntKeyList);
                change = change || change1 || change2;
            }
//            System.out.println("============== CHANGE ============== " + change);
//            firstHash.forEach((k,v)->{
//                System.out.println(k+" ; "+v);
//            });
//            System.out.println();
        }
        for(String key: variables){
            if (result.length()>0){
                result+=";";
            }
            result+=key;
            result+=",";
            String firstOfKey = "";
            for(int i=0;i<firstHash.get(key).size();i++){
                firstOfKey+=firstHash.get(key).get(i);
            }
            char charArray[] = firstOfKey.toCharArray();
            Arrays.sort(charArray);
            result+=new String(charArray);
        }
        System.out.println("First: "+result);
        return result;
    }

    public static Boolean epsilonIntersection(String key , ArrayList<String> list){
        Boolean res = false;
        Boolean conatinsEpsilon = false;
        for (String value : list){
//            System.out.println("HEREEE "+key+" "+value+" "+(value.equals("e")));
            Boolean contain = true;
            if (value.matches("[A-Z]+")){ // all chars of the value are not terminals
                for (int i=0;i<value.length();i++){
                    contain = contain && firstHash.get(value.charAt(i)+"").contains("e");
                }
            }else if (value.equals("e")){
                contain = true;
            }else{
                contain=false;
            }
            conatinsEpsilon = conatinsEpsilon || contain;
        }
        if (conatinsEpsilon){
            if (!firstHash.get(key).contains("e")) {
                ArrayList<String> updatedRules = new ArrayList<String>();
                for (int x = 0; x < firstHash.get(key).size(); x++) {
                    updatedRules.add(firstHash.get(key).get(x));
                }
                updatedRules.add("e");
                firstHash.put(key, updatedRules);
//                System.out.println("KEYEE "+key +" RULES "+updatedRules);
                res = true;
            }
        }
        return res;
    }

    public static Boolean firstSubset(String key,ArrayList<String> list){
//        System.out.println("ENTER firstSubset "+ key + "  "+list);
        Boolean res = false;
        for(int i=0;i<list.size();i++){
            for(int j=0;j<list.get(i).length();j++){
                String currentValue = list.get(i);
                ArrayList<String> updatedRules = new ArrayList<String>();
                for (int x = 0 ; x<firstHash.get(key).size();x++){
                    updatedRules.add(firstHash.get(key).get(x)) ;
                }
//                    System.out.println(key + "==>" + list + "==>" +list.get(i) + "==>" + j + " " +currentValue.charAt(j));
                if(j==0){
                    if( (currentValue.charAt(j)+"").matches("[A-Z]+") ){ // if the first char of the value is capital
                        ArrayList<String> currentFirstVariable = firstHash.get(currentValue.charAt(j)+"");
                        for(int k=0;k<currentFirstVariable.size();k++){
                            if(!updatedRules.contains(currentFirstVariable.get(k)) && !currentFirstVariable.get(k).equals("e")){
                                updatedRules.add(currentFirstVariable.get(k));
//                                    System.out.println("KEY1 "+key + " ADD " +currentFirstVariable.get(k)+" RULES "+updatedRules);
                                res=true;
                                firstHash.put(key,updatedRules);
                            }
                        }
                    }
                    else{ // if the first char of the value is terminal
                        if(!(updatedRules.contains(currentValue.charAt(j)+""))  ){
                            updatedRules.add(currentValue.charAt(j)+"");
//                                System.out.println("KEY2 "+key+" ADD " + currentValue.charAt(j) +" RULES "+updatedRules);
                            res=true;
                            firstHash.put(key,updatedRules);
                        }
                        break;
                    }
                }else{
                    if( (currentValue.charAt(j)+"").matches("[A-Z]+") ){
                        if ( epsilonIntersectionFirstSubset(currentValue.substring(0,j)) ){
                            ArrayList<String> currentFirstVariable = firstHash.get(currentValue.charAt(j)+"");
                            for(int k=0;k<currentFirstVariable.size();k++){
                                if(!updatedRules.contains(currentFirstVariable.get(k))){
                                    updatedRules.add(currentFirstVariable.get(k));
//                                        System.out.println("KEY3 "+key+" ADD " + currentFirstVariable.get(k) +" RULES "+updatedRules);
                                    res=true;
                                    firstHash.put(key,updatedRules);
                                }
                            }
                        }
                    }else{
                        if(!updatedRules.contains(currentValue.charAt(j)+"") && epsilonIntersectionFirstSubset(currentValue.substring(0,j)) ){
                            updatedRules.add(currentValue.charAt(j)+"");
//                                System.out.println("KEY4 "+key+" ADD " + currentValue.charAt(j) +" RULES "+updatedRules);
                            res=true;
                            firstHash.put(key,updatedRules);
                        }
                        break;
                    }
                }
            }
        }
//        System.out.println("END firstSubset "+key +" "+res);
        return res;
    }

    public static Boolean epsilonIntersectionFirstSubset(String value){
        Boolean conatinsEpsilon = true;
        if (value.matches("[A-Z]+")){ // all chars of the value are not terminals
            for (int i=0;i<value.length();i++){
                conatinsEpsilon = conatinsEpsilon && firstHash.get(value.charAt(i)+"").contains("e");
            }
        }
        else{
            conatinsEpsilon=false;
        }
        return conatinsEpsilon;
    }

    public static String calculateFollow(String input){
        String result="";
        ArrayList<String> addDollar = new ArrayList<String>();
        addDollar.add("$");
        followHash.put(input.charAt(0)+"",addDollar);

        Boolean change = true;
        while(change){
            change=false;
            for (String key : variables) {
                ArrayList<String> currntKeyList = ruleHash.get(key);
                Boolean change1 = followEpsilonIntersection(key,currntKeyList);
                change = change || change1;
            }
//            System.out.println("============== CHANGE ============== " + change);
//            followHash.forEach((k,v)->{
//                System.out.println(k+" ; "+v);
//            });
//            System.out.println();
        }

        for(String key: variables){
            if (result.length()>0){
                result+=";";
            }
            result+=key;
            result+=",";
            String firstOfKey = "";
            for(int i=0;i<followHash.get(key).size();i++){
                firstOfKey+=followHash.get(key).get(i);
            }
            char charArray[] = firstOfKey.toCharArray();
            Arrays.sort(charArray);
            String fol = new String(charArray);
            if( (fol.charAt(0)+"").equals("$") ){
                fol = fol.substring(1);
                fol+="$";
            }
            result+=fol;
        }
        System.out.println("Follow: "+result);
        System.out.println();

        return result;
    }

    public static Boolean followEpsilonIntersection(String key , ArrayList<String> list){
        Boolean res = false;
        for (String value : list){
            for(int i=0;i<value.length();i++){
                if( (value.charAt(i)+"").matches("[A-Z]+") ){
                    if(i==value.length()-1){    // if the last letter is Variable
                        ArrayList<String> updatedRules = new ArrayList<String>();
                        for (int x = 0; x < followHash.get(value.charAt(i)+"").size(); x++) {
                            updatedRules.add(followHash.get(value.charAt(i)+"").get(x));
                        }
                        for (int x = 0; x < followHash.get(key).size(); x++) {
                            if ( !updatedRules.contains(followHash.get(key).get(x)) && !followHash.get(key).get(x).equals("e")){
                                updatedRules.add(followHash.get(key).get(x));
                                followHash.put(value.charAt(i)+"",updatedRules);
//                                System.out.println("FOLLOW 1 "+key+" ADD " + followHash.get(key).get(x) +" RULES "+updatedRules);
                                res=true;
                            }
                        }
                    }else{
                        if( (value.charAt(i+1)+"").matches("[A-Z]+") ) { // if variable followed by variable
                            //first case with no epsilon
                            ArrayList<String> betaRules = new ArrayList<String>();
                            for (int x = 0; x < firstHash.get(value.charAt(i + 1) + "").size(); x++) {
                                if (!firstHash.get(value.charAt(i + 1) + "").get(x).equals("e")) {
                                    betaRules.add(firstHash.get(value.charAt(i + 1) + "").get(x));
                                }
                            }
                            ArrayList<String> updatedRules0 = new ArrayList<String>();
                            for (int x = 0; x < followHash.get(value.charAt(i) + "").size(); x++) {
                                updatedRules0.add(followHash.get(value.charAt(i) + "").get(x));
                            }
                            for (int x = 0; x < betaRules.size(); x++) {
                                if (!updatedRules0.contains(betaRules.get(x))) {
                                    updatedRules0.add(betaRules.get(x));
                                    res = true;
                                    followHash.put(value.charAt(i) + "", updatedRules0);
//                                    System.out.println("FOLLOW 2 " + value.charAt(i) + " ADD " + betaRules.get(x) + " RULES " + updatedRules0);
                                }
                            }

                            //second case check if epsilon in the first of the follow
                            int n=1;
                            Boolean next = true;
                            while (next){
                                next=false;
                                ArrayList<String> updatedRules = new ArrayList<String>();
                                for (int x = 0; x < followHash.get(value.charAt(i) + "").size(); x++) {
                                    updatedRules.add(followHash.get(value.charAt(i) + "").get(x));
                                }
                                String index = value.charAt(i + n) + "";
                                if(!index.matches("[A-Z]+")){
                                    if(!updatedRules.contains(index)){
                                        updatedRules.add(index);
                                        followHash.put(value.charAt(i)+"",updatedRules);
                                        res=true;
                                    }
                                }else{
                                    if(firstHash.get(index).contains("e")){
                                        next=true;
                                        for(int x = 0; x<firstHash.get(index).size(); x++){
                                            if(!updatedRules.contains(firstHash.get(index).get(x)) && !firstHash.get(index).get(x).equals("e")){
                                                updatedRules.add(firstHash.get(index).get(x));
                                                followHash.put(value.charAt(i)+"",updatedRules);
                                                res=true;
                                            }
                                        }
                                        if( (i+n) == value.length()-1){
                                            next=false;
                                            for(int x = 0; x<followHash.get(key).size(); x++){
                                                if(!updatedRules.contains(followHash.get(key).get(x))){
                                                    updatedRules.add(followHash.get(key).get(x));
                                                    followHash.put(value.charAt(i)+"",updatedRules);
                                                    res=true;
                                                }
                                            }
                                        }
                                    }
                                }
                                n++;
                            }


                        }else{  // if variable followed by terminal
                            ArrayList<String> updatedRules = new ArrayList<String>();
                            for (int x = 0; x < followHash.get(value.charAt(i)+"").size(); x++) {
                                updatedRules.add(followHash.get(value.charAt(i)+"").get(x));
                            }
                            if (!updatedRules.contains(value.charAt(i+1)+"")){
                                updatedRules.add(value.charAt(i+1)+"");
                                followHash.put(value.charAt(i)+"",updatedRules);
//                                System.out.println("FOLLOW 5 "+ value.charAt(i)+" ADD " + value.charAt(i+1) +" RULES "+updatedRules);
                                res=true;
                            }
                        }
                    }
                }
            }
        }
        return  res;
    }


    public static void main(String[]args){
//        String[]input = {
//                "S,aBDh;B,cA;A,bA,e;D,EF;E,g,e;F,f,e",
//                "S,A;A,aY,Ad;Y,b",
//                "S,bLc,a;L,SN;N,kSN,e",
//                "S,Sa,B;B,cB,D;D,o,e",
//                "S,ACB,CbB,Ba;A,da,BC;B,g,e;C,h,e"
//        };
        String[] input ={
                "S,tOlS,d;O,OQ,zSzQ,z,e;Q,Qz,s,e",

                "S,cOS,ftE,f;O,ccft,SES,e;E,tO,tw",

                "S,XSqX,SoXt,key;X,qtrX,mn,b",

                "S,SpHr,a;T,STH,pUr,yU;H,SrH,Uio,e;U,hi,a",

                "S,Szf,QzQQ,dok;Q,fScz,zie,zf"
        };
        for(String x : input){
            System.out.println("====== "+x+" ======");
            calculateFirAndFol(x);
        }
//        calculateFirAndFol("S,ACB,CbB,Ba;A,da,BC;B,g,e;C,h,e");

    }

}
