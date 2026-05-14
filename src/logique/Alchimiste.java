package logique;

import java.util.Random;

public class Alchimiste
{
    public static final int EXPERIENCE_POUR_NIVEAU_SUIVANT = 500;
    private String nom;
    private int niveau;
    private int experience;

    public Alchimiste(String nom)
    {
        this.setNom(nom);
        this.setNiveau(1);
        this.setExperience(0);
    }

    public Alchimiste(String nom,int niveau)
    {
        this(nom);
        setNiveau(niveau);
    }

    public String getNom()
    {
        return this.nom;
    }
    public int getNiveau()
    {
        return this.niveau;
    }
    public int getExperience()
    {
        return this.experience;
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
    private void setNom(String nom)
    {

        if (nom == null)
            throw new IllegalArgumentException("Le nom ne peut pas être null");

        int longueurNom = nom.length();

        if (longueurNom < 6)
            throw new IllegalArgumentException("Le mot de pass doit contenir au minimum six caractères");

        this.nom = nom;
    }
    private void setNiveau(int niveau)
    {
        this.niveau = niveau;
    }
    private void setExperience(int experience)
    {
        this.experience = experience;
    }

    /**
     * Cette methode sert a creer une potion a partir du recette. Elle donne aussi
     * un quantite d'experience si reussi
     *
     * @param recette est le nom de la recette que l'utilisateur souhaite faire
     * @throws IllegalArgumentException si le param recette est null
     * @return vrai si la recette a ete un succes, retourne faux dans le cas contraire
     *
     * La potion sera fait ou non selon un nombre aleatoire.
     */
    public boolean fairePotion(Recette recette)
    {

        if (recette == null)
            throw new IllegalArgumentException("La recette ne peut pas être null");

        boolean estReussi = false;
        double tauxExperience = this.niveau * 0.05;
        double tauxEchec = (recette.getDifficulte() * 0.25) - tauxExperience;

        if (tauxEchec < 0)
            tauxEchec = 0;

        Random random = new Random();
        double jetSuccess = ((double) random.nextInt(1, 101))/100;

        if (jetSuccess >= tauxEchec)
        {
            int nbExperience = recette.getPointExperience();
            this.ajouterExperience(nbExperience);
            estReussi = true;
        }

        return estReussi;
    }

    private void ajouterExperience(int experience)
    {
        this.setExperience(this.getExperience() + experience);

        if(this.getExperience() >= EXPERIENCE_POUR_NIVEAU_SUIVANT)
        {
            this.setNiveau(this.getNiveau() + 1);
            this.setExperience(this.getExperience() - EXPERIENCE_POUR_NIVEAU_SUIVANT);
            System.out.println("Vous êtes maintenant un alchimiste de niveau " + this.getNiveau());
        }
    }

}
