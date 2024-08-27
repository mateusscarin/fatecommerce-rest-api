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

import br.com.fatecommerce.api.entity.Category;
import br.com.fatecommerce.api.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll(Sort.by(Direction.ASC, "name"));
    }

    public Category save(Category category) {
        return this.repository.saveAndFlush(category);
    }

    public Category findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoria não encontrada!"));
    }

    public HashMap<String, Object> delete(Long id) {
        Optional<Category> category = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoria não encontrada!")));

        repository.delete(category.get());
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "Categoria: " + category.get().getName() + " excluída com sucesso!");
        return result;
    }

    public Category update(Category category) {
        if (category.getId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found Category!");
        }
        return this.repository.saveAndFlush(category);
    }

}
