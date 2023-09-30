package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.DatabaseController.ProductCategoryDAO;
import com.example.myapplication.DatabaseController.ProductDAO;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewAdapter.CategoryProductAdapter;
import com.example.myapplication.RecyclerViewAdapter.ProductAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<String> listCategoryName;
    private ArrayList<Integer> listCategoryImage = new ArrayList<>();
    private ArrayList<Product> productArrayList;


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
        // Create Recycler view of category
        InitRecyclerView_Category(view);
        // Create Recycler view of product
        InitRecyclerView_Product(view);

        // Inflate the layout for this fragment
        return view;
    }
    //List product

    private void InitRecyclerView_Product(View view) {
        LoadData_Product();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_Product);
        recyclerView.setLayoutManager(linearLayoutManager);
        ProductAdapter productAdapter = new ProductAdapter(view.getContext(), productArrayList);
        recyclerView.setAdapter(productAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(),LinearLayoutManager.VERTICAL));
    }
    private void LoadData_Product() {
        ProductDAO productDAO = new ProductDAO();
        productArrayList = productDAO.getAllProduct();
    }
    // Category of Product
    private void InitRecyclerView_Category(View view) {
        LoadData_Category();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_Category);
        recyclerView.setLayoutManager(linearLayoutManager);
        CategoryProductAdapter categoryProductAdapter = new CategoryProductAdapter(view.getContext(), listCategoryName, listCategoryImage);
        recyclerView.setAdapter(categoryProductAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.HORIZONTAL));
    }

    private void LoadData_Category() {
        ProductCategoryDAO productCategoryDAO = new ProductCategoryDAO();
        this.listCategoryName = productCategoryDAO.getAllProductCategory();

        this.listCategoryImage.add(R.drawable.coffee);
        this.listCategoryImage.add(R.drawable.coffee_machine);
        this.listCategoryImage.add(R.drawable.tea);
        this.listCategoryImage.add(R.drawable.iced_coffee);
    }
}