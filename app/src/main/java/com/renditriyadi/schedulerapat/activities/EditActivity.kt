package com.renditriyadi.schedulerapat.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.renditriyadi.schedulerapat.Rapat
import com.renditriyadi.schedulerapat.databinding.ActivityEditBinding
import com.renditriyadi.schedulerapat.room.DatabaseHelper
import com.renditriyadi.schedulerapat.room.DatabaseRapat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.text.SimpleDateFormat
import java.util.*

class EditActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditBinding

    private lateinit var meetingDate: Calendar
    private lateinit var meetingTime: Calendar
    private lateinit var hari: String
    private var id: Int? = null
    private lateinit var tanggal: String
    private lateinit var waktu: String
    private var dbAdd: DatabaseRapat?=null
    val databaseHelper= DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fillData()

        binding.ivShowDatePicker.setOnClickListener{
            showDatePicker()
        }

        binding.ivShowTimePicker.setOnClickListener{
            showTimePicker()
        }

        binding.btnEdit.setOnClickListener {
            updateRapat()
        }

        binding.btnCancel.setOnClickListener {
            backToMainActivity()
        }
    }

    private fun fillData() {
        var itemRapat = intent.getParcelableExtra<Rapat>("rapat")
        binding.etJudulRapat.setText(itemRapat?.judul.orEmpty())
        binding.etLokasi.setText(itemRapat?.lokasi.orEmpty())
        hari = itemRapat?.hari.orEmpty()
        waktu = itemRapat?.waktu.orEmpty()
        tanggal = itemRapat?.tanggal.orEmpty()
        id = itemRapat?.id
        renderDate()
        renderTime()
    }

    private fun renderDate() {
        binding.tvDatePicked.text = "Meeting Date: $hari, $tanggal"
    }

    private fun renderTime() {
        binding.tvTimePicked.text = "Meeting Time: $waktu"
    }

    private fun backToMainActivity() {
        val intent = Intent (this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun updateRapat() {
        dbAdd= DatabaseRapat.getInstance(this)
        val objectRapat = Rapat(
            id,
            binding.etJudulRapat.text.toString(),
            hari,
            tanggal,
            waktu,
            binding.etLokasi.text.toString()
        )

        GlobalScope.async {
            databaseHelper.updateRapat(objectRapat)
        }
        backToMainActivity()
    }

    private fun showTimePicker() {
        meetingTime = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hour, minute ->
            meetingTime.set(Calendar.HOUR_OF_DAY, hour)
            meetingTime.set(Calendar.MINUTE, minute)

            val timeFormat = "HH:mm"
            val sdf = SimpleDateFormat(timeFormat, Locale.getDefault())
            waktu = sdf.format(meetingTime.time)
            renderTime()
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
            renderDate()
        }

        DatePickerDialog(this, dateSetListener,
            meetingDate.get(Calendar.YEAR),
            meetingDate.get(Calendar.MONTH),
            meetingDate.get(Calendar.DAY_OF_MONTH)).show()
    }

}