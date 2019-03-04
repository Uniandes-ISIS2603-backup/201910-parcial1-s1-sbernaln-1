/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.resources;

import co.edu.uniandes.csw.recipes.dtos.RecipeDetailDTO;
import co.edu.uniandes.csw.recipes.ejb.RecipeLogic;
import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import co.edu.uniandes.csw.recipes.exceptions.BusinessLogicException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author CesarF
 */

@Path("recipes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class RecipeResource {
    @Inject
    private RecipeLogic recipeLogic;
    
    @POST
    public RecipeDetailDTO createRecipe(RecipeDetailDTO recipe) throws BusinessLogicException {
        RecipeEntity recipeEntity = recipe.toEntity();
        recipeEntity = recipeLogic.createRecipe(recipeEntity); 
        return new RecipeDetailDTO(recipeEntity);
    }


    @GET
    @Path("{id: \\d+}")
    public RecipeDetailDTO getRecipe(@PathParam("id") Long id) {
        RecipeEntity recipe = recipeLogic.getRecipe(id);
        if (recipe == null) 
            throw new WebApplicationException("La recipe no existe");
        return new RecipeDetailDTO(recipe); 
    }
    
}
