package org.lasantsy.lasantsy.models;

import lombok.*;

import java.time.Instant;

@Data
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "station", "product"})
@Getter
@Setter
@AllArgsConstructor
public class Movement {
    private Long id;
    private Long idStation;
    private Long idProduct;
    private double value;
    private String type;
}