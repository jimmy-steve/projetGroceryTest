package com.example.projetgrocerytest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.projetgrocerytest.R;
import com.example.projetgrocerytest.models.DiscountedProducts;

import java.util.List;


public class DiscountedProductAdapter extends RecyclerView.Adapter<DiscountedProductAdapter.DiscountedProductViewHolder> {

    Context context;
    List<DiscountedProducts> discountedProductsList;

    /**
     * Constructeur avec paramètre
     *
     * @param context                le contexte de l'application
     * @param discountedProductsList la liste de produit en rabais
     */
    public DiscountedProductAdapter(Context context, List<DiscountedProducts> discountedProductsList) {
        this.context = context;
        this.discountedProductsList = discountedProductsList;
    }

    /**
     * Permet de renvoyer un holder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public DiscountedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.discounted_row_items, parent, false);
        return new DiscountedProductViewHolder(view);
    }

    /**
     * Permey d'ajouter l'image au holder
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull DiscountedProductViewHolder holder, int position) {

        holder.discountImageView.setImageResource(discountedProductsList.get(position).getImageUrl());

    }

    /**
     * Permet d'avoir le nombre d'item
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return discountedProductsList.size();
    }

    /**
     * Permet de construire le holder avec la view
     */
    public static class DiscountedProductViewHolder extends RecyclerView.ViewHolder {
        ImageView discountImageView;

        public DiscountedProductViewHolder(@NonNull View itemView) {
            super(itemView);
            discountImageView = itemView.findViewById(R.id.categoryImage);

        }
    }
}