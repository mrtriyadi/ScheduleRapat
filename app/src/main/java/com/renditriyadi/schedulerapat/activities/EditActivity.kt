package com.renditriyadi.schedulerapat.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.renditriyadi.schedulerapat.databinding.ActivityEditBinding
import com.renditriyadi.schedulerapat.room.DatabaseHelper
import com.renditriyadi.schedulerapat.room.DatabaseRapat
import java.util.*

class EditActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditBinding

    private lateinit var meetingDate: Calendar
    private lateinit var meetingTime: Calendar
    private lateinit var hari: String
    private lateinit var tanggal: String
    private lateinit var waktu: String
    private var dbAdd: DatabaseRapat?=null
    val databaseHelper= DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEdit.setOnClickListener {
            updateRapat()
        }

        binding.btnCancel.setOnClickListener {
            backToMainActivity()
        }
    }

    private fun backToMainActivity() {
        val intent = Intent (this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun updateRapat() {
        TODO("Not yet implemented")
    }
}