package com.example.stressmusicrecommender

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.sqrt

class EmaSurveyActivity : AppCompatActivity() {

    private lateinit var selectedEmotionTextView: TextView
    private lateinit var musicRadioGroup: RadioGroup
    private lateinit var questionIfYes: TextView
    private lateinit var seekBarFocusAway: SeekBar
    private lateinit var questionDwellOn: TextView
    private lateinit var seekBarDwellOn: SeekBar

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ema_survey)

        val emotionGrid: ImageView = findViewById(R.id.emotionGrid)
        selectedEmotionTextView = findViewById(R.id.selectedEmotion)
        musicRadioGroup = findViewById(R.id.musicRadioGroup)
        questionIfYes = findViewById(R.id.questionIfYes)
        seekBarFocusAway = findViewById(R.id.seekBarFocusAway)
        questionDwellOn = findViewById(R.id.questionDwellOn)
        seekBarDwellOn = findViewById(R.id.seekBarDwellOn)

        setupEmotionGrid(emotionGrid)
        setupMusicQuestion()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupEmotionGrid(emotionGrid: ImageView) {
        val emotionCoordinates = mapOf(
            // Top-Left Quadrant (Unpleasant, High Arousal)
            Pair(3.4f, 8.9f) to "Infuriated",
            Pair(4.4f, 8.0f) to "Fear",
            Pair(3.4f, 7.4f) to "Angry",
            Pair(4.4f, 6.9f) to "Alarmed",
            Pair(3.4f, 6.1f) to "Frustrated",
            Pair(4.4f, 5.6f) to "Annoyed",

            // Top-Right Quadrant (Pleasant, High Arousal)
            Pair(5.9f, 8.8f) to "Excited",
            Pair(6.4f, 8.0f) to "Happy",
            Pair(5.9f, 7.2f) to "Delighted",
            Pair(5.4f, 6.8f) to "Amused",
            Pair(6.2f, 6.0f) to "Glad",
            Pair(5.4f, 5.3f) to "Curious",

            // Bottom-Left Quadrant (Unpleasant, Low Arousal)
            Pair(3.4f, 4.5f) to "Miserable",
            Pair(4.4f, 3.9f) to "Bored",
            Pair(3.4f, 3.1f) to "Depressed",
            Pair(4.4f, 2.6f) to "Gloomy",
            Pair(3.4f, 1.9f) to "Sad",
            Pair(4.4f, 1.1f) to "Tired",

            // Bottom-Right Quadrant (Pleasant, Low Arousal)
            Pair(6.3f, 4.4f) to "Content",
            Pair(5.1f, 3.3f) to "Satisfied",
            Pair(6.4f, 2.3f) to "Relaxed",
            Pair(5.7f, 1.2f) to "Calm"
        )

        emotionGrid.setOnTouchListener { _, event ->
            if (event.action == android.view.MotionEvent.ACTION_DOWN) {
                val touchX = event.x
                val touchY = event.y
                val imageWidth = emotionGrid.width.toFloat()
                val imageHeight = emotionGrid.height.toFloat()

                if (touchX < 0 || touchY < 0 || touchX > imageWidth || touchY > imageHeight) {
                    selectedEmotionTextView.text = "None"
                } else {
                    val closestEmotion = findClosestEmotion(touchX, touchY, imageWidth, imageHeight, emotionCoordinates)
                    selectedEmotionTextView.text = closestEmotion ?: "None"
                }
                true
            } else {
                false
            }
        }
    }

    private fun findClosestEmotion(touchX: Float, touchY: Float, imageWidth: Float, imageHeight: Float, emotionCoordinates: Map<Pair<Float, Float>, String>): String? {
        val gridX = (touchX / imageWidth) * 10
        val gridY = (10 - (touchY / imageHeight) * 10)

        return emotionCoordinates.minByOrNull { (coord, _) ->
            sqrt((coord.first - gridX).pow(2) + (coord.second - gridY).pow(2))
        }?.value
    }

    private fun setupMusicQuestion() {
        musicRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioNo -> {
                    questionIfYes.visibility = View.GONE
                    seekBarFocusAway.visibility = View.GONE
                    questionDwellOn.visibility = View.GONE
                    seekBarDwellOn.visibility = View.GONE
                }
                R.id.radioYes -> {
                    questionIfYes.visibility = View.VISIBLE
                    seekBarFocusAway.visibility = View.VISIBLE
                    questionDwellOn.visibility = View.VISIBLE
                    seekBarDwellOn.visibility = View.VISIBLE
                }
            }
        }
    }
}
