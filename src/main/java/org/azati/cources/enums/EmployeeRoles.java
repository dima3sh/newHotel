package org.azati.cources.enums;

public enum EmployeeRoles {
    ADMINISTRATOR (1),
    CHIEF(2),
    COOK(3),
    HOUSEHOLD(4),
    ACCOUNTANT(5),
    ASSISTANT(6),
    WORKER(7);

    private Integer index;

    EmployeeRoles(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
