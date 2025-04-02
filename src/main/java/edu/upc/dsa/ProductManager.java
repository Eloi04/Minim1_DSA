package edu.upc.dsa;
import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;

import java.util.List;

public interface ProductManager {

    public void addProduct(String id, String name, double price);

    public List<Product> getProductsByPrice();

    public void addOrder(Order order);

    public int numOrders();

    public Order deliverOrder();

    Product getProduct(String c1);

    User getUser(String number);

    List<Product> findAll();

    public void addProduct(User user);

    Product updateProduct(Product product);

    void deleteProduct(String id);
}
