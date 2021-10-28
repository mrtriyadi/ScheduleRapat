package com.renditriyadi.schedulerapat

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.sql.Time
import java.util.*

@Entity
@Parcelize
data class Rapat (
    @PrimaryKey(autoGenerate = true) var id:Int?,
    @ColumnInfo(name="Judul") var judul: String,
    @ColumnInfo(name = "Hari") var hari: String,
    @ColumnInfo(name = "Tanggal") var tanggal: String,
    @ColumnInfo(name = "Waktu") var waktu: String,
    @ColumnInfo(name = "Lokasi") var lokasi: String
    ) : Parcelable

