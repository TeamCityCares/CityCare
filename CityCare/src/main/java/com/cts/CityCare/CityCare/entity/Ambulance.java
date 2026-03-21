package com.cts.CityCare.CityCare.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Table(name = "ambulances",
        uniqueConstraints = @UniqueConstraint(columnNames = "vehicle_number"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ambulance extends BaseEntity {

    public enum Status {
        AVAILABLE,    // Ready to be dispatched to an emergency
        DISPATCHED,   // Currently en route to an emergency
        MAINTENANCE   // Under repair – not available for dispatch
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ambulanceId;

    @NotBlank
    @Pattern(regexp = "^[A-Z0-9-]{4,15}$", message = "Vehicle number must be alphanumeric and appropriately formatted")
    @Column(name = "vehicle_number", nullable = false, unique = true)
    private String vehicleNumber; // e.g. "TN-01-AB-1234"

    @Size(max = 100)
    private String model; // e.g. "Toyota HiAce Advanced Life Support"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Status status = Status.AVAILABLE; // Always starts AVAILABLE when added
}
