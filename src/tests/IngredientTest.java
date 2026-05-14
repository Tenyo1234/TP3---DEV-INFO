/**
 * Author : Thomas Labbe
 * Ordre de conception : 1e
 */

package tests;

import logique.Alchimiste;
import logique.Ingredient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    private String nomTest;
    private int prixTest;

    @BeforeEach void setUp(){

        nomTest = "Joseph"; //6
        prixTest = 10;
    }

    //SetNom
    @Test
    void siNomNullException(){

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new Ingredient(null, prixTest));

        assertEquals("Le nom d'ingredient ne peut pas être null", e.getMessage());
    }

    @Test
    void siSetNomEstValide(){

        Ingredient i1 = new Ingredient(nomTest, prixTest);

        assertTrue(i1.getNom().length() >= 6);
    }

    @Test
    void siNomTropCourtException(){

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new Ingredient("Bob", prixTest));

        assertEquals("Le nom d'ingredient doit contenir au minimum six caractères", e.getMessage());
    }

    @Test
    void siNomExact5Exception(){

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new Ingredient("Bobby", prixTest));

        assertEquals("Le nom d'ingredient doit contenir au minimum six caractères", e.getMessage());
    }

    //prix potion

    @Test void siPrixSuper0(){

        Ingredient i1 = new Ingredient(nomTest, prixTest);

        assertTrue(i1.getPrix() > 0);
    }

    @Test void siPrixExact1(){

        prixTest = 1;

        Ingredient i1 = new Ingredient(nomTest, prixTest);

        assertEquals(1, i1.getPrix());
    }

    @Test
    void siPrixInfException(){

        prixTest = -10;

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new Ingredient(nomTest, prixTest));

        assertEquals("Le prix doit être superieur a zero", e.getMessage());
    }
}