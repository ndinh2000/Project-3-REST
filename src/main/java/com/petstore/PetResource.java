package com.petstore;

import com.petstore.model.Pet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This class contains the methods that will respond to the various endpoints that you define for your RESTful API Service.
 *
 */

@Path("/pets")
public class PetResource {

    @Path("{pet_id}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    public Response getPetByID(@PathParam("pet_id") String pet_id/* The {id} placeholder parameter is resolved */) {
        Pet pet = com.petstore.service.PetService.getPetByID(pet_id);

        //Respond with a 404 if there is no such todo_list item for the id provided
        if(pet == null) {
//            String result = "database query returned null!!1!1!!";
//            return Response.status(201).entity(result).build();
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //Respond with a 200 OK if you have a todo_list_item object to return as response
        return Response.ok(pet).build();
    }

}
