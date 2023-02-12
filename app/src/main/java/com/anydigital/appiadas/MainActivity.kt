package com.anydigital.appiadas

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.anydigital.appiadas.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    // Substitui o setContentView(R.layout.activity_main)
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var lastRandowNumber = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        binding.btNewJoke.setOnClickListener {
            tellJoke()
            playSong()
        }
    }

    private fun playSong() {
        val mediaPlayer = MediaPlayer.create(this, R.raw.badumtss)
        mediaPlayer.start()
    }

    private fun tellJoke() {
        binding.tvJokeAnswers.visibility = View.GONE
        val jokes = resources.getStringArray(R.array.jokers) // Busca o array de piadas ...
        val randomNumber = Random.nextInt(jokes.size)
        val joke = jokes[randomNumber]

        val jokesAnswers = resources.getStringArray(R.array.jokersAnswers) // Busca o array de piadas ...
        val randomNumberAnswer = Random.nextInt(jokesAnswers.size)
        val jokesAnswer = jokesAnswers[randomNumber]

        binding.tvJoke.text = joke
        binding.tvJokeAnswers.text = jokesAnswer


        if (lastRandowNumber != randomNumber) {
            val timeDelay = 2000
            Handler(Looper.getMainLooper()).postDelayed({
                binding.tvJokeAnswers.visibility = View.VISIBLE
                playSong()
            }, timeDelay.toLong())

        } else {
            tellJoke()
        }
        lastRandowNumber = randomNumber
    }
}