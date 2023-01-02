package com.example.appyanu2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appyanu2.network.ApiService
import com.example.appyanu2.network.Result

class MainActivity : AppCompatActivity() {

    private lateinit var Films: RecyclerView
    private lateinit var FilmAdapter: ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*kode program dibawah akan mendelakrasikan recyclerView dan mengatur arah scroll data*/
        Films = findViewById(R.id.popular_movies)
        Films.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        /*kode program dibawah akan mempassing data dari ItemAdapter agar tampil sesuai recycleView*/
        FilmAdapter = ItemAdapter(listOf()){ movie -> DetailsFilm(movie) }
        Films.adapter = FilmAdapter

        /*kode program dibawah beerfungsi untuk memanggil dan menerima callback layanan web*/
        ApiService.getFilms(
            onSuccess = ::onPopularMoviesFetched,
            onError = ::onError
        )
    }
    /*function dibawah berfungsi sebagai implementasi jika permintaan http di respon*/
    private fun onPopularMoviesFetched(movies: List<Result>) {
        FilmAdapter.updateFilm(movies)
    }
    /*function dibawah akan merespon jika jaringan tidak dapat diakses*/
    private fun onError() {
        Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }

    /* Intent akan mendefinisikan package MainActivity dan akan pindah ke DetailActivity jika Item diclick
     * putExtra berfungsi untuk mengirim data dari MainActivty ke DetailActivity
     * startActivity berfungsi untuk menjalankan intent agar dapat berpindah.*/
    private fun DetailsFilm(film: Result) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(IMAGE, film.posterPath)
        intent.putExtra(TITLE, film.title)
        intent.putExtra(OVERVIEW, film.overview)
        startActivity(intent)
    }
}