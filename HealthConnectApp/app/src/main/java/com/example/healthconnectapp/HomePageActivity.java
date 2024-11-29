package com.example.healthconnectapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.healthconnectapp.R;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class HomePageActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    FragmentManager fragmentManager;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (item.getItemId() == R.id.menu_about) {
            openFragment(new AboutFragment());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        fragmentManager = getSupportFragmentManager();
        openFragment(new HomeFragment());
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.nav_appointments) {
                    openFragment(new AppointmentFragment());
                    return true;
                } else if (item.getItemId() == R.id.nav_home) {
                    openFragment(new HomeFragment());
                    return true;
                } else if (item.getItemId() == R.id.nav_cart) {
                    openFragment(new CartFragment());
                    return true;
                }
                return false;
            }
        });


        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                bottomNavigationView.setSelectedItemId(R.id.nav_home);
                if (item.getItemId() == R.id.nav_profile) {
                    openFragment(new ProfileFragment());
                } else if (item.getItemId()  == R.id.nav_my_orders) {
                    openFragment(new OrdersFragment());
                } else if (item.getItemId()  == R.id.nav_contact) {
                    openFragment(new ContactFragment());
                } else if (item.getItemId()  == R.id.nav_feedback) {
                    openFragment(new FeedbackFragment());
                } else if (item.getItemId()  == R.id.logout) {
                    clearUserSession();
                    navigateToLoginPage();
                    Toast.makeText(HomePageActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                    return  true;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }
    private void showSnackbar(String message) {
        View rootView = findViewById(android.R.id.content);
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }
    private void clearUserSession() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
    private void navigateToLoginPage() {
        Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
