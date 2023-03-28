package com.example.ecommerceonhtml.controllor;

import com.example.ecommerceonhtml.DTO.ModelDTO;
import com.example.ecommerceonhtml.DTO.ProductDTO;
import com.example.ecommerceonhtml.entity.Product;
import com.example.ecommerceonhtml.exception.InvalidDataException;
import com.example.ecommerceonhtml.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;




    @PostMapping("/product/category/{cid}")
    public ResponseEntity<ProductDTO> saveProduct(@PathVariable Long cid, @RequestParam("data") String productData,@RequestParam("image") MultipartFile image){
        ObjectMapper mapper = new ObjectMapper();
        try {
            ProductDTO productDTO = mapper.readValue(productData,ProductDTO.class);
              return ResponseEntity.ok(productService.save(cid,productDTO,image));
        } catch (JsonProcessingException e) {
         throw new InvalidDataException("Invalid product Data");
        }

    }
    @PutMapping("product/{pid}/category/{cid}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long pid,@PathVariable Long cid, @RequestParam("data") String productData,@RequestParam("image") MultipartFile image){
        ObjectMapper mapper = new ObjectMapper();
        try {
            ProductDTO productDTO = mapper.readValue(productData,ProductDTO.class);
            return ResponseEntity.ok(productService.update(pid,cid,productDTO,image));
        } catch (JsonProcessingException e) {
            throw new InvalidDataException("Invalid product Data");
        }

    }
    @GetMapping("/product")
    public ResponseEntity<List<ProductDTO>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }
    @DeleteMapping("/product/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getById(id));
    }
    @PatchMapping("/product/set-active-product/{id}")
    public ResponseEntity<ProductDTO> setActive(@PathVariable Long id){
        return ResponseEntity.ok(productService.setActive(id));
    }
    @PatchMapping("/product/set-deactivate-product/{id}")
    public ResponseEntity<ProductDTO> setDeactivate(@PathVariable Long id){
        return ResponseEntity.ok(productService.setDeactivate(id));
    }
    @GetMapping(value = "/product/image/{fileName}",produces = MediaType.IMAGE_JPEG_VALUE )
    public void downloadImage(
            @PathVariable("fileName") String fileName,
            HttpServletResponse response
    ) throws IOException {

        InputStream resource=this.productService.getFile(fileName);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
