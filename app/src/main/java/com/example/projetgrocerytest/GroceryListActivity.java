package com.example.projetgrocerytest;

import static com.example.projetgrocerytest.OtherListActivity.items;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projetgrocerytest.adapter.CustomAdapter;
import com.example.projetgrocerytest.adapter.ListViewAdapter;
import com.example.projetgrocerytest.models.Article;
import com.example.projetgrocerytest.models.GroceryList;
import com.example.projetgrocerytest.models.GroceryListParcelable;

import java.util.ArrayList;

public class GroceryListActivity extends AppCompatActivity {
    //mon liste view sur l'interface
    private static ListView listing;

    //mon mockl mour la base de donnée
    private MockDataManager mockDataManager;

    //une liste d'article
    private ArrayList<Article> articles;

    //une liste de string pour le nom des listes qui vient d'une groceryList
    static ArrayList<String> nameOfGroceryList;

    //creation dun arrayAdater pour une liste de string
    private ArrayAdapter<Article> monAdapter;

    private ArrayAdapter<String> monAdapterTypeString;

    //l'adapteur
    public static ListViewAdapter adapter;

    EditText etItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);
        setwidgets();
        setListeners();
    }

    /**
     * Pour le menu
     * @param menu de retour
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.first_fragment_menu, menu);
        return true;
    }

    /**
     * l'action du menu de retour
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toHome:
                Intent returnToMenuIntent =
                        new Intent(getApplicationContext(), MainActivity.class);
                startActivity(returnToMenuIntent);
                break;
        }

        return true;
    }

    private void setListeners() {
        listing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(), "Vous avez cliquer sur le " + i + " element", Toast.LENGTH_LONG).show();
                Intent startActivityResultatActivity =
                        new Intent(getApplicationContext(), ResultatActivity.class);

                GroceryList groceryListTest = new GroceryList("Ma liste d'épicerie" );
                startActivityResultatActivity.putExtra("position", i);
                startActivityResultatActivity.putExtra("name", nameOfGroceryList.get(i));

//                GroceryListParcelable groceryListParcelable = (GroceryListParcelable) adapterView.getItemAtPosition(i);
                GroceryListParcelable groceryListParcelable = new GroceryListParcelable(1,"GrocelistParcelableTest");
                startActivityResultatActivity.putExtra("GroceryListParcelable", groceryListParcelable);

                startActivity(startActivityResultatActivity);
            }

        });
    }

    private void setwidgets() {
        etItem = findViewById(R.id.etItem);

        listing = findViewById(R.id.listing);
        //J'utilise tout ma base de donnée
        ArrayList<GroceryList> listofGroceryList = MockDataManager.CreateListsOfGroceryList();
        nameOfGroceryList = MockDataManager.creationFausseGroceryListeDeDepart(listofGroceryList);

        //-------------juste pour récuperer une liste d'article ---temporaire
        ArrayList<String> nomDesArticle = new ArrayList<>();
        articles = new ArrayList<>();

        for (GroceryList tmp : listofGroceryList
        ) {

            for (Article tmp2 : tmp.getListArticles()
            ) {
                nomDesArticle.add(tmp2.getNom());
            }
            articles = tmp.getListArticles();
            break;
        }
        for (Article tmp : articles
             ) {
            System.out.println(tmp);
        }

        //Adapter
        //le context en paramètre / layout / la list
        adapter = new ListViewAdapter(this, nameOfGroceryList);
        listing.setAdapter(adapter);

    }



    /*
Méthod for addind item
 */
    private void _addItem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GroceryListActivity.this);
        builder.setTitle("Ad New Item");

        View v = LayoutInflater.from(GroceryListActivity.this).inflate(R.layout.item_dialog, null, false);

        builder.setView(v);

        EditText etItem = v.findViewById(R.id.etItem);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!etItem.getText().toString().isEmpty()) {
                    //TODO ajouter opération pour faire un update
                    nameOfGroceryList.add(etItem.getText().toString().trim());
                    GroceryListActivity.adapter.notifyDataSetChanged();
                } else {
                    etItem.setError("add item here !");
                }


            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();

    }

    // function to remove an item given its index in the grocery list.
    public static void removeItem(int i) {
        Toast.makeText(adapter.getContext(), "Bravo" + nameOfGroceryList.get(i) , Toast.LENGTH_SHORT).show();
        nameOfGroceryList.remove(i);
        listing.setAdapter(adapter);
    }

    // function to add an item given its name.
    public static void addItem(String item) {
        nameOfGroceryList.add(item);
        listing.setAdapter(adapter);
    }






}