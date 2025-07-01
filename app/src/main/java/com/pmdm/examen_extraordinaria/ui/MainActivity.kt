package com.pmdm.examen_extraordinaria.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmdm.examen_extraordinaria.R
import com.pmdm.examen_extraordinaria.adapter.RecetaAdapter
import com.pmdm.examen_extraordinaria.databinding.ActivityMainBinding
import com.pmdm.examen_extraordinaria.model.DatosReceta
import com.pmdm.examen_extraordinaria.viewmodel.RecetaViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var listaActiva = "easy"
    private val viewModel: RecetaViewModel by viewModels()
    private lateinit var adapter: RecetaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.rvRecetas.layoutManager = LinearLayoutManager(this)
        adapter = RecetaAdapter(mutableListOf()) { datosReceta ->
            onItemSelected(datosReceta)
        }
        binding.rvRecetas.adapter = adapter
        cargarRecetas()
    }

    private fun cargarRecetas() {
        when (listaActiva) {
            "allDificulties" -> {
                viewModel.recetasTodas.observe(this) { recetaList ->
                    val datosRecetas = recetaList.flatMap { item -> item.recipes }
                    if (datosRecetas.isNotEmpty()) {
                        adapter.updateData(datosRecetas)
                    } else {
                        Toast.makeText(this, "Error al cargar los datos", Toast.LENGTH_SHORT).show()
                    }
                    listaVacia(datosRecetas)
                }
                this.setTitle("Todas las dificultades")
            }

            "easy" -> {
                viewModel.recetasFaciles.observe(this) { recetaList ->
                    val datosRecetas = recetaList.flatMap { item -> item.recipes }
                    println("Recetas: $datosRecetas");
                    if (datosRecetas.isNotEmpty()) {
                        adapter.updateData(datosRecetas)
                    } else {
                        Toast.makeText(this, "Error al cargar los datos", Toast.LENGTH_SHORT).show()
                    }
                    listaVacia(datosRecetas)
                }
                this.setTitle("Recetas fáciles")
            }

            "medium" -> {
                viewModel.recetasMedium.observe(this) { recetaList ->
                    // ✅ Extraer las recetas individuales del contenedor
                    val datosRecetas = recetaList.flatMap { item -> item.recipes }
                    if (datosRecetas.isNotEmpty()) {
                        adapter.updateData(datosRecetas)
                    } else {
                        Toast.makeText(this, "Error al cargar los datos", Toast.LENGTH_SHORT).show()
                    }
                    listaVacia(datosRecetas)
                }
                this.setTitle("Recetas medianas")
            }
        }
    }

    private fun listaVacia(recetaList: List<DatosReceta>) {
        if (recetaList.isEmpty()) {
            binding.tvEmpty.visibility = View.VISIBLE
        } else {
            binding.tvEmpty.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.allDificulties -> listaActiva = "allDificulties"
            R.id.easy -> listaActiva = "easy"
            R.id.medium -> listaActiva = "medium"
        }
        cargarRecetas()
        return true
    }

    private fun onItemSelected(receta: DatosReceta) {
        val intent = Intent(this, DetalleActivity::class.java)
        intent.putExtra("receta", receta)
        this.startActivity(intent)
    }
}
