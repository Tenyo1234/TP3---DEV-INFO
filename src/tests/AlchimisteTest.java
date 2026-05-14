package tests;

import logique.Alchimiste;
import logique.Ingredient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlchimisteTest {

    //SetNom
    @Test void siNomNullException(){

        assertThrows(IllegalArgumentException.class, () -> new Alchimiste(null));
    }

    @Test void siSetNomEstValide(){

        String nomTest2 = "Joseph"; //6

        Alchimiste a2 = new Alchimiste(nomTest2);

        assertTrue(a2.getNom().length() >= 6);
    }

    @Test void siNomTropCourtException(){

        assertThrows(IllegalArgumentException.class, () -> new Alchimiste("Bob"));
    }

    @Test void siNomExact5Exception(){

        assertThrows(IllegalArgumentException.class, () -> new Alchimiste("Bobby"));
    }

    //Recette
}