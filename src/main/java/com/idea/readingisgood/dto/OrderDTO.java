package com.idea.readingisgood.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.idea.readingisgood.domain.enums.EnumOrderStatus;

public class OrderDTO extends BaseDTO {

    @NotEmpty
    private String customerId;

    @NotNull
    private Set<OrderedBookDTO> orderedBooks;

    private Date createDate;

    private Date updateDate;

    private EnumOrderStatus enumOrderStatus;

    public OrderDTO() {
    }

    public OrderDTO(@NotEmpty String customerId, @NotEmpty Set<OrderedBookDTO> orderedBooks,
        Date createDate, Date updateDate, EnumOrderStatus enumOrderStatus) {
        this.customerId = customerId;
        this.orderedBooks = orderedBooks;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.enumOrderStatus = enumOrderStatus;
    }

    public OrderDTO(String id, @NotEmpty String customerId,
        @NotEmpty Set<OrderedBookDTO> orderedBooks, Date createDate, Date updateDate,
        EnumOrderStatus enumOrderStatus) {
        super(id);
        this.customerId = customerId;
        this.orderedBooks = orderedBooks;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.enumOrderStatus = enumOrderStatus;
    }

    public Set<OrderedBookDTO> getOrderedBooks() {
        return orderedBooks;
    }

    public void setOrderedBooks(Set<OrderedBookDTO> orderedBookDTOList) {
        this.orderedBooks = orderedBookDTOList;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
