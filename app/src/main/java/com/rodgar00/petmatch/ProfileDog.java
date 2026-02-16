package com.rodgar00.petmatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProfileDog extends Activity {

    ImageView imageView;
    TextView nombre, duenyo, categoria, refugio, raza, localizacion, descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_animal);

        // 1. Enlazar con tu XML
        imageView = findViewById(R.id.TvDogPicture);
        nombre = findViewById(R.id.tvDogName);
        duenyo = findViewById(R.id.tvDogDuenio);
        categoria = findViewById(R.id.tvDogCategoria);
        refugio = findViewById(R.id.tvDogRefugio);
        raza = findViewById(R.id.tvDogRaza);
        localizacion = findViewById(R.id.tvUserLocation);
        descripcion = findViewById(R.id.TVDogDescripcion);

        // 2. Recibir los datos del Adapter
        Intent intent = getIntent();

        String nombreDog = intent.getStringExtra("nombre");
        String duenyoDog = intent.getStringExtra("duenyo");
        String categoriaDog = intent.getStringExtra("categoria");
        String refugioDog = intent.getStringExtra("refugio");
        String razaDog = intent.getStringExtra("raza");
        String localizacionDog = intent.getStringExtra("localizacion");
        String descripcionDog = intent.getStringExtra("descripcion");
        String imagenDog = intent.getStringExtra("imagen");

        // 3. Mostrar los datos en la pantalla
        if (nombreDog != null) nombre.setText(nombreDog);
        if (duenyoDog != null) duenyo.setText("Dueño: " + duenyoDog);
        if (categoriaDog != null) categoria.setText("Categoría: " + categoriaDog);
        if (refugioDog != null) refugio.setText("Refugio: " + refugioDog);
        if (razaDog != null) raza.setText("Raza: " + razaDog);
        if (localizacionDog != null) localizacion.setText(localizacionDog);
        if (descripcionDog != null) descripcion.setText(descripcionDog);

        // Cargar imagen con Glide solo si hay una URL válida
        if (imagenDog != null && !imagenDog.isEmpty()) {
            Glide.with(this)
                    .load(imagenDog)
                    .into(imageView);
        }
    }
}