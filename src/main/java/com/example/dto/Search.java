package com.example.dto;

import lombok.Data;

@Data
public class Search {
     
    final String[] typeCode = {"phone","name","address","type"}; //value
    final String[] typeName = {"연락처","상호명","주소","종류"}; //text
    //entity로 만들면 안됨 database가 없어서


    private int page=1;
    private String type="phone";
    private String text="";
}
