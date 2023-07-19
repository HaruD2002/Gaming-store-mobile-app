package com.example.prm_project.views;

import android.annotation.SuppressLint;
import android.widget.Toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.prm_project.R;


public class UpdateStoreActivity extends Activity {
    private EditText storeNameEditText, storeAddressEditText, storePhoneEditText, storeOperatingHoursEditText;
    private Button addProductButton, uploadImageButton, uploadVideoButton, saveChangesButton;
    private ListView productListView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to UI elements
        storeNameEditText = findViewById(R.id.storeNameEditText);
        storeAddressEditText = findViewById(R.id.storeAddressEditText);
        storePhoneEditText = findViewById(R.id.storePhoneEditText);
        storeOperatingHoursEditText = findViewById(R.id.storeOperatingHoursEditText);

        addProductButton = findViewById(R.id.addProductButton);
        productListView = findViewById(R.id.productListView);

        uploadImageButton = findViewById(R.id.uploadImageButton);
        uploadVideoButton = findViewById(R.id.uploadVideoButton);

        saveChangesButton = findViewById(R.id.saveChangesButton);

        // Set click listener for Add Product button
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action when Add Product button is clicked
                Toast.makeText(UpdateStoreActivity.this, "Add Product clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for Upload Image button
        uploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action when Upload Image button is clicked
                Toast.makeText(UpdateStoreActivity.this, "Upload Image clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for Upload Video button
        uploadVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action when Upload Video button is clicked
                Toast.makeText(UpdateStoreActivity.this, "Upload Video clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for Save Changes button
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action when Save Changes button is clicked
                String storeName = storeNameEditText.getText().toString();
                String storeAddress = storeAddressEditText.getText().toString();
                String storePhone = storePhoneEditText.getText().toString();
                String storeOperatingHours = storeOperatingHoursEditText.getText().toString();

                // Save the changes to the store
                // Your implementation here

                Toast.makeText(UpdateStoreActivity.this, "Changes saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



