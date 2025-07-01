package com.pmdm.examen_extraordinaria.viewmodel

import RecetaRepository
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class RecetaViewModel() : ViewModel() {
    private val repositorio = RecetaRepository()

    val recetasTodas = liveData(Dispatchers.IO) {
        Log.d("RecetaViewModel", "🎯 Iniciando carga de recetas todas")
        val data = repositorio.obtenerRecetasTodos()
        Log.d("RecetaViewModel", "📊 ViewModel recibió: ${data.size} elementos")
        emit(data)
    }

    val recetasFaciles = liveData(Dispatchers.IO) {
        Log.d("RecetaViewModel", "🎯 Iniciando carga de recetas fáciles")
        val data = repositorio.obtenerRecetasFaciles()
        Log.d("RecetaViewModel", "📊 ViewModel recibió: ${data.size} elementos")
        emit(data)
    }

    val recetasMedium = liveData(Dispatchers.IO) {
        Log.d("RecetaViewModel", "🎯 Iniciando carga de recetas medium")
        val data = repositorio.obtenerRecetasMedium()
        Log.d("RecetaViewModel", "📊 ViewModel recibió: ${data.size} elementos")
        emit(data)
    }
}