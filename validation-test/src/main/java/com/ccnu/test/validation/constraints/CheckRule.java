package com.ccnu.test.validation.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckRule {
    Rule[] rules();

    /**
     *
     */
    public @interface Rule {

        String exp();

        String message();
    }

}