package com.eldar.person_service.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "customers")
@SQLDelete(sql = " UPDATE customers SET deleted_at= current_timestamp WHERE id=?")
@SQLRestriction("deleted_at IS NULL")
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false,length = 45)
    protected String firstname;

    @Column(nullable = false,length = 45)
    protected String lastname;

    @Column(nullable = false, unique = true, length = 12)
    protected String dni;

    @Column(nullable = false,unique = true, length = 45)
    protected String email;

    @Column(nullable = false,length = 150)
    protected String address;

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    private Timestamp deletedAt;
}
