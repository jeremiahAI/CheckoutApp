package com.flutterwave.checkout;

import java.io.Serializable;

public class Product implements Serializable {
    int imageResID;
    double price;
    String title;
    String rating;
    int numberOfOrders;
    String sellerName, sellerRating;
    int sellerItemsNumber, sellerFollowersNumber;
    public static final String PRODUCT_DETAILS = "Product details";

    public Product(int imageResID,
                   double price,
                   String title,
                   String rating,
                   int numberOfOrders,
                   String sellerName,
                   String sellerRating,
                   int sellerItemsNumber,
                   int sellerFollowersNumber) {
        this.imageResID = imageResID;
        this.price = price;
        this.title = title;
        this.rating = rating;
        this.numberOfOrders = numberOfOrders;
        this.sellerName = sellerName;
        this.sellerRating = sellerRating;
        this.sellerItemsNumber = sellerItemsNumber;
        this.sellerFollowersNumber = sellerFollowersNumber;
    }

    public static Product products[] = {
            new Product(R.drawable.watch_1,
                    9000.00,
                    "LIGE New Mens Watches Top Brand Luxury Quartz Watch Men Calendar Leather Military Waterproof Sport Wrist Watch Relogio Masculino",
                    "4.6",
                    2537,
                    "LIGE Official Store",
                    "98.3%",
                    619,
                    1725),
            new Product(R.drawable.watch_2,
                    7350,
                    "LIGE Top Brand Luxury Watch Men Fashion Casual Business Men Watches Military" +
                            " Sports Waterproof Quartz Watch Relogio Masculino",
                    "4.4",
                    993,
                    "LIGE Official Store",
                    "98.3%",
                    619,
                    1725),
            new Product(R.drawable.watch_3,
                    7100,
                    "LIGE Watch Men Fashion Sport Quartz Clock Mens Watches Top Brand Luxury Full" +
                            " Steel Business Waterproof Watch Relogio Masculino",
                    "4.7",
                    4174,
                    "LIGE Official Store",
                    "98.3%",
                    619,
                    1725),
            new Product(R.drawable.watch_4,
                    6300,
                    "2017 New Fashion Mens Watches Naviforce Militray Sport Quartz Men Watch " +
                            "Leather Waterproof Male Wristwatches Relogio Masculino",
                    "4.8",
                    3578,
                    "Kaduna Watch Factory",
                    "97.6%",
                    530,
                    3349),
            new Product(R.drawable.watch_5,
                    5400,
                    "2016 New Black Men's Skeleton WristWatch Stainless steel Antique Steampunk " +
                            "Casual Automatic Skeleton Mechanical Watches Male",
                    "4.7",
                    3701,
                    "Kaduna Watch Factory",
                    "97.6%",
                    530,
                    3349),
            new Product(R.drawable.watch_6,
                    5500,
                    "Forsining Men Watches Top Brand Luxury Mechanical Skeleton Watch Black " +
                            "Golden 3D Literal Design Roman Number Black Dial Clock",
                    "4.7",
                    1162,
                    "FORSINING Official Store",
                    "97.7%",
                    700,
                    4039),
            new Product(R.drawable.watch_7,
                    7000,
                    "Men Chronograph Watches Crrju Top Luxury Brand Men Military Sport Wristwatch" +
                            " Quartz Watch Relogio Masculino support dropshipping",
                    "4.5",
                    2577,
                    "Kaduna Watch Factory",
                    "97.6%",
                    530,
                    3349),
            new Product(R.drawable.watch_8,
                    3000,
                    "Crrju Top Brand Luxury Watches Men Stainless Steel Ultra Thin Watches Men " +
                            "Classic Quartz Men's Wrist Watch Relogio Masculino",
                    "4.5",
                    1824,
                    "Kaduna Watch Factory",
                    "97.6%",
                    530,
                    3349),
            new Product(R.drawable.watch_9,
                    6400,
                    "winner Sport Racing Design Geometric Triangle Design Genuine Leather Strap " +
                            "Mens Watches Top Brand Luxury Automatic Wrist Watch",
                    "4.4",
                    4563,
                    "Chima Straps Store",
                    "98.2%",
                    4358,
                    5000),
            new Product(R.drawable.watch_10,
                    8000,
                    "Megir Men's Sports Chronograph Wristwatches Luminous Hands Fashion Date " +
                            "Brown Leather Casual Military Quartz Watch Montre Homme",
                    "4.5",
                    3339,
                    "Kemi Accessories Store",
                    "95.3%",
                    3901,
                    5336)
    };
}
