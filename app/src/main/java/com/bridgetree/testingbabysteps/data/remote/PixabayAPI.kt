package com.bridgetree.testingbabysteps.data.remote

import com.bridgetree.testingbabysteps.BuildConfig
import com.bridgetree.testingbabysteps.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    //Note that the API_KEY is added in the 'gradle.properties' file, which is excluded from git, i.e, gitIgnored
    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY): Response<ImageResponse>

}