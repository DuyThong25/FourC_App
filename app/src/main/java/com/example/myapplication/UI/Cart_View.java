package com.example.myapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.Fragment.MenuFragment;
import com.example.myapplication.Model.CartProduct;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewAdapter.CartProductAdapter;
import com.example.myapplication.RecyclerViewAdapter.CategoryProductAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cart_View extends AppCompatActivity implements Serializable {
    private RecyclerView recyclerView_Cart;
    private ArrayList<CartProduct> productCart_ArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);

        // Ánh xạ
        recyclerView_Cart = findViewById(R.id.recycler_view_Product_Cart);

        CreateRecyclerView_Cart();

    }

    private void CreateRecyclerView_Cart() {
        Intent intent = getIntent();
        productCart_ArrayList = (ArrayList<CartProduct>) intent.getSerializableExtra("ProductCart");

        // Khởi tạo một danh sách để lưu trữ các sản phẩm duy nhất
        ArrayList<CartProduct> productCart_ArrayList_Unique = RemoveDuplicateProductCart(productCart_ArrayList);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Cart_View.this, LinearLayoutManager.VERTICAL, false);
        recyclerView_Cart.setLayoutManager(linearLayoutManager);
        CartProductAdapter cartProductAdapter = new CartProductAdapter(Cart_View.this, (ArrayList<CartProduct>) productCart_ArrayList_Unique);
        recyclerView_Cart.setAdapter(cartProductAdapter);
        recyclerView_Cart.addItemDecoration(new DividerItemDecoration(Cart_View.this, LinearLayoutManager.VERTICAL));
    }

    public ArrayList<CartProduct> RemoveDuplicateProductCart(ArrayList<CartProduct> productCart_ArrayList) {
        ArrayList<CartProduct> productCart_ArrayList_Unique = new ArrayList<>();
        // Lặp qua tất cả các sản phẩm trong productCart_ArrayList
        for (CartProduct cartProduct : productCart_ArrayList) {
            boolean isDuplicate = false;
            // Kiểm tra xem sản phẩm đã tồn tại trong danh sách productCart_ArrayList_Unique chưa
            for (CartProduct uniqueProduct : productCart_ArrayList_Unique) {
                if (cartProduct.getProduct().getIdProduct() == uniqueProduct.getProduct().getIdProduct()) {
                    isDuplicate = true;
                    break; // Nếu đã tồn tại, thoát khỏi vòng lặp
                }
            }
            // Nếu sản phẩm không trùng lặp, thêm vào danh sách productCart_ArrayList_Unique
            if (!isDuplicate) {
                productCart_ArrayList_Unique.add(cartProduct);
            }
        }
        return productCart_ArrayList_Unique;
    }
}