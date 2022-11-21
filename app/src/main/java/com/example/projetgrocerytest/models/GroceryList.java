package com.example.projetgrocerytest.models;

import com.example.projetgrocerytest.MockDataManager;

import java.util.ArrayList;

/**
 * Classe GroceryList
 * Une liste contient un nom et a une list d'article de base
 *
 * @author Francis Lafontaine
 * @version V1
 * @since 12 novembre 2022
 */
public class GroceryList {
    private String name;
    private ArrayList<Article> listArticles;

    /**
     * Construction sans paramètre qui permet d'avoir une liste d'article de base
     */
    public GroceryList() {
        this.listArticles = new ArrayList<>();
        //J'ajoute des produit de base dans ma list
        listArticles = MockDataManager.CreateLists();
    }

    /**
     * Constructeur avec le nom de la liste
     * Permet d'avoir Une liste d'épicerie avec un nom et une liste d'article de base
     *
     * @param name de la liste d'épicerie
     */
    public GroceryList(String name) {
        this.name = name;
        this.listArticles = new ArrayList<>();
        //J'ajoute des produit de base dans ma list
        listArticles = MockDataManager.CreateLists();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Article> getListArticles() {
        return listArticles;
    }

    public void setListArticles(ArrayList<Article> listArticles) {
        this.listArticles = listArticles;
    }

    @Override
    public String toString() {
        return "GroceryList{" +
                "name='" + name + '\'' +
                ", listArticles=" + listArticles +
                '}';
    }
}
