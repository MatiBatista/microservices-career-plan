package com.eldar.person_service.models;

import com.eldar.person_service.utils.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "employes")
@SQLDelete(sql = " UPDATE persons SET deleted_at= current_timestamp WHERE id=?")
@SQLRestriction("id IN (SELECT id FROM persons WHERE deleted_at IS NULL)")
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Employee extends Person{

    @Column(nullable = false, unique = true, length = 45)
    private String username;

    @Column(nullable = false,length = 500)
    private String password;

    @Column(nullable = false)
    private String roles;

    public List<RoleEnum> getRoles() {
        return Stream.of(roles.split(","))
                .map(RoleEnum::valueOf).toList();
    }


}
