package com.example.projetgrocerytest;

import static com.example.projetgrocerytest.MockDataManager.CreateListsOfGroceryList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetgrocerytest.adapter.CustomAdapter;
import com.example.projetgrocerytest.adapter.DiscountedProductAdapter;
import com.example.projetgrocerytest.adapter.GroceryListAdapter;
import com.example.projetgrocerytest.adapter.ListViewAdapter;
import com.example.projetgrocerytest.models.Article;
import com.example.projetgrocerytest.models.DiscountedProducts;
import com.example.projetgrocerytest.models.GroceryList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

public class MainActivity extends AppCompatActivity {

    //Array list for dataView------juste pour la vue
    ArrayList<String> list = new ArrayList<>();

    //test Array liste de GroceryList for data------pour avoir l'ensemble des donnée
    ArrayList<GroceryList> liftOfGroceryList = new ArrayList<>();


    //la liste view qui est dans le xml
    ListView list_view;
    //Adapter
    ArrayAdapter arrayAdapter;


    //pour le recyclerView du premier panneau des rabais
    RecyclerView discountRecyclerView;
    DiscountedProductAdapter discountedProductAdapter;
    List<DiscountedProducts> discountedProductsList;

    //pour le bouter ajouter
    Button btnAjouter;
    //pour le nom de la liste
    TextView txtName;




    //le fait d'être dans le mode édition
    public static boolean isActionMode = false;
    public static ArrayList<Article> userSelectionList = new ArrayList<>();
    public static ActionMode actionModeList = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWidgets();
        setListeners();

    }

    private void setListeners() {
        //listerner pour le bouton ajouter
        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtName.getText().toString().isEmpty()) {

                    //je créé un liste d'épicerie
                    GroceryList myGroceryList = new GroceryList();
                    System.out.println(txtName.getText().toString());
                    myGroceryList.setName(txtName.getText().toString());

                    //vérification de ce que contient mon objet
                    System.out.println(myGroceryList);

                    //j'ajoute au nom de la liste en mémoire
                    list.add(txtName.getText().toString().trim());


                    list.add(myGroceryList.getName());


                    //je notifier l'aadapter
                    arrayAdapter.notifyDataSetChanged();
                    txtName.setText("");
                } else {
                    txtName.setError("add item here !");
                }
            }
        });

        //listener our le listView
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {

                            case R.id.item_update:
                                //function for update
                                AlertDialog.Builder builder =
                                        new AlertDialog.Builder(MainActivity.this);
                                View v = LayoutInflater.from(MainActivity.this)
                                        .inflate(R.layout.item_dialog, null, false);
                                builder.setTitle("Update Item");
                                EditText editText = v.findViewById(R.id.etItem);
                                editText.setText(list.get(i));

                                //set custom view to dialog
                                builder.setView(v);


                                builder.setPositiveButton("Update",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int j) {

                                                if (!editText.getText().toString().isEmpty()) {
                                                    list.set(i, editText.getText().toString().trim());

                                                    arrayAdapter.notifyDataSetChanged();
                                                    Toast.makeText(MainActivity.this,
                                                            "Item Updated", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    editText.setError("add item here !");
                                                }
                                            }
                                        });


                                builder.setNegativeButton("Cancel",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        });
                                builder.show();

                                break;
                            case R.id.item_del:
                                Toast.makeText(MainActivity.this,
                                        "Item Deleted", Toast.LENGTH_LONG).show();
                                list.remove(i);
                                arrayAdapter.notifyDataSetChanged();

                                break;
                        }

                        return true;
                    }
                });

                //Ne pas oublier sa
                popupMenu.show();

            }
        });


    }

    private void setWidgets() {
        // je connecter les widget avec leur id

        //pour le button
        btnAjouter = findViewById(R.id.btnAjouter);
        //pour le txt du nom de la liste
        txtName = findViewById(R.id.txtName);

        //----------------------------------recyclerView----- bloc rabais
        discountRecyclerView = findViewById(R.id.discountedRecycler);

        //creation de la list de discount pour le recyclerView
        discountedProductsList = new ArrayList<>();
        discountedProductsList = createListDiscountedProducts();

        //création d'une fausse liste de départ

        //-----------------la liste pour l'"option 1
        list = MockDataManager.creationFausseListeDeDepart();//version simple

        //-------------------la liste pour l'option 2
        liftOfGroceryList = CreateListsOfGroceryList();
        System.out.println("----------------------");
        System.out.println(liftOfGroceryList);
        //Je récupère juste les nom des liste pour pouvoir les afficher
        //list = MockDataManager.creationFausseGroceryListeDeDepart(liftOfGroceryList);


        // je set le recycler avec sa liste
        setDiscountedRecycler(discountedProductsList);

        //la listview
        list_view = findViewById(R.id.list_view);
        //-----------------------------------------------------je relie les 2 ensemble
        //TODO C'est ici que sa se passe il faut choisir le bon adapteur
        //------------------Option 1
        arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, list);

        //---------------------------------Option 2
        //TODO option de ligne checked
        //CustomAdapter arrayAdapter = new CustomAdapter(getApplicationContext(), list);

        //-------------------------------------------------------Option 3
        //GroceryListAdapter arrayAdapter = new GroceryListAdapter(MainActivity.this, list);



        list_view.setAdapter(arrayAdapter);
    }


    public <List> ArrayList<DiscountedProducts> createListDiscountedProducts() {
        ArrayList<DiscountedProducts> list = new ArrayList<>();
        list.add(new DiscountedProducts(1, R.drawable.discountberry));
        list.add(new DiscountedProducts(2, R.drawable.discountbrocoli));
        list.add(new DiscountedProducts(3, R.drawable.discountmeat));
        list.add(new DiscountedProducts(4, R.drawable.discountberry));
        list.add(new DiscountedProducts(5, R.drawable.discountbrocoli));
        list.add(new DiscountedProducts(6, R.drawable.discountmeat));

        return list;
    }

    /**
     * Méthode pour setter le Discounted Recycler
     *
     * @param dataList qui est un list de catégorie avec une icone
     */
    private void setDiscountedRecycler(List<DiscountedProducts> dataList) {
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        discountRecyclerView.setLayoutManager(layoutManager);
        discountedProductAdapter = new DiscountedProductAdapter(this, dataList);
        discountRecyclerView.setAdapter(discountedProductAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                //function to add
                _addItem();
                break;
            case R.id.versLaListe:
                _versLaListe();
                break;
            case R.id.versLaListe2:
                _versLaListe2();
                break;
            case R.id.versMainActivityMain2:
                _versMainActivityMain2();
                break;

        }

        return true;
    }

    private void _versMainActivityMain2() {
        Intent startActivityGroceryList =
                new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(startActivityGroceryList);
    }

    private void _versLaListe2() {
        //pour aller vers la liste 2 pour faire des test
        Intent startActivityGroceryList =
                new Intent(getApplicationContext(), OtherListActivity.class);
        startActivity(startActivityGroceryList);
    }

    private void _versLaListe() {
        Intent startActivityGroceryList =
                new Intent(getApplicationContext(), GroceryListActivity.class);
        startActivity(startActivityGroceryList);
    }

    /*
    Méthod for addind item
     */
    private void _addItem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Ad New Item");

        View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_dialog, null, false);

        builder.setView(v);

        EditText etItem = v.findViewById(R.id.etItem);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!etItem.getText().toString().isEmpty()) {
                    list.add(etItem.getText().toString().trim());
                    arrayAdapter.notifyDataSetChanged();
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
}