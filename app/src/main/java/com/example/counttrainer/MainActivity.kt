package com.example.counttrainer

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button110: Button = findViewById(R.id.button110)
        val button1050: Button = findViewById(R.id.button1050)
        val button5099: Button = findViewById(R.id.button5099)
        val buttonPlus: Button = findViewById(R.id.buttonPlus)
        val buttonMinus: Button = findViewById(R.id.buttonMinus)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val button10s: Button = findViewById(R.id.button10s)
        val button30s: Button = findViewById(R.id.button30s)
        val button60s: Button = findViewById(R.id.button60s)
        val buttonStart: Button = findViewById(R.id.buttonStop)

        var chosenNumbersMin = ""
        var chosenNumbersMax = ""
        val chosenActions = arrayListOf("")
        var chosenTime = ""

        var button110checked = false
        var button1050checked = false
        var button5099checked = false
        var buttonPlusChecked = false
        var buttonMinusChecked = false
        var buttonMultiplyChecked = false
        var button10sChecked = false
        var button30sChecked = false
        var button60sChecked = false

        chosenActions.clear()

        fun doChangeButtonColor(button: Button, isBtnChecked: Boolean) {
            if (isBtnChecked) {
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.primary4))
            } else {
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.primary3))
            }
        }

        fun doCheckButton(button: Boolean, string: Int) {
            if (button) {
                chosenActions.add(getString(string))
            } else {
                chosenActions.remove(getString(string))
            }
        }

        fun doChangeActions(buttonPlus: Boolean, buttonMinus: Boolean, buttonMultiply: Boolean) {
            chosenActions.clear()
            doCheckButton(buttonPlus, R.string.actionPlus)
            doCheckButton(buttonMinus, R.string.actionMinus)
            doCheckButton(buttonMultiply, R.string.actionMultiply)
        }

        button110.setOnClickListener {
            button110checked = !button110checked
            button1050checked = false
            button5099checked = false
            doChangeButtonColor(button110, button110checked)
            doChangeButtonColor(button1050, button1050checked)
            doChangeButtonColor(button5099, button5099checked)
            chosenNumbersMin = ""
            chosenNumbersMax = ""
            if (button110checked) {
                chosenNumbersMin = getString(R.string.easyLevelMinNumber)
                chosenNumbersMax = getString(R.string.easyLevelMaxNumber)
            }

        }

        button1050.setOnClickListener {
            button1050checked = !button1050checked
            button110checked = false
            button5099checked = false
            doChangeButtonColor(button110, button110checked)
            doChangeButtonColor(button1050, button1050checked)
            doChangeButtonColor(button5099, button5099checked)
            chosenNumbersMin = ""
            chosenNumbersMax = ""
            if (button1050checked) {
                chosenNumbersMin = getString(R.string.mediumLevelMinNumber)
                chosenNumbersMax = getString(R.string.mediumLevelMaxNumber)
            }
        }

        button5099.setOnClickListener {
            button5099checked = !button5099checked
            button1050checked = false
            button110checked = false
            doChangeButtonColor(button110, button110checked)
            doChangeButtonColor(button1050, button1050checked)
            doChangeButtonColor(button5099, button5099checked)
            chosenNumbersMin = ""
            chosenNumbersMax = ""
            if (button5099checked) {
                chosenNumbersMin = getString(R.string.hardLevelMinNumber)
                chosenNumbersMax = getString(R.string.hardLevelMaxNumber)
            }
        }

        buttonPlus.setOnClickListener {
            buttonPlusChecked = !buttonPlusChecked
            doChangeButtonColor(buttonPlus, buttonPlusChecked)
            doChangeActions(buttonPlusChecked, buttonMinusChecked, buttonMultiplyChecked)
        }

        buttonMinus.setOnClickListener {
            buttonMinusChecked = !buttonMinusChecked
            doChangeButtonColor(buttonMinus, buttonMinusChecked)
            doChangeActions(buttonPlusChecked, buttonMinusChecked, buttonMultiplyChecked)
        }

        buttonMultiply.setOnClickListener {
            buttonMultiplyChecked = !buttonMultiplyChecked
            doChangeButtonColor(buttonMultiply, buttonMultiplyChecked)
            doChangeActions(buttonPlusChecked, buttonMinusChecked, buttonMultiplyChecked)
        }

        button10s.setOnClickListener {
            button10sChecked = !button10sChecked
            button30sChecked = false
            button60sChecked = false
            doChangeButtonColor(button10s, button10sChecked)
            doChangeButtonColor(button30s, button30sChecked)
            doChangeButtonColor(button60s, button60sChecked)
            chosenTime = ""
            if (button10sChecked) {
                chosenTime = getString(R.string.easyLevelTime)
            }
        }

        button30s.setOnClickListener {
            button30sChecked = !button30sChecked
            button10sChecked = false
            button60sChecked = false
            doChangeButtonColor(button10s, button10sChecked)
            doChangeButtonColor(button30s, button30sChecked)
            doChangeButtonColor(button60s, button60sChecked)
            chosenTime = ""
            if (button30sChecked) {
                chosenTime = getString(R.string.mediumLevelTime)
            }
        }

        button60s.setOnClickListener {
            button60sChecked = !button60sChecked
            button30sChecked = false
            button10sChecked = false
            doChangeButtonColor(button10s, button10sChecked)
            doChangeButtonColor(button30s, button30sChecked)
            doChangeButtonColor(button60s, button60sChecked)
            chosenTime = ""
            if (button60sChecked) {
                chosenTime = getString(R.string.hardLevelTime)
            }
        }

        buttonStart.setOnClickListener {
            if (chosenNumbersMin == "" || chosenNumbersMax == "" || chosenActions.isEmpty() || chosenTime == "") {
                Toast.makeText(this, "Выберите условия в каждом разделе", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("chosenNumbersMin", chosenNumbersMin)
                intent.putExtra("chosenNumbersMax", chosenNumbersMax)
                intent.putExtra("chosenTime", chosenTime)
                intent.putExtra("chosenActions", chosenActions.joinToString(" "))
                startActivity(intent)
            }
        }
    }
}