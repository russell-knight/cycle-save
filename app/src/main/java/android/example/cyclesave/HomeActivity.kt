package android.example.cyclesave

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

// import FloatingButton here?

class HomeActivity : AppCompatActivity() {

    private val returnHomeRequestCode = 1

    private var totalCost = 0
    private var tripsTaken = 0
    private var moneySaved = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Global variable
        var moneySaved = findViewById<TextView>(R.id.money_saved_value)
        moneySaved.text = "$" + GlobalVariables.totalCost.toString()
        // Set initial values
        var tripsText = findViewById<TextView>(R.id.trips_taken_value)
        tripsText.text = tripsTaken.toString()

        val addTrip = findViewById<Button>(R.id.add_trip_button)
        addTrip.setOnClickListener{
            tripsTaken++
            tripsText.text = tripsTaken.toString()
        }

        val subtractTrip = findViewById<Button>(R.id.subtract_trip_button)
        subtractTrip.setOnClickListener{
            tripsTaken--
            tripsText.text = tripsTaken.toString()
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
        // Retrieve global variables
        var moneySaved = findViewById<TextView>(R.id.money_saved_value)
        moneySaved.text = "$" + GlobalVariables.totalCost.toString()
    }
}
