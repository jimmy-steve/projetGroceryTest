package com.example.projetgrocerytest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.Toast;

import com.example.projetgrocerytest.R;
import com.example.projetgrocerytest.models.Article;
import com.example.projetgrocerytest.models.GroceryList;

import java.util.ArrayList;

public class GroceryListAdapter extends BaseAdapter {
    GroceryList myGroceryList;
    Context context;
    LayoutInflater inflter;
    String value;

    public GroceryListAdapter(Context context, GroceryList myGroceryList) {
        this.context = context;
        this.myGroceryList = myGroceryList;
        inflter = (LayoutInflater.from(context));

    }

    @Override
    public int getCount() {
        return myGroceryList.getListArticles().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflter.inflate(R.layout.test, null);
        final CheckedTextView simpleCheckedTextView = (CheckedTextView) view.findViewById(R.id.simpleCheckedTextView);
//        simpleCheckedTextView.setText( myGroceryList.get(position));
        // perform on Click Event Listener on CheckedTextView
        simpleCheckedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (simpleCheckedTextView.isChecked()) {
                    // set cheek mark drawable and set checked property to false
                    value = "un-Checked";
                    simpleCheckedTextView.setCheckMarkDrawable(0);
                    simpleCheckedTextView.setChecked(false);
                } else {
                    // set cheek mark drawable and set checked property to true
                    value = "Checked";
                    simpleCheckedTextView.setCheckMarkDrawable(R.drawable.checked);
                    simpleCheckedTextView.setChecked(true);
                }
                Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}

