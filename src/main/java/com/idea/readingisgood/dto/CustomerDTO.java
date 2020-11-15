package com.idea.readingisgood.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerDTO extends BaseDTO{

    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    @Size(min = 2)
    private String lastName;

    @NotNull
    @Size(min = 7)
    private String email;

    @NotNull
    @Size(min = 10)
    private String address;

    @NotNull
    @Size(min = 10, max = 12)
    private String telephone;

    @NotNull
    @Size(min = 4, max = 4)
    private Integer yearOfBirth;

    public CustomerDTO() {
    }

    public CustomerDTO(@NotNull @Size(min = 2) String name, @NotNull @Size(min = 2) String lastName,
        @NotNull @Size(min = 7) String email, @NotNull @Size(min = 10) String address,
        @NotNull @Size(min = 10, max = 12) String telephone, @NotNull @Size(min = 4, max = 4) Integer yearOfBirth) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
        this.yearOfBirth = yearOfBirth;
    }

    public static CustomerDTO.CustomerDTOBuilder builder() {
        return new CustomerDTO.CustomerDTOBuilder();
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

    public static class CustomerDTOBuilder {
        private String name;
        private String lastName;
        private String email;
        private String address;
        private String telephone;
        private Integer yearOfBirth;

        CustomerDTOBuilder() {
        }

        public CustomerDTO.CustomerDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CustomerDTO.CustomerDTOBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CustomerDTO.CustomerDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CustomerDTO.CustomerDTOBuilder address(String address) {
            this.address = address;
            return this;
        }

        public CustomerDTO.CustomerDTOBuilder telephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public CustomerDTO.CustomerDTOBuilder yearOfBirth(Integer yearOfBirth) {
            this.yearOfBirth = yearOfBirth;
            return this;
        }

        public CustomerDTO build() {
            return new CustomerDTO(this.name, this.lastName, this.email, this.address, this.telephone,
                this.yearOfBirth);
        }
    }
}
