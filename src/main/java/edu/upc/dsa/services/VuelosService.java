package edu.upc.dsa.services;

import edu.upc.dsa.VuelosManager;
import edu.upc.dsa.VuelosManagerImpl;
import edu.upc.dsa.models.Vuelo;
import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Maleta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/Vuelos", description = "Endpoint to Vuelos Service")
@Path("/vuelos")
public class VuelosService {

    private VuelosManager vm;

    public VuelosService() {
        this.vm = VuelosManagerImpl.getInstance();
        if (this.vm.getVuelos().size() == 0) {
            vm.addAvion("A1", "Boeing 747", "A");
            vm.addAvion("A2", "Airbus A320", "B");
            vm.addAvion("A3", "Cessna 172", "C");
            vm.addAvion("A4", "Boeing 737", "D");


            vm.addVuelo(101, "EETAC", "UOC", 12, 13, "A1");
            vm.addVuelo(102, "UAB", "ETSEB", 14, 15, "A2");
            vm.addVuelo(103, "ETSAB", "UPF", 16, 17, "A3");

            Maleta m1 = new Maleta("Eloi");
            Maleta m2 = new Maleta("Toni");
            Maleta m3 = new Maleta("Eloi");

            vm.facturarMaleta(101, m1);
            vm.facturarMaleta(102, m2);
            vm.facturarMaleta(103, m3);
        }

    }


    @GET
    @ApiOperation(value = "Get a Vuelo", notes = "Get details of a specific vuelo by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Vuelo.class),
            @ApiResponse(code = 404, message = "Vuelo not found")
    })
    @Path("/obtener/{idVuelo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVuelo(@PathParam("idVuelo") int idVuelo) {
        Vuelo vuelo = this.vm.getVuelo(idVuelo);
        if (vuelo == null) {
            return Response.status(404).entity("Vuelo not found").build();
        } else {
            return Response.status(201).entity(vuelo).build();
        }
    }


    @POST
    @ApiOperation(value = "Create a new Avion", notes = "Create a new avion")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Avion created successfully", response = Avion.class),
            @ApiResponse(code = 500, message = "Validation error")
    })
    @Path("/avion/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAvion(Avion avion) {
        if (avion == null || avion.getIdAvion() == null || avion.getModel() == null || avion.getCompany() == null) {
            return Response.status(500).entity("Validation error: Missing required fields").build();
        }
        this.vm.addAvion(avion.getIdAvion(), avion.getModel(), avion.getCompany());
        return Response.status(201).entity(avion).build();
    }

    // Crear un nuevo vuelo
    @POST
    @ApiOperation(value = "Create a new Vuelo", notes = "Create a new vuelo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Vuelo created successfully", response = Vuelo.class),
            @ApiResponse(code = 500, message = "Validation error")
    })
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createVuelo(Vuelo vuelo) {
        if (vuelo == null || vuelo.getAvion() == null || vuelo.getOrigen() == null || vuelo.getDestino() == null) {
            return Response.status(500).entity("Validation error: Missing required fields").build();
        }
        this.vm.addVuelo(vuelo.getIdVuelo(), vuelo.getOrigen(), vuelo.getDestino(), vuelo.getSalida(), vuelo.getLlegada(), vuelo.getAvion().getIdAvion());
        return Response.status(201).entity(vuelo).build();
    }

    // Facturar maleta
    @POST
    @ApiOperation(value = "Facturar Maleta", notes = "Facturar una maleta en un vuelo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Maleta facturada correctamente"),
            @ApiResponse(code = 404, message = "Vuelo not found")
    })
    @Path("/facturar/{idVuelo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response facturarMaleta(@PathParam("idVuelo") int idVuelo, Maleta maleta) {
        Vuelo vuelo = this.vm.getVuelo(idVuelo);
        if (vuelo == null) {
            return Response.status(404).entity("Vuelo not found").build();
        }
        this.vm.facturarMaleta(idVuelo, maleta);
        return Response.status(201).entity("Maleta facturada correctamente").build();
    }

    // Obtener maletas facturadas de un vuelo
    @GET
    @ApiOperation(value = "Get Maletas facturadas", notes = "Obtener las maletas facturadas en un vuelo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Maleta.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Vuelo not found")
    })
    @Path("/maletas/{idVuelo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMaletasFacturadas(@PathParam("idVuelo") int idVuelo) {
        Vuelo vuelo = this.vm.getVuelo(idVuelo);
        if (vuelo == null) {
            return Response.status(404).entity("Vuelo not found").build();
        }
        List<Maleta> maletas = this.vm.getMaletasFacturadas(idVuelo);
        GenericEntity<List<Maleta>> entity = new GenericEntity<List<Maleta>>(maletas) {};
        return Response.status(201).entity(entity).build();
    }
}
