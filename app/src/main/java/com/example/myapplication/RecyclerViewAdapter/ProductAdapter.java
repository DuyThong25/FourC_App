package com.example.myapplication.RecyclerViewAdapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;

import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    ArrayList<Product> productArrayList = new ArrayList<>();

    Context context;

    public ProductAdapter(Context context, ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.TitleProduct.setText(this.productArrayList.get(position).getTitleProduct());

        if (this.productArrayList.get(position).getPriceSaleProduct() == 0.00) {
            holder.PriceSaleProduct.setText(holder.PriceProduct.getText());
            holder.PriceProduct.setVisibility(View.GONE);
        } else {
            // Chuyển kiểu double thành string và sau đó đặt vào text view
            holder.PriceSaleProduct.setText(String.format(new DecimalFormat("#,### đ")
                    .format(this.productArrayList.get(position).getPriceSaleProduct())));
            holder.PriceProduct.setText(String.format(String.format(new DecimalFormat("#,### đ")
                    .format(this.productArrayList.get(position).getPriceProduct()))));
            // Tạo underline cho price
            holder.PriceProduct.setPaintFlags(holder.PriceProduct.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.DescriptionProduct.setText(this.productArrayList.get(position).getDescriptionProduct());
        }

        String urlImage = this.productArrayList.get(position).getImageProduct();
        if (urlImage != null) {
            urlImage = "http://anhthanh260599-001-site1.ftempurl.com" + urlImage;
            Glide.with(context)
                    .load(urlImage)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .fitCenter()
                    .into(holder.imageView_product);
        } else {
            holder.imageView_product.setImageResource(R.drawable.no_image);
        }
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TitleProduct, PriceSaleProduct, PriceProduct, DescriptionProduct;
        ImageView imageView_product;

        public ViewHolder(View itemView) {
            super(itemView);
            TitleProduct = itemView.findViewById(R.id.textView_TitleProduct);
            PriceSaleProduct = itemView.findViewById(R.id.textView1_PriceSaleProduct);
            PriceProduct = itemView.findViewById(R.id.textView2_PriceProduct);
            DescriptionProduct = itemView.findViewById(R.id.textView3_DescriptionProduct);

            imageView_product = itemView.findViewById(R.id.imageView_product);

        }
    }

}
