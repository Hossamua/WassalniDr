package com.example.wassalniDR.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.wassalniDR.data.LoggedInDriver
import com.example.wassalniDR.database.DriversRetrofit
import com.example.wassalniDR.database.TripsRetrofit
import com.example.wassalniDR.util.Constant
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context):SharedPreferences{
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun providesLoggedInDriver(sharedPreferences: SharedPreferences):LoggedInDriver{
        val email=sharedPreferences.getString("email","")
        val token=sharedPreferences.getString("token","")
        return LoggedInDriver(email!!,token!!)
    }
    @Provides
    @Singleton
    fun providesDriverRetrofit(sharedPreferences: SharedPreferences):DriversRetrofit{
        val retrofit = Retrofit.Builder().baseUrl(Constant.BASEURL)
            .addConverterFactory(
                MoshiConverterFactory.create()).build()
        return retrofit.create(DriversRetrofit::class.java)
    }
}