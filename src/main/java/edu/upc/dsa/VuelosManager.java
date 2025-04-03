package edu.upc.dsa;
import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.Avión;
import edu.upc.dsa.models.Vuelo;

import java.util.List;

public interface ProductManager {

    public void addProduct(String id, String name, double price);

    public List<Avión> getProductsByPrice();

    public void addOrder(Maleta maleta);

    public int numOrders();

    public Maleta deliverOrder();

    Avión getProduct(String c1);

    Vuelo getUser(String number);

    List<Avión> findAll();

    public void addProduct(Vuelo vuelo);

    Avión updateProduct(Avión avión);

    void deleteProduct(String id);
}
