package com.rodgar00.petmatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;


public class Pets extends Activity {
    ImageView closeMenu;
    NavigationView navigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);

        LinearLayout cardAslan = findViewById(R.id.ivPet1);
        androidx.cardview.widget.CardView cardChulapo = findViewById(R.id.cardPet2);

// IMPORTANTE: Verifica que no sean nulos antes de asignar el clic
        if (cardAslan != null) {
            cardAslan.setOnClickListener(v -> {
                irAEditor("Aslan", "5", "Aslantico", "android.resource://" + getPackageName() + "/" + R.drawable.aslantico);
            });
        }

        if (cardChulapo != null) {
            cardChulapo.setOnClickListener(v -> {
                irAEditor("Chulapo", "5", "Perro Mestizo", "android.resource://" + getPackageName() + "/" + R.drawable.chulapo);
            });
        }

        ImageView logoApp = findViewById(R.id.logoApp);

        DrawerLayout drawerLayout = findViewById(R.id.main);

        ImageView menuHamburguesa = findViewById(R.id.menuHamburguesa);
        closeMenu = findViewById(R.id.Close);
        navigationView = findViewById(R.id.navView);

        logoApp.setOnClickListener(v ->{
            Intent intent = new Intent(Pets.this, MainActivity.class);
            startActivity(intent);
        });

        menuHamburguesa.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.END);
        });

        closeMenu.setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.END);
        });

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_profile) {
                startActivity(new Intent(Pets.this, Profile.class));
            } else if (id == R.id.nav_pets) {
                startActivity(new Intent(Pets.this, Pets.class));
            } else if (id == R.id.nav_refugio) {
                startActivity(new Intent(Pets.this, Refugio.class));
            } else if (id == R.id.nav_exit) {
                startActivity(new Intent(Pets.this, Usuarios.class));
            }

            drawerLayout.closeDrawer(GravityCompat.END);
            return true;
        });
    }
    private void irAEditor(String nombre, String edad, String raza, String imagenRes) {
        Intent intent = new Intent(Pets.this, EditorPet.class);
        intent.putExtra("nombre", nombre);
        intent.putExtra("Edad", edad);
        intent.putExtra("Raza", raza);
        intent.putExtra("imagen", imagenRes);
        startActivity(intent);
    }

}


