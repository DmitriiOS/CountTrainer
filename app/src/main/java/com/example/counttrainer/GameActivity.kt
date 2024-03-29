package com.example.counttrainer

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.math.max
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val textViewScore: TextView = findViewById(R.id.textViewScore)
        val textViewTime: TextView = findViewById(R.id.textViewTime)

        val textViewNumberOne: TextView = findViewById(R.id.textViewNumberOne)
        val textViewSign: TextView = findViewById(R.id.textViewSign)
        val textViewNumberTwo: TextView = findViewById(R.id.textViewNumberTwo)

        val buttonAnswer1: Button = findViewById(R.id.buttonAnswer1)
        val buttonAnswer2: Button = findViewById(R.id.buttonAnswer2)
        val buttonAnswer3: Button = findViewById(R.id.buttonAnswer3)
        val buttonAnswer4: Button = findViewById(R.id.buttonAnswer4)

        val buttonStop: Button = findViewById(R.id.buttonStop)

        val chosenNumbersMin = intent.getStringExtra("chosenNumbersMin")!!.toInt()
        val chosenNumbersMax = intent.getStringExtra("chosenNumbersMax")!!.toInt()
        val chosenActions = intent.getStringExtra("chosenActions")
        var chosenTime = intent.getStringExtra("chosenTime")!!.toInt()

        var actions = chosenActions!!.split(" ")
        var score = 0
        textViewScore.text = score.toString()
        textViewTime.text = chosenTime.toString()

        var rightAnswer = 0

        val timer = object: CountDownTimer((chosenTime.toLong() * 1000), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                chosenTime--
                textViewTime.text = chosenTime.toString()
            }

            override fun onFinish() {
                buttonAnswer1.isEnabled = false
                buttonAnswer2.isEnabled = false
                buttonAnswer3.isEnabled = false
                buttonAnswer4.isEnabled = false
                buttonStop.text = "НАЗАД"

                val builder: AlertDialog.Builder = AlertDialog.Builder(this@GameActivity)
                builder
                    .setMessage("Ваш счет: $score.")
                    .setTitle("Игра окончена.")
                    .setNegativeButton("В главное меню") { dialog, which ->
                        val intent = Intent(this@GameActivity, MainActivity::class.java)
                        startActivity(intent)
                    }

                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }

        timer.start()

        fun randomNumber(): Int = Random.nextInt(chosenNumbersMin, chosenNumbersMax)

        fun doRandomActionIndex(actions: List<String>): Int {
            if (actions.count() == 1) {
                return 0
            } else {
                return (Random.nextInt(0, actions.count()))
            }
        }

        fun showSign(action: String): String {
            var theSign = ""
            when (action) {
                "plus" -> theSign = "+"
                "minus" -> theSign = "-"
                "multiply" -> theSign = "×"
            }
            return theSign
        }

        fun doNewExercise() {
            val randomSign = showSign(actions[doRandomActionIndex(actions)])
            val numberOne = randomNumber()
            val numberTwo = randomNumber()

            when (randomSign) {
                "+" -> { rightAnswer = numberOne + numberTwo }
                "-" -> { rightAnswer = numberOne - numberTwo }
                "×" -> { rightAnswer = numberOne * numberTwo }
            }

            val answerVariants = arrayListOf<String>()
            answerVariants.add(rightAnswer.toString())

            while (answerVariants.count() < 4) {
                val randomElement = Random.nextInt((rightAnswer - 4), (rightAnswer + 4)).toString()
                if (!answerVariants.contains(randomElement)) {
                    answerVariants.add(randomElement)
                }
            }

            answerVariants.shuffle()

            textViewNumberOne.text = numberOne.toString()
            textViewNumberTwo.text = numberTwo.toString()
            textViewSign.text = randomSign

            buttonAnswer1.text = answerVariants[0]
            buttonAnswer2.text = answerVariants[1]
            buttonAnswer3.text = answerVariants[2]
            buttonAnswer4.text = answerVariants[3]
        }

        fun doCheckAnswer(answer: String) {
            if (answer == rightAnswer.toString()) {
                score++
                textViewScore.text = score.toString()
            }
            doNewExercise()
        }

        doNewExercise()

        buttonAnswer1.setOnClickListener {
            doCheckAnswer(buttonAnswer1.text.toString())
        }
        buttonAnswer2.setOnClickListener {
            doCheckAnswer(buttonAnswer2.text.toString())
        }
        buttonAnswer3.setOnClickListener {
            doCheckAnswer(buttonAnswer3.text.toString())
        }
        buttonAnswer4.setOnClickListener {
            doCheckAnswer(buttonAnswer4.text.toString())
        }

        buttonStop.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }
}