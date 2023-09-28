package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.DatabaseController.ProductCategoryDAO;
import com.example.myapplication.DatabaseManager.DatabaseManager;
import com.example.myapplication.Fragment.HomeFragment;
import com.example.myapplication.Fragment.MenuFragment;
import com.example.myapplication.Fragment.OrdersHistoryFragment;
import com.example.myapplication.Fragment.OthersFragment;
import com.example.myapplication.Fragment.StoresFragment;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView mnBottom;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gọi tới kết nối db
        DatabaseManager.getInstance();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Set fragment default la home
        loadFragment(new HomeFragment());

        binding.BottomNavigationViewId.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    loadFragment(new HomeFragment());
                    break;
                case R.id.menu:
                    loadFragment(new MenuFragment());
                    break;
                case R.id.ordersHistory:
                    loadFragment(new OrdersHistoryFragment());
                    break;
                case R.id.stores:
                    loadFragment(new StoresFragment());
                    break;
                case R.id.others:
                    loadFragment(new OthersFragment());
                    break;
            }

            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_id, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}