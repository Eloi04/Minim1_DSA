package edu.upc.dsa;


import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.Avión;
import edu.upc.dsa.models.Vuelo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;

public class ProductManagerImpl implements ProductManager {

    private List<Avión> aviónList;
    private static final Logger logger = LogManager.getLogger(ProductManagerImpl.class);
    private Queue<Maleta> maletaQueue;
    private HashMap<String, Vuelo> users;
    private static ProductManagerImpl instance;

    public static ProductManagerImpl getInstance() { //Se crea una instancia para poder tener una comun entre los diferentes clientes
        if (instance == null) {
            instance = new ProductManagerImpl();
        }
        return instance;
    }


    public ProductManagerImpl() {
        aviónList = new ArrayList<>();
        maletaQueue = new LinkedList<>();
        users = new HashMap<>();
    }



    @Override
    public void addProduct(String id, String name, double price) {
        logger.info("Adding product with id " + id + " and name " + name);
        aviónList.add(new Avión(id, name, price));

    }


    @Override
    public List<Avión> getProductsByPrice() {
        aviónList.sort(Comparator.comparingDouble(Avión::getCompany).reversed());
        return aviónList;
    }

    @Override
    public void addOrder(Maleta maleta) {
        maletaQueue.add(maleta);

        String userDni = maleta.getUser();
        users.putIfAbsent(userDni, new Vuelo(userDni));
        users.get(userDni).addMaleta(maleta); // Hash map Key:DNI y value: Objeto User que ocntiene los orders del usuario

    }

    @Override
    public int numOrders() {
        return maletaQueue.size();
    }

    @Override
    public Maleta deliverOrder() {
        if (!maletaQueue.isEmpty()) {
            Maleta maleta = maletaQueue.poll();

            for (String item : maleta.getItems()) {
                String[] parts = item.split("x ");
                int quantity = Integer.parseInt(parts[0]);
                String productId = parts[1];

                Avión avión = getProduct(productId);
                if (avión != null) {
                    avión.addSales(quantity);
                }
            }
            return maleta;
        }
        return null;
    }

    @Override
    public Avión getProduct(String id) {
        for (Avión p : aviónList) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Vuelo getUser(String number) {

        String dni;

        dni = number;

        return users.get(dni);

    }

        @Override
        public void deleteProduct(String id) {
            aviónList.removeIf(p -> p.getId().equals(id));
        }

    @Override
    public List<Avión> findAll() {
        return aviónList;
    }

    @Override
    public void addProduct(Vuelo vuelo) {users.put(vuelo.getDni(), vuelo);

    }


    @Override
    public Avión updateProduct(Avión avión) {
        for (Avión p : this.aviónList) {
            if (p.getId().equals(avión.getId())) {
                return avión;
            }
        }
        return null;
    }

}
