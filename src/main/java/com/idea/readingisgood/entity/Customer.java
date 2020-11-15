package com.idea.readingisgood.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
public class Customer extends BaseEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "TELEPHONE", unique = true)
    private String telephone;

    @Column(name = "YEAR_OF_BIRTH")
    private Integer yearOfBirth;

    public Customer() {
    }

    public Customer(String name, String lastName, String email, String address, String telephone, Integer yearOfBirth) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.yearOfBirth = yearOfBirth;
    }

    public static Customer.CustomerBuilder builder() {
        return new Customer.CustomerBuilder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Customer)) {
            return false;
        }

        Customer other = (Customer) object;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public static class CustomerBuilder {
        private String name;
        private String lastName;
        private String email;
        private String address;
        private String telephone;
        private Integer yearOfBirth;

        CustomerBuilder() {
        }

        public CustomerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CustomerBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CustomerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder address(String address) {
            this.address = address;
            return this;
        }

        public CustomerBuilder telephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public CustomerBuilder yearOfBirth(Integer yearOfBirth) {
            this.yearOfBirth = yearOfBirth;
            return this;
        }

        public Customer build() {
            return new Customer(this.name, this.lastName, this.email, this.address, this.telephone, this.yearOfBirth);
        }
    }

}
