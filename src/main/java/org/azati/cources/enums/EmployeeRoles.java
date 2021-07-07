package org.azati.cources.enums;

public enum EmployeeRoles {
    ADMINISTRATOR (0),
    CHIEF(1),
    COOK(2),
    HOUSEHOLD(3),
    ACCOUNTANT(4),
    ASSISTANT(5),
    WORKER(6);

    private Integer index;

    EmployeeRoles(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
