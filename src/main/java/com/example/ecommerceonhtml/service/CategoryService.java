package com.example.ecommerceonhtml.service;

import com.example.ecommerceonhtml.DTO.CategoryDTO;
import com.example.ecommerceonhtml.entity.Category;
//import com.example.ecommerceonhtml.entity.Model;
import com.example.ecommerceonhtml.exception.DuplicateRecordException;
import com.example.ecommerceonhtml.exception.RecordNotFoundException;
import com.example.ecommerceonhtml.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ModelMapper modelMapper;
    private Category DtoToEntity(CategoryDTO dto){
        return modelMapper.map(dto,Category.class);
    }
    private CategoryDTO EntityToDto(Category entity){
        return modelMapper.map(entity,CategoryDTO.class);
    }




    public CategoryDTO save(CategoryDTO categoryDTO) {

        Category isFound=categoryRepository.findByCategoryName(this.DtoToEntity(categoryDTO).getCategoryName());

        if(ObjectUtils.isEmpty(isFound))
        {
            categoryDTO.setIsActive(true);
            return this.EntityToDto(categoryRepository.save(this.DtoToEntity(categoryDTO)));
        }
       throw new DuplicateRecordException(String.format("This record is already exist on => %d",isFound.getId()));
    }

    public List<CategoryDTO> findAll() {
        List<Category> categories= categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS=new ArrayList<>();
        categories.stream().forEach(entity-> categoryDTOS.add(this.EntityToDto(entity)));
        return categoryDTOS;
    }

    public void delete(Long id) {
        Optional<Category> isFound=categoryRepository.findById(id);
        if(!ObjectUtils.isEmpty(isFound)) {
            categoryRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException(String.format("Category not found on => %d",id));
        }
    }

    public CategoryDTO update(CategoryDTO categoryDTO,Long id) {
        Category isFound=categoryRepository.findByCategoryName(this.DtoToEntity(categoryDTO).getCategoryName());
        Optional<Category> forUpdate=categoryRepository.findById(id);
        if(!(ObjectUtils.isEmpty(isFound)))
        {
            throw new DuplicateRecordException(String.format("This record is already exist on => %d",isFound.getId()));
        }
        else if(ObjectUtils.isEmpty(forUpdate))
        {
            throw new RecordNotFoundException(String.format("Category not found on => %d",id));

        }
        else {
            forUpdate.get().setCategoryName(categoryDTO.getCategoryName());
            return this.EntityToDto(categoryRepository.save(forUpdate.get()));
        }
    }

    public CategoryDTO setActive(Long id) {
       Optional<Category> isFound=categoryRepository.findById(id);
       if(!ObjectUtils.isEmpty(isFound)) {
           isFound.get().setIsActive(true);
           return this.EntityToDto(categoryRepository.save(isFound.get()));
       }
       throw new RecordNotFoundException(String.format("Category not found on => %d",id));
    }

    public CategoryDTO setDeactivate(Long id) {
        Optional<Category> isFound=categoryRepository.findById(id);
        if(!ObjectUtils.isEmpty(isFound)) {
            isFound.get().setIsActive(false);
            return this.EntityToDto(categoryRepository.save(isFound.get()));
        }
        throw new RecordNotFoundException(String.format("Category not found on => %d",id));
    }

    public CategoryDTO findById(Long id) {
        Optional<Category> isFound=categoryRepository.findById(id);
        if(!ObjectUtils.isEmpty(isFound)) {
           return this.EntityToDto(isFound.get());
        }
        throw new RecordNotFoundException(String.format("Category not found on => %d",id));
    }
}
