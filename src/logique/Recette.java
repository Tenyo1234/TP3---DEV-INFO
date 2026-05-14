/**
 * Author : Thomas Labbe
 * Ordre de conception : 2e
 */

package logique;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Author : Mathieu Bourgoin
 * Ordre de conception : 3e
 */
public class Recette
{
    private ArrayList<Ingredient> ingredients;
    private String nom;
    private int difficulte;
    private int pointExperience;

    /**
     * trois ingredients non null, un nom, une difficulte et des point d'experience sont necesaire pour une recette
     *
     * @throws IllegalArgumentException si au moins un des trois ingredients est null
     * @throws IllegalArgumentException si des ingredients sont identiques
     * @param ing1 l'un des ingredients
     * @param ing2 l'un des ingredients
     * @param ing3 l'un des ingredients
     * @param nom le nom de la recette
     * @param difficulte le niveau de difficulte de la recette
     * @param pointExperience la quantite d'experience que la recette donnera
     *
     * Construit un object Recette, qui a un nom, une difficulte et donne une quantite d'experience
     */
    public Recette(Ingredient ing1, Ingredient ing2, Ingredient ing3, String nom, int difficulte, int pointExperience)
    {

        if (ing1 == null || ing2 == null || ing3 == null)
            throw new IllegalArgumentException("Les ingrédients ne peuvent pas être null");

        if (ing1 == ing2 || ing1 == ing3 || ing2 == ing3)  {
            throw new IllegalArgumentException("Les ingrédients ne peuvent pas être identiques");
        }

        this.ingredients = new ArrayList<Ingredient>();
        this.ingredients.add(ing1);
        this.ingredients.add(ing2);
        this.ingredients.add(ing3);

        this.setDifficulte(difficulte);
        this.setNom(nom);
        this.setPointExperience(pointExperience);
    }


    public String getNom() {
        return nom;
    }

    /**
     * Cette methode s'assure que le nom est non null est au dessus de 10 caracteres
     *
     * @throws IllegalArgumentException si le nom est null
     * @throws IllegalArgumentException si le nom a moins de 10 caracteres
     * @param nom le nom de la recette
     *
     * Donne le nom qui sera utlise par le programme
     */
    private void setNom(String nom) {

        if (nom == null)
            throw new IllegalArgumentException("Le nom de la recette ne peut pas être null");

        int longueurNom = nom.length();

        if (longueurNom < 10)
            throw new IllegalArgumentException("Le nom de recette doit contenir au minimum dix caractères");

        this.nom = nom;
    }

    public int getDifficulte() {
        return difficulte;
    }

    /**
     * Une difficulte comprise entre 1 et 5 inclusivement
     *
     * @throws IllegalArgumentException si le param difficulte n'est pas compris entre 1 et 5 inclusivement
     * @param difficulte la difficulte de creation de la recette
     *
     * Donne la difficulte de creation de la recette
     */
    private void setDifficulte(int difficulte) {

        if (!(difficulte <= 5 && difficulte >= 1))
            throw new IllegalArgumentException("La difficulté doit être entre les valeurs 1 et 5 inclusivement");

        this.difficulte = difficulte;
    }

    public int getPointExperience() {
        return pointExperience;
    }

    /**
     * Le nombre d'experience donne doit etre superieur a 0
     *
     * @throws IllegalArgumentException si le param pointExperience est inferieur ou egale a 0
     * @param pointExperience qui seront attribue si la creation de la potion est un reussite
     * Donne le nombre d'experience que la recette creer donnera si reussite
     */
    private void setPointExperience(int pointExperience) {

        if(pointExperience < 0)
            throw new IllegalArgumentException("Le nombre de points d’expérience doit être plus grand que 0");

        this.pointExperience = pointExperience;
    }

    public int obtenirPrix()
    {
        int prixTotal = 0;

        for (Ingredient ing : this.ingredients)
            prixTotal += ing.getPrix();

        return prixTotal;
    }

    /**
     * Recoit un nom qui ne doit pas etre null et recherche dans la list d'ingredient si le nom de
     * l'ingredient souhaite s'y trouve
     *
     * @param nom le nom que l'utilisateur souhaite utilise
     * @throws IllegalArgumentException si le param nom est null
     * @return vrai si l'ingredient est trouve, retourne faux dans le cas contraire
     */
    public boolean contientIngredient(String nom)
    {

        if (nom == null)
            throw new IllegalArgumentException("Le nom de recette ne peut pas être null");

        boolean estContenu = false;

        for (Ingredient ing : this.ingredients)
        {
            if (ing.getNom().equals(nom))
            {
                estContenu = true;
                break;
            }
        }

        return estContenu;
    }
    @Override
    public String toString()
    {
        return String.format("%s|%s|%s|%s|%s|%s", this.getNom(), this.ingredients.get(0).getNom(), this.ingredients.get(1).getNom(), this.ingredients.get(2).getNom(), this.getDifficulte(), this.getPointExperience());
    }
}
