package com.example.examapp;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.examapp.databinding.ActivityMainBinding;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    // Declare the Floating Action Buttons
    private FloatingActionButton fabMain;
    private FloatingActionButton fabNotes;
    private FloatingActionButton fabImages;
    private FloatingActionButton fabEnvironmental;
    private FloatingActionButton fabGps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Initialize the Floating Action Buttons
        fabMain = findViewById(R.id.fab_main);
        fabNotes = findViewById(R.id.fab_notes);
        fabImages = findViewById(R.id.fab_images);
        fabEnvironmental = findViewById(R.id.fab_environmental);
        fabGps = findViewById(R.id.fab_gps);

        // Set initial visibility of the secondary FABs to GONE
        fabNotes.setVisibility(View.GONE);
        fabImages.setVisibility(View.GONE);
        fabEnvironmental.setVisibility(View.GONE);
        fabGps.setVisibility(View.GONE);

        // Set the click listener for the main FAB
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle visibility of secondary FABs
                if (fabNotes.getVisibility() == View.GONE) {
                    // Show the secondary FABs
                    fabNotes.setVisibility(View.VISIBLE);
                    fabImages.setVisibility(View.VISIBLE);
                    fabEnvironmental.setVisibility(View.VISIBLE);
                    fabGps.setVisibility(View.VISIBLE);
                } else {
                    // Hide the secondary FABs
                    fabNotes.setVisibility(View.GONE);
                    fabImages.setVisibility(View.GONE);
                    fabEnvironmental.setVisibility(View.GONE);
                    fabGps.setVisibility(View.GONE);
                }
            }
        });

        // Add click listeners for each secondary FAB if needed (e.g., to open fragments)
        fabNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle opening the Notes Fragment
                openNotesFragment();
            }
        });

        fabImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle opening the Images Notes Fragment
                openImagesFragment();
            }
        });

        fabEnvironmental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle opening the Environmental Monitoring Fragment
                openEnvironmentalFragment();
            }
        });

        fabGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle opening the GPS Tracking Fragment
                openGpsTrackingFragment();
            }
        });
    }

    // Method to open the Notes Fragment
    private void openNotesFragment() {
        // Create a new instance of NotesFragment
        NotesFragment notesFragment = new NotesFragment();
        // Begin a fragment transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace the current fragment with the new NotesFragment
        transaction.replace(R.id.fragment_container, notesFragment);
        // Add to back stack to allow navigation back
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }

    // Method to open the Images Notes Fragment
    private void openImagesFragment() {
        // Create a new instance of the ImagesNotesFragment
        ImagesNotesFragment imagesNotesFragment = new ImagesNotesFragment();
        // Begin a fragment transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace the current fragment in the container with the new fragment
        transaction.replace(R.id.fragment_container, imagesNotesFragment);
        // Add this transaction to the back stack, so the user can navigate back
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }

    // Method to open the Environmental Monitoring Fragment
    private void openEnvironmentalFragment() {
        // Create a new instance of the EnvironmentalMonitoringFragment
        EnvironmentalMonitoringFragment environmentalMonitoringFragment = new EnvironmentalMonitoringFragment();
        // Begin a fragment transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace the current fragment in the container with the new fragment
        transaction.replace(R.id.fragment_container, environmentalMonitoringFragment);
        // Add this transaction to the back stack
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }

    // Method to open the GPS Tracking Fragment
    public void openGpsTrackingFragment() {
        // Create a new instance of the GPS Tracking Fragment
        GpsTrackingFragment gpsTrackingFragment = new GpsTrackingFragment();

        // Replace the current fragment with the GPS Tracking Fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, gpsTrackingFragment)
                .addToBackStack(null) // Optional, allows you to navigate back to the previous fragment
                .commit();
    }

}
