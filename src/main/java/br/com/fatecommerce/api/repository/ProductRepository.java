package br.com.fatecommerce.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fatecommerce.api.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByEan(String ean);

    List<Product> findByIgnoreCaseSkuContainingOrderByNameAsc(String sku);

}
