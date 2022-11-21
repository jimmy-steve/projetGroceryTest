package com.example.projetgrocerytest.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.projetgrocerytest.GroceryListActivity;
import com.example.projetgrocerytest.MainActivity;
import com.example.projetgrocerytest.R;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<String> {
    ImageView btnMore;
    ArrayList<String> list;
    Context context;

    // The ListViewAdapter Constructor
    // @param context: the Context from the MainActivity
    // @param items: The list of items in our Grocery List
    public ListViewAdapter(Context context, ArrayList<String> items) {
        super(context, R.layout.list_row, items);
        this.context = context;
        list = items;
    }

    // The method we override to provide our own layout for each View (row) in the ListView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_row, null);
            TextView name = convertView.findViewById(R.id.name);
            btnMore = convertView.findViewById(R.id.ic_more);
            name.setText(list.get(position));


            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(context, view);
                    popupMenu.getMenuInflater().inflate(R.menu.pop_up_menu, popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {

                            switch (menuItem.getItemId()) {

                                case R.id.item_update:
                                    //function for update
                                    AlertDialog.Builder builder =
                                            new AlertDialog.Builder(context);
                                    View v = LayoutInflater.from(context)
                                            .inflate(R.layout.item_dialog, null, false);
                                    builder.setTitle("Update Item");
                                    EditText editText = v.findViewById(R.id.etItem);


                                    //TODO j'ai besoin de la position dans la liste pour faire fonctionner le delete et update
                                    editText.setText(list.get(position));

                                    //set custom view to dialog
                                    builder.setView(v);


                                    builder.setPositiveButton("Update",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int j) {
                                                    //function for update
                                                    AlertDialog.Builder builder =
                                                            new AlertDialog.Builder(context.getApplicationContext());
                                                    View v = LayoutInflater.from(context.getApplicationContext())
                                                            .inflate(R.layout.item_dialog, null, false);
                                                    builder.setTitle("Update Item");
                                                    EditText editText = v.findViewById(R.id.etItem);
                                                    editText.setText(list.get(position));

                                                    //set custom view to dialog
                                                    builder.setView(v);

                                                    if (!editText.getText().toString().isEmpty()) {
                                                        //TODO ici aussi il faut ajouter un élément dans la liste
                                                        list.set(position, editText.getText().toString().trim());
                                                        //--------il faut informer le changement a l'adapter qui est dans lautre class
                                                        GroceryListActivity.adapter.notifyDataSetChanged();
                                                        Toast.makeText(context.getApplicationContext(),
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

                                case R.id.item_del://----------si on veut deleter la liste
                                    //TODO il faut deleter une une liste ici
                                    Toast.makeText(context.getApplicationContext(),
                                            "Item Deleted at position : " + position, Toast.LENGTH_LONG).show();
//                                    list.remove(position);
                                    GroceryListActivity.removeItem(position);
                                    //--------il faut informer le changement a l'adapter qui est dans lautre classe
                                    GroceryListActivity.adapter.notifyDataSetChanged();
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
        return convertView;
    }


}
