package com.rodgar00.petmatch;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DogRecycler extends RecyclerView.Adapter<DogRecycler.DogViewHolder> {

    private Context context;
    private ArrayList<DogModel> dogModels;
    private String tablaActual = "adoptados"; // Variable para saber en qué pestaña estamos

    public DogRecycler(Context context, ArrayList<DogModel> dogModels) {
        this.context = context;
        this.dogModels = dogModels;
    }

    // Método para que MainActivity avise al adapter qué pestaña está activa
    public void setTablaActual(String tabla) {
        this.tablaActual = tabla;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.tarjeta, parent, false);
        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        DogModel dog = dogModels.get(position);

        holder.nombreText.setText(dog.getNombre());

        // Mostrar dueño si existe
        if (dog.getDuenyo() != null && !dog.getDuenyo().isEmpty()) {
            holder.duenyoText.setText("Dueño: " + dog.getDuenyo());
            holder.duenyoText.setVisibility(View.VISIBLE);
        } else {
            holder.duenyoText.setVisibility(View.GONE);
        }

        holder.categoriaText.setText("Categoría: " + dog.getCategoria());
        holder.refugioText.setText("Refugio: " + dog.getEsRefugio());

        // LÓGICA DEL CORAZÓN: Solo se muestra si estamos en "adoptados"
        if (tablaActual.equals("adoptados")) {
            holder.btnFavorito.setVisibility(View.VISIBLE);
        } else {
            holder.btnFavorito.setVisibility(View.GONE);
        }

        // Acción al hacer clic en el corazón
        holder.btnFavorito.setOnClickListener(v -> {
            Toast.makeText(context, "Añadido a favoritos: " + dog.getNombre(), Toast.LENGTH_SHORT).show();
            // Aquí en el futuro puedes hacer la llamada a tu API para guardarlo en favoritos
        });

        // Aquí es donde enviamos TODOS los datos al perfil
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProfileDog.class);

            intent.putExtra("nombre", dog.getNombre());
            intent.putExtra("duenyo", dog.getDuenyo());
            intent.putExtra("categoria", dog.getCategoria());
            intent.putExtra("refugio", dog.getEsRefugio());
            intent.putExtra("imagen", dog.getImagen());
            intent.putExtra("descripcion", dog.getDescripcion());
            intent.putExtra("raza", dog.getRaza());
            intent.putExtra("localizacion", dog.getLocalizacion());

            context.startActivity(intent);
        });

        Glide.with(context)
                .load(dog.getImagen())
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(holder.dogImage);
    }

    @Override
    public int getItemCount() {
        return dogModels.size();
    }

    static class DogViewHolder extends RecyclerView.ViewHolder {

        TextView nombreText, duenyoText, categoriaText, refugioText;
        ImageView dogImage;
        ImageView btnFavorito; // Enlazamos el botón del corazón

        public DogViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreText = itemView.findViewById(R.id.textoNombre);
            duenyoText = itemView.findViewById(R.id.textoDuenyo);
            categoriaText = itemView.findViewById(R.id.textoCategoria);
            refugioText = itemView.findViewById(R.id.textoRefugio);
            dogImage = itemView.findViewById(R.id.imagenTarjetaDog);
            btnFavorito = itemView.findViewById(R.id.btnFavorito); // Asegúrate de que este ID coincida con tu tarjeta.xml
        }
    }
}