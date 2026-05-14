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

        assertThrows(IllegalArgumentException.class, () -> new Ingredient(null, prixTest));
    }

    @Test void siSetNomEstValide(){

        Ingredient i1 = new Ingredient(nomTest, prixTest);

        assertTrue(i1.getNom().length() >= 6);
    }

    @Test void siNomTropCourtException(){

        assertThrows(IllegalArgumentException.class, () -> new Ingredient("Bob", prixTest));
    }

    @Test void siNomExact5Exception(){

        assertThrows(IllegalArgumentException.class, () -> new Ingredient("Bobby", prixTest));
    }

    //prix potion

    @Test void siPrixSuper0(){

        Ingredient i1 = new Ingredient("Joseph", prixTest);

        assertTrue(i1.getPrix() > 0);
    }

    @Test void siPrixExact1(){

        prixTest = 1;

        Ingredient i1 = new Ingredient(nomTest, prixTest);
    }

    @Test void siPrixInfException(){

        prixTest = -10;

        assertThrows(IllegalArgumentException.class, () -> new Ingredient(nomTest, prixTest));
    }
}