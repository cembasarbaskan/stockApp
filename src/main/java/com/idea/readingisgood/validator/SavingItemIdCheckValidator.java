package com.idea.readingisgood.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.idea.readingisgood.dto.BaseDTO;

public class SavingItemIdCheckValidator implements ConstraintValidator<SavingItemIdCheck, Object> {
    private String message;
    private Class propName;

    @Override
    public void initialize(SavingItemIdCheck constraintAnnotation) {
        message = constraintAnnotation.message();
        propName = constraintAnnotation.propName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof BaseDTO) {
            BaseDTO entityDto = (BaseDTO) value;
            if (entityDto.getId().trim().length() > 0) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message + propName.getName()).addConstraintViolation();
                return false;
            }
        }
        return true;
    }

}
