package com.pmdm.examen_extraordinaria.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.pmdm.examen_extraordinaria.databinding.ItemRecetBinding
import com.pmdm.examen_extraordinaria.model.DatosReceta
import com.squareup.picasso.Picasso

class RecetaViewHolder (view: View): ViewHolder(view){
    private val binding = ItemRecetBinding.bind(view)
    fun bind(producto: DatosReceta, onClickListener:(DatosReceta)->Unit) {
        binding.tvName.text=producto.name
        Picasso.get().load(producto.image)
            .into(binding.ivReceta)
        itemView.setOnClickListener{
            onClickListener(producto)
        }
    }

}