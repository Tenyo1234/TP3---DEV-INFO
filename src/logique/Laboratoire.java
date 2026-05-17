/**
 * Author : Thomas Labbe
 * Ordre de conception : 3e
 */

package logique;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Laboratoire
{
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Recette> recettes;
    private Alchimiste proprietaire;

    /**
     * Cree un objet Laboratoire en utilisant un object alchimiste
     * @throws IllegalArgumentException si le param alchimiste est null
     * @param alchimiste le proprietaire du laboratoire
     */
    public Laboratoire(Alchimiste alchimiste)
    {

        if (alchimiste == null)
            throw new IllegalArgumentException("alchimiste ne peut pas etre null");

        this.chargerIngredients();
        this.chargerRecettes();

        this.proprietaire = alchimiste;
    }

    /**
     *
     * @return la l'arrayliste d'ingredients
     */
    public List<Ingredient> getIngredients()
    {
        return ingredients;
    }

    /**
     *
     * @return la l'arrayliste de recette
     */
    public List<Recette> getRecettes()
    {
        return recettes;
    }

    /**
     *
     * @return le proprietaire / l'objet alchimiste
     */
    public Alchimiste getProprietaire()
    {
        return proprietaire;
    }

    /**
     * Prend trois ingredients non null pour en faire une potion
     *
     * @param ing1 l'un des ingredients
     * @param ing2 l'un des ingredients
     * @param ing3 l'un des ingredients
     * @throws IllegalArgumentException si l'un des param est null
     * @return le resultat, si reussi ou non
     */
    public ResultatExperience fairePotion(String ing1, String ing2, String ing3)
    {

        if (ing1 == null || ing2 == null || ing3 == null)
            throw new IllegalArgumentException("Aucun ingredients peuvent etre null");

        ResultatExperience experience = new ResultatExperience();

        Recette recette = this.trouverRecette(ing1, ing2, ing3);

        if(recette != null)
        {
            boolean success = this.proprietaire.fairePotion(recette);

            experience.setExiste(true);
            experience.setSuccess(success);
        }

        return experience;
    }

    /**
     * Prend les param necessaire pour une recette,
     * verifie si elle existe deja, si non, cree une nouvelle recette a partir des param
     *
     * @param ing1 l'un des ingredients
     * @param ing2 l'un des ingredients
     * @param ing3 l'un des ingredients
     * @param nom le nom de la recette
     * @param difficulte le niveau de difficulte de creation de la recette
     * @param pointExperience les points experience attribue si reussi
     * @throws IllegalArgumentException si au moins un des param Object est null
     * @return si vrai si la recette existe, retourne faux si elle n'existe pas + la nouvelle recette.
     */
    public ResultatExperience creerNouvellePotion(String ing1, String ing2, String ing3, String nom, int difficulte, int pointExperience)
    {

        if (ing1 == null || ing2 == null || ing3 == null || nom == null)
            throw new IllegalArgumentException("Aucun ingredients ainsi que le nom de recette peuvent etre null");

        ResultatExperience experience = new ResultatExperience();
        experience.setExiste(true);

        Recette recette = this.trouverRecette(ing1, ing2, ing3);

        if(recette == null)
        {
            experience.setExiste(false);
            experience.setSuccess(true);

            Ingredient ingredient1 = this.trouverIngredient(ing1);
            Ingredient ingredient2 = this.trouverIngredient(ing2);
            Ingredient ingredient3 = this.trouverIngredient(ing3);

            recette = new Recette(ingredient1, ingredient2, ingredient3, nom, difficulte, pointExperience);
            ajouterRecette(recette);
        }

        return experience;
    }

    /**
     * Donne les trois ingredients (non null) et la methode cherche si la recette existe
     * ou non.
     *
     * @param ing1 l'un des ingredients
     * @param ing2 l'un des ingredients
     * @param ing3 l'un des ingredients
     * @throws IllegalArgumentException si l'un des param est null
     * @return null si non trouve, la recette si trouve
     */
    public Recette trouverRecette(String ing1, String ing2, String ing3)
    {

        if (ing1 == null || ing2 == null || ing3 == null)
            throw new IllegalArgumentException("Pour trouver une recette, les ingredients ne peuvent etre null");

        Recette resultat = null;

        for(Recette element : this.recettes)
        {
            if (element.contientIngredient(ing1) && element.contientIngredient(ing2) && element.contientIngredient(ing3))
            {
                resultat = element;
                break;
            }
        }

        return resultat;
    }

    /**
     * Donne le nom d'un ingredient pour savoir s'il se trouve dans la liste.
     *
     * @param nom Le nom de la recette
     * @throws IllegalArgumentException si le param est null
     * @return null si non trouves, true si trouves
     */
    public Ingredient trouverIngredient(String nom)
    {

        if(nom == null)
            throw new IllegalArgumentException("Il faut un nom d'ingredients valide");

        Ingredient resultat = null;

        for (Ingredient ing : ingredients)
        {
            if (ing.getNom().equals(nom))
            {
                resultat = ing;
                break;
            }
        }

        return resultat;
    }

    private ArrayList<Ingredient> chargerIngredients()
    {
        ingredients = new ArrayList<Ingredient>();
        List<String> lignesFichier = null;

        try
        {
            Path path = Paths.get("src/ingredients.txt");
            lignesFichier = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Fichier non trouvé");
        }

        for (String ligneFichier : lignesFichier)
        {
            String[] valeurs = ligneFichier.split("\\|");
            String nomIngredient = valeurs[0];
            int prixIngredient = Integer.parseInt(valeurs[1]);

            Ingredient ingredient = new Ingredient(nomIngredient, prixIngredient);
            ingredients.add(ingredient);
        }

        return ingredients;
    }

    private ArrayList<Recette> chargerRecettes()
    {
        recettes = new ArrayList<Recette>();
        List<String> lignesFichier = null;

        try
        {
            Path path = Paths.get("src/recettes.txt");
            lignesFichier = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Fichier non trouvé");
        }

        for (String ligneFichier : lignesFichier)
        {
            String[] valeurs = ligneFichier.split("\\|");
            String nomRecette = valeurs[0];
            Ingredient ingredent1 = this.trouverIngredient(valeurs[1]);
            Ingredient ingredent2 = this.trouverIngredient(valeurs[2]);
            Ingredient ingredent3 = this.trouverIngredient(valeurs[3]);
            int difficulte = Integer.parseInt(valeurs[4]);
            int pointExperience = Integer.parseInt(valeurs[5]);

            Recette recette = new Recette(ingredent1, ingredent2, ingredent3, nomRecette, difficulte, pointExperience);
            recettes.add(recette);
        }

        return recettes;
    }

    private void ajouterRecette(Recette recette)
    {
        String nouvelleRecette = recette.toString();
        try(PrintWriter output = new PrintWriter(new FileWriter("src/recettes.txt",true)))
        {
            output.printf("%s\r\n", nouvelleRecette);
        }
        catch (Exception e){}

        this.recettes.add(recette);
    }
}