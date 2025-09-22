package Traza2;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@ToString
public class ImagenArticulo {
    private Long id;
    private String denominacion;

    private Articulo articulo;
}