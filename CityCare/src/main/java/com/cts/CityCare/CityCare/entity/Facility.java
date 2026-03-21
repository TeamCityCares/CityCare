package com.cts.CityCare.CityCare.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Table(name = "facilities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Facility extends BaseEntity {

    public enum Type {
        HOSPITAL, CLINIC
    }

    public enum Status {
        ACTIVE, INACTIVE, MAINTENANCE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facilityId;

    @NotBlank
    @Size(min = 2, max = 100, message = "Facility name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @NotBlank
    @Size(max = 255, message = "Location is too long")
    @Column(nullable = false)
    private String location;

    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 10000, message = "Capacity exceeds limit")
    @Column(nullable = false)
    private int capacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Status status = Status.ACTIVE;
}
