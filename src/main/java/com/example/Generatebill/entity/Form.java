package com.example.Generatebill.entity;

import lombok.Data;

import java.util.List;
@Data
public class Form {

    private String buyerAddress;
    private String seller;
    private String sellerGstin;
    private String sellerAddress;
    private String buyer;
    private String buyerGstin;
    private List<Item> items;


}



