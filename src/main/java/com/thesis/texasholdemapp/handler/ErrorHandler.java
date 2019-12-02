package com.thesis.texasholdemapp.handler;

import java.util.ArrayList;

public class ErrorHandler {
    ArrayList<String> errorList = new ArrayList<String>();
    public ArrayList<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(ArrayList<String> errorList) {
        this.errorList = errorList;
    }
}
