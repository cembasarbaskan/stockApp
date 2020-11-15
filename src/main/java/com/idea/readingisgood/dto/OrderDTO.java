package com.idea.readingisgood.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.idea.readingisgood.entity.Customer;
import com.idea.readingisgood.entity.enums.EnumOrderStatus;

public class OrderDTO extends BaseDTO {

    @NotNull
    private String customerId;

    private String code;

    @NotEmpty
    private List<OrderedBookDTO> orderedBooks;

    private Date createDate;

    private Date updateDate;

    private EnumOrderStatus enumOrderStatus;

    public OrderDTO() {
    }

    public OrderDTO(@NotNull String customerId, String code, @NotEmpty List<OrderedBookDTO> orderedBooks,
        Date createDate, Date updateDate, EnumOrderStatus enumOrderStatus) {
        this.customerId = customerId;
        this.code = code;
        this.orderedBooks = orderedBooks;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.enumOrderStatus = enumOrderStatus;
    }

    public OrderDTO(String id, @NotNull String customerId, String code, @NotEmpty List<OrderedBookDTO> orderedBooks,
        Date createDate, Date updateDate, EnumOrderStatus enumOrderStatus) {
        super(id);
        this.customerId = customerId;
        this.code = code;
        this.orderedBooks = orderedBooks;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
