package com.jordanbunke.delta_time.scripting.ast.nodes;

import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public interface IllegalLanguageFeature {
    default void fireError() {
        ScriptErrorLog.fireError(ScriptErrorLog.Message.CUSTOM_CT,
                getPosition(), getError());
    }

    TextPosition getPosition();

    default String getError() {
        return "Illegal language feature: " + getDescription();
    }

    String getDescription();
}
