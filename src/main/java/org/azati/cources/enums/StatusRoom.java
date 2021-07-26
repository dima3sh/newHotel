package org.azati.cources.enums;

public enum StatusRoom {
    clean(1),
    needscleaning(2),
    dirty(3);

    private Integer index;

    StatusRoom(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
