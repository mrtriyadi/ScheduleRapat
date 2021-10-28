package com.renditriyadi.schedulerapat.room

import android.content.Context
import com.renditriyadi.schedulerapat.Rapat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



class DatabaseHelper(private val context: Context) {

    private val databaseRapat: DatabaseRapat by lazy {
        DatabaseRapat.getInstance(context)
    }

    private val rapatDao: RapatDAO by lazy {
        databaseRapat.rapatDao()
    }

    suspend fun getRapat(): List<Rapat> = withContext(Dispatchers.IO) {
        rapatDao.getAllRapat()
    }

    suspend fun addRapat(rapat: Rapat) = withContext(Dispatchers.IO) {
        rapatDao.addRapat(rapat)
    }

    suspend fun updateRapat(rapat: Rapat) = withContext(Dispatchers.IO) {
        rapatDao.addRapat(rapat)
    }

    suspend fun deleteRapat(rapat: Rapat) = withContext(Dispatchers.IO) {
        rapatDao.addRapat(rapat)
    }

}