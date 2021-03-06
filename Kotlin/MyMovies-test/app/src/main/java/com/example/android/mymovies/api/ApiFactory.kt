package com.example.android.mymovies.api

import com.example.android.mymovies.AppConstants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiFactory {

    /**
     * Creating Auth Interceptor to add api_key query in front of all the requests.
     * Создание перехватчика аутентификации для добавления запроса api_key перед всеми запросами.
     */
    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("api_key", AppConstants.API_KEY)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    /**
     * OkhttpClient for building http request url
     * OkhttpClient для создания URL-адреса http-запроса
     */
    private val tmdbClient = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .build()

    fun retrofit(): Retrofit = Retrofit.Builder()
        .client(tmdbClient)
        .baseUrl(AppConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    object MovieApi {
        val apiService: ApiService by lazy {
            retrofit().create(ApiService::class.java)
        }
    }
}