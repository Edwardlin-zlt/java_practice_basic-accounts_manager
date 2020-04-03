package com.thoughtworks.exceptions;

public class FieldIllegalException extends Exception{
    private final String fieldName;

    public FieldIllegalException(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName(){
        return fieldName;
    }

    @Override
    public String getMessage(){
        return String.format("%s不合法", fieldName);
    }

}
