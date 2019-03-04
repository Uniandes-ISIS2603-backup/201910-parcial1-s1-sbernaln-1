/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.ejb;

import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import co.edu.uniandes.csw.recipes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.recipes.persistence.RecipePersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author CesarF
 */
@Stateless
public class RecipeLogic {
    @Inject
    private RecipePersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    public RecipeEntity getRecipe(Long id) {
        return persistence.find(id);
    }
    
    public RecipeEntity createRecipe(RecipeEntity rEntity) throws BusinessLogicException
    {
        if(rEntity.getName().isEmpty() || rEntity.getName() == null || rEntity.getName().toCharArray().length < 30 )
        {
            throw new BusinessLogicException("El formato del nombre no es valido");
        }
        if(rEntity.getDescription().isEmpty() || rEntity.getDescription() == null || rEntity.getDescription().toCharArray().length>150)
        {
            throw new BusinessLogicException("El formato de la descripcion no es valido");
        }
        if(persistence.findByName(rEntity.getName())!= null)
        {
            throw new BusinessLogicException("Ya existe una receta con ese nombre");
        }
        
        persistence.createRecipe(rEntity);
        return rEntity;
    }

    //TODO crear el método createRecipe


}
