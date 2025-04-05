package edu.upc.dsa;

import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Vuelo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;

public class VuelosManagerImpl implements VuelosManager {

    private List<Avion> avionList;
    private List<Vuelo> vueloList;
    private Queue<Maleta> maletasQueue;

    private static final Logger logger = LogManager.getLogger(VuelosManagerImpl.class);
    private static VuelosManagerImpl instance;


    public static VuelosManagerImpl getInstance() {
        if (instance == null) {
            instance = new VuelosManagerImpl();
        }
        return instance;
    }


    // Constructor
    public VuelosManagerImpl() {
        avionList = new ArrayList<>();
        vueloList = new ArrayList<>();
        this.maletasQueue = new LinkedList<>();
    }

    @Override
    public void addAvion(String id, String model, String company) {
        // Comprobar si el avión ya existe
        for (Avion avion : avionList) {
            if (avion.getId().equals(id)) {
                // Si ya existe, actualizar sus datos
                avion.setModel(model);
                avion.setCompany(company);
                logger.info("Avion con ID " + id + " actualizado.");
                return;
            }
        }

        Avion avion = new Avion(id, model, company);
        avionList.add(avion);
        logger.info("Avion con ID " + id + " agregado.");
    }

    @Override
    public void addVuelo(int id, String origen, String destino, int salida, int llegada, String avionID) {
       logger.info("Vuelo con ID " + id + " agregado.");
       vueloList.add(new Vuelo(id, origen, destino, salida, llegada, avionID));
    }

    @Override
    public Vuelo getVuelo(int id) {
        // Buscar el vuelo por ID
        for (Vuelo vuelo : vueloList) {
            if (vuelo.getId() == id) {
                return vuelo;
            }
        }
        return null;
    }

    @Override
    public List<Vuelo> getVuelos() {
        return vueloList;
    }


    @Override
    public void addMaleta(int vueloId, Maleta maleta) {
        Vuelo vuelo = this.vueloList.get(vueloId);  // Buscar el vuelo por ID

        if (vuelo == null) {
            throw new IllegalArgumentException("El vuelo con ID " + vueloId + " no existe.");
        }

        vuelo.addMaleta(maleta);  // Agregar la maleta al vuelo

        maletasQueue.add(maleta);  // Agregar la maleta a la cola global
    }

    @Override
    public int numMaletas() {
        return maletasQueue.size();  // Retorna el tamaño de la cola de maletas
    }




    @Override
    public List<Maleta> getMaletasDeVuelo(int vueloId) {
        // Buscar el vuelo por ID
        Vuelo vuelo = getVuelo(vueloId);
        if (vuelo == null) {
            throw new IllegalArgumentException("El vuelo con ID " + vueloId + " no existe.");
        }

        // Retornar las maletas facturadas en ese vuelo
        return vuelo.getMaletas();
    }



}
