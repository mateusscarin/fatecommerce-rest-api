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

import br.com.fatecommerce.api.entity.Product;
import br.com.fatecommerce.api.service.ProductService;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin(value = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Object> getProducts() {
        List<Product> find = productService.findAll();
        if (find.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(find);
    }

    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody Product product) {
        productService.save(product);
        return ResponseEntity.created(null).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Product result = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
        HashMap<String, Object> result = productService.delete(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping()
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
        Product result = productService.save(product);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/ean/{ean}")
    public ResponseEntity<Object> getByEan(@PathVariable String ean) {
        Product result = productService.findByEan(ean);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/sku/{sku}")
    public ResponseEntity<Object> getBySku(@PathVariable String sku) {
        List<Product> result = productService.findBySku(sku);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
