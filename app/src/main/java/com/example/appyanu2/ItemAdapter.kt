package com.example.appyanu2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.appyanu2.network.Result

/*class ItemAdapter akan mengatur jumlah list data yang ditampilkan sehingga tidak memenuhi memory karena
* sebagian data disimpan pada adapter.
* ItemAdapter juga berfungsi untuk membuat object baru dari ViewHolder dengan mngisikan data dan mengembalikan*/
class ItemAdapter(private var films: List<Result>,
                  private val onClick: (film: Result) -> Unit
): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    /*
   * class itemviewholder akan memperkenalkan item seperti gambar dan string
   * serta akan mencari tempat untuk data ditampilkan*/
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val poster: ImageView = itemView.findViewById(R.id.img_item)
        val title: TextView = itemView.findViewById(R.id.item_title)
    }
    /*
     * pada method dibawah akan menentukan lokasi dimana viewholder akan ditampilkan
     * R.layout.list_item merupakan lokasi komponen ItemViewHolder akan ditampilkan*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = films.size

    /*
    * function onBindViewHolder digunakan untuk menghubungkan data dengan objek ViewHolder
    * pada function dibawah terdapat library Glide yang digunakan untuk memuat data gambar
    * function setOnClick digunakan untuk reaksi klik untuk melakukan perpindahan activity*/
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var result = films[position]
        holder.title.text = result.title
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w342${result.posterPath}")
            .transform(CenterCrop())
            .into(holder.poster)
        holder.itemView.setOnClickListener { onClick.invoke(result) }
    }

    /*
    * function updateFilm akan melakukan redraw data sehingga data baru akan ditampilkan*/
    fun updateFilm(films: List<Result>) {
        this.films = films
        notifyDataSetChanged()
    }
}