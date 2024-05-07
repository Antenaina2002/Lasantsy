package org.lasantsy.lasantsy.models;

import lombok.*;

import java.time.Instant;

@Data
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "name"})
@AllArgsConstructor
@Getter
@Setter
public class Station {
    private Long id;
    private String name;
    private String longitude;
    private String latitude;
    private int employeeNumber;
    private Instant updatedAt = Instant.now();
}