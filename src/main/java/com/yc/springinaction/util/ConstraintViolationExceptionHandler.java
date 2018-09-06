package com.yc.springinaction.util;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

public class ConstraintViolationExceptionHandler {

    public static String getMessage(ConstraintViolationException e){
        ArrayList<String> msgList = new ArrayList<>();
        for (ConstraintViolation constraintViolation : e.getConstraintViolations()){
            msgList.add(constraintViolation.getMessage());
        }
        String messages = StringUtils.join(msgList.toArray(), ";");
        return messages;
    }
}
