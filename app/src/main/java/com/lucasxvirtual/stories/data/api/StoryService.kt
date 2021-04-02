package com.lucasxvirtual.stories.data.api

import com.lucasxvirtual.stories.data.response.StoryResponse
import retrofit2.http.GET
import com.lucasxvirtual.stories.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Interface to access story api.
 */
interface StoryService {

    @GET("v1/images/latest")
    suspend fun getStories(): StoryResponse

    companion object {
        private const val BASE_URL = BuildConfig.BASE_URL

        fun create(): StoryService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StoryService::class.java)
        }
    }
}
