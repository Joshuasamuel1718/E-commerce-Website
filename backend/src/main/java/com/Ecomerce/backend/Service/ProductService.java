package com.Ecomerce.backend.Service;
import com.Ecomerce.backend.Model.Product;
import com.Ecomerce.backend.Repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    Repo repo;
    List<Product> products=new ArrayList<>();


    public List<Product> getProduct() {
        return  repo.findAll();
    }

    public Product getProductBYId(int id) {
       return  repo.findById(id).orElse(null);
    }


    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
       product.setImangeName(imageFile.getOriginalFilename());
       product.setImageType(imageFile.getContentType());
       product.setData(imageFile.getBytes());
       return  repo.save(product);
    }


    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        product.setImangeName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setData(imageFile.getBytes());
        return  repo.save(product);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<Product> searchByProduct(String keyword) {
        return repo.searchByProduct(keyword);
    }



}
