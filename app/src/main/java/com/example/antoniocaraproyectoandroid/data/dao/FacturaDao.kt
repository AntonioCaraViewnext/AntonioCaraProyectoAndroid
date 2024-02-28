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

    @Query("SELECT * FROM factura " +
            "WHERE factura.importeOrdenacion <= :importe " +
            "AND (substr(fecha,7,4) || '-' || substr(fecha,4,2) || '-' || substr(fecha,1,2) BETWEEN" +
            " substr(:infoBtnDesde,7,4) || '-' || substr(:infoBtnDesde,4,2) || '-' || substr(:infoBtnDesde,1,2) " +
            "AND substr(:infoBtnHasta,7,4) || '-' || substr(:infoBtnHasta,4,2) || '-' || substr(:infoBtnHasta,1,2)) " +
            "AND (factura.descEstado IN (:listaCheckBoxMutable))")
    suspend fun filtro(importe: Int?, listaCheckBoxMutable: MutableList<String?>,infoBtnDesde : String? ,infoBtnHasta : String?): MutableList<FacturaEntity>
}