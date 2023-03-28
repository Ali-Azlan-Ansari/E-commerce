package com.example.ecommerceonhtml.service;

import com.example.ecommerceonhtml.DTO.ProductCartDTO;
import com.example.ecommerceonhtml.DTO.ProductDTO;
import com.example.ecommerceonhtml.entity.Cart;
import com.example.ecommerceonhtml.entity.Category;
import com.example.ecommerceonhtml.entity.Product;
import com.example.ecommerceonhtml.entity.ProductCart;
import com.example.ecommerceonhtml.exception.RecordNotFoundException;
import com.example.ecommerceonhtml.repository.CartRepository;
import com.example.ecommerceonhtml.repository.ProductCartRepository;
import com.example.ecommerceonhtml.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class ProductCartService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ProductRepository product;
    @Autowired
    CartRepository cart;
    @Autowired
    ProductCartRepository productCartRepository;

    private ProductCart DtoToEntity(ProductCartDTO dto){
        return modelMapper.map(dto,ProductCart.class);
    }
    private ProductCartDTO EntityToDto(ProductCart entity){
        return modelMapper.map(entity,ProductCartDTO.class);
    }

    public ProductCartDTO save(ProductCartDTO productCartDTO, Long pid, Long cid) {
        Optional<Product> productIsFound=product.findById(pid);
        Optional<Cart> cartIsFound=cart.findById(cid);
        if(ObjectUtils.isEmpty(productIsFound)){
            throw new RecordNotFoundException(String.format("product is not exist on id=> %d",pid));
        }
        else if (ObjectUtils.isEmpty(cartIsFound)) {
            throw new RecordNotFoundException(String.format("cart is not exist on id=> %d",cid));
        }
        productCartDTO.setCart(cartIsFound.get());
        productCartDTO.setProduct(productIsFound.get());

        return EntityToDto(productCartRepository.save(DtoToEntity(productCartDTO)));

    }
}
