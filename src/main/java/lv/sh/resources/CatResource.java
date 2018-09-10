package lv.sh.resources;

import lv.sh.dto.Cat;
import lv.sh.service.cat.CatServiceImpl;
import lv.sh.service.cat.ICatService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("cats")
public class CatResource {

    @GET
    @Path("all")
    @Produces("application/json")
    public Response getAll(){
        ICatService catService=new CatServiceImpl();
        return Response.status(200).
                entity(catService.getAllCats()).build();
    }

    @POST
    @Path("add")
    @Produces("application/json")
    public Response post(Cat cat){
        ICatService catService=new CatServiceImpl();
        catService.addCat(cat);
        return Response.ok().entity(cat).build();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Cat getById(@PathParam( "id" ) String catId){
        ICatService catService=new CatServiceImpl();
        return catService.getCatById(catId);
    }

    @RolesAllowed("ADMIN")
    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam( "id" ) String catId){
        ICatService catService=new CatServiceImpl();
        catService.deleteCat(catId);
        return Response.ok().entity("Cat was deleted").build();
    }

    @PUT
    @Path("update/{id}")
    @Produces("application/json")
    public Response update(@PathParam( "id" ) String catId, Cat cat){
        ICatService catService=new CatServiceImpl();
        catService.updateCat(catId, cat);
        return Response.ok().entity(cat).build();
    }
}
