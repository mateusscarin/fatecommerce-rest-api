package br.com.fatecommerce.api.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatecommerce.api.entity.Category;
import br.com.fatecommerce.api.service.CategoryService;

@RestController
@RequestMapping(value = "/category")
@CrossOrigin(value = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Object> getGategories() {
        List<Category> find = categoryService.findAll();
        if (find.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(find);
    }

    @PostMapping
    public ResponseEntity<Object> saveCategory(@RequestBody Category category) {
        categoryService.save(category);
        return ResponseEntity.created(null).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Category result = categoryService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") Long id) {
        HashMap<String, Object> result = categoryService.delete(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping()
    public ResponseEntity<Object> updateCategory(@RequestBody Category category) {
        Category result = categoryService.save(category);
        return ResponseEntity.ok(result);
    }

}
