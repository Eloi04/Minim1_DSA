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

    @Before  //Este metodo se ejecuta antes de cada peueba
    public void setUp() {
        vm = new VuelosManagerImpl(); //Siempre garantiza que empieza en el mismo estado inicial"
        vm.addVuelo(101, "EETAC", "UOC", 12, 13, "A1");
        vm.addVuelo(102, "UAB", "ETSEB", 14, 15, "A2");
        vm.addVuelo(103, "ETSAB", "UPF", 1600, 1700, "A3");


        vm.addAvion("A1", "Boeing 747", "A");
        vm.addAvion("A2", "Airbus A320", "B");
        vm.addAvion("A3", "Cessna 172", "C");
        vm.addAvion("A4", "Boeing 737", "D");
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
    public void testAddMaleta() {
        Assert.assertEquals(0, vm.numMaletas()); // No hay maletas al inicio
        Maleta m = new Maleta("381112838","101"); // Crear una maleta
        m.addLV(1, "A1"); // Asignar productos al vuelo
        m.addLV(2, "A3");
        vm.addMaleta(101,m); // Añadir maleta al vuelo 101

    }

    //Aqui ja ho fet a la desesperada perque no m'ha donat temps, pero ho fare a casa a tope!
    @Test
    public void testVuelosConAvion() {
        Vuelo vuelo = vm.getVuelo(101); // Obtenemos el vuelo con id 101
        Assert.assertNotNull(vuelo); // El vuelo debería existir

        Assert.assertEquals("A1", vuelo.getAvionID()); // Comprobamos que el avión asignado al vuelo es correcto
    }


}
