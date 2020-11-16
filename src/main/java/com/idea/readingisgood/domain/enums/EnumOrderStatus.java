package com.idea.readingisgood.domain.enums;

public enum EnumOrderStatus {
    PREPARING("prepared"), CANCELLED("cancelled"), SHIPPING("prepared"), SHIPPED("on_the_road"), REACHED("reached");

    private final String value;

    EnumOrderStatus(String value) {
        this.value = value;
    }
}
