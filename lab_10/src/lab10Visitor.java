// Generated from /home/fady-wasfalla/Documents/Semester 10/Lap/Compilers-Lab/lab_10/src/lab10.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link lab10Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface lab10Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link lab10Parser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(lab10Parser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab10Parser#x}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitX(lab10Parser.XContext ctx);
}