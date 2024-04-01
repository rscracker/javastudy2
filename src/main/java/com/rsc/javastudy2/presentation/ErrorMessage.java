package com.rsc.javastudy2.presentation;

import java.util.List;
public class ErrorMessage {

    private  List<String> errors;

    public ErrorMessage(List<String> erros){
        this.errors = erros;
    }

    public List<String> getErrors(){
        return errors;
    }
}
