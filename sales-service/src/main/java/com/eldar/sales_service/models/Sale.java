package com.eldar.sales_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sales")
@SQLDelete(sql = " UPDATE sales SET deleted_at= current_timestamp WHERE id=?")
@SQLRestriction("deleted_at IS NULL")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false, scale = 2)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private Integer discountPercentage;

    @Column(nullable = false, scale = 2)
    private BigDecimal discountAmount;

    @Column(nullable = false, scale = 2)
    private BigDecimal subtotal;

    @Column(nullable = false)
    private Long customer_id;

    @Column(nullable = false)
    private Long employee_id;

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    private Timestamp deletedAt;
}
