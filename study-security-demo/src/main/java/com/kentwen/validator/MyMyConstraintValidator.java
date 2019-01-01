package com.kentwen.validator;

import com.kentwen.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by 毕文 on 2018/12/24.
 */
public class MyMyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {
    @Autowired
    HelloService helloService;

    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        helloService.greeting((String) o);
        System.out.println(o);
        return false;
    }
}
