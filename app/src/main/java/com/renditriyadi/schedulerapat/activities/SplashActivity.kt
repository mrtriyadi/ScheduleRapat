package com.renditriyadi.schedulerapat.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.renditriyadi.schedulerapat.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    private lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler()
        handler.postDelayed(
            {
                val intent = Intent (this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
    }
}