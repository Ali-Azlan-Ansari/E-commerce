package com.example.ecommerceonhtml.controllor;

import com.example.ecommerceonhtml.DTO.ProductCartDTO;
import com.example.ecommerceonhtml.DTO.ProductDTO;

import com.example.ecommerceonhtml.service.ProductCartService;
import com.example.ecommerceonhtml.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductCartController {
    @Autowired
    ProductCartService productCartService;

    @PostMapping("/product-cart/product/{pid}/cart/{cid}")
    public ResponseEntity<ProductCartDTO> saveUser(@PathVariable Long pid,@PathVariable Long cid, @RequestBody ProductCartDTO productCart){
        return ResponseEntity.ok(productCartService.save(productCart,pid,cid));
    }
//    @GetMapping("/product-cart")
//    public ResponseEntity<List<ProductCartDTO>> findAllUser(){
//        return ResponseEntity.ok(productCartService.findAll());
//    }
//    @GetMapping("/product-cart/{id}")
//    public ResponseEntity<ProductCartDTO> findUserById(@PathVariable Long id){
//        return ResponseEntity.ok(productCartService.findById(id));
//    }
//    @PutMapping("/product-cart/{id}")
//    public ResponseEntity<ProductCartDTO> updateUser(@PathVariable Long id,@RequestBody ProductCartDTO productCart){
//        return ResponseEntity.ok(productCartService.update(productCart,id));
//    }
//    @DeleteMapping("/product-cart/{id}")
//    public void DeleteByIdUser(@PathVariable Long id){
//        productCartService.delete(id);
//    }


}
