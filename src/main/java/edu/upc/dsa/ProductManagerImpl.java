package edu.upc.dsa;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;

import java.util.*;

public class ProductManagerImpl implements ProductManager {
    private List<Product> productList;
    private Queue<Order> orderQueue;
    private HashMap<String, User> users;


    public ProductManagerImpl() {
        productList = new ArrayList<>();
        orderQueue = new LinkedList<>();
        users = new HashMap<>();
    }

    @Override
    public void addProduct(String id, String name, double price) {
        productList.add(new Product(id, name, price));

    }

    @Override
    public List<Product> getProductsByPrice() {
        productList.sort(Comparator.comparingDouble(Product::getPrice).reversed());
        return productList;
    }

    @Override
    public void addOrder(Order order) {
        orderQueue.add(order);

        String userDni = order.getUser();
        users.putIfAbsent(userDni, new User(userDni));
        users.get(userDni).addOrder(order); // Hash map Key:DNI y value: Objeto User que ocntiene los orders del usuario

    }

    @Override
    public int numOrders() {
        return orderQueue.size();
    }

    @Override
    public Order deliverOrder() {
        if (!orderQueue.isEmpty()) {
            Order order = orderQueue.poll();

            for (String item : order.getItems()) {
                String[] parts = item.split("x ");
                int quantity = Integer.parseInt(parts[0]);
                String productId = parts[1];

                Product product = getProduct(productId);
                if (product != null) {
                    product.addSales(quantity);
                }
            }
            return order;
        }
        return null;
    }

    @Override
    public Product getProduct(String id) {
        for (Product p : productList) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public User getUser(String number) {

        String dni;

        dni = number;

        return users.get(dni);

    }
}
