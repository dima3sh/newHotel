package org.azati.cources.enums;

public enum StatusRoom {
    CLEAN(0),
    NEEDSCLEANING(1),
    DIRTY(2);

    private Integer index;

    StatusRoom(Integer index) {
        this.index = index;
    }

    public Integer getIndex(){
        return index;
    }
}
