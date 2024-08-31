package br.com.fatecommerce.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fatecommerce.api.entity.Product;
import br.com.fatecommerce.api.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll(Sort.by(Direction.ASC, "name"));
    }

    public Product save(Product product) {
        return this.repository.saveAndFlush(product);
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado!"));
    }

    public HashMap<String, Object> delete(Long id) {
        Optional<Product> product = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado!")));

        repository.delete(product.get());
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "Produto: " + product.get().getName() + " excluído com sucesso!");
        return result;
    }

    public Product update(Product product) {
        if (product.getId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found Product!");
        }
        return this.repository.saveAndFlush(product);
    }

}
