package com.castro.visitascard.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.castro.visitascard.data.VisitasCard
import com.castro.visitascard.databinding.ItemVisitasCardBinding

class VisitasCardAdapter: ListAdapter<VisitasCard, VisitasCardAdapter.ViewHolder>(DiffCallback()) {

    var listenerShare: (View) -> Unit = {}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemVisitasCardBinding.inflate(inflater, parent, false)

            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(getItem(position))
        }

    inner class ViewHolder(
        private val binding: ItemVisitasCardBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: VisitasCard){
            binding.tvNome.text = item.name
            binding.tvEmail.text = item.email
            binding.tvTelefone.text = item.telefone
            binding.tvEmpresa.text = item.empresa
            binding.cardVisitas.setCardBackgroundColor(Color.parseColor(item.background))
            binding.imvProfile!!.setImageURI(item.profile!!.toUri())
            binding.cardVisitas.setOnClickListener {
                listenerShare(it)
            }

        }
    }

}

class DiffCallback: DiffUtil.ItemCallback<VisitasCard>(){
   override fun areItemsTheSame(oldItem: VisitasCard, newItem: VisitasCard) = oldItem == newItem
   override fun areContentsTheSame(oldItem: VisitasCard, newItem: VisitasCard) =
        oldItem.id == newItem.id


}