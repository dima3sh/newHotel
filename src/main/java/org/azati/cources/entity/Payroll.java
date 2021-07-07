package org.azati.cources.entity;

public class Payroll {
    private Integer payroll_id;
    private Integer salary;
    private String bankAccount;

    public Payroll(){}

    public Payroll(Integer salary, String bankAccount, Integer payroll_id) {
        this.salary = salary;
        this.bankAccount = bankAccount;
        this.payroll_id = payroll_id;
    }

    public Integer getPayroll_id() {
        return payroll_id;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setPayroll_id(Integer payroll_id) {
        this.payroll_id = payroll_id;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "Payroll{" +
                "payroll_id=" + payroll_id +
                "salary=" + salary +
                ", bankAccount='" + bankAccount + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payroll)) return false;
        Payroll payroll = (Payroll) o;
        return payroll_id.equals(payroll.payroll_id) && salary.equals(payroll.salary) && bankAccount.equals(payroll.bankAccount);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(payroll_id);
        result = 31 * result + Integer.hashCode(salary);
        result = 31 * result + bankAccount.hashCode();
        return result;
    }
}
