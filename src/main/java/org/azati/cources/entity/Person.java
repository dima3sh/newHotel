package org.azati.cources.entity;

public class Person {
    protected String name;
    protected String phoneNumber;
    protected String emailAddress;

    public Person(){}

    public Person(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return name.equals(person.name) && phoneNumber.equals(person.phoneNumber) && emailAddress.equals(person.emailAddress);
    }

    @Override
    public  int hashCode(){
        int result = name.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + emailAddress.hashCode();
        return result;
    }
}
