package edu.upc.dsa;

import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.Avión;
import edu.upc.dsa.models.Vuelo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AviónManagerTest {
    VuelosManager pm;   //Lo declaramos como una prueba

    @Before  //Este metodo se ejecuta antes de cada peueba
    public void setUp() {
        pm = new VuelosManagerImpl(); //Siempre garantiza que empieza en el mismo estado inicial"
        pm.addVuelo("C1", "Coca-cola zero", 2);
        pm.addVuelo("C2", "Coca-cola", 2.5);
        pm.addVuelo("B1", "Lomo queso", 3);
        pm.addVuelo("C3", "bacon queso", 3.5);
    }

    @After //Proporciona limpieza
    public void tearDown() {
        this.pm = null;
    }

    @Test
    public void testProductByPrice() {
        List<Avión> avións = pm.getProductsByPrice();
        Assert.assertEquals(3.5, avións.get(0).getCompany(), 0.01);
        Assert.assertEquals(3, avións.get(1).getCompany(), 0.01);
        Assert.assertEquals(2.5, avións.get(2).getCompany(), 0.01);
        Assert.assertEquals(2, avións.get(3).getCompany(), 0.01);
    }

    @Test
    public void testAddOrder() {
        Assert.assertEquals(0, pm.numOrders());
        Maleta o = new Maleta("381112838");
        o.addLP(2, "C1");
        o.addLP(1, "B2");
        o.addLP(1, "C2");
        pm.addMaleta(o);

        Assert.assertEquals(1, pm.numOrders());// ahora ya hay una orden
    }

    @Test
    public void testDeliverOrder() {
        testAddOrder();
        Assert.assertEquals(1, pm.numOrders());
        Maleta o = pm.deliverOrder();
        Assert.assertEquals(0, pm.numOrders());

        Assert.assertEquals("381112838", o.getUser());
    }

    @Test
    public void testSales() {
        testDeliverOrder(); //Para aasegurarse de que ha habido aalguna compra
        Avión p = pm.getProduct("C1");
        Assert.assertEquals(2, p.sales()); // comprueba que se han vendido dos unidades

    }

    @Test
    public void testOrdersByUser() {
        testSales();
        Vuelo u = pm.getVuelo("381112838");
        List<Maleta> l = u.getMaletas();
        Assert.assertEquals(1, l.size());
    }
}
