package com.example.randomstringgenerator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.randomstringgenerator.databinding.ItemStringBinding
import com.example.randomstringgenerator.model.RandomString

class RandomStringAdapter(private val onDelete: (RandomString) -> Unit) : RecyclerView.Adapter<RandomStringAdapter.ViewHolder>() {

    private val items = mutableListOf<RandomString>()

    fun update(data: List<RandomString>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemStringBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStringBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.textValue.text = item.value
        holder.binding.textLength.text = "Length: ${item.length}"
        holder.binding.textCreated.text = "Created: ${item.created}"
        //  holder.binding.textMeta.text = "Length: ${item.length}, Created: ${item.created}"
        holder.binding.buttonDelete.setOnClickListener {
            onDelete(item)
        }

        // Animation
        holder.itemView.alpha = 0f
        holder.itemView.animate().alpha(1f).setDuration(300).start()
    }
}
