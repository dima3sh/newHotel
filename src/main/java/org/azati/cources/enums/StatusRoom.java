package org.azati.cources.enums;

public enum StatusRoom {
    CLEAN(1 , "чисто"),
    NEEDSCLEANING(2, "нужно убраться"),
    DIRTY(3, "грязно");

    private final Integer index;
    private final String message;

    StatusRoom(Integer index, String message) {
        this.index = index;
        this.message = message;
    }

    public Integer getIndex() {
        return index;
    }

    public String getMessage() {
        return message;
    }
}
