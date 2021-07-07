package org.azati.cources.entity;

import org.azati.cources.enums.EmployeeRoles;

public class Employee extends Person {
    private Integer employee_id;
    private EmployeeRoles employeeRoles;

    public Employee(){}

    public Employee(String name, String phoneNumber, String emailAddress, Integer employee_id, EmployeeRoles employeeRoles) {
        super(name, phoneNumber, emailAddress);
        this.employee_id = employee_id;
        this.employeeRoles = employeeRoles;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public EmployeeRoles getEmployeeRoles() {
        return employeeRoles;
    }

    public void setEmployeeRoles(EmployeeRoles employeeRoles) {
        this.employeeRoles = employeeRoles;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", employeeRoles=" + employeeRoles +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return employee_id.equals(employee.employee_id) && employeeRoles.equals(employee.employeeRoles) && super.equals(o);
    }

    @Override
    public  int hashCode() {
        int result = Integer.hashCode(employee_id);
        result = 31 * result + employeeRoles.hashCode();
        result = 31 * result + super.hashCode();
        return result;
    }
}
