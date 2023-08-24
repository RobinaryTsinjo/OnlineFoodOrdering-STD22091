package com.mealordering.mealordering.entity;

import java.util.Objects;

public class Customer {
    private Integer customerId;
    private String name;
    private String phoneNumber;
    private String address;


    public Customer(Integer customerId, String name, String phoneNumber, String address) {
        this.customerId = customerId;
        this.name = name;

        this.phoneNumber = phoneNumber;
        this.address = address;
    }


    public Customer() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomer(Customer customer) {
        this.name = customer.getName();
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String  toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(phoneNumber, customer.phoneNumber) &&
                Objects.equals(address, customer.address);
    }


    @Override
    public int hashCode() {
        return Objects.hash(customerId, name, phoneNumber, address);
    }


}
