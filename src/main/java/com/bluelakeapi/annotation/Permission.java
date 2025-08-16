package com.bluelakeapi.annotation;

import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {

    /**
     * @return businessName 业务线
     */
    String businessName() default "";

    /**
     * @return action 具体某一权限
     */
    String action() default "";

    /**
     * @return the groups the constraint belongs to
     */
    Class<?>[] groups() default {};

    /**
     * @return the payload associated to the constraint
     */
    Class<? extends Payload>[] payload() default {};

}
