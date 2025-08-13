package com.bluelakeapi.annotation.impl;

import com.bluelakeapi.annotation.RegexValid;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class RegexValidator implements ConstraintValidator<RegexValid, String> {

    private RegexValid annotation;

    @Override
    public void initialize(RegexValid constraintAnnotation) {
        annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        String regex = annotation.regex();
        //value有值，才进行校验
        if (!StringUtils.isEmpty(value)) {
            return Pattern.matches(regex, value);
        }
        return true;
    }

}