package org.lasantsy.lasantsy.models;

import lombok.*;

import java.time.Instant;

@Data
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "station", "product"})
@Getter
@Setter
public class Movement {
    private Long id;
    private Station station;
    private Product product;
    private double value;
    private String type;
    private Instant movementDatetime;
    private Instant updatedAt = Instant.now();
}