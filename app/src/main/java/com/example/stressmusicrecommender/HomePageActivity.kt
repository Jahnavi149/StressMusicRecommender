package com.example.stressmusicrecommender

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomePageActivity : AppCompatActivity() {

    private lateinit var surveyLayout: LinearLayout
    private lateinit var surveysCompleted: TextView
    private lateinit var nextSurvey: TextView
    private lateinit var pendingSurveys: TextView
    private val dateFormatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        surveyLayout = findViewById(R.id.survey_buttons)
        surveysCompleted = findViewById(R.id.surveys_completed)
        nextSurvey = findViewById(R.id.next_survey)
        pendingSurveys = findViewById(R.id.pending_surveys)
        val calendarView: CalendarView = findViewById(R.id.calendarView)

        // Display the EMA survey details for the current date by default
        val currentDate = dateFormatter.format(Calendar.getInstance().time)
        updateSurveyDetails(currentDate)

        // Update the survey details whenever a new date is selected
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = dateFormatter.format(Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }.time)
            updateSurveyDetails(selectedDate)
        }
    }

    private fun updateSurveyDetails(date: String) {
        // Example of populating survey data based on the selected date
        // Clear the previous survey buttons
        surveyLayout.removeAllViews()

        // Dummy data for the number of surveys and times
        // In a real scenario, fetch the data based on the date
        val surveys = listOf("Survey of $date 10:00 AM", "Survey of $date 11:00 AM")
        val completedSurveys = 1 // Example count
        val pendingCount = surveys.size - completedSurveys

        surveysCompleted.text = "Surveys completed today: $completedSurveys/${surveys.size}"
        nextSurvey.text = "Next survey in: 30 mins" // Example, customize based on logic
        pendingSurveys.text = "Pending Surveys: $pendingCount"

        // Dynamically add buttons for each survey
        for (survey in surveys) {
            val button = Button(this)
            button.text = survey
            button.setOnClickListener {
                // Navigate to the EMA Survey page with the survey details
                val intent = Intent(this, EmaSurveyActivity::class.java)
                // Optionally, pass date or survey info to the EMA survey activity
                intent.putExtra("SURVEY_DATE", date)
                startActivity(intent)
            }
            surveyLayout.addView(button)
        }
    }
}
