package com.idea.readingisgood.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.idea.readingisgood.entity.Customer;
import com.idea.readingisgood.entity.enums.EnumOrderStatus;

public class OrderDTO extends BaseDTO {

    @NotNull
    private Customer customer;

    @NotEmpty
    private List<OrderedBookDTO> orderedBooks;

    private Date createDate;

    private Date updateDate;

    private EnumOrderStatus enumOrderStatus;

    public OrderDTO(String id, @NotNull Customer customer, @NotEmpty List<OrderedBookDTO> orderedBookDTOList,
        Date createDate, Date updateDate, EnumOrderStatus enumOrderStatus) {
        super(id);
        this.customer = customer;
        this.orderedBooks = orderedBookDTOList;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.enumOrderStatus = enumOrderStatus;
    }

    public List<OrderedBookDTO> getOrderedBooks() {
        return orderedBooks;
    }

    public void setOrderedBooks(List<OrderedBookDTO> orderedBookDTOList) {
        this.orderedBooks = orderedBookDTOList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public EnumOrderStatus getEnumOrderStatus() {
        return enumOrderStatus;
    }

    public void setEnumOrderStatus(EnumOrderStatus enumOrderStatus) {
        this.enumOrderStatus = enumOrderStatus;
    }

}
