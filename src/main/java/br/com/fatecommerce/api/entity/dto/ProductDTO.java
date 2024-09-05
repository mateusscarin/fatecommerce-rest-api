package br.com.fatecommerce.api.entity.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductDTO {

    private Long id;

    @NotBlank(message = "O campo nome é obrigatório!")
    @Length(min = 3, max = 100, message = "O nome do produto deve possuir entre 3 e 100 caracteres!")
    private String name;

    @Length(max = 1000, message = "A descrição deve possuir no máximo 1000 caracteres!")
    private String description;

    private String sku;

    private String ean;

    @DecimalMin(value = "0.0", message = "O preço não pode ser menor que zero!")
    @NotNull(message = "O preço não pode ser nulo!")
    private BigDecimal costPrice;

    @DecimalMin(value = "0.0", message = "A quantia não pode ser menor que zero!")
    @NotNull(message = "A quantia não pode ser nula!")
    private BigDecimal amount;

    @Min(value = 0, message = "O valor de estoque não pode ser menor que zero!")
    private Long stock;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    private Long categoryId;

    private String categoryName;

}
