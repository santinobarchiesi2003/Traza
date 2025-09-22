import Traza1.*;
import Traza2.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //Inicializar Repositorios
        InMemory<Categoria> categoriaInMemoryRepository = new InMemory<>();
        InMemory<ArticuloInsumo> articuloInsumoInMemoryRepository = new InMemory<>();
        InMemory<ArticuloManufacturado> articuloManufacturadoInMemoryRepository = new InMemory<>();
        InMemory<UnidadMedida> unidadMedidaInMemoryRepository = new InMemory<>();
        InMemory<Empresa> empresaInMemoryRepository = new InMemory<>();

        //Crear Pais, Provincias, Localidades y Domicilios
        Pais pais1 = Pais.builder().nombre("Argentina").build();

        Provincia prov1 = Provincia.builder().id(1L).nombre("Buenos Aires").pais(pais1).build();

        Localidad local1 = Localidad.builder().id(1L).nombre("CABA").provincia(prov1).build();
        Domicilio dom1 = Domicilio.builder().id(1L).calle("Av. Cordoba").numero(4100).cp(1870).localidad(local1).build();

        Localidad local2 = Localidad.builder().id(2L).nombre("La Plata").provincia(prov1).build();
        Domicilio dom2 = Domicilio.builder().id(2L).calle("Av. 1").numero(1150).cp(1900).localidad(local2).build();

        Provincia prov2 = Provincia.builder().id(2L).nombre("Cordoba").pais(pais1).build();

        Localidad local3 = Localidad.builder().id(3L).nombre("Cordoba Capital").provincia(prov2).build();
        Domicilio dom3 = Domicilio.builder().id(3L).calle("Jujuy").numero(2602).cp(5000).localidad(local3).build();

        Localidad local4 = Localidad.builder().id(4L).nombre("Villa Carlos Paz").provincia(prov2).build();
        Domicilio dom4 = Domicilio.builder().id(4L).calle("San Martin").numero(500).cp(5152).localidad(local4).build();

        // Crear sucursales y relacionar a Empresas
        Sucursal s1= Sucursal.builder().id(1L).nombre("Sucursal 1").horarioApertura(LocalTime.of(10,0)).horarioCierre(LocalTime.of(23,0)).esCasaMatriz(true).domicilio(dom1).build();
        Sucursal s2= Sucursal.builder().id(2L).nombre("Sucursal 2").horarioApertura(LocalTime.of(15,0)).horarioCierre(LocalTime.of(20,0)).esCasaMatriz(false).domicilio(dom2).build();
        Sucursal s3= Sucursal.builder().id(3L).nombre("Sucursal 3").horarioApertura(LocalTime.of(9,0)).horarioCierre(LocalTime.of(17,0)).esCasaMatriz(true).domicilio(dom3).build();
        Sucursal s4= Sucursal.builder().id(4L).nombre("Sucursal 4").horarioApertura(LocalTime.of(17,0)).horarioCierre(LocalTime.of(23,30)).esCasaMatriz(false).domicilio(dom4).build();

        Empresa emp1=Empresa.builder().id(1L).nombre("Empresa 1").CUIT(345858575L).razonSocial("Empresa 1 SA").icon("logo1.jpg").sucursales(new HashSet<>(Set.of(s1,s2))).build();
        Empresa emp2=Empresa.builder().id(2L).nombre("Empresa 2").CUIT(123456789L).razonSocial("Empresa 2 SRL").icon("logo2.jpg").sucursales(new HashSet<>(Set.of(s3,s4))).build();

        s1.setEmpresa(emp1);
        s2.setEmpresa(emp1);
        s3.setEmpresa(emp2);
        s4.setEmpresa(emp2);

        empresaInMemoryRepository.save(emp1);
        empresaInMemoryRepository.save(emp2);

        //Crear Categorias
        Categoria pizza= Categoria.builder().id(1L).denominacion("Pizza").build();
        Categoria sandwich = Categoria.builder().id(2L).denominacion("Sandwich").build();
        Categoria lomos = Categoria.builder().id(3L).denominacion("Lomos").build();
        Categoria insumos = Categoria.builder().id(4L).denominacion("Insumos").build();

        categoriaInMemoryRepository.save(pizza);
        categoriaInMemoryRepository.save(sandwich);
        categoriaInMemoryRepository.save(lomos);
        categoriaInMemoryRepository.save(insumos);

        //Crear Unidades de Medida
        UnidadMedida kg=UnidadMedida.builder().id(1L).denominacion("Kilogramos").build();
        UnidadMedida g=UnidadMedida.builder().id(2L).denominacion("Gramos").build();
        UnidadMedida L=UnidadMedida.builder().id(3L).denominacion("Litros").build();

        unidadMedidaInMemoryRepository.save(kg);
        unidadMedidaInMemoryRepository.save(g);
        unidadMedidaInMemoryRepository.save(L);

        //Crear Insumos
        ArticuloInsumo sal=ArticuloInsumo.builder().id(1L).denominacion("Sal").precioCompra(1000.00).stockActual(10000).stockMinimo(500).esParaElaborar(true).unidadMedida(g).categoria(insumos).sucursal(s1).build();
        ArticuloInsumo aceite=ArticuloInsumo.builder().id(2L).denominacion("Aceite").precioCompra(4000.00).stockActual(50).stockMinimo(10).esParaElaborar(true).unidadMedida(L).categoria(insumos).sucursal(s1).build();
        ArticuloInsumo carne=ArticuloInsumo.builder().id(3L).denominacion("Carne").precioCompra(8000.00).stockActual(20).stockMinimo(1).esParaElaborar(true).unidadMedida(kg).categoria(insumos).sucursal(s1).build();
        ArticuloInsumo harina=ArticuloInsumo.builder().id(4L).denominacion("Harina").precioCompra(1000.00).stockActual(50).stockMinimo(5).esParaElaborar(true).unidadMedida(kg).categoria(insumos).sucursal(s1).build();

        articuloInsumoInMemoryRepository.save(sal);
        articuloInsumoInMemoryRepository.save(aceite);
        articuloInsumoInMemoryRepository.save(carne);
        articuloInsumoInMemoryRepository.save(harina);

        //Crear Imagenes y Articulos Manufacturados
        ImagenArticulo img1=ImagenArticulo.builder().id(1L).denominacion("Hawaiana1").build();
        ImagenArticulo img2=ImagenArticulo.builder().id(2L).denominacion("Hawaiana2").build();
        ImagenArticulo img3=ImagenArticulo.builder().id(3L).denominacion("Hawaiana3").build();
        ImagenArticulo img4=ImagenArticulo.builder().id(4L).denominacion("LomoCompleto1").build();
        ImagenArticulo img5= ImagenArticulo.builder().id(5L).denominacion("LomoCompleto2").build();
        ImagenArticulo img6=ImagenArticulo.builder().id(6L).denominacion("LomoCompleto3").build();

        ArticuloManufacturadoDetalle p1=ArticuloManufacturadoDetalle.builder().id(1L).cantidad(1).articuloInsumo(sal).build();
        ArticuloManufacturadoDetalle p2=ArticuloManufacturadoDetalle.builder().id(2L).cantidad(2).articuloInsumo(harina).build();
        ArticuloManufacturadoDetalle p3=ArticuloManufacturadoDetalle.builder().id(3L).cantidad(1).articuloInsumo(aceite).build();

        ArticuloManufacturadoDetalle l1 = ArticuloManufacturadoDetalle.builder().id(4L).cantidad(1).articuloInsumo(sal).build();
        ArticuloManufacturadoDetalle l2 = ArticuloManufacturadoDetalle.builder().id(5L).cantidad(1).articuloInsumo(aceite).build();
        ArticuloManufacturadoDetalle l3 = ArticuloManufacturadoDetalle.builder().id(6L).cantidad(1).articuloInsumo(carne).build();

        ArticuloManufacturado pizzaHawaiana = ArticuloManufacturado.builder().id(1L).denominacion("Pizza Hawaiana").precioVenta(12000.00).tiempoEstimadoMinutos(20).descripcion("8 porciones").preparacion("Mezclar los ingredientes").categoria(pizza).unidadMedida(kg).imagenes(new HashSet<>(Set.of(img1,img2,img3))).articuloManufacturadoDetalles(new HashSet<>(Set.of(p1,p2,p3))).sucursal(s1).build();
        ArticuloManufacturado LomoCompleto = ArticuloManufacturado.builder().id(2L).denominacion("Lomo Completo").precioVenta(15000.00).tiempoEstimadoMinutos(10).descripcion("Comen 2 personas").preparacion("Asar la carne a la parrilla").categoria(lomos).unidadMedida(kg).imagenes(new HashSet<>(Set.of(img4,img5,img6))).articuloManufacturadoDetalles(new HashSet<>(Set.of(l1,l2,l3))).sucursal(s1).build();

        articuloManufacturadoInMemoryRepository.save(pizzaHawaiana);
        articuloManufacturadoInMemoryRepository.save(LomoCompleto);

        //Resultados
        //Traza 1
        // Mostrar empresas
        List<Empresa> listarEmpresas = empresaInMemoryRepository.findAll();
        listarEmpresas.forEach(System.out::println);

        //Buscar Empresa por ID
        Optional<Empresa> buscarEmpresaID = empresaInMemoryRepository.findById(1L);
        buscarEmpresaID.ifPresent(empresa -> System.out.println("Empresa con ID 1 es: "+empresa));

        //Buscar Empresa por Nombre
        List<Empresa> buscarEmpresaNombre = empresaInMemoryRepository.genericFindByField("nombre","Empresa 1");
        System.out.println("Empresas con nombre 'Empresa 1':");
        buscarEmpresaNombre.forEach(System.out::println);

        //Actualizar Buscando por ID
        Optional<Empresa> actualizarCuit = empresaInMemoryRepository.findById(2L);
        actualizarCuit.ifPresent(empresa -> empresa.setCUIT(987654321L));

        //Eliminar Empresa por ID
        empresaInMemoryRepository.genericDelete(1L);

        //Traza2
        //Listar Categorias
        List<Categoria> listarCategorias = categoriaInMemoryRepository.findAll();
        listarCategorias.forEach(System.out::println);

        //Buscar por ID
        Optional<ArticuloManufacturado> buscarArticuloManufacturadoID = articuloManufacturadoInMemoryRepository.findById(1L);
        buscarArticuloManufacturadoID.ifPresent(artman -> System.out.println("Traza2.Articulo Manufacturado con ID 1:  "+artman));

        //Buscar Articulos
        List<ArticuloInsumo> buscarArticuloInsumo = articuloInsumoInMemoryRepository.findAll();
        buscarArticuloInsumo.forEach(System.out::println);

        List<ArticuloManufacturado> buscarArticuloManufacturado = articuloManufacturadoInMemoryRepository.findAll();
        buscarArticuloManufacturado.forEach(System.out::println);

        //Actualizar Articulos manufacturados
        Optional<ArticuloManufacturado> actualizarArticuloManufacturado = articuloManufacturadoInMemoryRepository.findById(2L);
        actualizarArticuloManufacturado.ifPresent(artman -> artman.setDenominacion("Lomo Provolone"));

        //Eliminar articulo por ID
        articuloManufacturadoInMemoryRepository.genericDelete(1L);
    }
}