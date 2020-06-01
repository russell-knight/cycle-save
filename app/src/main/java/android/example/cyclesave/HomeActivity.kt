package android.example.cyclesave

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import kotlin.math.roundToInt

// import FloatingButton here?

class HomeActivity : AppCompatActivity() {

    private val returnHomeRequestCode = 1

    private var transportSavings = 0.0
    private var tripsTaken = 0
    private var costRemaining = 0.0
    private var repaymentProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        calculateCosts()
        updateValues()

        val addTrip = findViewById<Button>(R.id.add_trip_button)
        addTrip.setOnClickListener{
            tripsTaken++
            calculateCosts()
            updateValues()
        }

        val subtractTrip = findViewById<Button>(R.id.subtract_trip_button)
        subtractTrip.setOnClickListener{
            // trips taken cannot be negative
            if (tripsTaken != 0) {
                tripsTaken--
                calculateCosts()
                updateValues()
            }

        }

        // Set button to go to Costs Activity
        val viewCostsBtn = findViewById<Button>(R.id.button_view_costs)
        viewCostsBtn.setOnClickListener{
            val costsIntent = Intent(this, CostsActivity::class.java)
            startActivity(costsIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        calculateCosts()
        updateValues()
    }
    /* This function updates the values for the TextViews on the home screen */
    fun updateValues() {
        trips_taken_value.text = tripsTaken.toString()
        money_saved_value.text = "$" + transportSavings
        cost_remaining.text = "Repayment remaining: $" + costRemaining
        repayment_percentage.text = "Repayment Progress: " + repaymentProgress.toString() + "%"
    }

    /* Calculates costs and updates necessary variables */
    fun calculateCosts() {
        this.transportSavings = tripsTaken * GlobalVariables.FULL_FARE
        costRemaining = GlobalVariables.totalCost - transportSavings
        if (GlobalVariables.totalCost == 0.0) {
            repaymentProgress = 0
        }
        else {
            repaymentProgress = 100 - (costRemaining/GlobalVariables.totalCost*100).roundToInt()
        }

    }
}
