package com.renditriyadi.schedulerapat.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.renditriyadi.schedulerapat.databinding.ActivityLoginBinding
import com.renditriyadi.schedulerapat.sharedpref.Constant
import com.renditriyadi.schedulerapat.sharedpref.PreferencesHelper

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    lateinit var sharedpref: PreferencesHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedpref = PreferencesHelper(this)

        binding.btnLogin.setOnClickListener {
            if (binding.etSecondpageId.text.isNotEmpty() && binding.etSecondpageId.text.isNotEmpty()) {
                saveSession(binding.etSecondpageId.text.toString(), binding.editTextTextPassword.text.toString())
                Toast.makeText(this, "Berhasil Login", Toast.LENGTH_SHORT).show()
                moveIntent()
            }
        }

    }

    private fun moveIntent() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun saveSession(username: String , password: String) {
        sharedpref.put(Constant.PREF_USERNAME, username)
        sharedpref.put(Constant.PREF_PASSWORD, password)
        sharedpref.put(Constant.PREF_IS_LOGIN, true)
    }

    override fun onStart() {
        super.onStart()
        if (sharedpref.getBoolean(Constant.PREF_IS_LOGIN)) {
            moveIntent()
        }
    }
}
