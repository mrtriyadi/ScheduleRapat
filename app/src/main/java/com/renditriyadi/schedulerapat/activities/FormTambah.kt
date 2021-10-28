package com.renditriyadi.schedulerapat.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.renditriyadi.schedulerapat.room.DatabaseHelper
import com.renditriyadi.schedulerapat.room.DatabaseRapat
import com.renditriyadi.schedulerapat.Rapat
import com.renditriyadi.schedulerapat.databinding.ActivityFormTambahBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.text.SimpleDateFormat
import java.util.*


class FormTambah : AppCompatActivity() {
    private lateinit var binding: ActivityFormTambahBinding
    private lateinit var meetingDate: Calendar
    private lateinit var meetingTime: Calendar
    private lateinit var hari: String
    private lateinit var tanggal: String
    private lateinit var waktu: String
    private var dbAdd: DatabaseRapat?=null
    val databaseHelper= DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormTambahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            addRapat()
        }

        binding.btnCancel.setOnClickListener {
            this.onDestroy()
        }

        binding.ivShowDatePicker.setOnClickListener {
            showDatePicker()
        }
        binding.ivShowTimePicker.setOnClickListener {
            showTimePicker()
        }
    }

    private fun addRapat() {
        dbAdd= DatabaseRapat.getInstance(this)
        val objectRapat = Rapat(
            null,
            binding.etJudulRapat.text.toString(),
            hari,
            tanggal,
            waktu,
            binding.etLokasi.text.toString()
        )

        GlobalScope.async {
            databaseHelper.addRapat(objectRapat)
        }

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)


    }

    private fun showTimePicker() {
        meetingTime = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hour, minute ->
            meetingTime.set(Calendar.HOUR_OF_DAY, hour)
            meetingTime.set(Calendar.MINUTE, minute)

            val timeFormat = "HH:mm"
            val sdf = SimpleDateFormat(timeFormat, Locale.getDefault())
            waktu = sdf.format(meetingTime.time)
            binding.tvTimePicked.text = "Meeting Time: $waktu"
        }
        TimePickerDialog(this, timeSetListener,
        meetingTime.get(Calendar.HOUR_OF_DAY),
        meetingTime.get(Calendar.MINUTE), true).show()
    }

    private fun showDatePicker() {
        meetingDate = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            meetingDate.set(Calendar.YEAR, year)
            meetingDate.set(Calendar.MONTH, monthOfYear)
            meetingDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val dateFormat = "dd-MM-yyyy"
            val dayFormat = "EEEE"
            val sdfTanggal = SimpleDateFormat(dateFormat, Locale.getDefault())
            val sdfHari = SimpleDateFormat(dayFormat, Locale.getDefault())

            hari = sdfHari.format(meetingDate.time)
            tanggal = sdfTanggal.format(meetingDate.time)
            binding.tvDatePicked.text = "Meeting Date: "+sdfHari.format(meetingDate.time)+", "+sdfTanggal.format(meetingDate.time)
        }

        DatePickerDialog(this, dateSetListener,
            meetingDate.get(Calendar.YEAR),
            meetingDate.get(Calendar.MONTH),
            meetingDate.get(Calendar.DAY_OF_MONTH)).show()
    }
}