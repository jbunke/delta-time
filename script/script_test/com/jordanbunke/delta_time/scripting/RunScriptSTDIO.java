package com.jordanbunke.delta_time.scripting;

import com.jordanbunke.delta_time.scripting.ast.nodes.function.HeadFuncNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.declaration.DeclarationNode;

import java.util.Scanner;

import static com.jordanbunke.delta_time.scripting.ScriptTestHelper.getScript;

public final class RunScriptSTDIO {
    public static void main(final String[] o) {
        final Scanner scanner = new Scanner(System.in);

        System.out.print("Script code: ");
        final String code = scanner.nextLine();

        final HeadFuncNode script = getScript(code);

        if (script == null) {
            System.out.println("Failed to import a script for the code \"" +
                    code + "\"");
            return;
        }

        final DeclarationNode[] params = script.signature.parameters.getParams();
        final int paramCount = params.length;
        final String[] args = new String[paramCount];

        for (int i = 0; i < paramCount; i++) {
            System.out.print(params[i].getIdent() + ": ");
            args[i] = scanner.nextLine().trim();
        }

        final Object res = Interpreter.get().run(script, (Object[]) args);
        System.out.println(res);
    }
}
