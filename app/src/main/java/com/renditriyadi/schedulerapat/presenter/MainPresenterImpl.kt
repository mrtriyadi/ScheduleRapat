package com.renditriyadi.schedulerapat.presenter

import android.content.Context
import androidx.room.Database
import com.renditriyadi.schedulerapat.Rapat
import com.renditriyadi.schedulerapat.room.DatabaseRapat
import com.renditriyadi.schedulerapat.room.RapatDAO
import com.renditriyadi.schedulerapat.view.MainView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainPresenterImpl (private val context: Context, private val mainView: MainView) : MainPresenter{

    private val rapatDatabase: DatabaseRapat by lazy {
        DatabaseRapat.getInstance(context)
    }

    private val rapatDao: RapatDAO by lazy {
        rapatDatabase.rapatDao()
    }

    override suspend fun getRapat()= withContext(Dispatchers.IO) {
        val rapats = rapatDao.getAllRapat()
        mainView.onGetRapat(rapats)
    }

    override suspend fun addRapat(rapat: Rapat) {
        TODO("Not yet implemented")
    }

    override suspend fun updateRapat(rapat: Rapat) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRapat(rapat: Rapat) {
        TODO("Not yet implemented")
    }
}