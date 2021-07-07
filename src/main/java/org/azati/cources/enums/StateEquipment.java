package org.azati.cources.enums;

public enum StateEquipment {
    NEW(0),
    SMALLDEFECT(1),
    BROKEN(2),
    HUGEDEFECT(3),
    NEEDSREPLACE(4);

    private Integer index;

    StateEquipment(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
