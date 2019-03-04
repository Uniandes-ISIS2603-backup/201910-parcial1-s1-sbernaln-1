# Instrucciones

1. Descargue este repositorio y abra los proyectos en Netbeans.

2. En Netbeans vaya a Services > Databases > JavaDB y cree una base de datos que se llame `recipesPU` (los demás campos déjelos en blanco).

3. En Netbeans vaya a Tools > Options > Java, seleccione la pestaña Maven y marque la opción `Skip Tests for any build executions no directly related to testing`.

4. Cada vez que complete un paso genere un commit.

6. Al finalizar ingrese a sicua y suba el link de su repositorio.

7. Sonria :)

## Contexto

Esta aplicación permite gestionar recetas. El recurso presente en la aplicación es `Recipe`, la cual tiene un nombre (`name: String`), una descripción (`description: String`) y un identificador (`id: Long`) que es la llave primaria.

En la aplicación usted encontrará que las funcionalidades de solicitar (`GET`) ya están implementadas.

Se nos ha solicitado completar los siguientes requisitos:

## Punto 1 (40%): Crear receta
Se desea que el sistema permita crear una receta.

Ud. debe extender su programa para que cuando ejecute

```POST localhost:8080/s4_recipes-api/api/recipes```

con el json:

```json
{
    "name": "Tacos de res",
    "description": "Receta para cocinar tacos típicos con un fuerte sabor picante"
}
```

se cree la receta con la información.

Para esto Ud. debe:

1. (5%) Crear el método `createRecipe` en la clase `RecipePersistence` el cual permita almacenar la receta en la base de datos

2. (5%) Completar el método de prueba `createRecipeTest` para que valide si se crea correctamente una receta.

3. (10%) Crear el método `createRecipe` en la clase `RecipeLogic` el cual valida las siguientes reglas de negocio.
  * El nombre de la receta es válido, no es vacio ni nulo y tampoco supera los 30 caracteres
  * No deben haber dos recetas con el mismo nombre
  * La descripción es válida, no es vacia ni nula y tampoco supera los 150 caracteres

4. (10%) Cree la clase de pruebas `RecipeLogicTest` que contiene el método de prueba `createRecipeTest` para que valide si se crea correctamente una receta.

5. (5%) Cree el método `createRecipe` en la clase `RecipeResource` que recibe el cuerpo de la receta, solicita la creación de la receta y retorna la receta con su nuevo id.

6. (5%) Cree una colección de postman para probar tanto al creación como las reglas de negocio asociadas. Exporte la colección y guardela en la carpeta collections del proyecto s4_recipes-api. Esta colección debe estar parametrizada para correr las pruebas automáticamente.

Al finalizar suba los cambios y cree un release con el nombre `punto_1`

## Punto 2 (60%): Agregar ingrediente

1. (10%) Crear las clases `IngredientDTO` y `IngredientEntity` que modelan al ingrediente. Un ingrediente tiene un nombre (`name: String`) y una cantidad de calorias (`calories: Long`) y un id (`id: Long`). En la clase `IngredientDTO`, además de tener un constructor sin parámetros, defina un constructor para convertir un `IngredientEntity` en un `IngredientDTO`:

```java
public IngredientDTO(IngredientEntity ingredient) {
    this.id = ingredient.getId();
    this.name = ingredient.getName();
    ...
}
```

Para convertir un `IngredientDTO` en un `IngredientEntity` defina el siguiente método:

```java
public IngredientEntity toEntity() {
    IngredientEntity entity = new IngredientEntity();
    entity.setId(this.id);
    entity.setName(this.name);   
    ...		
    return entity;
}
```
2. (10%) Defina en `RecipeEntity` la relación con `Ingrediente` (unidireccional) e implemente sus `set/get`. Esta es una relación de **composición** de uno de muchos (`OneToMany`).

3. (5%) Defina un atributo nuevo en `RecipeDetailDTO` que representa el listado de ingredientes de la receta. Defina `set/get` y actualice el método constructor que recibe un `RecipeEntity` al igual que el método `toEntity`, el cual retorna un objeto de tipo `RecipeEntity`, para que también hagan la conversión del listado de ingredientes.

4. (5%) Modifique el método `createRecipe` de la clase `RecipeLogic` para que tenga en cuenta la siguiente regla de negocio.
- Toda receta debe tener al menos un ingrediente

Si las reglas de negocio se cumplen, se debe llamar la persistencia para que el objeto sea persistido, de lo contrario debe lanzar una excepción `BussinessLogicException` con un mensaje donde se especifique cuál regla no se cumplió.

6. (10%) Modifique la prueba unitaria de la capa de persistencia `createRecipe` para que ahora valide si la lista de ingredientes que se almacena es correcta.

7. (10%) Modifique la prueba unitaria `createRecipe` de la capa de lógica para que ahora valide si la lista de ingredientes que se almacena es correcta.

8. (10%) Cree una colección de postman para probar tanto al creación como las reglas de negocio asociadas. Exporte la colección y guardela en la carpeta collections del proyecto s4_recipes-api. Puede apoyarse en los siguientes escenarios de prueba. Esta colección debe estar parametrizada para correr las pruebas automáticamente.

### Escenarios de prueba

`POST localhost:8080/s4_recipes-api/api/recipes/`

```json
{
    "name": "Tacos de pollo",
    "description": "Receta para cocinar tacos típicos con un fuerte sabor picante"
}
```
Respuesta: 412, no hay ingredientes en la receta

---

`POST localhost:8080/s4_recipes-api/api/recipes/`

```json
{
    "name": "Tacos al pastor",
    "description": "Los originales y únicos",
    "ingredients": [{
                      "name": "Ají",
              		    "calories": 20
                    }, {
                      "name": "Frijol",
              		    "calories": 12000
                    }]
}

Respuesta: 200, ok
```

`POST localhost:8080/s4_recipes-api/api/recipes/`

```json
{
    "name": "Tacos al pastor",
    "description": "Los mismos de antes pero más ricos",
    "ingredients": [{
        "name": "Tomate",
		    "calories": 1000
    }]
}
```

Respuesta: 412, ya existe esa receta
---

Al finalizar suba los cambios y cree un release con el nombre `punto_2`

En el link de sicua suba el link de su repositorio, tenga presente que no se permitirán cambios posterior a la fecha de entrega.
