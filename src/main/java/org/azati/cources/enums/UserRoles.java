package org.azati.cources.enums;

public enum UserRoles {
    administrator (1),
    chief(2),
    COOK(3),
    HOUSEHOLD(4),
    ACCOUNTANT(5),
    ASSISTANT(6),
    WORKER(7);

    private Integer index;

    UserRoles(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
