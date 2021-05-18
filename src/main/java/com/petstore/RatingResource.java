package com.petstore;

import com.petstore.model.Pet;
import com.petstore.model.Rating;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 * This class contains the methods that will respond to the various endpoints that you define for your RESTful API Service.
 *
 */

@Path("/ratings")
public class RatingResource {
    @Path("{pet_id}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    public Response getRatingByID(@PathParam("pet_id") String pet_id/* The {id} placeholder parameter is resolved */) {
        List<Rating> ratings = com.petstore.service.RatingService.getRatingByID(pet_id);

        //Respond with a 404 if there is no such todo_list item for the id provided
        if(ratings == null) {
//            String result = "database query returned null!!1!1!!";
//            return Response.status(201).entity(result).build();
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //Respond with a 200 OK if you have a todo_list_item object to return as response
        return Response.ok(ratings).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON}) //This method accepts form parameters.
    //If you were to send a POST through a form submit, this method would be called.
    public Response addRating(Rating rating) throws SQLException {

        boolean success = com.petstore.service.RatingService.addRating(rating);
        if(success) {
            return Response.ok().entity("Rating Added Successfully").build();
        }

        return Response.ok().entity("Failed to add rating").build();
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

    }

    @DELETE
    @Consumes({MediaType.APPLICATION_JSON}) //This method accepts form parameters.
    //If you were to send a POST through a form submit, this method would be called.
    public Response deleteRating(Rating rating) throws SQLException {

        boolean success = com.petstore.service.RatingService.deleteRating(rating);
        if(success) {
            return Response.ok().entity("Rating Deleted Successfully").build();
        }

        return Response.ok().entity("Failed to delete rating").build();
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

    }

}
