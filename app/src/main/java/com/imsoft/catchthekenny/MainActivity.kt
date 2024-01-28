package com.imsoft.catchthekenny

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.imsoft.catchthekenny.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var score: Int = 0
    private var imageArray = ArrayList<ImageView>()
    private var runnable = Runnable { }
    private var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageArray.add(binding.imageView1)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)

        showKenny()



        object : CountDownTimer(16000,1000) {
            override fun onFinish() {

                binding.timeView.text = "Time: 0"
                handler.removeCallbacks(runnable)

                for (kenny in imageArray) {
                    kenny.visibility = View.INVISIBLE
                }

                val alert = AlertDialog.Builder(this@MainActivity)

                alert.setTitle("Game Over")
                alert.setMessage("Restart Game")
                alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    finish()
                    startActivity(intent)
                })

                alert.setNegativeButton("No", null)

                alert.show()

            }

            override fun onTick(millisUntilFinished: Long) {
                binding.timeView.text = "Time: ${millisUntilFinished/1000}"
            }
        }.start()

    }

    fun showKenny () {

        runnable = object : Runnable {
            override fun run() {
                for (kenny in imageArray) {
                    kenny.visibility = View.INVISIBLE
                }

                val random = Random().nextInt(9)
                imageArray[random].visibility = View.VISIBLE

                handler.postDelayed(runnable, 500)
            }
        }

        handler.post(runnable)

    }

    fun setScore(view: View) {
        score += 1
        binding.scoreView.text = score.toString()
    }
}