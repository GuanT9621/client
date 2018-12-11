package com.guan.sso.client.data;

public class SsoDataHolder {
    /**
     * ThreadLocal to hold the SsoData for Threads to access.
     */
    private static final ThreadLocal<SsoData> threadLocal = new ThreadLocal<SsoData>();

    /**
     * Retrieve the SsoData from the ThreadLocal.
     *
     * @return the SsoData associated with this thread.
     */
    public static SsoData getSsoData() {
        return threadLocal.get();
    }

    /**
     * Add the SsoData to the ThreadLocal.
     *
     * @param ssoData the assertion to add.
     */
    public static void setAssertion(final SsoData ssoData) {
        threadLocal.set(ssoData);
    }

    /**
     * Clear the ThreadLocal.
     */
    public static void clear() {
        threadLocal.set(null);
    }
}
