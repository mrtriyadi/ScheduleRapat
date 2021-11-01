package com.renditriyadi.schedulerapat.presenter

import com.renditriyadi.schedulerapat.Rapat

interface MainPresenter {
    suspend fun getRapat()
    suspend fun addRapat(rapat: Rapat)
    suspend fun updateRapat(rapat: Rapat)
    suspend fun deleteRapat(rapat: Rapat)
}