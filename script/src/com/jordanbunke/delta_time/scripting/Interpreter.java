package com.jordanbunke.delta_time.scripting;

import com.jordanbunke.delta_time.scripting.ast.nodes.function.HeadFuncNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.ScriptVisitor;
import com.jordanbunke.delta_time.scripting.util.TextPosition;
import com.jordanbunke.delta_time.scripting.util.TypeCompatibility;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
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
            final HeadFuncNode script, final Object... args
    ) {
        ScriptErrorLog.clearErrors();

        final SymbolTable scriptTable = SymbolTable.root(script);

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

    private static boolean check(
            final HeadFuncNode script,
            final SymbolTable scriptTable
    ) {
        try {
            script.semanticErrorCheck(scriptTable);
        } catch (Exception e) {
            errorHandling(false);
            return false;
        }

        return ScriptErrorLog.hasNoErrors();
    }

    private static Optional<Object> execute(
            final HeadFuncNode script,
            final SymbolTable scriptTable,
            final Object[] args
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
