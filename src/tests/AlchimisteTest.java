/**
 * Author : Thomas Labbe
 * Ordre de conception : 3e
 */

package tests;

import logique.Alchimiste;
import logique.Ingredient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlchimisteTest {

    //SetNom

    @Test
    void siNomNullException(){

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new Alchimiste(null));

        assertEquals("Le nom d'alchimiste ne peut pas être null", e.getMessage());
    }

    @Test void siSetNomEstValide(){

        String nomTest2 = "Joseph"; //6

        Alchimiste a = new Alchimiste(nomTest2);

        assertTrue(a.getNom().length() >= 6);
    }

    @Test
    void siNomTropCourtException(){

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new Alchimiste("Bob"));

        assertEquals("Le nom d'alchimiste doit contenir au minimum six caractères", e.getMessage());
    }

    @Test
    void siNomExact5Exception(){

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new Alchimiste("Bobby"));

        assertEquals("Le nom d'alchimiste doit contenir au minimum six caractères", e.getMessage());
    }

    //Recette
}