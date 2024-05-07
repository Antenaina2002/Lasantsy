package org.lasantsy.lasantsy.models;

import lombok.*;

import java.time.Instant;
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "station"})
@Setter
@Getter
public class Stock {
    private Long id;
    private Station station;
    private double value;
    private Instant stockDatetime;
    private Instant updatedAt = Instant.now();
}