package com.pmdm.examen_extraordinaria.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pmdm.examen_extraordinaria.R
import com.pmdm.examen_extraordinaria.databinding.ActivityDetalleBinding
import com.pmdm.examen_extraordinaria.model.DatosReceta
import com.squareup.picasso.Picasso

class DetalleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val receta = intent.getSerializableExtra("receta", DatosReceta::class.java)

        receta?.let { recipe ->
            // Título de la receta
            binding.tvTituloReceta.text = recipe.name

            // Imagen de la receta
            Picasso.get().load(recipe.image)
                .into(binding.ivRecet)

            // Información de la receta
            binding.tvDificultad.text = "Difficulty: ${recipe.difficulty}"
            binding.tvTimePreparation.text = "Prep: ${recipe.prepTimeMinutes} min"
            binding.tvTiempoCoccion.text = "Cook: ${recipe.cookTimeMinutes} min"
            binding.tvCuisine.text = "Cuisine: ${recipe.cuisine}"

            // Ingredientes
            binding.tvDescripcionIngredientes.text = recipe.ingredients.joinToString("\n• ", "• ")

            // Instrucciones
            binding.tvDescripcionInstructions.text = recipe.instructions.mapIndexed { index, instruction ->
                "${index + 1}. $instruction"
            }.joinToString("\n\n")
        }

    }
    }
