package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CarsDTO;
import dtos.RaceDTO;
import entities.Race;
import facades.RaceCarFacade;

import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("RaceCarInfo")
public class RaceCarResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static RaceCarFacade raceCarFacade = RaceCarFacade.getRaceCarFacade(EMF);
    private String securityToken;


    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Races")
    public List<RaceDTO> allRaces() {
        {
            return raceCarFacade.getAllRaces();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("newRace")
    public Response createRace(String race) {
        RaceDTO raceDTO = gson.fromJson(race, RaceDTO.class);
        RaceDTO newRace = raceCarFacade.createRace(raceDTO);
        return Response.ok(gson.toJson(newRace), MediaType.APPLICATION_JSON).build();
    }

    @Path("carsFromRace/{raceID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCarsFromRace(@PathParam("raceID") int raceID){
        CarsDTO cDTO = raceCarFacade.getCarFromRaceID(raceID);
        return Response.ok(gson.toJson(cDTO), MediaType.APPLICATION_JSON).build();
    }

}
