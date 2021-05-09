public class Change {
    public static void main(String[]args){
        String x = "(a+b((a+bb*a)a)*(a+bb*a)b)*(b((a+bb*a)a)*)";
        for(char z : x.toCharArray()){
            if (z=='a'){
                System.out.print("ZERO ");
            }
            else if (z=='b'){
                System.out.print("ONE ");
            }
            else if (z=='+'){
                System.out.print("| ");
            }
            else{
                System.out.print(z);
            }
        }
    }
}
