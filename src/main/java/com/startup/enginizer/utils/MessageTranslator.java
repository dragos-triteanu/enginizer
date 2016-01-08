package com.startup.enginizer.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 11/18/2015.
 */
@Component
public class MessageTranslator {

    @Autowired
    private MessageSource messageSource;


    public String getLocalizedMessage(final String messageKey , final Object[] args){
        return messageSource.getMessage(messageKey, args, LocaleContextHolder.getLocale());
    }

    public List<String> getErrors(final BindingResult bindingResult){
        List<String> errors = new ArrayList<>();
        for(FieldError error : bindingResult.getFieldErrors()){
            Object[] arguments = error.getArguments();
            if(arguments.length > 1){
                errors.add(getLocalizedMessage(error.getDefaultMessage(),arguments));
            }else{
                errors.add(getLocalizedMessage(error.getDefaultMessage(), new Object[]{error.getField()}));
            }
        }
        return errors;
    }
}
