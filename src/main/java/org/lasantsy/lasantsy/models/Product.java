package org.lasantsy.lasantsy.models;

import lombok.*;

import java.time.Instant;

@Data
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "name"})
@AllArgsConstructor
@Getter
@Setter
public class Product {
    private Long id;
    private String name;
    private double price;
}