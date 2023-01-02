package com.example.appyanu2.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// TODO 2 : Retrofit API
/*
* class interface Api akan menghubungkan data api ke model untuk disimpan.
* function getFilm terdapat parameter get digunakan untuk menentukan endpoint url web.
* parameter query digunakan untuk mendeklarasikan api key yang digunakan untuk mengakses data api
* call digunakan untuk memamnggil model data yang digunakan untuk repository*/
interface FilmApi {
    @GET("movie/popular")
    fun getFilm(
        @Query("api_key") apiKey: String = "2c19b72b3ad10852f55b6d5e37566e7e",
        @Query("page") page: Int
    ): Call<Film>
}

object ApiService {
    private val api: FilmApi

    //value retrofit akan memeerlukan url untuk mengakses data api, serta GsonConverter yang digunakan
    //untuk mengkonversi data json ke java dan selanjutnya akan di kirim ke model object
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(FilmApi::class.java)
    }
    /*
    * pada function getFilms akan mengcallback data yang disimpan di dalam model dan akan ditampilkan
    * pada main activity*/
    fun getFilms(
        page: Int = 1,
        onSuccess: (movies: List<Result>) -> Unit,
        onError: () -> Unit
    ) {
        /*
        * enqueue akan mengirimkan permintaan dan memberitahu aplikasi untuk meneripa respon HTTP
        * yang akan dilakukan oleh function onResponse dan function onFailure akan dipanggil ketika
        * pengecualian jaringan terjadi saat melakukan permaintaan*/
        api.getFilm(page = page)
            .enqueue(object : Callback<Film> {
                override fun onResponse(
                    call: Call<Film>,
                    response: Response<Film>
                ) {
                    //terdapat validasi untuk menampilkan apakah permintaan sukses atau tidak
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<Film>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}