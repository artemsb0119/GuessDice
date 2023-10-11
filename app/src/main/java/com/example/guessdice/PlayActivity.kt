package com.example.guessdice

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.Gravity
import android.view.KeyEvent
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.bumptech.glide.Glide
import java.util.Random

class PlayActivity : AppCompatActivity() {

    private lateinit var imageViewFon2: ImageView
    private lateinit var imageDice: ImageView
    private lateinit var imageHome: ImageView
    private lateinit var imageRestart: ImageView
    private lateinit var button1: AppCompatButton
    private lateinit var button2: AppCompatButton
    private lateinit var button3: AppCompatButton
    private lateinit var button4: AppCompatButton
    private lateinit var button5: AppCompatButton
    private lateinit var button6: AppCompatButton
    private lateinit var textViewScore: TextView
    private lateinit var textViewStavka: TextView
    private lateinit var sharedPreferences: SharedPreferences

    var score = 0
    var stavka = 0

    private val images = arrayOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )

    private var currentImage: Int = 0
    private var userChoice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        textViewStavka = findViewById(R.id.textViewStavka)
        textViewScore = findViewById(R.id.textViewScore)
        imageViewFon2 = findViewById(R.id.imageViewFon2)
        imageHome = findViewById(R.id.imageHome)
        imageRestart = findViewById(R.id.imageRestart)
        imageDice = findViewById(R.id.imageDice)

        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        score = sharedPreferences.getInt("score", 5)
        textViewScore.text = "Score: " + score.toString()

        Glide.with(this)
            .load("http://135.181.248.237/27/fon2.png")
            .into(imageViewFon2)
        Glide.with(this)
            .load("http://135.181.248.237/27/dice_1.png")
            .into(imageDice)

        imageHome.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
        imageRestart.setOnClickListener {
            recreate()
        }

        button1.setOnClickListener {
            userChoice = R.drawable.dice_1
            rollDice()
        }
        button2.setOnClickListener {
            userChoice = R.drawable.dice_2
            rollDice()
        }
        button3.setOnClickListener {
            userChoice = R.drawable.dice_3
            rollDice()
        }
        button4.setOnClickListener {
            userChoice = R.drawable.dice_4
            rollDice()
        }
        button5.setOnClickListener {
            userChoice = R.drawable.dice_5
            rollDice()
        }
        button6.setOnClickListener {
            userChoice = R.drawable.dice_6
            rollDice()
        }

        showDialog()
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.stavka_layout)

        val editTextValue = dialog.findViewById<TextView>(R.id.editTextValue)
        val buttonOk = dialog.findViewById<TextView>(R.id.buttonOk)

        buttonOk.setOnClickListener {
            if (TextUtils.isEmpty(editTextValue.text)) {
                stavka = 0
            } else {
                stavka = editTextValue.text.toString().toInt()
            }
            if (stavka == 0) {

            } else if (stavka <= 50 && stavka > score) {
                stavka = score
                dialog.dismiss()
            } else if (stavka > 50 && stavka <= score) {
                stavka = 50
                dialog.dismiss()
            } else if (stavka > 50 && stavka > score) {
                if (score > 50) {
                    stavka = 50
                    dialog.dismiss()
                } else {
                    stavka = score
                    dialog.dismiss()
                }
            } else {
                dialog.dismiss()
            }
            textViewStavka.text = stavka.toString()
            score -= stavka
            textViewScore.text = "Score: " + score.toString()
        }

        val window = dialog.window
        val layoutParams = WindowManager.LayoutParams()

        layoutParams.gravity = Gravity.CENTER
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = (resources.displayMetrics.heightPixels * 0.4).toInt()
        window?.attributes = layoutParams

        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun rollDice() {
        button1.isClickable = false
        button2.isClickable = false
        button3.isClickable = false
        button4.isClickable = false
        button5.isClickable = false
        button6.isClickable = false
        val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_up)
        val scaleDownAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_down)
        scaleDownAnimation.duration = 1000
        scaleAnimation.duration = 1000
        scaleAnimation.fillAfter = true

        val handler = Handler(Looper.getMainLooper())

        imageDice.startAnimation(scaleAnimation)

        handler.postDelayed({
            val permutator = Runnable {
                val randomImage = images.random()
                currentImage = randomImage
                Glide.with(this@PlayActivity).load(randomImage).into(imageDice)
            }
            handler.postDelayed(permutator, 0)
            handler.postDelayed(permutator, 200)
            handler.postDelayed(permutator, 400)
            handler.postDelayed(permutator, 600)
            handler.postDelayed(permutator, 800)
            handler.postDelayed(permutator, 1000)
            handler.postDelayed(permutator, 1200)
            handler.postDelayed(permutator, 1400)
            handler.postDelayed(permutator, 1600)
            handler.postDelayed(permutator, 1800)
            handler.postDelayed(permutator, 2000)
            handler.postDelayed(permutator, 2200)
            handler.postDelayed(permutator, 2400)
            handler.postDelayed(permutator, 2600)
            handler.postDelayed(permutator, 2800)
            handler.postDelayed(permutator, 3000)
        }, 200)

        handler.postDelayed({
            val scaleDownAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_down)
            scaleDownAnimation.duration = 1000
            imageDice.startAnimation(scaleDownAnimation)

            // Поставьте showDialogResult() в очередь через 2 секунды (2000 миллисекунд)
            handler.postDelayed({
                showDialogResult()
            }, 2000)
        }, 4000)
    }

    private fun showDialogResult() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.result_layout)

        val textViewResult = dialog.findViewById<TextView>(R.id.textViewResult)
        val buttonRetry = dialog.findViewById<AppCompatButton>(R.id.buttonRetry)

        if (userChoice == currentImage) {
            textViewResult.text = "YOU WON!"
            score += stavka * 6
            textViewScore.text = "Score: " + score.toString()
            sharedPreferences.edit().putInt("score", score).apply()
        } else {
            textViewResult.text = "YOU'VE LOST!"
            sharedPreferences.edit().putInt("score", score).apply()

        }

        buttonRetry.setOnClickListener {
            recreate()
        }

        val window = dialog.window
        val layoutParams = WindowManager.LayoutParams()

        layoutParams.gravity = Gravity.CENTER
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = (resources.displayMetrics.heightPixels * 0.4).toInt()
        window?.attributes = layoutParams

        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        startActivity(Intent(this, MenuActivity::class.java))
        finish()
        return super.onKeyDown(keyCode, event)
    }
}