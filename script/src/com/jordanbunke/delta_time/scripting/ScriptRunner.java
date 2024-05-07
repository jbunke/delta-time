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

public class ScriptRunner {
    private static ScriptVisitor visitor = new ScriptVisitor();

    public static ScriptRunner get() {
        return new ScriptRunner();
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

    private boolean check(
            final HeadFuncNode script,
            final SymbolTable scriptTable
    ) {
        try {
            script.semanticErrorCheck(scriptTable);
        } catch (Exception e) {
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.CUSTOM_CT,
                    TextPosition.N_A, "Unknown and unexpected");
            return false;
        }

        return ScriptErrorLog.hasNoErrors();
    }

    private Optional<Object> execute(
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
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.CUSTOM_RT,
                    TextPosition.N_A, "Unknown and unexpected");
            return Optional.empty();
        }
    }

    protected void displayErrors() {
        final String[] errors = ScriptErrorLog.getErrors();

        for (String error : errors)
            System.out.println(error);
    }

    public static void overrideVisitor(final ScriptVisitor visitor) {
        ScriptRunner.visitor = visitor;
    }
}
