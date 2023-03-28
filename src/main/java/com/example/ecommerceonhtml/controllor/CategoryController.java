package com.example.ecommerceonhtml.controllor;

import com.example.ecommerceonhtml.DTO.CategoryDTO;
import com.example.ecommerceonhtml.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("/category")
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.save(categoryDTO));
    }
    @GetMapping("/category")
    public ResponseEntity<List<CategoryDTO>> findAllCategory(){
        return ResponseEntity.ok(categoryService.findAll());
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findById(id));
    }
    @PutMapping("/category/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id,@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.update(categoryDTO,id));
    }
    @DeleteMapping("/category/{id}")
    public void DeleteByIdCategory(@PathVariable Long id){

        categoryService.delete(id);
    }

    @PatchMapping("/category/set-active-category/{id}")
    public ResponseEntity<CategoryDTO> setActive(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.setActive(id));
    }
    @PatchMapping("/category/set-deactivate-category/{id}")
    public ResponseEntity<CategoryDTO> setDeactivate(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.setDeactivate(id));
    }

}
