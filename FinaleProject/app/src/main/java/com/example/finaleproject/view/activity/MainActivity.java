package com.example.finaleproject.view.activity;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.finaleproject.R;
import com.example.finaleproject.view.fragment.HomeFragment;
import com.example.finaleproject.view.fragment.ListFragment;
import com.example.finaleproject.view.fragment.ShipmentFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.mainNavbar);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // Memunculkan fragment di awal
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFrame, new HomeFragment())
                .commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                       case R.id.navHome:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.navShipment:
                            selectedFragment = new ShipmentFragment();
                            break;
                        case R.id.navList:
                            selectedFragment = new ListFragment();
                            break;
                    }

                    int commit = getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.mainFrame, selectedFragment)
                            .commit();

                    return true;
                }
            };
}
