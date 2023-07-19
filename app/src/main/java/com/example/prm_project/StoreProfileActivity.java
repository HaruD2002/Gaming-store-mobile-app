package com.example.prm_project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StoreProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeprofile2);

        // Get references to UI elements
        TextView storeNameTextView = findViewById(R.id.storeNameTextView);
        TextView storeAddressTextView = findViewById(R.id.storeAddressTextView);
        TextView storePhoneTextView = findViewById(R.id.storePhoneTextView);
        TextView storeOperatingHoursTextView = findViewById(R.id.storeOperatingHoursTextView);
        ListView productListView = findViewById(R.id.productListView);
        ListView feedbackListView = findViewById(R.id.feedbackListView);
        Button contactStoreButton = findViewById(R.id.contactStoreButton);
        Button addToFavoritesButton = findViewById(R.id.addToFavoritesButton);

        // Set store information
        storeNameTextView.setText("Store Name");
        storeAddressTextView.setText("Address");
        storePhoneTextView.setText("Phone Number");
        storeOperatingHoursTextView.setText("Operating Hours");

        // Set product list
        String[] products = {"Product 1", "Product 2", "Product 3"};
        ArrayAdapter<String> productAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        productListView.setAdapter(productAdapter);

        // Set feedback and rating
        String[] feedbacks = {"Good product", "Fast delivery", "Excellent service"};
        ArrayAdapter<String> feedbackAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feedbacks);
        feedbackListView.setAdapter(feedbackAdapter);

        // Set click listener for contact store button
        contactStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action when contact store button is clicked
                Toast.makeText(StoreProfileActivity.this, "Contact Store clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for add to favorites button
        addToFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action when add to favorites button is clicked
                Toast.makeText(StoreProfileActivity.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
