package com.idea.readingisgood.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.idea.readingisgood.domain.enums.EnumOrderStatus;

@Entity
@Table(name = "ORDER_TABLE")
public class Order extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderedBook> orderedBooks;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EnumOrderStatus status;

    public Order() {
    }

    public Order(Customer customer, List<OrderedBook> orderedBooks, EnumOrderStatus status) {
        this.customer = customer;
        this.orderedBooks = orderedBooks;
        this.status = status;
    }

    public Order(String id, Customer customer, List<OrderedBook> orderedBooks, EnumOrderStatus status) {
        super(id);
        this.customer = customer;
        this.orderedBooks = orderedBooks;
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderedBook> getOrderedBooks() {
        return orderedBooks;
    }

    public void setOrderedBooks(List<OrderedBook> orderedBooks) {
        this.orderedBooks = orderedBooks;
    }

    public EnumOrderStatus getStatus() {
        return status;
    }

    public void setStatus(EnumOrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Order)) {
            return false;
        }

        Order other = (Order) object;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
