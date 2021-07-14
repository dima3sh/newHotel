package org.azati.cources.enums;

public enum StatusRoom {
    clean("clean"),
    needscleanig("needscleaning"),
    dirty("dirty");

    private String code;

    private StatusRoom(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
