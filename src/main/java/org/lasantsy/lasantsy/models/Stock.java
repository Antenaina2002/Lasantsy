package org.lasantsy.lasantsy.models;

import lombok.*;

import java.time.Instant;
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "station"})
@Setter
@Getter
@AllArgsConstructor
public class Stock {
    private Long id;
    private Long idStation;
    private double value;
}