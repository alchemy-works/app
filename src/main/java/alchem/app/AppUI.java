package alchem.app;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;

@Component
public class AppUI {

    private final Environment env;
    private final Resource icon;

    public AppUI(@NotNull Environment env,
                 @Value("classpath:icons/Jvav.png") Resource icon) {
        this.env = env;
        this.icon = icon;
    }

    private String getAppLink() {
        var port = this.env.getProperty("local.server.port");
        return "http://localhost:%s".formatted(port);
    }

    public void start() {
        SwingUtilities.invokeLater(() -> {
            if (SystemTray.isSupported()) {
                var systemTray = SystemTray.getSystemTray();
                var trayIcon = this.createTrayIcon();
                try {
                    systemTray.add(trayIcon);
                } catch (AWTException ex) {
                    throw new IllegalStateException(ex);
                }
                openLink(this.getAppLink());
            }
        });
    }

    private @NotNull TrayIcon createTrayIcon() {
        var popup = new PopupMenu();
        {
            popup.add(createMenu("Open AlchemApp", (ev) -> openLink(this.getAppLink())));
            popup.addSeparator();
            popup.add(createMenu("Quit", (ev) -> System.exit(0)));
        }
        var icon = new TrayIcon(this.getTrayIconImage(), "App", popup);
        icon.setImageAutoSize(true);
        return icon;
    }

    private @NotNull Image getTrayIconImage() {
        try {
            return Toolkit.getDefaultToolkit().createImage(this.icon.getContentAsByteArray());
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    private static @NotNull MenuItem createMenu(String label, ActionListener listener) {
        var menu = new MenuItem(label);
        menu.addActionListener(listener);
        return menu;
    }

    private static void openLink(@NotNull String link) {
        try {
            Desktop.getDesktop().browse(URI.create(link));
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
