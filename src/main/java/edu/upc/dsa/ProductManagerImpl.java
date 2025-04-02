package edu.upc.dsa;


import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;

public class ProductManagerImpl implements ProductManager {

    private List<Product> productList;
    private static final Logger logger = LogManager.getLogger(ProductManagerImpl.class);
    private Queue<Order> orderQueue;
    private HashMap<String, User> users;
    private static ProductManagerImpl instance;

    public static ProductManagerImpl getInstance() { //Se crea una instancia para poder tener una comun entre los diferentes clientes
        if (instance == null) {
            instance = new ProductManagerImpl();
        }
        return instance;
    }


    public ProductManagerImpl() {
        productList = new ArrayList<>();
        orderQueue = new LinkedList<>();
        users = new HashMap<>();
    }



    @Override
    public void addProduct(String id, String name, double price) {
        logger.info("Adding product with id " + id + " and name " + name);
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

        @Override
        public void deleteProduct(String id) {
            productList.removeIf(p -> p.getId().equals(id));
        }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public void addProduct(User user) {users.put(user.getDni(),user);

    }


    @Override
    public Product updateProduct(Product product) {
        for (Product p : this.productList) {
            if (p.getId().equals(product.getId())) {
                return product;
            }
        }
        return null;
    }

}
