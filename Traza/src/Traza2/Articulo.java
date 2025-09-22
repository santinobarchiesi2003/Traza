package Traza2;

import Traza1.Sucursal;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder
@ToString
public class Articulo {
    protected Long id;
    protected Double precioVenta;
    protected String denominacion;

    @Builder.Default

    protected Set<ImagenArticulo> imagenes =new HashSet<>();
    protected UnidadMedida unidadMedida;
    protected Categoria categoria;
    protected Sucursal sucursal;
}