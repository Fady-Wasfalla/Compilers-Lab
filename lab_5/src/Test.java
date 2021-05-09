import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Test {
    public static void main(String[] args) {
        task5Lexer lexer = new task5Lexer(CharStreams.fromString("011101"));
        task5Parser parser = new task5Parser(new CommonTokenStream(lexer));
        parser.start();
    }
}
