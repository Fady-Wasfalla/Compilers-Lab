import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Test {

    public  static  void run(String testCase){
        System.out.print("- "+testCase+" -> ");
        lab10Lexer lexer = new lab10Lexer(CharStreams.fromString(testCase));
        lab10Parser parser = new lab10Parser(new CommonTokenStream(lexer));
        parser.start();
    }
    public static void main(String[] args) {
        String[] testCases = {
                "001" ,
                "110" ,
                "11011" ,
                "011111",
                "0100011",
                "00010000" ,
                "111101100",
                "1111100010",
                "10100110011",
                "110101010111",
        };
        for (String t : testCases){
            run(t);
        }
    }
}
