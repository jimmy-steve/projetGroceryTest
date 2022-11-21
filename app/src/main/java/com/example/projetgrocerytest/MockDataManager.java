package com.example.projetgrocerytest;

import com.example.projetgrocerytest.models.Article;
import com.example.projetgrocerytest.models.GroceryList;

import java.util.ArrayList;

/**
 * Classe MockDataManager
 * Permet de simuler des accès a une base de donnée
 *
 * @author Francis lafontaine
 * @V1
 * @since 12/novembre/2022
 */
public class MockDataManager {
    private ArrayList<Article> articles;
    private ArrayList<GroceryList> groceryLists;

    /**
     * Permet d'instancier une liste d'article de base a la création
     */
    public MockDataManager() {
        articles = CreateLists();
    }

    /**
     * Permet d'avoir acces a la liste d'article
     *
     * @return une liste d'article
     */
    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    /**
     * Name : CreateLists
     * Permet de création une liste d'article de base
     *
     * @return une liste de6 article de base avec leur attribut
     */
    public static ArrayList<Article> CreateLists() {
        ArrayList<Article> fakeList = new ArrayList<>();
        Article article = new Article(1, "Banane", 6, 1.22);
        Article article2 = new Article(2, "Chocolat", 1, 12.50);
        Article article3 = new Article(3, "Pain", 6, 4.55);
        Article article4 = new Article(4, "Bière", 1, 35.50);
        Article article5 = new Article(5, "Orange", 4, 5.99);
        Article article6 = new Article(6, "Chou-fleur", 1, 4.55);
        Article article7 = new Article(7, "Lait de poule", 6, 1.22);
        Article article8 = new Article(8, "Chocolat", 1, 12.50);
        Article article9 = new Article(9, "Pain à Hamburger", 6, 4.55);
        Article article10 = new Article(10, "Bière", 1, 35.50);
        Article article11 = new Article(11, "Orange", 4, 5.99);
        Article article12 = new Article(12, "Chou-fleur", 1, 4.55);

        fakeList.add(article);
        fakeList.add(article2);
        fakeList.add(article3);
        fakeList.add(article4);
        fakeList.add(article5);
        fakeList.add(article6);
        fakeList.add(article7);
        fakeList.add(article8);
        fakeList.add(article9);
        fakeList.add(article10);
        fakeList.add(article11);
        fakeList.add(article12);
        return fakeList;
    }

    /**
     * Name : CreateListsDifferend
     * Permet de création une liste d'article de base différend de lautre
     *
     * @return une liste de 2 article de base avec leur attribut
     */
    public static ArrayList<Article> CreateListsDifferend() {
        ArrayList<Article> fakeList = new ArrayList<>();
        Article article = new Article(1, "Banane", 6, 1.22);
        Article article2 = new Article(2, "Chocolat", 1, 12.50);

        fakeList.add(article);
        fakeList.add(article2);
        return fakeList;
    }

    /**
     * Name : CreateListOfGroceryList
     * Permet d'avoir en mémoire plusieurs liste de GroceryList avec plusieurs
     * liste d'article avec leur attribut
     *
     * @return une liste de GroceryList
     */
    public static ArrayList<GroceryList> CreateListsOfGroceryList() {
        ArrayList<GroceryList> fakeList = new ArrayList<>();

        GroceryList groceryList1 = new GroceryList("Ma Liste 1");
        GroceryList groceryList2 = new GroceryList("Ta Liste 2");
        GroceryList groceryList3 = new GroceryList("Votre Liste 3");
        GroceryList groceryList4 = new GroceryList("Notre Liste 4");

        GroceryList groceryList5 = new GroceryList("Ma Liste 5");
        GroceryList groceryList6 = new GroceryList("Ta Liste 6");
        GroceryList groceryList7 = new GroceryList("Votre Liste 7");
        GroceryList groceryList8 = new GroceryList("Notre Liste 8");

        GroceryList groceryList9 = new GroceryList("Ma Liste 9");
        GroceryList groceryList10 = new GroceryList("Ta Liste 10");
        GroceryList groceryList11 = new GroceryList("Votre Liste 11");
        GroceryList groceryList12 = new GroceryList("Notre Liste 12");

        fakeList.add(groceryList1);
        fakeList.add(groceryList2);
        fakeList.add(groceryList3);
        fakeList.add(groceryList4);

        fakeList.add(groceryList5);
        fakeList.add(groceryList6);
        fakeList.add(groceryList7);
        fakeList.add(groceryList8);

        fakeList.add(groceryList9);
        fakeList.add(groceryList10);
        fakeList.add(groceryList11);
        fakeList.add(groceryList12);

        return fakeList;
    }

    /**
     * CreationFausseListeDeDepart
     * Permet d'avoir une liste de string pour l'affiche des liste
     *
     * @param <List> de type string
     * @return une liste de string qui est les nom des liste de groceryList
     */
    public static <List> ArrayList<String> creationFausseListeDeDepart() {
        ArrayList<String> list = new ArrayList<>();

        list.add("Ma Liste 1");
        list.add("Ma liste 2");
        list.add("Ma liste du 31 décembre");
        list.add("Ma liste d'épicerie");
        list.add("Ma liste d'épicerie2");
        list.add("Ma liste d'épicerie3");
        list.add("Ma liste d'épicerie4");
        list.add("Ma liste d'épicerie5");

        return list;
    }

    /**
     * Name CreationFauisseGroceryListeDeDepart
     * Permet d'aller chercher les nom  des liste de groceryList
     *
     * @param listOfGroceryList une liste de GroceryList
     * @param <List>            de groceryList
     * @return une liste de string qui est les nom des groceryList
     */
    public static <List> ArrayList<String> creationFausseGroceryListeDeDepart(ArrayList<GroceryList> listOfGroceryList) {

        ArrayList<String> list = new ArrayList<>();

        for (GroceryList tmp : listOfGroceryList
        ) {
            list.add(tmp.getName());
        }

        return list;
    }
}
