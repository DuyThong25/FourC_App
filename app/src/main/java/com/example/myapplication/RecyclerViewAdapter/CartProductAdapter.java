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
import com.example.myapplication.Model.CartProduct;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.ViewHolder> {
    ArrayList<CartProduct> product_Cart_ArrayList;
    Context context;

    public CartProductAdapter( Context context, ArrayList<CartProduct> product_Cart_ArrayList) {
        this.product_Cart_ArrayList = product_Cart_ArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_in_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = this.product_Cart_ArrayList.get(position).getProduct();
        int quantity =  this.product_Cart_ArrayList.get(position).getQuantity();
        String size = this.product_Cart_ArrayList.get(position).getSize();

        // Set hinh anh
        String urlImage = product.getImageProduct();
        if (urlImage != null) {
            urlImage = "http://anhthanh260599-001-site1.ftempurl.com" + urlImage;
            Glide.with(context)
                    .load(urlImage)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .fitCenter()
                    .into(holder.imageView_Product_Cart);
        } else {
            holder.imageView_Product_Cart.setImageResource(R.drawable.no_image);
        }
        holder.textView_ProductName_Cart.setText(product.getTitleProduct());
        // Set text cho gia san pham
        if (product.getPriceSaleProduct() == 0.00) {
            holder.textView_ProductName_Cart.setText(String.format(new DecimalFormat("#,### đ")
                    .format(product.getPriceProduct())));
        }else {
            holder.textView_ProductName_Cart.setText(String.format(new DecimalFormat("#,### đ")
                    .format(product.getPriceSaleProduct())));
        }

        // Set text cho size
        holder.textView_ProductSize_Cart.setText(size);
        // Set text cho quantity
        holder.textView_ProductQuantity_Cart.setText("(" + quantity + "x)");
    }

    @Override
    public int getItemCount() {
        return product_Cart_ArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView_Product_Cart;
        TextView textView_ProductName_Cart, textView_ProductQuantity_Cart, textView_ProductSize_Cart, textView_ProductPrice_Cart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView_Product_Cart = itemView.findViewById(R.id.imageView_Product_Cart);
            textView_ProductName_Cart = itemView.findViewById(R.id.textView_NameProduct_In_Cart);
            textView_ProductQuantity_Cart = itemView.findViewById(R.id.textView_ProductQuantity_Cart);
            textView_ProductSize_Cart = itemView.findViewById(R.id.textView_SizeProduct_In_Cart);
            textView_ProductPrice_Cart = itemView.findViewById(R.id.textView_ProductPrice_Cart);
        }
    }
}
