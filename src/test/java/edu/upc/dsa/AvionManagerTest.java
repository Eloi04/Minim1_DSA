package edu.upc.dsa;

import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Vuelo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AvionManagerTest {
    VuelosManager vm;   //Lo declaramos como una prueba

    @Before  //Este metodo se ejecuta antes de cada prueba
    public void setUp() {
        vm = new VuelosManagerImpl(); //Siempre garantiza que empieza en el mismo estado inicial

        // Primero se añaden los aviones
        vm.addAvion("A1", "Boeing 747", "A");
        vm.addAvion("A2", "Airbus A320", "B");
        vm.addAvion("A3", "Cessna 172", "C");
        vm.addAvion("A4", "Boeing 737", "D");

        // Luego se añaden los vuelos, ahora que los aviones ya están disponibles
        vm.addVuelo(101, "EETAC", "UOC", 12, 13, "A1");
        vm.addVuelo(102, "UAB", "ETSEB", 14, 15, "A2");
        vm.addVuelo(103, "ETSAB", "UPF", 16, 17, "A3");
    }


    @After //Proporciona limpieza
    public void tearDown() {
        this.vm = null;
    }

    @Test
    public void testGetVuelos() {
        List<Vuelo> vuelos = vm.getVuelos();
        Assert.assertEquals(3, vuelos.size()); //
    }

    @Test
    public void testAddVuelo() {
          // Ahora verificamos que el vuelo se ha añadido correctamente
            Vuelo vuelo = vm.getVuelo(103);
            Assert.assertNotNull(vuelo); // Verifica que el vuelo no es nulo
            Assert.assertEquals("ETSAB", vuelo.getOrigen()); // Verifica el origen
            Assert.assertEquals("UPF", vuelo.getDestino()); // Verifica el destino
            Assert.assertEquals(16, vuelo.getSalida()); // Verifica la hora de salida
            Assert.assertEquals(17, vuelo.getLlegada()); // Verifica la hora de llegada

    }



    @Test
    public void testAddAvion() {

        Avion avion = vm.getAvion("A1");
        Assert.assertNotNull(avion); // Verifica que el vuelo no es nulo
        Assert.assertEquals("Boeing 747", avion.getModel()); // Verifica el origen
        Assert.assertEquals("A", avion.getCompany()); // Verifica el destino

    }

    @Test
    public void testFacturarMaleta() {

        vm.addAvion("A1", "Boeing 747", "A");
        vm.addVuelo(101, "EETAC", "UOC", 12, 13, "A1");

        Maleta maleta = new Maleta("Eloi");
        vm.facturarMaleta(101, maleta);
        // Verificar que la maleta tiene un id asignado (no debe ser null)
        Assert.assertNotNull(maleta.getIdMaleta());

        // Verificar que la maleta se ha añadido correctamente al vuelo
        List<Maleta> maletasFacturadas = vm.getMaletasFacturadas(101);
        Assert.assertEquals(1, maletasFacturadas.size());
        Assert.assertEquals("Eloi", maletasFacturadas.get(0).getPasajero());
    }

    @Test
    public void testMaletasFacturadas() {
        // Crear aviones y vuelos
        vm.addAvion("A1", "Boeing 747", "A");
        vm.addVuelo(101, "EETAC", "UOC", 12, 13, "A1");

        // Crear maletas para facturar
        Maleta maleta1 = new Maleta( "Eloi");
        Maleta maleta2 = new Maleta( "Toni Oller");
        Maleta maleta3 = new Maleta( "Juan Lopez");

        // Facturar las maletas en el vuelo con id 101
        vm.facturarMaleta(101, maleta1);
        vm.facturarMaleta(101, maleta2);
        vm.facturarMaleta(101, maleta3);

        // Obtener las maletas facturadas del vuelo 101
        List<Maleta> maletasFacturadas = vm.getMaletasFacturadas(101);

        // Verificar que las maletas sean entregadas en el orden correcto (última entra, primera sale)
        Assert.assertEquals(3, maletasFacturadas.size());

        // La última maleta en entrar (maleta3) debe ser la primera en salir
        Assert.assertEquals("Juan Lopez", maletasFacturadas.get(0).getPasajero());
        Assert.assertEquals("Toni Oller", maletasFacturadas.get(1).getPasajero());
        Assert.assertEquals("Eloi", maletasFacturadas.get(2).getPasajero());


    }



}