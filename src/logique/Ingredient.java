/**
 * Author : Thomas Labbe
 * Ordre de conception : 1e
 */

package logique;

public class Ingredient
{
    private String nom;
    private int prix;

    public Ingredient(String nom, int prix)
    {
        this.setNom(nom);
        this.setPrix(prix);
    }

    public String getNom() {
        return nom;
    }

    /**
     * Cette methode s'assure que le nom est non null est au dessus de 6 caracteres
     *
     * @param nom le nom que l'utilisateur souhaite utilise
     * @throws IllegalArgumentException si le param nom est null
     * @throws IllegalArgumentException si le param nom est plus petit que 6 caractères
     *
     * Donne le nom qui sera utlise par le programme
     */
    private void setNom(String nom) {

        if (nom == null)
            throw new IllegalArgumentException("Le nom ne peut pas être null (ingredient)");

        int longueurNom = nom.length();

        if (longueurNom < 6)
            throw new IllegalArgumentException("Le nom doit contenir au minimum six caractères");

        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    /**
     * L'ingredient se doit d'avoir un prix, il doit donc etre au minimum de 1$
     *
     * @throws IllegalArgumentException si le prix donne n'est pas superieur a 0
     * @param prix le prix unitaire de l'ingredient en question
     *
     * donne un prix pour l'ingredient creer qui est au minimum 1$
     */
    private void setPrix(int prix) {

        if (prix < 0)
            throw new IllegalArgumentException("Le prix doit être superieur a zero");

        this.prix = prix;
    }

    @Override
    public String toString()
    {
        return "Ingredient{" + "nom=" + nom + ", prix=" + prix + '}';
    }
}