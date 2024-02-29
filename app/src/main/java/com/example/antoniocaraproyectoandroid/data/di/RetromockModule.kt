package com.example.antoniocaraproyectoandroid.data.di

import co.infinum.retromock.Retromock
import com.example.antoniocaraproyectoandroid.data.network.RetromockService
import com.example.antoniocaraproyectoandroid.data.di.NetworkModule.providerRetrofit
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

    @Singleton
    @Provides
    fun provideRetromockService(retromock: Retromock): RetromockService{
        return retromock.create(RetromockService::class.java)
    }
}