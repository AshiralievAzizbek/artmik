package com.example.artmik.view.countries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.artmik.R
import com.example.artmik.data.remote.models.Country
import com.example.artmik.view.utils.load

class CountriesAdapter : ListAdapter<Country, CountriesAdapter.ViewHolder>(DiffCallback()) {

    private lateinit var onClickAction: (country: Country) -> Unit

    fun setOnItemClickAction(action: (country: Country) -> Unit) {
        onClickAction = action
    }

    private class DiffCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.rv_item_name)
        private val flag = itemView.findViewById<ImageView>(R.id.rv_item_flag)

        fun bind(country: Country) {
            name.text = country.name
            flag.load(country.flags.png)
            itemView.setOnClickListener {
                onClickAction(country)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.country, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}
