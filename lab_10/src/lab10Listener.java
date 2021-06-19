// Generated from /home/fady-wasfalla/Documents/Semester 10/Lap/Compilers-Lab/lab_10/src/lab10.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link lab10Parser}.
 */
public interface lab10Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link lab10Parser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(lab10Parser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab10Parser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(lab10Parser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab10Parser#x}.
	 * @param ctx the parse tree
	 */
	void enterX(lab10Parser.XContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab10Parser#x}.
	 * @param ctx the parse tree
	 */
	void exitX(lab10Parser.XContext ctx);
}