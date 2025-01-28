package com.eldar.products_service.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
@Table(name = "products")
@SQLDelete(sql = " UPDATE products SET deleted_at= current_timestamp WHERE id=?")
@SQLRestriction("deleted_at IS NULL")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // Permite operaciones de calculo estricto
    @Column(nullable = false, scale = 2)
    private BigDecimal price;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Brand brand;

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private Category category;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer stock;

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    private Timestamp deletedAt;
}
