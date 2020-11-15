package com.idea.readingisgood.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { SavingItemIdCheckValidator.class })
public @interface SavingItemIdCheck {
    Class propName();
    String messageForId() default "User can not define an id to a new ";
    String messageForStatus() default "Default status for new created them is ";
}
