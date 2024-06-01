package alchem.support;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract class Zx {

    public static @NotNull CompletableFuture<String> $(@NotNull List<@NotNull String> command) {
        return run(command);
    }

    public static @NotNull CompletableFuture<String> $(@NotNull String @NotNull ... args) {
        return run(Arrays.stream(args).toList());
    }

    public static @NotNull CompletableFuture<String> $(@NotNull String s) {
        return run(Arrays.stream(s.split("\\s")).toList());
    }

    private static @NotNull CompletableFuture<String> run(@NotNull List<@NotNull String> command) {
        try {
            return new ProcessBuilder()
                    .command(command)
                    .redirectErrorStream(true)
                    .start()
                    .onExit()
                    .thenApply((p) -> {
                        try (var is = p.getInputStream()) {
                            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
                        } catch (IOException ex) {
                            throw new UncheckedIOException(ex);
                        }
                    });
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
