package com.imsoft.catchthekenny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.imsoft.catchthekenny.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var score: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun setScore(view: View) {
        score += 1
        binding.scoreView.text = score.toString()
    }
}