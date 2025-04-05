package edu.upc.dsa;

import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Vuelo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;

public class VuelosManagerImpl implements VuelosManager {

    private static final Logger logger = LogManager.getLogger(VuelosManagerImpl.class);
    private static VuelosManagerImpl instance;

    private Map<String, Avion> aviones;
    private Map<Integer, Vuelo> vuelos;
    private int idMaletaCounter;

    public static VuelosManagerImpl getInstance() {
        if (instance == null) {
            instance = new VuelosManagerImpl();
        }
        return instance;
    }

    public VuelosManagerImpl() {
        this.aviones = new HashMap<>();
        this.vuelos = new HashMap<>();
        this.idMaletaCounter = 1;
    }

    @Override
    public void addAvion(String id, String model, String company) {
        logger.info("Añadiendo/modificando avión: id=" + id);
        aviones.put(id, new Avion(id, model, company));
    }

    @Override
    public void addVuelo(int id, String origen, String destino, int salida, int llegada, String avionID) {
        logger.info("Añadiendo vuelo id=" + id);
        Avion avion = aviones.get(avionID);

        if (avion == null) {
            logger.warn("No se encontró el avión con id=" + avionID);
            return;
        }

        vuelos.put(id, new Vuelo(id, origen, destino, salida, llegada, avion));
    }

    @Override
    public Vuelo getVuelo(int id) {
        logger.info("Buscando vuelo con id=" + id);
        return vuelos.get(id);
    }

    @Override
    public Avion getAvion(String id) {
        logger.info("Buscando avion con id=" + id);
        return aviones.get(id);    }

    @Override
    public List<Vuelo> getVuelos() {
        logger.info("Obteniendo todos los vuelos");
        return new ArrayList<>(vuelos.values());
    }

    @Override
    public void facturarMaleta(int idVuelo, Maleta maleta) {
        logger.info("Facturando maleta en vuelo id=" + idVuelo);
        Vuelo vuelo = vuelos.get(idVuelo);

        if (vuelo == null) {
            logger.warn("Vuelo no encontrado");
            return;
        }

        maleta.setIdMaleta(idMaletaCounter++);
        vuelo.addMaleta(maleta);
    }

    @Override
    public List<Maleta> getMaletasFacturadas(int idVuelo) {
        logger.info("Obteniendo maletas del vuelo id=" + idVuelo);
        Vuelo vuelo = vuelos.get(idVuelo);

        if (vuelo == null) {
            logger.warn("Vuelo no encontrado");
            return null;
        }

        List<Maleta> lista = new ArrayList<>(vuelo.getMaletas());
        Collections.reverse(lista); // Última entra, primera sale
        return lista;
    }
}
