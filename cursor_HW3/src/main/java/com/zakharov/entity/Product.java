package com.zakharov.entity;


import lombok.Data;

@Data
public class Product {

    private int idProduct;
    private String name;
    private double price;
    private int count;
    private String type;

}
