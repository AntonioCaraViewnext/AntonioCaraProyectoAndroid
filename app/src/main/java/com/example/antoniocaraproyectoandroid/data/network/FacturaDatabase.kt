package com.example.antoniocaraproyectoandroid.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.antoniocaraproyectoandroid.data.dao.FacturaDao
import com.example.antoniocaraproyectoandroid.data.model.FacturaEntity

@Database(entities = arrayOf(FacturaEntity::class),version =1)
abstract class FacturaDatabase : RoomDatabase() {
    abstract fun facturaDao(): FacturaDao

}