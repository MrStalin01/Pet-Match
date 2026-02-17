package com.rodgar00.petmatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

public class ProfileDog extends Activity {

    ImageView closeMenu;
    NavigationView navigationView;
    ImageView imageView;
    TextView nombre, duenyo, categoria, refugio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_animal);
        DrawerLayout drawerLayout = findViewById(R.id.main);
        ImageView logoApp = findViewById(R.id.logoApp);

        logoApp.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileDog.this, MainActivity.class);
            startActivity(intent);
        });

        imageView = findViewById(R.id.TvDogPicture);
        nombre = findViewById(R.id.tvDogName);
        duenyo = findViewById(R.id.tvDogDuenio);
        categoria = findViewById(R.id.tvDogCategoria);
        refugio = findViewById(R.id.tvDogRefugio);

        // Recibir datos
        Intent intent = getIntent();

        String nombreDog = intent.getStringExtra("nombre");
        String duenyoDog = intent.getStringExtra("duenyo");
        String categoriaDog = intent.getStringExtra("categoria");
        String refugioDog = intent.getStringExtra("refugio");
        String imagenDog = intent.getStringExtra("imagen");

        // Asignar datos
        nombre.setText(nombreDog);
        duenyo.setText("Dueño: " + duenyoDog);
        categoria.setText("Categoría: " + categoriaDog);
        refugio.setText("Refugio: " + refugioDog);

        Glide.with(this)
                .load(imagenDog)
                .into(imageView);

        ImageView menuHamburguesa = findViewById(R.id.menuHamburguesa);
        closeMenu = findViewById(R.id.Close);
        navigationView = findViewById(R.id.navView);

        menuHamburguesa.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.END);
        });

        closeMenu.setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.END);
        });

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_profile) {
                startActivity(new Intent(ProfileDog.this, Profile.class));
            } else if (id == R.id.nav_pets) {
                startActivity(new Intent(ProfileDog.this, Pets.class));
            } else if (id == R.id.nav_refugio) {
                startActivity(new Intent(ProfileDog.this, Refugio.class));
            } else if (id == R.id.nav_exit) {
                startActivity(new Intent(ProfileDog.this, Usuarios.class));
            }

            drawerLayout.closeDrawer(GravityCompat.END);
            return true;
        });
    }
}
