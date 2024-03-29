package com.rsc.javastudy2.domain;

import com.rsc.javastudy2.presentation.ProductDto;

import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private Integer price;
    private Integer amount;

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    public Boolean sameId(Long id){
        return this.id.equals(id);
    }

    public Boolean containsName(String name){
        return this.name.contains(name);
    }

    public void setId(Long id){
        this.id = id;
    }

}
