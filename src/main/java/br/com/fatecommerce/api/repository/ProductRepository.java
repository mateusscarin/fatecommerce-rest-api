package br.com.fatecommerce.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fatecommerce.api.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
