package com.example.projetgrocerytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.projetgrocerytest.adapter.ListViewAdapterOriginal;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.projetgrocerytest.databinding.ActivityOtherListBinding;

import java.util.ArrayList;

public class OtherListActivity extends AppCompatActivity {
    TextView txtBudget, lblAvailableAmountResult, lblTotalAmountResult;
    ImageButton btnListItemAdd;

    //---------------------------pour le listView
    static ListView listViewList;
    static ListViewAdapterOriginal adapter;
    static ArrayList<String> items;
    //----------------------------

    private AppBarConfiguration appBarConfiguration;
    private ActivityOtherListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOtherListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setWidgets();
        setListeners();
        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_other_list);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setListeners() {
        btnListItemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String v_txtBudget = txtBudget.getText().toString();
                lblTotalAmountResult.setText(v_txtBudget);
                lblAvailableAmountResult.setText(v_txtBudget);
                Toast.makeText(OtherListActivity.this, "Votre budget est de : " + v_txtBudget, Toast.LENGTH_SHORT).show();
                txtBudget.setText("");
            }
        });
    }

    private void setWidgets() {
        txtBudget = findViewById(R.id.txtBudget);
        btnListItemAdd = findViewById(R.id.btnListItemAdd);
        lblTotalAmountResult = findViewById(R.id.lblTotalAmountResult);
        lblAvailableAmountResult = findViewById(R.id.lblAvailableAmountResult);

        listViewList = findViewById(R.id.listViewList);

        // add hardcoded items to grocery list
        items = new ArrayList<>();
        items.add("Apple");
        items.add("Banana");
        items.add("Orange");
        items.add("Strawberry");
        items.add("Kiwi");


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_other_list);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
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
                        new Intent(getApplicationContext(), MainActivity.class);
                startActivity(returnToMenuIntent);
                break;
        }

        return true;
    }

    // function to remove an item given its index in the grocery list.
    public static void removeItem(int i) {
        items.remove(i);
        listViewList.setAdapter(adapter);
    }

    // function to add an item given its name.
    public static void addItem(String item) {
        items.add(item);
        listViewList.setAdapter(adapter);
    }

    // function to make a Toast given a string
    static Toast t;

}