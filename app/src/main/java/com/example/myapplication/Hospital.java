package com.example.myapplication;

public class Hospital {
    public String hosName,docName,spec;
    Hospital(String _docName,String _hosName, String _spec){
        hosName = _hosName;
        docName = _docName;
        spec = _spec;
    }
    Hospital(){
        this("","","");
    }
}
