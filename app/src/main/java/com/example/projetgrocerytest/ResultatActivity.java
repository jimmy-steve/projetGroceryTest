package com.example.projetgrocerytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.projetgrocerytest.adapter.ArticleAdapter;
import com.example.projetgrocerytest.models.Article;
import com.example.projetgrocerytest.models.GroceryListParcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ResultatActivity extends AppCompatActivity {
    private TextView lblPosition;
    private TextView lblName;
    private GroceryListParcelable groceryListParcelable;

    //pour la liste d'article
    RecyclerView recyclerListArticle;
    ArticleAdapter articleAdapter;
    List<Article> articleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        //--------------------------------pour setter les element
        setWidgets();

        //--------------------------------pour recevoir les donnée
        Intent intentReceived = getIntent();

        //--------------------------------pour recupérer chaque donnée
        int positionDeLaliste = intentReceived.getIntExtra("position", 0);
        String listName = intentReceived.getStringExtra("name");

        //---------------/test de parcelable
//        groceryListParcelable = getIntent().getParcelableExtra("GroceryListParcelable");
//        String name1 = groceryListParcelable.getName();
//        System.out.println(name1);

        //--------------------------------pour setter les nouvelle donnée
        lblPosition.setText(String.valueOf("Cette Liste est a la position # (" +positionDeLaliste+ ")"));
        lblName.setText(listName);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.first_fragment_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toHome:
                Intent returnToMenuIntent =
                        new Intent(getApplicationContext(), GroceryListActivity.class);
                startActivity(returnToMenuIntent);
                break;
        }

        return true;
    }

    private void setWidgets() {
        lblPosition = findViewById(R.id.lblPosition);
        lblName = findViewById(R.id.lblName);

        //pour la liste d'article
        recyclerListArticle = findViewById(R.id.recyclerListArticle);
        articleList = new ArrayList<>();
        articleList = MockDataManager.CreateLists();
        setArticleRecycler(articleList);

        // récupérer les TableLayout
//        TableLayout suiviCourantTable = (TableLayout) findViewById(R.id.TableLayout_Courant);
//        // ajouter les données
//        addDataCourant(suiviCourantTable);


    }

    private void setArticleRecycler(List<Article> articleList) {
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerListArticle.setLayoutManager(layoutManager);
        articleAdapter = new ArticleAdapter(this, articleList);
        recyclerListArticle.setAdapter(articleAdapter);
    }

//    private void addTextToRowWithValues(TableRow headerRow, String string, int textColor, float textSize) {
//        TextView textView = new TextView(this);
//        textView.setTextSize(textSize);
//        textView.setTextColor(textColor);
//        textView.setText(string);
//        headerRow.addView(textView);
//    }

//    private void addDataCourant(TableLayout sTable) {
//        // Ajouter donn�es �chantillons pour le moment
//        Integer[] listQuantite = {2, 1, 4, 3, 12};
//        Double[] listPrixArticle = {2.99, 3.99, 12.50, 4.77, 9.00};
//        String[] listeNomArticle = {"Banane", "Bleuet", "Chocolat", "Guimauve","Blé d'indes"};
//
//        int length = listQuantite.length;
//        for (int i = 0; i < length; i++) {
//            insertDataRow(sTable, listeNomArticle[i], listQuantite[i], listPrixArticle[i]);
//        }
//    }

//    private void insertDataRow(TableLayout sTable, String name, Integer quantite, Double price) {
//        final TableRow newRow = new TableRow(this);
//        int textColor = getResources().getColor(R.color.purple_700);
//        float textSize = getResources().getDimension(R.dimen.help_text_size);
//        addTextToRowWithValues(newRow, name, textColor, textSize);
//        addTextToRowWithValues(newRow, quantite.toString(), textColor, textSize);
//        addTextToRowWithValues(newRow, price.toString(), textColor, textSize);
//
//        sTable.addView(newRow);
//
//    }
}