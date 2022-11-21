package com.example.projetgrocerytest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetgrocerytest.R;
import com.example.projetgrocerytest.models.Article;

import java.util.List;

/**
 * Name: ArticleAdapter
 * Permet de faire l'adapteur du recycler view
 *
 * @author Francis Lafontaine
 * @version V1
 * @since 17 novembre 2002
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    //prend le context actuel ---- l'activité
    static Context context;

    //une liste de produit
    static List<Article> articleList;

    /**
     * Constructeur sans paramètre
     */
    public ArticleAdapter() {
    }

    /**
     * Constructeur avec paramètre
     *
     * @param context     le context de l'app
     * @param articleList la list d'article
     */
    public ArticleAdapter(Context context, List<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    /**
     * Name onCreateViewHolder
     * Permet de relier le layout
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ArticleAdapter.ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_list_row, parent, false);
        return new ArticleViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ArticleViewHolder holder, int position) {
        holder.lblListItemName.setText(articleList.get(position).getNom());
        holder.lblListItemQuantity.setText(String.valueOf(articleList.get(position).getQuantite()));
        holder.lblListItemPrice.setText(String.valueOf(articleList.get(position).getPrix()));

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }


    /**
     * Name : ArticleViewHolder
     * Permet de construire le holder avec la view
     */
    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        ImageButton btnListItemRemove, btnListItemAdd;
        TextView lblListItemName, lblListItemQuantity, lblListItemPrice;
        CheckBox chkListItem;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            btnListItemRemove = itemView.findViewById(R.id.btnListItemRemove);
            btnListItemAdd = itemView.findViewById(R.id.btnListItemAdd);
            lblListItemName = itemView.findViewById(R.id.lblListItemName);
            lblListItemQuantity = itemView.findViewById(R.id.lblListItemQuantity);
            lblListItemPrice = itemView.findViewById(R.id.lblListItemPrice);


            btnListItemRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context.getApplicationContext(),
                            "Moins .....", Toast.LENGTH_SHORT).show();
                }
            });


            btnListItemAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context.getApplicationContext(),
                            "Plus .....", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
