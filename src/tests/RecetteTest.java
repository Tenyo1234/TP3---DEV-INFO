/**
 * Author : Thomas Labbe
 * Ordre de conception : 2e
 */

package tests;

import logique.Ingredient;
import logique.Recette;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecetteTest {

    private Ingredient i1;
    private Ingredient i2;
    private Ingredient i3;

    private Recette r1;

    @BeforeEach
    void setUp() {
        i1 = new Ingredient("Carotte", 3);
        i2 = new Ingredient("Tomate", 6);
        i3 = new Ingredient("Citrouille", 10);

        r1 = new Recette(i1, i2, i3, "Potion 123", 3, 1);

    }

    //constructeur

    @Test
    void siIngredientNull() {

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new Recette(null, i1, i2, "Potion de legumes", 3, 12));

        assertEquals("Les ingrédients ne peuvent pas être null", e.getMessage());
    }

    @Test
    void siIngredientIdentiques() {

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new Recette(i1, i2, i2, "Potion de legumes", 3, 12));

        assertEquals("Les ingrédients ne peuvent pas être identiques", e.getMessage());
    }

    //SetNom

    @Test
    void siNomNullException() {

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new Recette(i1, i2, i3, null, 5, 23));

        assertEquals("Le nom de la recette ne peut pas être null", e.getMessage());
    }

    @Test
    void siNomLongueurValide() {

        //10
        Recette r2 = new Recette(i1, i2, i3, "0123456789", 1, 1);
        //11
        Recette r3 = new Recette(i1, i2, i3, "01234567890", 1, 1);


        assertTrue(r2.getNom().length() >= 10);
        assertTrue(r3.getNom().length() >= 10);
    }

    @Test
    void siNom9ExactException() {

        //9
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new Recette(i1, i2, i3, "123456789", 1, 1));

        assertEquals("Le nom de recette doit contenir au minimum dix caractères", e.getMessage());
    }

    @Test
    void siNomTropCourtException() {

        //5
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new Recette(i1, i2, i3, "12345", 1, 1));

        assertEquals("Le nom de recette doit contenir au minimum dix caractères", e.getMessage());
    }

    //setDifficulte

    @Test
    void siDiffEntre1Et5(){

        Recette rTest = new Recette(i1, i2, i3, "0123456789", 4, 1);

        assertTrue(rTest.getDifficulte() >= 1);
        assertTrue(rTest.getDifficulte() <= 5);
    }

    @Test
    void siDiffMoins1Exception(){

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new Recette(i1, i2, i3, "1234567890", 0, 1));

        assertEquals("La difficulté doit être entre les valeurs 1 et 5 inclusivement", e.getMessage());
    }

    @Test
    void siDiffPlus5Exception(){

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new Recette(i1, i2, i3, "1234567890", 6, 1));

        assertEquals("La difficulté doit être entre les valeurs 1 et 5 inclusivement", e.getMessage());
    }

    //setpointExperience

    @Test
    void ExpInferieur0Exception(){

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new Recette(i1, i2, i3, "1234567890", 1, -1));

        assertEquals("Le nombre de points d’expérience doit être plus grand que 0", e.getMessage());
    }

    @Test
    void ExpEgale0Exception (){

        Recette rTest = new Recette(i1, i2, i3, "0123456789", 4, 0);

        assertTrue(rTest.getPointExperience() == 0);
    }

    @Test
    void ExpSuperieur0 (){

        assertTrue(r1.getPointExperience() > 0);
    }

    //obtenirPrix

    @Test
    void siObtenirPrixRetourneLeBonPrix(){
//        i1 > 3, i2 > 6, i3 > 10
        assertEquals(19, r1.obtenirPrix());
    }

    //contientIngredient

    @Test
    void siNomContIngrNull(){

            Exception e = assertThrows(IllegalArgumentException.class, () ->
                    r1.contientIngredient(null));

            assertEquals("Le nom de recette ne peut pas être null", e.getMessage());
        }

    }
