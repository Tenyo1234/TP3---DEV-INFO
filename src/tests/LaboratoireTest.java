/**
 * Author : Thomas Labbe
 * Ordre de conception : 5e
 */

package tests;

import logique.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaboratoireTest {

    private Ingredient i1;
    private Ingredient i2;
    private Ingredient i3;

    private Recette r1;

    private Alchimiste doc1;

    Laboratoire labTest;

    @BeforeEach
    void setUp() {
        i1 = new Ingredient("Carotte", 3);
        i2 = new Ingredient("Tomate", 6);
        i3 = new Ingredient("Citrouille", 10);

        r1 = new Recette(i1, i2, i3, "Potion 123", 3, 1);

        doc1 = new Alchimiste("Macaronie", 2);

        labTest = new Laboratoire(doc1);

    }

    //constructeur

    @Test
    void siParamIngNullException(){

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new Laboratoire(null));

        assertEquals("alchimiste ne peut pas etre null", e.getMessage());
    }

    //fairePotion

    @Test
    void siParamIngFairePotionNullException(){

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                labTest.fairePotion("Patate", "Pomme", null));

        assertEquals("Aucun ingredients peuvent etre null", e.getMessage());
    }

    @Test
    void siFairePotionRecetteExist(){

        ResultatExperience rTest =
                labTest.fairePotion("Poudre bleue", "Poudre rose", "Mandagore");

        assertTrue(rTest.getExiste());
    }

    @Test
    void siFairePotionRecetteExistPas(){

        Laboratoire labTest1 = new Laboratoire(doc1);

        ResultatExperience rTest =
                labTest1.fairePotion("Poudre bleue", "Poudre rose", "Pomme");

        assertFalse(rTest.getExiste());
    }

    //creerNouvellePotion

    @Test
    void siParamObjCreerPotionNullException(){

        Laboratoire labTest1 = new Laboratoire(doc1);

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                labTest1.creerNouvellePotion("Liveche", "Armoise", null, "potion de test",
                        1, 1));

        assertEquals("Aucun ingredients ainsi que le nom de recette peuvent etre null", e.getMessage());
    }

    @Test
    void siNouvelleRecetteExistePas(){

        labTest.creerNouvellePotion("Liveche", "Armoise", "Mandagore",
                "Philtre de Test", 1, 1);

        Recette resultat =
                labTest.trouverRecette("Liveche", "Armoise", "Mandagore");

        assertEquals("Philtre de Test", resultat.getNom());

    }

    @Test
    void siNouvelleRecetteExiste(){

        ResultatExperience resultat =
        labTest.creerNouvellePotion("Poudre bleue", "Poudre rose", "Mandagore",
                "Philtre de paix", 3, 250);

        assertTrue(resultat.getExiste());
    }

    //trouverRecette

    @Test
    void siParamIngNullTrouverRecetteException(){

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                labTest.trouverRecette("Eau de miel", "Infusion de verveine", null));

        assertEquals("Pour trouver une recette, les ingredients ne peuvent etre null",
                e.getMessage());
    }

    @Test
    void siRecetteExistePasTrouvRecette(){

        Recette recetteTrouveTest =
            labTest.trouverRecette("Eau de miel", "Mandagore", "Poudre rose");

        //methode retourne null si rien n'est trouve
        assertEquals(null, recetteTrouveTest);
    }

    @Test
    void siRecetteExisteTrouvRecette(){

        Recette recetteTrouveTest =
                labTest.trouverRecette("Eau de miel", "Infusion de verveine", "Liveche");

        assertEquals("Philtre Revigorant", recetteTrouveTest.getNom());
    }

    //trouverIngredients

    @Test
    void siParamIngNullTrouverIngredientException(){

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                labTest.trouverIngredient(null));

        assertEquals("Il faut un nom d'ingredients valide",
                e.getMessage());
    }

    @Test
    void siRecetteExistePasTrouvIngredient(){

        Ingredient ingredientTrouveTest =
                labTest.trouverIngredient("Orange");

        //methode retourne null si rien n'est trouve
        assertEquals(null, ingredientTrouveTest);
    }

    @Test
    void siRecetteExisteTrouvIngredient(){

        Ingredient ingredientTrouveTest =
                labTest.trouverIngredient("Liveche");

        assertEquals("Liveche", ingredientTrouveTest.getNom());
    }


}