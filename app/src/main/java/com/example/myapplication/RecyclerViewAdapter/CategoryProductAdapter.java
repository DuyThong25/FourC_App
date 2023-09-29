package com.example.myapplication.RecyclerViewAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DatabaseController.ProductCategoryDAO;
import com.example.myapplication.R;

import java.util.ArrayList;

public class CategoryProductAdapter extends RecyclerView.Adapter<CategoryProductAdapter.ViewHolder> {
    ArrayList<String> listCategoryName;
    ArrayList<Integer> listCategoryImage;

    Context context;

    public CategoryProductAdapter(Context context, ArrayList<String> listCategoryName, ArrayList<Integer> listCategoryImage) {
        this.listCategoryName = listCategoryName;
        this.listCategoryImage = listCategoryImage;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView_CategoryProduct.setText(this.listCategoryName.get(position));

        holder.imageView_CategoryProduct.setImageResource(this.listCategoryImage.get(position));
        holder.textView_CategoryProduct.setOnClickListener(item -> {
            System.out.println("Ban da bam vao " + item.getContext().toString());
        });
    }

    @Override
    public int getItemCount() {
        return listCategoryName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView_CategoryProduct;
        TextView textView_CategoryProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_CategoryProduct = itemView.findViewById(R.id.imageView_ImageCategory);
            textView_CategoryProduct = itemView.findViewById(R.id.textView_NameCategory);
        }

    }
}
