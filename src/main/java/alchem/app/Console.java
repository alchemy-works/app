package alchem.app;

import org.jetbrains.annotations.NotNull;

public abstract class Console {

    public static void log(@NotNull Object o) {
        System.out.println(o);
    }

    public static void error(@NotNull Object o) {
        System.err.println(o);
    }
}
