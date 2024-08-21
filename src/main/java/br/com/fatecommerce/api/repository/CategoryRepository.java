package br.com.fatecommerce.api.repository;

import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
