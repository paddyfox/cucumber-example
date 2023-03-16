package pfox.seleniumframework.environment;

import io.restassured.specification.RequestSpecification;

import pfox.seleniumframework.log.Log;

import static pfox.seleniumframework.environment.Environment.executingWithLegacyUsers;

public class Proxy {

    private static final String ProxyHost1 = "proxy.local";
    private static final int ProxyPort1 = 8080;

    private static final String ProxyHost2 = "proxy-l";
    private static final int ProxyPort2 = 1234;

    /**
     * Set the request proxy for SOAP calls, method depends on execution environment, CI or local.
     *
     * @param request Rest Assured request object.
     * @return modified request object.
     */
    public static RequestSpecification setProxy(RequestSpecification request) {
        if (executingWithLegacyUsers()) {
            Log.Info("Executing in legacy Proxy");
            request.proxy(ProxyHost2, ProxyPort2);
        } else {
            Log.Info("Executing in new Proxy");
            request.proxy(ProxyHost1, ProxyPort1);
        }
        return request;
    }

    public static String getProxyNameAndPort() {
        if (executingWithLegacyUsers()) {
            return (ProxyHost2 + ":" + ProxyPort2);
        } else {
            return (ProxyHost1 + ":" + ProxyPort1);
        }
    }

    public static String getProxyName() {
        if (executingWithLegacyUsers()) {
            return getProxyHost2();
        }
        else {
            return getEBSAProxy();
        }
    }

    public static String getProxyPort() {
        if (executingWithLegacyUsers()) {
            return getProxyPort2();
        }
        else {
            return getProxyPort1();
        }
    }

    private static String getEBSAProxy() {
        return ProxyHost1;
    }

    private static String getProxyPort1() {
        return String.valueOf(ProxyPort1);
    }

    private static String getProxyHost2() {
        return ProxyHost2;
    }

    private static String getProxyPort2() {
        return String.valueOf(ProxyPort2);
    }
}
