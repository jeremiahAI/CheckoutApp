package com.flutterwave.checkout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.flutterwave.checkout.Product.products;

public class BrowseProductsActivity extends AppCompatActivity {
    Toolbar toolbar;

    List<Product> productList = new ArrayList<>();
    RecyclerView productRecyclerView;
    ProductsAdapter productsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_products);
        toolbar = findViewById(R.id.activity_browse_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Choose product");

        setupActivity();
    }

    private void setupActivity() {
        productRecyclerView = (RecyclerView) findViewById(R.id.product_recycler_view);

        productsAdapter = new ProductsAdapter(productList);
        productRecyclerView.setLayoutManager(new GridLayoutManager
                (getApplicationContext(), 2, GridLayoutManager.VERTICAL, false));
        productRecyclerView.setItemAnimator(new DefaultItemAnimator());
        productRecyclerView.setAdapter(productsAdapter);

        populateProductData();
    }

    private void populateProductData() {
        productList.addAll(Arrays.asList(Product.products));
    }
}
