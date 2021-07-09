package org.azati.cources.enums;

public enum StatusRoom {
    clean(0),
    needscleaning(1),
    dirty(2);

    private Integer index;

    StatusRoom(Integer index) {
        this.index = index;
    }

    public Integer getIndex(){
        return index;
    }
}
