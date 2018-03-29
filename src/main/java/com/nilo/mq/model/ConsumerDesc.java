package com.nilo.mq.model;

/**
 * Created by admin on 2017/11/18.
 */

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConsumerDesc {

    String group();

    String topic();

    String filterExpression();

    boolean consumeOrderly() default false;


}
