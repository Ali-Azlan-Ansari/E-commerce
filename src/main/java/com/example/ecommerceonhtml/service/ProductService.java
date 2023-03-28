package com.example.ecommerceonhtml.service;

//import com.example.ecommerceonhtml.DTO.CategoryDTO;
import com.example.ecommerceonhtml.DTO.CategoryDTO;
import com.example.ecommerceonhtml.DTO.ModelDTO;
import com.example.ecommerceonhtml.DTO.ProductDTO;
//import com.example.ecommerceonhtml.entity.Category;
import com.example.ecommerceonhtml.entity.Category;
//import com.example.ecommerceonhtml.entity.Model;
import com.example.ecommerceonhtml.entity.Product;
import com.example.ecommerceonhtml.exception.InvalidDataException;
import com.example.ecommerceonhtml.exception.RecordNotFoundException;
import com.example.ecommerceonhtml.repository.CategoryRepository;
//import com.example.ecommerceonhtml.repository.ModelRepository;
import com.example.ecommerceonhtml.repository.ProductRepository;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {





    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
//    @Autowired
//    ModelRepository modelRepository;
    @Value("${project.image}")
    private String path;

    private Product DtoToEntity(ProductDTO dto){
        return modelMapper.map(dto,Product.class);
    }
    private ProductDTO EntityToDto(Product entity){
        return modelMapper.map(entity,ProductDTO.class);
    }




    public String uploadImage(MultipartFile file) {


      if(
              file.getContentType().equals("image/png")||
              file.getContentType().equals("image/svg")||
              file.getContentType().equals("image/jpg")||
              file.getContentType().equals("image/jpeg")||
              file.getContentType().equals("image/jfif")||
              file.getContentType().equals("image/pjpeg")||
              file.getContentType().equals("image/pjp")

        )
      {
          String fileName=file.getOriginalFilename();
          String randomID= UUID.randomUUID().toString();
          String randomFileName=randomID.concat(fileName.substring(fileName.lastIndexOf(".")));
          String fullPath=path.concat(File.separator.concat(randomFileName));
          File file1=new File(path);

          if(!file1.exists()){
              file1.mkdir();
          }
          try {
              Files.copy(file.getInputStream(), Paths.get(fullPath));
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
          return randomFileName;

    }
        throw new InvalidDataException("file type is not image");
    }


    public InputStream getFile(String FileName) throws FileNotFoundException {
        String fullPath=path.concat(File.separator.concat(FileName));
        InputStream is=new FileInputStream(fullPath);
        return is;
    }

    public ProductDTO save(Long cid, ProductDTO productDTO, MultipartFile image) {
        Optional<Category> categoryIsFound=categoryRepository.findById(cid);

        String imageName=uploadImage(image);
       if(ObjectUtils.isEmpty(categoryIsFound)){
           throw new RecordNotFoundException(String.format("Category is not exist on id=> %d",cid));
       }

       else {
           productDTO.setCategory(categoryIsFound.get());
           productDTO.setImage(imageName);
           return EntityToDto(productRepository.save(DtoToEntity(productDTO)));
       }
    }

    public List<ProductDTO> findAll(){
        List<Product> products= productRepository.findAll();
        List<ProductDTO> productDTOS=new ArrayList<>();
        products.stream().forEach(entity-> productDTOS.add(this.EntityToDto(entity)));
        return productDTOS;
    }

    public void deleteById(Long id) {
        Optional<Product> isFound=productRepository.findById(id);
        if(!ObjectUtils.isEmpty(isFound)) {
            productRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException(String.format("Product not found on => %d",id));
        }

    }

    public ProductDTO update(Long pid, Long cid, ProductDTO productDTO, MultipartFile image) {
        Optional<Product> productIsFound=productRepository.findById(pid);
        Optional<Category> categoryIsFound=categoryRepository.findById(cid);

        String imageName=uploadImage(image);
        if(ObjectUtils.isEmpty(productIsFound)){
            throw new RecordNotFoundException(String.format("Product is not exist on id=> %d",pid));
        }
        if(ObjectUtils.isEmpty(categoryIsFound)){
            throw new RecordNotFoundException(String.format("Category is not exist on id=> %d",cid));
        }

        else {

            productIsFound.get().setImage(imageName);
            productIsFound.get().setName(productDTO.getName());
            productIsFound.get().setCost(productDTO.getCost());
            productIsFound.get().setPrice(productDTO.getPrice());
            productIsFound.get().setStock(productDTO.getStock());
            productIsFound.get().setIsActive(true);

            productIsFound.get().setCategory(categoryIsFound.get());


            return EntityToDto(productRepository.save(productIsFound.get()));
        }
    }

    public ProductDTO getById(Long id){
        Optional<Product> isFound=productRepository.findById(id);
        if(!ObjectUtils.isEmpty(isFound)) {
            return this.EntityToDto(isFound.get());
        }
        throw new RecordNotFoundException(String.format("Product not found on => %d",id));
    }

    public ProductDTO setActive(Long id) {
        Optional<Product> isFound=productRepository.findById(id);
        if(!ObjectUtils.isEmpty(isFound)) {
            isFound.get().setIsActive(true);
            return this.EntityToDto(productRepository.save(isFound.get()));
        }
        throw new RecordNotFoundException(String.format("Product not found on => %d",id));
    }

    public ProductDTO setDeactivate(Long id) {
        Optional<Product> isFound=productRepository.findById(id);
        if(!ObjectUtils.isEmpty(isFound)) {
            isFound.get().setIsActive(false);
            return this.EntityToDto(productRepository.save(isFound.get()));
        }
        throw new RecordNotFoundException(String.format("Product not found on => %d",id));
    }
}
