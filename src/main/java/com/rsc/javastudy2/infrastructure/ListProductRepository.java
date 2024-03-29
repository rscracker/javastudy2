package com.rsc.javastudy2.infrastructure;

import com.rsc.javastudy2.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
@Repository
public class ListProductRepository {
    private List<Product> products = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);
    // CopyOnWriteArrayList 멀티 스레드 환경 안정성
    public Product add(Product product){
        product.setId(sequence.getAndAdd(1L));
        products.add(product);
        return product;
    }

    public Product findById(Long id){
        return products.stream()
                .filter(product -> product.sameId(id))
                .findFirst()
                .orElseThrow();
    }

    public List<Product> findAll(){
        return products;
    }

    public List<Product> findByName(String name){
        return products.stream().filter(
                product -> product.containsName(name)
        ).toList();
    }

    public Product update(Product product){
        Integer indexToModify =  products.indexOf(product);
        products.set(indexToModify, product);
        return product;
    }

    public void delete(Long id){
        Product product = this.findById(id);
        products.remove(product);
    }
}
