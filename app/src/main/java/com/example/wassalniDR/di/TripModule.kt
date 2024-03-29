package com.example.wassalniDR.di

import com.example.wassalniDR.database.TripsRetrofit
import com.example.wassalniDR.datasource.DirectionApiService
import com.example.wassalniDR.util.Constant.BASEURL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object TripModule {

    @Provides
    @ViewModelScoped
    fun getTripRetrofit(): TripsRetrofit {
        val retrofit = Retrofit.Builder().baseUrl(BASEURL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
                )
            ).build()
        return retrofit.create(TripsRetrofit::class.java)
    }

    @Provides
    @ViewModelScoped
    fun provideDirectionApiService(): DirectionApiService {
        //https://maps.googleapis.com/maps/api/directions/json?origin=Disneyland&destination=Universal+Studios+Hollywood&key=YOUR_API_KEY
        val baseUrl = "https://maps.googleapis.com/maps/api/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        return retrofit.create(DirectionApiService::class.java)
    }

}