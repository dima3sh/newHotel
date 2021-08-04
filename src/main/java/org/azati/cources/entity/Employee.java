package org.azati.cources.entity;

import org.azati.cources.enums.UserRoles;

public class Employee extends Person {
    private Integer employee_id;
    private UserRoles userRoles;

    public Employee() {
    }

    public Employee(String name, String phoneNumber, String emailAddress, Integer employee_id, UserRoles userRoles) {
        super(name, phoneNumber, emailAddress);
        this.employee_id = employee_id;
        this.userRoles = userRoles;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public UserRoles getEmployeeRoles() {
        return userRoles;
    }

    public void setEmployeeRoles(UserRoles userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", employeeRoles=" + userRoles +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return employee_id.equals(employee.employee_id) && userRoles.equals(employee.userRoles) && super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(employee_id);
        result = 31 * result + userRoles.hashCode();
        result = 31 * result + super.hashCode();
        return result;
    }
}
