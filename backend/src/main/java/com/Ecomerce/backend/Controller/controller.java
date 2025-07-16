package com.Ecomerce.backend.Controller;
import com.Ecomerce.backend.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import com.Ecomerce.backend.Model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class controller {

    @Autowired
    public ProductService service;
    @GetMapping("/products")
    public List<Product> getProducts() {
        return service.getProduct();
    }
    @GetMapping("/product/{id}")
    public ResponseEntity< Product> getproductbyId(@PathVariable int id)
    {
        Product product=service.getProductBYId(id);
        if(product!=null)
        {
            return  new ResponseEntity<>(product, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/product")
    public ResponseEntity<?> addProducgt(@RequestPart Product product, @RequestPart MultipartFile imageFile)
    {try {
        Product product1=service.addProduct(product,imageFile);
        return new ResponseEntity<>(product,HttpStatus.CREATED);
    }catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable int id)
    {
          Product product=service.getProductBYId(id);
          byte[] image=product.getData();
          return ResponseEntity
                  .ok()
                  .contentType(MediaType.valueOf(product.getImageType()))
                  .body(image);
    }
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProdeucts(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile) throws IOException {
    try
        {
            Product product1 = service.updateProduct(id, product, imageFile);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
      return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id)
    {try {
        service.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    catch (Exception e)
    {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    }
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchByProduct(@RequestParam String keyword) {
        List<Product> product = service.searchByProduct(keyword);
        return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
    }












}
