package alchem.app;

public abstract class AppEnv {

    public static final String APP_TYPE = "APP";
    public static final String UI = "UI";
    public static final String CLI = "CLI";

    public static boolean isUI() {
        return UI.equals(getAppType());
    }

    public static String getAppType() {
        var app = System.getenv(APP_TYPE);
        if (app == null) {
            app = System.getProperty(APP_TYPE);
        }
        if (app == null) {
            app = "Unknown";
        }
        return app;
    }
}
