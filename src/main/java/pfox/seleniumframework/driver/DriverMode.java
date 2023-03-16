package pfox.seleniumframework.driver;

import javax.inject.Singleton;

@Singleton
public class DriverMode {

    private static boolean remote;

    public static boolean isRemote() {
        return remote;
    }

    public static void setRemote(boolean remote) {
        DriverMode.remote = remote;
    }
}
