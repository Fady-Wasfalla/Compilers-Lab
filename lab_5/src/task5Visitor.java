// Generated from /home/fady/Documents/Semester 10/Compilers_Labs/Compilers-Lab/lab_5/src/task5.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link task5Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface task5Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link task5Parser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(task5Parser.StartContext ctx);
}