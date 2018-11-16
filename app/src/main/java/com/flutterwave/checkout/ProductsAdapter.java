package com.flutterwave.checkout;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {


    List<Product> productList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView productIV;
        public TextView productTitleTV, productPriceTV, productRatingTV;
        public CardView productCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productIV = (ImageView) itemView.findViewById(R.id.product_image);
            productTitleTV = (TextView) itemView.findViewById(R.id.product_title_tv);
            productPriceTV = (TextView) itemView.findViewById(R.id.product_price_tv);
            productRatingTV = (TextView) itemView.findViewById(R.id.product_rating_tv);
            productCardView = (CardView) itemView.findViewById(R.id.product_card);

        }
    }

    public ProductsAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_card, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Product product = productList.get(position);
        holder.productIV.setImageResource(product.imageResID);
        holder.productTitleTV.setText(product.title);
        holder.productPriceTV.setText(String.format("NGN %.2f", product.price));
        holder.productRatingTV.setText(product.rating);

        holder.productCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductDetailsActivity.class);
                intent.putExtra(Product.PRODUCT_DETAILS, product);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
