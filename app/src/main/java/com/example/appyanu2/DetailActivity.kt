package com.example.appyanu2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop


const val IMAGE = "extra_movie_poster"
const val TITLE = "extra_movie_title"
const val OVERVIEW = "extra_movie_overview"
class DetailActivity : AppCompatActivity() {

    /*kode program dibawah akan mendefinikan variable sebagai image atau text*/
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var overview: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        /*
        * kode program digunakan untuk menentukan letak data akan ditampilkan*/
        poster = findViewById(R.id.intent_img)
        title = findViewById(R.id.intent_title)
        overview = findViewById(R.id.overview)
        val extras = intent.extras

        if (extras != null) {
            Details(extras)
        } else {
            finish()
        }
    }
    /*kode porgram dibawah akan mengumpulkan data yang dikirim variabel datafilm yang berada di MainActivity
    * serta mendefinisikan tipe data dan dimana data diambil.*/
    private fun Details(extras: Bundle) {
        extras.getString(IMAGE)?.let { posterPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w400$posterPath")
                .transform(CenterCrop())
                .into(poster)
        }

        title.text = extras.getString(TITLE, "")
        overview.text = extras.getString(OVERVIEW, "")
    }
}