package org.azati.cources.enums;

public enum StateEquipment {
    novel(0),
    smalldefect(1),
    broken(2),
    hugedefect(3),
    needsreplace(4);

    private Integer index;

    StateEquipment(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
