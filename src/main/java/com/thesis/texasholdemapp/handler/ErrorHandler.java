package com.thesis.texasholdemapp.handler;

import java.util.ArrayList;

public class ErrorHandler {
    ArrayList<String> errorList = new ArrayList<>();

    public ErrorHandler() {
        errorList.clear();
    }

    public ArrayList<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(ArrayList<String> errorList) {
        this.errorList = errorList;
    }

    public void addError(String error) {
        errorList.add(error);
    }
}
