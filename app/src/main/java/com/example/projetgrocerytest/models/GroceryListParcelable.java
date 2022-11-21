package com.example.projetgrocerytest.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class GroceryListParcelable  implements Parcelable {
    private int id;
    private String name;
    List<Article> articles;


    public GroceryListParcelable() {
    }

    public GroceryListParcelable(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public GroceryListParcelable(Parcel in){
        id = in.readInt();
        name = in.readString();

    }

    public static final Creator<GroceryListParcelable> CREATOR = new Creator<GroceryListParcelable>() {
        @Override
        public GroceryListParcelable createFromParcel(Parcel in) {
            return new GroceryListParcelable(in);
        }

        @Override
        public GroceryListParcelable[] newArray(int size) {
            return new GroceryListParcelable[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
    }

    @Override
    public String toString() {
        return "GroceryListParcelable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", articles=" + articles +
                '}';
    }
}
