package com.renditriyadi.schedulerapat.room


import androidx.room.*
import com.renditriyadi.schedulerapat.Rapat

@Dao
interface RapatDAO{
    @Query("SELECT * FROM RAPAT")
    fun getAllRapat(): List<Rapat>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRapat(rapat: Rapat)

    @Update
    fun updateRapat(rapat: Rapat)

    @Delete
    fun deleteRapat(rapat: Rapat)
}
