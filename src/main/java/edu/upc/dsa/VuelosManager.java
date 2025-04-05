package edu.upc.dsa;
import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Vuelo;

import java.util.List;

public interface VuelosManager {

    void addAvion(String id, String model, String company);

    void addVuelo(int id, String origen, String destino, int salida, int llegada, String avionID);

     Vuelo getVuelo(int id);

    Avion getAvion(String id);

    List<Vuelo> getVuelos();


    void facturarMaleta(int idVuelo, Maleta maleta);

    List<Maleta> getMaletasFacturadas(int idVuelo);
}
