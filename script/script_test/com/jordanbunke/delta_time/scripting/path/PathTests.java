package com.jordanbunke.delta_time.scripting.path;

import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.PathHelper;
import com.jordanbunke.delta_time.scripting.util.TextPosition;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;
import java.util.Map;

public class PathTests {
    @Test
    public void pathsMatch() {
        // here
        final Path fakePath = Path.of("some", "script", "path", "script.ses");
        final SymbolTable table = tableForTest(fakePath);

        final Map<String, Path> expectedValuePairs = Map.ofEntries(
                Map.entry("./subdir/file.txt",
                        fakePath.getParent().resolve(
                                Path.of("subdir", "file.txt"))),
                Map.entry("../other/subdir/../file.txt",
                        fakePath.getParent().getParent().resolve(
                                Path.of("other", "file.txt"))),
                Map.entry("..\\..\\further\\out",
                        fakePath.getParent().getParent().getParent()
                                .resolve(Path.of("further", "out"))));

        for (String pathString : expectedValuePairs.keySet()) {
            Assert.assertEquals(
                    expectedValuePairs.get(pathString),
                    PathHelper.process(pathString, table, TextPosition.N_A)
            );
        }
    }

    private SymbolTable tableForTest(final Path path) {
        return new SymbolTable(null, null, path);
    }
}
