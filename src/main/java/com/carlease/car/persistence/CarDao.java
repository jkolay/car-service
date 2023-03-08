package com.carlease.car.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Car Dao class
 */
@Entity
@DynamicUpdate
@Table(name = "car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    private Integer carId;

    @Column(nullable = false)
    private String make;

    @Column
    private String model;

    @Column
    private String version;

    @Column
    private Integer numberOfDoors;

    @Column
    private Double co2Emission;

    @Column
    private Double grossPrice;

    @Column
    private Double netPrice;

    @Column
    private String status;

    @Column
    private Long mileage;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

