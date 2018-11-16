package com.flutterwave.checkout;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flutterwave.raveandroid.RaveConstants;
import com.flutterwave.raveandroid.RavePayActivity;
import com.flutterwave.raveandroid.RavePayManager;

public class ProductDetailsActivity extends AppCompatActivity {

    Toolbar toolbar;

    ImageView productImageView;
    TextView productPriceTV,
            productTitleTV,
            productRatingTV,
            ordersTV,
            sellerNameTV,
            sellerRatingTV,
            sellerItemsTV,
            sellerFollowersTV;

    Button checkoutButton;
    FloatingActionButton favButton;
    public Product product;
    private boolean addedToFavorites = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        product = (Product) getIntent().getSerializableExtra(Product.PRODUCT_DETAILS);
        setupActivity(product);


    }

    void setupActivity(final Product product) {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        productImageView = findViewById(R.id.product_image);
        productPriceTV = findViewById(R.id.product_price_tv);
        productTitleTV = findViewById(R.id.product_title_tv);
        productRatingTV = findViewById(R.id.product_rating_tv);
        ordersTV = findViewById(R.id.number_of_orders_tv);
        sellerNameTV = findViewById(R.id.seller_name_tv);
        sellerRatingTV = findViewById(R.id.seller_rating_tv);
        sellerItemsTV = findViewById(R.id.seller_items_tv);
        sellerFollowersTV = findViewById(R.id.seller_followers_tv);
        checkoutButton = findViewById(R.id.pay_button);

        productImageView.setImageDrawable(getResources().getDrawable(product.imageResID));
        productPriceTV.setText(String.format("NGN %.2f", product.price));
        productRatingTV.setText(product.rating);
        ordersTV.setText(String.valueOf(product.numberOfOrders));
        sellerNameTV.setText(product.sellerName);
        sellerRatingTV.setText(product.sellerRating);
        sellerItemsTV.setText(String.valueOf(product.sellerItemsNumber));
        sellerFollowersTV.setText(String.valueOf(product.sellerFollowersNumber));

        favButton = findViewById(R.id.wishlist_button);
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavState();
            }
        });


        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkout(product);
            }
        });


    }

    private void checkout(Product product) {
        new RavePayManager(ProductDetailsActivity.this)
                .setAmount(product.price)
                .setCountry("NG")
                .setCurrency("NGN")
                .setEmail("user@example.com")
                .setfName("Wuraola")
                .setlName("Benson")
                .setNarration("Narration")
                .setPublicKey(BuildConfig.STAGING_PUBLIC_KEY)
                .setEncryptionKey(BuildConfig.STAGING_ENCRYPTION_KEY)
                .setTxRef("Transaction reference")
                .acceptCardPayments(true)
                .acceptMpesaPayments(false)
                .acceptGHMobileMoneyPayments(false)
                .acceptAccountPayments(true)
                .onStagingEnv(true)
//                .allowSaveCardFeature(true)
//                .withTheme(styleId)
//                .setSubAccounts(List<SubAccount>)
                .initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.wishlist) {
            toggleFavState();
        } else if (item.getItemId() == R.id.reset_page) {
            resetPage();
        }
        return super.onOptionsItemSelected(item);
    }

    private void resetPage() {
        checkoutButton.setText(getResources().getString(R.string.buy_now));
        checkoutButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkout(product);
            }
        });

        favButton.setImageResource(R.drawable.ic_favorite_border_24dp);
        addedToFavorites = false;
    }

    private void toggleFavState() {
        if (addedToFavorites) {
            removeFromFavorites();
        } else addToFavorites();
    }

    private void addToFavorites() {
        Toast.makeText(this, "Added to wishlist", Toast.LENGTH_SHORT).show();
        addedToFavorites = true;
        favButton.setImageResource(R.drawable.ic_favorite_24dp);
    }


    private void removeFromFavorites() {
        Toast.makeText(this, "Removed from wishlist", Toast.LENGTH_SHORT).show();
        addedToFavorites = false;
        favButton.setImageResource(R.drawable.ic_favorite_border_24dp);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (addedToFavorites) {
            menu.findItem(R.id.wishlist).setTitle("Remove from wishlist");
        } else menu.findItem(R.id.wishlist).setTitle("Add to wishlist");
        if (!checkoutButton.getText().equals(getResources().getString(R.string.buy_now))) {
            menu.findItem(R.id.reset_page).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            String message = data.getStringExtra("response");
            Log.d("Verify response", "" + message);
            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                Toast.makeText(this, "Payment successful", Toast.LENGTH_SHORT).show();
                checkoutButton.setText("Bought");
                checkoutButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                checkoutButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ProductDetailsActivity.this,
                                "You have already made payment",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (resultCode == RavePayActivity.RESULT_ERROR) {
                Toast.makeText(this,
                        "There was an error processing your payment. Please try again.",
                        Toast.LENGTH_SHORT).show();
            } else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                Toast.makeText(this, "Payment cancelled ", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
