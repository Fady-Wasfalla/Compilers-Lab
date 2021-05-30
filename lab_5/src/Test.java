import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Test {

    public  static  void run(String testCase){
        System.out.print(testCase+" : ");
        task5Lexer lexer = new task5Lexer(CharStreams.fromString(testCase));
        task5Parser parser = new task5Parser(new CommonTokenStream(lexer));
        parser.start();
        System.out.println();
    }
    public static void main(String[] args) {
        String[] testCases = {
                "01001111",
                "1001111",
                "01011111",
                "0010100111",
                "1111011",
                "0010100",
                "1110010100",
                "11100101",
                "1100111101",
                "011000001"
        };
        for (String t : testCases){
            run(t);
        }
    }
}
