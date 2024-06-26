package org.lasantsy.lasantsy.models;

import lombok.*;
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "station"})
@Getter
@Setter
@AllArgsConstructor
public class EvaporationRate {
    private Long id;
    private Long station;
    private double value;
}