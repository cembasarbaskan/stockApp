package com.idea.readingisgood.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.idea.readingisgood.dto.BaseDTO;
import com.idea.readingisgood.dto.OrderDTO;
import com.idea.readingisgood.domain.enums.EnumOrderStatus;

public class SavingItemIdCheckValidator implements ConstraintValidator<SavingItemIdCheck, Object> {
    private String messageForId;
    private String messageForStatus;
    private Class propName;

    @Override
    public void initialize(SavingItemIdCheck constraintAnnotation) {
        messageForId = constraintAnnotation.messageForId();
        messageForStatus = constraintAnnotation.messageForStatus();
        propName = constraintAnnotation.propName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof BaseDTO) {
            return checkForBaseDTO(value, context);
        }
        return true;
    }

    private boolean checkForBaseDTO(Object value, ConstraintValidatorContext context) {
        if (checkForId(value, context)) {
            return checkForOrderStatus(value, context);
        }
        return false;
    }

    private boolean checkForId(Object value, ConstraintValidatorContext context) {
        BaseDTO entityDto = (BaseDTO) value;
        if (entityDto.getId().trim().length() > 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(messageForId + propName.getName()).addConstraintViolation();
            return false;
        }
        return true;
    }

    private boolean checkForOrderStatus(Object orderDTO, ConstraintValidatorContext context) {
        OrderDTO temp = (OrderDTO) orderDTO;
        if (!temp.getEnumOrderStatus().equals(EnumOrderStatus.PREPARING)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                messageForStatus + propName.getName() + EnumOrderStatus.PREPARING).addConstraintViolation();
            return false;
        }
        return true;
    }

}
