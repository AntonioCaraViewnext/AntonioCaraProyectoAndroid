package com.example.antoniocaraproyectoandroid.di

import co.infinum.retromock.Retromock
import com.example.antoniocaraproyectoandroid.di.NetworkModule.providerRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetromockModule {
    @Singleton
    @Provides
    fun providerRetromock(): Retromock {
        val retromock = Retromock.Builder()
            .retrofit(providerRetrofit())
            .build()
        return retromock
    }
}