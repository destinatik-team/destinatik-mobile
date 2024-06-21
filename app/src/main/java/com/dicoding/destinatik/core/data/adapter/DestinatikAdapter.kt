package com.dicoding.destinatik.core.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.destinatik.R
import com.dicoding.destinatik.core.domain.model.Place

class DestinatikAdapter(private val onItemClick: (Place) -> Unit) :
    RecyclerView.Adapter<DestinatikAdapter.PlaceViewHolder>() {

    private var places: List<Place> = listOf()

    fun setPlaces(places: List<Place>) {
        this.places = places
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_destinasi, parent, false)
        return PlaceViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(places[position])
    }

    override fun getItemCount(): Int = places.size

    class PlaceViewHolder(itemView: View, private val onItemClick: (Place) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title)
        fun bind(place: Place) {
            title.text = place.placeName
            itemView.setOnClickListener {
                onItemClick(place)
            }
        }
    }
}
