/*
  2024
*/
package org.project.infrastructure.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class ProductEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private Double price;
    private int quantity;
    private String internalReference;
    private int shellId;
    private String inventoryStatus;
    private double rating;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;


}