package com.guan.sso.client.data;

/**
 * Save the Sso Result
 */
public class SsoData {

    private boolean valid;

    private String id;

    public SsoData(boolean valid, String id) {
        this.valid = valid;
        this.id = id;
    }

    public boolean isValid() {
        return valid;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SsoData{" +
                "valid=" + valid +
                ", id='" + id + '\'' +
                '}';
    }

}
