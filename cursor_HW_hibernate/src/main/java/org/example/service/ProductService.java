package org.example.service;

import org.example.dao.ProductDao;
import org.example.dmo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {

    @Autowired
    ProductDao productDao;

    public List<Product> getProductList(){
        return productDao.getProductList();
    }

    public Product getProductById(long id){
        return productDao.getProductById(id);
    }

    public void addProduct(Product product){
        productDao.save(product);
    }

    public void deleteProduct(long id){
        productDao.deleteProduct(id);
    }

    public void updateProduct(Product product){
        productDao.updateProduct(product);
    }

}
