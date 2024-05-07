package org.lasantsy.lasantsy.models;

import lombok.*;

import java.time.Instant;
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "station"})
@Getter
@Setter
public class EvaporationRate {
    private Long id;
    private Station station;
    private double value;
    private Instant updatedAt = Instant.now();
}