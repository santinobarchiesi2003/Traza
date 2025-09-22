package Traza2;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@ToString
public class ArticuloManufacturadoDetalle {
    private Long id;
    private Integer cantidad;

    private ArticuloInsumo articuloInsumo;
}