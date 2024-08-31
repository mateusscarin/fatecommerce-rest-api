package br.com.fatecommerce.api.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false, length = 300, unique = true)
    @NotBlank(message = "O campo nome é obrigatório!")
    @Length(min = 3, max = 100, message = "O nome do produto deve possuir entre 3 e 100 caracteres!")
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false, unique = true, length = 10)
    private String sku;

    @Column(nullable = false, length = 15)
    private String ean;

    @Column(name = "cost_price", nullable = false, precision = 15, scale = 2)
    @DecimalMin(value = "0.0", message = "O preço não pode ser menor que zero!")
    @NotNull(message = "O preço não pode ser nulo!")
    private BigDecimal costPrice;

    @Column(nullable = false, precision = 15, scale = 2)
    @DecimalMin(value = "0.0", message = "A quantia não pode ser menor que zero!")
    @NotNull(message = "A quantia não pode ser nula!")
    private BigDecimal amount;

    @Column(nullable = false)
    private Boolean published;

    @Column(nullable = false)
    @Min(value = 0, message = "O valor de estoque não pode ser menor que zero!")
    private Long stock;

    @Column(name = "date_created", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    @Column(name = "date_update")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateUpdated;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @PrePersist
    private void prePersist() {
        setPublished(false);
        setDateCreated(LocalDate.now());
    }

    @PreUpdate
    private void preUpdate() {
        setDateUpdated(LocalDate.now());
    }

}