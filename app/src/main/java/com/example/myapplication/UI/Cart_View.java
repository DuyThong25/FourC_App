package com.example.myapplication.UI;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.Fragment.MenuFragment;
import com.example.myapplication.Model.CartProduct;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewAdapter.CartProductAdapter;
import com.example.myapplication.RecyclerViewAdapter.CategoryProductAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cart_View extends AppCompatActivity implements Serializable, CartProductAdapter.Listener {
    private RecyclerView recyclerView_Cart;
    private ArrayList<CartProduct> productCart_ArrayList;

    private BottomSheetDialog bottomSheetDialog;
    private TextView textView_Quantity_Icon_Cart;
    private ArrayList<CartProduct> product_Cart_ArrayList = new ArrayList<>();

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
        CartProductAdapter cartProductAdapter = new CartProductAdapter(Cart_View.this, (ArrayList<CartProduct>) productCart_ArrayList_Unique, Cart_View.this);
        recyclerView_Cart.setAdapter(cartProductAdapter);
        recyclerView_Cart.addItemDecoration(new DividerItemDecoration(Cart_View.this, LinearLayoutManager.VERTICAL));
    }

    // Xóa các product trùng
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

    //Nhấn vào product trong cart
    @Override
    public void onItemListener_Product_Cart(int pos, Product product, int quantity, String size) {
        View view = getLayoutInflater().inflate(R.layout.bottom_diaglog_product_detail, null, false);
        // Tạo bottom sheet dialog
        bottomSheetDialog = new BottomSheetDialog(Cart_View.this);
        CreateDialog(product, quantity, size, view);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

    }

    private void CreateDialog(Product product, int quantityInCart, String size, View view) {
        ImageView imageView_product_detail;
        TextView textView_NameProduct_Detail, textView_PriceProduct_Detail,
                textView_DescriptionProduct_Detail, textView_QuantityProduct_Detail;
        Button btn_AddQuantity, btn_RemoveQuantity, button_AddCart_Product_Detail;
        RadioGroup radioGroup;

        // anh xa
        imageView_product_detail = view.findViewById(R.id.imageView_product_detail);
        textView_NameProduct_Detail = view.findViewById(R.id.textView_NameProduct_Detail);
        textView_PriceProduct_Detail = view.findViewById(R.id.textView_PriceProduct_Detail);
        textView_DescriptionProduct_Detail = view.findViewById(R.id.textView3_DescriptionProduct_Detail);
        textView_QuantityProduct_Detail = view.findViewById(R.id.textView_QuantityProduct_Detail);
        btn_AddQuantity = view.findViewById(R.id.button_add_quantity);
        btn_RemoveQuantity = view.findViewById(R.id.button_remove_quantity);
        button_AddCart_Product_Detail = view.findViewById(R.id.button_AddCart_Product_Detail);
        radioGroup = view.findViewById(R.id.RadioGroup_Size);

        // Set số lượng hiện tại của sản phẩm
        textView_QuantityProduct_Detail.setText(quantityInCart + "");

        //Set size ma khach dang chon
        int radioCount = radioGroup.getChildCount();
        for (int i = 0; i < radioCount; i++) {
            View viewRadio = radioGroup.getChildAt(i);
            if (viewRadio instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) viewRadio;
                if (radioButton.getText().toString().equals(size)) {
                    radioButton.setChecked(true);
                }
            }
        }

        // Set hinh anh
        String urlImage = product.getImageProduct();
        if (urlImage != null) {
            urlImage = "http://anhthanh260599-001-site1.ftempurl.com" + urlImage;
            Glide.with(view.getContext())
                    .load(urlImage)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .fitCenter()
                    .into(imageView_product_detail);
        } else {
            imageView_product_detail.setImageResource(R.drawable.no_image);
        }

        // Set ten san pham
        textView_NameProduct_Detail.setText(product.getTitleProduct());

        // Set text cho gia san pham
        if (product.getPriceSaleProduct() == 0.00) {
            textView_PriceProduct_Detail.setText(String.format(new DecimalFormat("#,### đ")
                    .format(product.getPriceProduct())));
        } else {
            textView_PriceProduct_Detail.setText(String.format(new DecimalFormat("#,### đ")
                    .format(product.getPriceSaleProduct())));
        }

        //Set chữ cho button thêm vào cart
        button_AddCart_Product_Detail.setText("THÊM " + textView_PriceProduct_Detail.getText());

        // Set text cho mo ta
        textView_DescriptionProduct_Detail.setText(product.getDescriptionProduct());

        // Handle Event
        //Tang so luong

        btn_AddQuantity.setOnClickListener(view1 -> {
            int quantity = Integer.parseInt(textView_QuantityProduct_Detail.getText().toString());
            double price = product.getPriceSaleProduct() == 0.00 ? product.getPriceProduct() : product.getPriceSaleProduct();
            double sum = price;

            if (quantity > 100) {
                Toast.makeText(view.getContext(), "Không được nhiều hơn 100", Toast.LENGTH_SHORT).show();
            } else {
                quantity += 1;
                sum = price * quantity;
                button_AddCart_Product_Detail.setText("CẬP NHẬT " +
                        (String.format(new DecimalFormat("#,### đ")
                                .format(sum))));
            }

            textView_QuantityProduct_Detail.setText(String.valueOf(quantity));

        });
        // Giam so luong
        btn_RemoveQuantity.setOnClickListener(view1 -> {
            int quantity = Integer.parseInt(textView_QuantityProduct_Detail.getText().toString());
            double price = product.getPriceSaleProduct() == 0.00 ? product.getPriceProduct() : product.getPriceSaleProduct();
            double sum = price;

            if (quantity != 0) {
                quantity -= 1;
                sum = price * quantity;
                button_AddCart_Product_Detail.setText("CẬP NHẬT " +
                        (String.format(new DecimalFormat("#,### đ")
                                .format(sum))));
                if(quantity == 0) {
                    button_AddCart_Product_Detail.setText("XÓA SẢN PHẨM");
                    textView_QuantityProduct_Detail.setText(String.valueOf(quantity));
                }
                textView_QuantityProduct_Detail.setText(String.valueOf(quantity));
            }
        });

        // Them vao gio hang
        button_AddCart_Product_Detail.setOnClickListener(view1 -> {
            int quantity = quantityInCart;
            int sizeOfCart = this.product_Cart_ArrayList.size();
            RadioButton radioButton = view.findViewById(radioGroup.getCheckedRadioButtonId()); // Lay id cua radio button dang duoc chon

            for (int i = 0; i < quantity; i++) {
                CartProduct cartProduct = new CartProduct(product, quantity, radioButton.getText().toString());
                this.product_Cart_ArrayList.add(cartProduct);

            }
            textView_Quantity_Icon_Cart.setText(String.valueOf(quantity + sizeOfCart));
            Toast.makeText(view.getContext(), "Thêm thành công !", Toast.LENGTH_SHORT).show();
            bottomSheetDialog.dismiss();
        });
    }
}