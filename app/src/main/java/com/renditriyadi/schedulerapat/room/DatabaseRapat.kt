package com.renditriyadi.schedulerapat.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.renditriyadi.schedulerapat.Rapat

@Database(entities = [Rapat::class], version = 2)

abstract class DatabaseRapat : RoomDatabase(){
    abstract fun rapatDao(): RapatDAO

    companion object{
        @Volatile
        private var instance: DatabaseRapat?= null

        private fun buildDatabase(context: Context): DatabaseRapat {
            return Room.databaseBuilder(context, DatabaseRapat::class.java, "rapat_db")
                .fallbackToDestructiveMigration()
                .build()
        }

        fun getInstance(context: Context): DatabaseRapat {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

    }
}