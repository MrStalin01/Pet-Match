package com.rodgar00.petmatch;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;

public class ProfileMascotaPersonal extends Activity {
    private EditText editNombre, editEdad, editRaza;
    private Button btnSeleccionarImagen, btnAgregarAnimal;
    private ImageView imgPreview;
    private Uri imageUri;

    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_animal_personal);

        // 1. Inicializar vistas
        editNombre = findViewById(R.id.editNombre);
        editEdad = findViewById(R.id.editEdad);
        editRaza = findViewById(R.id.editRaza);
        btnSeleccionarImagen = findViewById(R.id.btnSeleccionarImagen);
        btnAgregarAnimal = findViewById(R.id.btnAgregarAnimal);
        imgPreview = findViewById(R.id.imgPreview);

        btnSeleccionarImagen.setOnClickListener(v -> abrirGaleria());

        btnAgregarAnimal.setOnClickListener(v -> guardarMascota());
    }

    private void abrirGaleria() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imgPreview.setImageURI(imageUri);
            imgPreview.setVisibility(View.VISIBLE);
        }
    }

    private void guardarMascota() {
        String nombre = editNombre.getText().toString().trim();
        String edad = editEdad.getText().toString().trim();
        String raza = editRaza.getText().toString().trim();
        String uriString = (imageUri != null) ? imageUri.toString() : "";

        if (nombre.isEmpty() || edad.isEmpty() || raza.isEmpty()) {
            Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent resultIntent = new Intent();
        resultIntent.putExtra("nuevo_nombre", nombre);
        resultIntent.putExtra("nuevo_edad", edad);
        resultIntent.putExtra("nuevo_raza", raza);
        resultIntent.putExtra("nuevo_imagen", uriString);

        setResult(RESULT_OK, resultIntent);
        finish(); // Cierra esta actividad y vuelve a Pets.java
    }
}
