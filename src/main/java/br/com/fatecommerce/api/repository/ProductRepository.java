package br.com.fatecommerce.api.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fatecommerce.api.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByEan(String ean);

    List<Product> findBySku(String sku);

    List<Product> findByIgnoreCaseSkuContainingOrderByNameAsc(String sku);

    List<Product> findByPublishedIsTrueOrderByDateCreatedDesc();

    @Query(value = "select p.* from product p where p.date_created >= ?;", nativeQuery = true)
    List<Product> findByDateCreated(@Param("dateCreated") LocalDate dateCreated);

}
