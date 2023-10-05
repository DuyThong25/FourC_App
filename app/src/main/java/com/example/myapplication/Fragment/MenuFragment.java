package com.example.myapplication.Fragment;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.DatabaseController.ProductCategoryDAO;
import com.example.myapplication.DatabaseController.ProductDAO;
import com.example.myapplication.Model.CartProduct;
import com.example.myapplication.Model.Product;
import com.example.myapplication.Model.ProductCategory;
import com.example.myapplication.Model.ProductCategorySelectedManager;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewAdapter.CategoryProductAdapter;
import com.example.myapplication.RecyclerViewAdapter.ProductAdapter;
import com.example.myapplication.UI.Cart_View;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment implements CategoryProductAdapter.Listener, ProductAdapter.Listener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<ProductCategory> listCategoryName = new ArrayList<>();
    private ArrayList<Integer> listCategoryImage = new ArrayList<>();
    private ArrayList<Product> productArrayList = new ArrayList<>();
    private ArrayList<Product> productArrayListFilter = new ArrayList<>();
    private ArrayList<CartProduct> product_Cart_ArrayList = new ArrayList<>();


    private RecyclerView recyclerView_Product;
    private RecyclerView recyclerView_Category;
    private static ProductAdapter productAdapter;
    private BottomSheetDialog bottomSheetDialog;
    private FloatingActionButton floatingActionButton_Cart;
    private TextView textView_Quantity_Icon_Cart;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        //ánh xạ
        textView_Quantity_Icon_Cart = view.findViewById(R.id.textView_Quantity_Icon_Cart);

        // Create Recycler view of category
        InitRecyclerView_Category(view);
        // Create Recycler view of product
        InitRecyclerView_Product(view);

        //Tạo floating button
        floatingActionButton_Cart = view.findViewById(R.id.floatingActionButton_Cart);
        floatingActionButton_Cart.setOnClickListener(view1 -> {
            if (this.product_Cart_ArrayList.size() != 0) {
                Intent intent = new Intent(this.getActivity(), Cart_View.class);
                intent.putExtra("ProductCart", this.product_Cart_ArrayList);
                startActivity(intent);
            } else {
                Toast.makeText(this.getContext(), "Giỏ hàng trống", Toast.LENGTH_SHORT).show();

            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    //List product
    private void InitRecyclerView_Product(View view) {
        LoadData_Product();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView_Product = view.findViewById(R.id.recycler_view_Product);
        recyclerView_Product.setLayoutManager(linearLayoutManager);
        productAdapter = new ProductAdapter(view.getContext(), productArrayListFilter, MenuFragment.this);
        recyclerView_Product.setAdapter(productAdapter);
        recyclerView_Product.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));

    }

    private void LoadData_Product() {
        ProductDAO productDAO = new ProductDAO();
        productArrayList = productDAO.getAllProduct();
        productArrayListFilter = new ArrayList<>(productArrayList);
    }

    // Category of Product
    private void InitRecyclerView_Category(View view) {
        LoadData_Category();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_Category = view.findViewById(R.id.recycler_view_Category);
        recyclerView_Category.setLayoutManager(linearLayoutManager);
        CategoryProductAdapter categoryProductAdapter = new CategoryProductAdapter(view.getContext(), listCategoryName, listCategoryImage, MenuFragment.this);
        recyclerView_Category.setAdapter(categoryProductAdapter);
        recyclerView_Category.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.HORIZONTAL));
    }

    private void LoadData_Category() {
        ProductCategoryDAO productCategoryDAO = new ProductCategoryDAO();
        this.listCategoryName = productCategoryDAO.getAllProductCategory();

        this.listCategoryImage.add(R.drawable.coffee);
        this.listCategoryImage.add(R.drawable.coffee_machine);
        this.listCategoryImage.add(R.drawable.iced_coffee);
        this.listCategoryImage.add(R.drawable.tea);
    }

    // Nhấn vào category
    @Override
    public void onItemListener_Category(int pos, ProductCategory productCategory) {
        ProductCategorySelectedManager.getInstance().setSelectedCategoryId(this.listCategoryName.get(pos).getProductCategoryID());
        int categoryId = ProductCategorySelectedManager.getInstance().getSelectedCategoryId();

        ArrayList<Product> newProducts = new ArrayList<>();

        for (int i = 0; i < productArrayList.size(); i++) {
            if (productArrayList.get(i).getIdProductCategory() == categoryId) {
                newProducts.add(productArrayList.get(i));
            }
        }
        productArrayListFilter.clear();
        productArrayListFilter.addAll(newProducts);
        productAdapter.notifyDataSetChanged();
    }

    //Nhấn vào product
    @Override
    public void onItemListener_Product(int pos, Product product) {
        View view = getLayoutInflater().inflate(R.layout.bottom_diaglog_product_detail, null, false);
        // Tạo bottom sheet dialog
        bottomSheetDialog = new BottomSheetDialog(view.getContext());
        CreateDialog(product, view);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

    }

    private void CreateDialog(Product product, View view) {
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


        // Set hinh anh
        String urlImage = product.getImageProduct();
        if (urlImage != null) {
            urlImage = "http://anhthanh260599-001-site1.ftempurl.com" + urlImage;
            Glide.with(this.getContext())
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
            int quantity = parseInt(textView_QuantityProduct_Detail.getText().toString());
            double price = Double.parseDouble(textView_PriceProduct_Detail.getText().toString().replace(",","").replace(" đ",""));
            double sum = price;

            if (quantity > 100) {
                Toast.makeText(this.getContext(), "Stopppp T.T", Toast.LENGTH_SHORT).show();
            } else {
                quantity += 1;
                sum = price * quantity;
            }

            button_AddCart_Product_Detail.setText("THÊM " +
                    (String.format(new DecimalFormat("#,### đ")
                            .format(sum))));
            textView_QuantityProduct_Detail.setText(String.valueOf(quantity));
        });
        // Giam so luong
        btn_RemoveQuantity.setOnClickListener(view1 ->
        {
            int quantity = parseInt(textView_QuantityProduct_Detail.getText().toString());
            double price = Double.parseDouble(textView_PriceProduct_Detail.getText().toString().replace(",","").replace(" đ",""));
            double sum = price;

            if (quantity == 1) {
                Toast.makeText(this.getContext(), "Stopppp T.T", Toast.LENGTH_SHORT).show();
            } else {
                quantity -= 1;
                sum = price * quantity;
                System.out.println("sum sau khi -: " + sum);
            }
            button_AddCart_Product_Detail.setText("THÊM " +
                    (String.format(new DecimalFormat("#,### đ")
                            .format(sum))));
            textView_QuantityProduct_Detail.setText(String.valueOf(quantity));
        });

        // Chọn size sản phẩm
        radioGroup.setOnCheckedChangeListener((radioGroup1, i) ->
        {
            int buttonId = radioGroup.getCheckedRadioButtonId();
            double price = product.getPriceSaleProduct() == 0.00 ? product.getPriceProduct() : product.getPriceSaleProduct();
            int quantity = parseInt(textView_QuantityProduct_Detail.getText().toString());
            double priceUpSize = 0;

            switch (buttonId) {
                case R.id.radioButton_Size_M:
                    priceUpSize = price + 5000;
                    break;
                case R.id.radioButton_Size_L:
                    priceUpSize = price + 10000;
                    break;
                case R.id.radioButton_Size_S:
                    priceUpSize = price + 0;
                            break;
            }
            textView_PriceProduct_Detail.setText(String.format(new DecimalFormat("#,### đ")
                    .format(priceUpSize)));
            button_AddCart_Product_Detail.setText("THÊM " +
                    (String.format(new DecimalFormat("#,### đ")
                            .format(priceUpSize * quantity))));
        });
        // Them vao gio hang
        button_AddCart_Product_Detail.setOnClickListener(view1 ->
        {
            int quantity = parseInt(textView_QuantityProduct_Detail.getText().toString());
            int sizeOfCart = this.product_Cart_ArrayList.size();
            RadioButton radioButton = view.findViewById(radioGroup.getCheckedRadioButtonId()); // Lay id cua radio button dang duoc chon

            for (int i = 0; i < quantity; i++) {
                CartProduct cartProduct = new CartProduct(product, quantity, radioButton.getText().toString());
                this.product_Cart_ArrayList.add(cartProduct);

            }
            textView_Quantity_Icon_Cart.setText(String.valueOf(quantity + sizeOfCart));
            Toast.makeText(this.getContext(), "Thêm thành công !", Toast.LENGTH_SHORT).show();
            bottomSheetDialog.dismiss();
        });
    }
}