package com.pmdm.examen_extraordinaria.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pmdm.examen_extraordinaria.R
import com.pmdm.examen_extraordinaria.model.DatosReceta
import com.pmdm.examen_extraordinaria.model.Recetas

class RecetaAdapter(
    private var productoList: MutableList<DatosReceta>,
    private val onClickListener: (DatosReceta) -> Unit
) :
    RecyclerView.Adapter<RecetaViewHolder>() {
    fun updateData(newProductos: List<DatosReceta>) {
        var tamanho = productoList.size
        productoList.clear()
        notifyItemRangeRemoved(0, tamanho)
        productoList.addAll(newProductos)
        tamanho = productoList.size
        notifyItemRangeInserted(0, tamanho)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recet, parent, false)
        return RecetaViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {
        holder.bind(productoList[position], onClickListener)
    }

    override fun getItemCount() = productoList.size
}
