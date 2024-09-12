package br.com.fatecommerce.api.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        return this.repository.saveAndFlush(product);
    }

    public Product findByEan(String ean) {
        return this.repository.findByEan(ean).orElseThrow(() -> new NoSuchElementException(
                String.format("Produto não econtrado para o ean informado (%s)", ean)));
    }

    public List<Product> findBySku(String sku) {
        List<Product> find = this.repository.findBySku(sku);
        if (find.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Produtos não econtrado para o sku informado (%s)", sku));
        return find;
    }

    public List<Product> findByIgnoreCaseSkuContainingOrderByNameAsc(String sku) {
        List<Product> find = this.repository.findByIgnoreCaseSkuContainingOrderByNameAsc(sku);
        if (find.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Produtos não econtrado para o sku informado (%s)", sku));
        return find;
    }

    public List<Product> findByPublishedIsTrueOrderByDateCreatedDesc() {
        List<Product> find = this.repository.findByPublishedIsTrueOrderByDateCreatedDesc();
        if (find.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produtos publicados não econtrados");
        return find;
    }

    public List<Product> findByDateCreated(LocalDate dateReference) {
        List<Product> find = this.repository.findByDateCreated(dateReference);
        if (find.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Produtos não econtrado para a data de referência informada (%s)", dateReference));
        return find;
    }

}
