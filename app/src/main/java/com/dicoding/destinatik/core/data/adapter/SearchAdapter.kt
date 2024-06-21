package com.dicoding.destinatik.core.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.destinatik.R
import com.dicoding.destinatik.core.domain.model.SearchModel

class SearchAdapter(private val onItemClick: (SearchModel) -> Unit) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var searchResults: List<SearchModel> = listOf()

    fun setSearchResults(searchResults: List<SearchModel>) {
        this.searchResults = searchResults
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_destinasi, parent, false)
        return SearchViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchResults[position])
    }

    override fun getItemCount(): Int = searchResults.size

    class SearchViewHolder(itemView: View, private val onItemClick: (SearchModel) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title)
        private val imageView: ImageView = itemView.findViewById(R.id.image)

        fun bind(searchModel: SearchModel) {
            title.text = searchModel.name
            Glide.with(itemView.context)
                .load(searchModel.photos.firstOrNull()?.url) // Assuming photos list is not empty and url is valid
                .placeholder(R.drawable.shape_content) // Placeholder image
                .into(imageView)
            itemView.setOnClickListener {
                onItemClick(searchModel)
            }
        }
    }
}
