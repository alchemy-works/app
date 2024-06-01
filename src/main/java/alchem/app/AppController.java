package alchem.app;

import alchem.support.Zx;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AppController {

    @GetMapping("/api/hi")
    public ResponseEntity<?> hi() {
        return ResponseEntity.ok(List.of("AlchemApp", uname()));
    }

    @SneakyThrows
    private static String uname() {
        return Zx.$("uname -srm").get().trim();
    }
}
