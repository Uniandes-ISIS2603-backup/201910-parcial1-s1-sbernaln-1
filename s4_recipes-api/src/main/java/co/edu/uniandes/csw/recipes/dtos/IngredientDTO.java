/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.dtos;

import co.edu.uniandes.csw.recipes.entities.IngredientEntity;
import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class IngredientDTO implements Serializable
{
    private String name;
    
    private Long calories;
    
    private Long id;
    
    public IngredientEntity toEntity() 
    {
    IngredientEntity entity = new IngredientEntity();
    entity.setId(this.id);
    entity.setName(this.name);   
    entity.setCalories(this.calories);
    return entity;
}

    public IngredientDTO(IngredientEntity ingredient) {
    this.id = ingredient.getId();
    this.name = ingredient.getName();
    this.calories = ingredient.getCalories();
}
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the calories
     */
    public Long getCalories() {
        return calories;
    }

    /**
     * @param calories the calories to set
     */
    public void setCalories(Long calories) {
        this.calories = calories;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
