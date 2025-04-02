package edu.upc.dsa;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProductManagerTest {
    ProductManager pm;   //Lo declaramos como una prueba

    @Before  //Este metodo se ejecuta antes de cada peueba
    public void setUp() {
        pm = new ProductManagerImpl(); //Siempre garantiza que empieza en el mismo estado inicial"
        pm.addProduct("C1", "Coca-cola zero", 2);
        pm.addProduct("C2", "Coca-cola", 2.5);
        pm.addProduct("B1", "Lomo queso", 3);
        pm.addProduct("C3", "bacon queso", 3.5);
    }

    @After //Proporciona limpieza
    public void tearDown() {
        this.pm = null;
    }

    @Test
    public void testProductByPrice() {
        List<Product> products = pm.getProductsByPrice();
        Assert.assertEquals(3.5, products.get(0).getPrice(), 0.01);
        Assert.assertEquals(3, products.get(1).getPrice(), 0.01);
        Assert.assertEquals(2.5, products.get(2).getPrice(), 0.01);
        Assert.assertEquals(2, products.get(3).getPrice(), 0.01);
    }

    @Test
    public void testAddOrder() {
        Assert.assertEquals(0, pm.numOrders());
        Order o = new Order("381112838");
        o.addLP(2, "C1");
        o.addLP(1, "B2");
        o.addLP(1, "C2");
        pm.addOrder(o);

        Assert.assertEquals(1, pm.numOrders());// ahora ya hay una orden
    }

    @Test
    public void testDeliverOrder() {
        testAddOrder();
        Assert.assertEquals(1, pm.numOrders());
        Order o = pm.deliverOrder();
        Assert.assertEquals(0, pm.numOrders());

        Assert.assertEquals("381112838", o.getUser());
    }

    @Test
    public void testSales() {
        testDeliverOrder(); //Para aasegurarse de que ha habido aalguna compra
        Product p = pm.getProduct("C1");
        Assert.assertEquals(2, p.sales()); // comprueba que se han vendido dos unidades

    }

    @Test
    public void testOrdersByUser() {
        testSales();
        User u = pm.getUser("381112838");
        List<Order> l = u.orders();
        Assert.assertEquals(1, l.size());
    }
}
