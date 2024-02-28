package com.example.antoniocaraproyectoandroid.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.antoniocaraproyectoandroid.data.model.FacturaEntity

@Dao
interface FacturaDao {
    @Query("SELECT * FROM factura")
    suspend fun getAllTasks(): MutableList<FacturaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(factura: List<FacturaEntity>)

    @Query("DELETE FROM factura")
    suspend fun nukeTable()

    @Query("SELECT * FROM factura WHERE factura.importeOrdenacion <= :importe")
    suspend fun filtro(importe : Int) : MutableList<FacturaEntity>
}