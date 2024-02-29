package com.example.antoniocaraproyectoandroid.data.di

import android.content.Context
import androidx.room.Room
import com.example.antoniocaraproyectoandroid.data.dao.FacturaDao
import com.example.antoniocaraproyectoandroid.data.network.FacturaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomDatabase {

    @Provides
    @Singleton
    fun inicializarDatabase(@ApplicationContext context: Context): FacturaDatabase{
        val room : FacturaDatabase =
            Room.databaseBuilder(context, FacturaDatabase::class.java, "factura-db").build()
        return room
    }

    @Provides
    @Singleton
    fun provideChannelDao(db: FacturaDatabase): FacturaDao {
        return db.facturaDao()
    }
}