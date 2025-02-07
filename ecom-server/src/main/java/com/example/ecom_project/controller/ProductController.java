package com.example.ecom_project.controller;

import com.example.ecom_project.model.Product;
import com.example.ecom_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.channels.ReadPendingException;
import java.util.List;

//@CrossOrigin
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService service;
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
         return new ResponseEntity<>(service.getProductById(id),HttpStatus.OK);
    }
    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try{
            Product product1= service.addProduct(product,imageFile);
            return new ResponseEntity<>(product1,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product=service.getProductById(productId);
        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(product.getImageData());
   }
   @PutMapping("/product/{productId}")
   public ResponseEntity<String> updateProduct(@PathVariable int productId,@RequestPart Product product,@RequestPart MultipartFile imageFile) throws IOException {
       Product product1=service.updateProduct(productId,product,imageFile);
       if(product1!=null){
           return new ResponseEntity<>("product updated",HttpStatus.OK);
       }
       else{
           return new ResponseEntity<>("failed to Update",HttpStatus.BAD_REQUEST);
       }
   }

}
