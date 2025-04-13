package com.jordanbunke.delta_time.scripting;

import com.jordanbunke.delta_time.scripting.ast.nodes.ASTNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.function.HeadFuncNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.function.HelperFuncNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.ScriptVisitor;
import com.jordanbunke.delta_time.scripting.util.TextPosition;
import com.jordanbunke.delta_time.scripting.util.TypeCompatibility;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.nio.file.Path;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Interpreter {
    private static ScriptVisitor visitor = new ScriptVisitor();
    private static Consumer<String> stdout = System.out::print;
    private static Supplier<String> stdin = () -> {
        final Scanner in = new Scanner(System.in);
        return in.nextLine();
    };

    public static Interpreter get() {
        return new Interpreter();
    }

    public static String read() {
        return stdin.get();
    }

    public static void print(final String message) {
        stdout.accept(message);
    }

    public static void println(final String message) {
        print(message + "\n");
    }

    public static String prompt(final String prompt) {
        print(prompt);
        return read();
    }

    public Object run(
            final HeadFuncNode script, final Path scriptPath,
            final Object... args
    ) {
        ScriptErrorLog.clearErrors();

        final SymbolTable scriptTable = SymbolTable.root(script, scriptPath);

        final boolean passedChecks = check(script, scriptTable);

        if (passedChecks) {
            final Optional<Object> result =
                    execute(script, scriptTable, args);

            if (!ScriptErrorLog.hasNoErrors())
                displayErrors();
            else if (result.isPresent())
                return result.get();
        } else
            displayErrors();

        return null;
    }

    public Object run(
            final HeadFuncNode script, final Object... args
    ) {
        return run(script, null, args);
    }

    public HeadFuncNode build(final String content) {
        try {
            final CharStream input = CharStreams.fromString(content);

            final ScriptLexer lexer = new ScriptLexer(input);
            lexer.removeErrorListeners();

            final ScriptParser parser = new ScriptParser(
                    new CommonTokenStream(lexer));
            parser.removeErrorListeners();

            return visitor.visitHead_rule(parser.head_rule());
        } catch (Exception e) {
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.COULD_NOT_READ,
                    TextPosition.N_A);
            displayErrors();
            return null;
        }
    }

    public HelperFuncNode buildFunction(final String func) {
        return build(func, ScriptParser::helper, ScriptVisitor::visitHelper);
    }

    public ExpressionNode buildExpression(final String expr) {
        try {
            final CharStream input = CharStreams.fromString(expr);

            final ScriptLexer lexer = new ScriptLexer(input);
            lexer.removeErrorListeners();

            final ScriptParser parser = new ScriptParser(
                    new CommonTokenStream(lexer));
            parser.removeErrorListeners();

            return (ExpressionNode) visitor.visit(parser.expr());
        } catch (Exception e) {
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.COULD_NOT_READ,
                    TextPosition.N_A);
            displayErrors();
            return null;
        }
    }

    public StatementNode buildStatement(final String stat) {
        try {
            final CharStream input = CharStreams.fromString(stat);

            final ScriptLexer lexer = new ScriptLexer(input);
            lexer.removeErrorListeners();

            final ScriptParser parser = new ScriptParser(
                    new CommonTokenStream(lexer));
            parser.removeErrorListeners();

            return (StatementNode) visitor.visit(parser.stat());
        } catch (Exception e) {
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.COULD_NOT_READ,
                    TextPosition.N_A);
            displayErrors();
            return null;
        }
    }

    private <N extends ASTNode, R> N build(
            final String content,
            final Function<ScriptParser, R> contextGetter,
            final BiFunction<ScriptVisitor, R, N> fNode
    ) {
        try {
            final CharStream input = CharStreams.fromString(content);

            final ScriptLexer lexer = new ScriptLexer(input);
            lexer.removeErrorListeners();

            final ScriptParser parser = new ScriptParser(
                    new CommonTokenStream(lexer));
            parser.removeErrorListeners();

            return fNode.apply(visitor, contextGetter.apply(parser));
        } catch (Exception e) {
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.COULD_NOT_READ,
                    TextPosition.N_A);
            displayErrors();
            return null;
        }
    }

    public static boolean check(
            final ASTNode node,
            final SymbolTable scriptTable
    ) {
        try {
            node.semanticErrorCheck(scriptTable);
        } catch (Exception e) {
            errorHandling(false);
            return false;
        }

        return ScriptErrorLog.hasNoErrors();
    }

    private static Optional<Object> execute(
            final HeadFuncNode script,
            final SymbolTable scriptTable,
            final Object... args
    ) {
        try {
            TypeCompatibility.prepArgs(args);
            final Object result = script.execute(scriptTable, args);

            return result != null
                    ? Optional.of(result)
                    : Optional.empty();
        } catch (Exception e) {
            errorHandling(true);
            return Optional.empty();
        }
    }

    private static void errorHandling(
            final boolean runtime
    ) {
        if (ScriptErrorLog.hasNoErrors())
            ScriptErrorLog.fireError(runtime
                            ? ScriptErrorLog.Message.CUSTOM_RT
                            : ScriptErrorLog.Message.CUSTOM_CT,
                    TextPosition.N_A, "Unknown and unexpected");
    }

    protected void displayErrors() {
        final String[] errors = ScriptErrorLog.getErrors();

        for (String error : errors)
            System.out.println(error);
    }

    public static void overrideVisitor(final ScriptVisitor visitor) {
        Interpreter.visitor = visitor;
    }

    public static void overrideStdout(final Consumer<String> stdout) {
        Interpreter.stdout = stdout;
    }

    public static void overrideStdin(final Supplier<String> stdin) {
        Interpreter.stdin = stdin;
    }
}
