package com.example.myapplication.RecyclerViewAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.GnssAntennaInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Product;
import com.example.myapplication.Model.ProductCategory;
import com.example.myapplication.Model.ProductCategorySelectedManager;
import com.example.myapplication.R;

import java.util.ArrayList;

public class CategoryProductAdapter extends RecyclerView.Adapter<CategoryProductAdapter.ViewHolder> {
    ArrayList<ProductCategory> listCategoryName;
    ArrayList<Integer> listCategoryImage;
    ProductCategorySelectedManager selectedCategoryID = ProductCategorySelectedManager.getInstance();
    Listener listener;

    Context context;

    public CategoryProductAdapter(Context context, ArrayList<ProductCategory> listCategoryName, ArrayList<Integer> listCategoryImage, Listener listener) {
        this.listCategoryName = listCategoryName;
        this.listCategoryImage = listCategoryImage;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_product, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ProductCategory productCategory = listCategoryName.get(position);

        holder.textView_CategoryProduct.setText(this.listCategoryName.get(position).getProductCategoryName());

        holder.imageView_CategoryProduct.setImageResource(this.listCategoryImage.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemListener_Category(position, productCategory);
            }
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

    public interface Listener{
        void onItemListener_Category(int pos, ProductCategory productCategory);
    }
}
