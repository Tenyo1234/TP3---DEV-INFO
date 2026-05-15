/**
 * Author : Thomas Labbe
 * Ordre de conception : 3e
 */

package tests;

import logique.Alchimiste;
import logique.Ingredient;
import logique.Recette;
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

    //FairePotion

    @Test
    void siParamRecetteNullException(){

        Alchimiste aTest = new Alchimiste("Joseph", 1);

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                aTest.fairePotion(null));

        assertEquals("La recette de potion ne peut pas être null", e.getMessage());
    }

    @Test
    void fairePotionEchecGarantie(){
        //diff 5, lvl 1 -> fail
        Alchimiste aTest = new Alchimiste("Joseph", 1);

        Ingredient i1 = new Ingredient("Carotte", 3);
        Ingredient i2 = new Ingredient("Tomate", 6);
        Ingredient i3 = new Ingredient("Citrouille", 10);

        Recette rTest = new Recette(i1, i2, i3, "Potion legumes", 5, 30);

        boolean test = aTest.fairePotion(rTest);

        assertFalse(test);

    }

    @Test
    void fairePotionReussieGarantie(){

        Alchimiste aTest = new Alchimiste("Joseph", 5);

        Ingredient i1 = new Ingredient("Carotte", 3);
        Ingredient i2 = new Ingredient("Tomate", 6);
        Ingredient i3 = new Ingredient("Citrouille", 10);

        Recette rTest = new Recette(i1, i2, i3, "Potion legumes", 1, 30);

        boolean test = aTest.fairePotion(rTest);

        assertTrue(test);

    }

    //Ajouter potion

    @Test
    void siAlchimisteMonteDeNiveau(){

        Alchimiste aTest = new Alchimiste("Joseph", 5);

        Ingredient i1 = new Ingredient("Carotte", 3);
        Ingredient i2 = new Ingredient("Tomate", 6);
        Ingredient i3 = new Ingredient("Citrouille", 10);

        Recette rTest = new Recette(i1, i2, i3, "Potion legumes", 1, 500);

        int niveauInitial = aTest.getNiveau();

        aTest.fairePotion(rTest);

        assertTrue(aTest.getNiveau() > niveauInitial);
    }

    @Test //cas limite avec 499
    void siAlchimistePasAssezXpPourMonteNiveau(){

        Alchimiste aTest = new Alchimiste("Joseph", 5);

        Ingredient i1 = new Ingredient("Carotte", 3);
        Ingredient i2 = new Ingredient("Tomate", 6);
        Ingredient i3 = new Ingredient("Citrouille", 10);

        Recette rTest = new Recette(i1, i2, i3, "Potion legumes", 1, 499);

        int niveauInitial = aTest.getNiveau();

        aTest.fairePotion(rTest);

        assertEquals(niveauInitial, aTest.getNiveau());
    }

}