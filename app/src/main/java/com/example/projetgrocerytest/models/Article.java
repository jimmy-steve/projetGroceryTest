package com.example.projetgrocerytest.models;

/**
 * Classe Name : Article
 * Classe POJO qui permet d'avoir des article avec leur attribut qui est
 * le id(int), le nom(string), la quantité(int), le prix(double)
 *
 * @author Francis Lafontaine
 * @version V1
 * @since 12 novembre 2022
 */
public class Article {
    private int id;
    private String nom;
    private int quantite;
    private double prix;

    public Article() {
    }

    /**
     * Constructeur avec tout les paramètre
     *
     * @param id       de l'aeticle
     * @param nom      le nom de l'article
     * @param quantite le nombre d'article
     * @param prix     le prix de cette article
     */
    public Article(int id, String nom, int quantite, double prix) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", quantite=" + quantite +
                ", prix=" + prix +
                '}';
    }
}
