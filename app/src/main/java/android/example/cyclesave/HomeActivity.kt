package android.example.cyclesave

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

// import FloatingButton here?

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val costs_button = findViewById<Button>(R.id.button_view_costs)
        costs_button.setOnClickListener{
            val costs_intent = Intent(this, CostsActivity::class.java)
            startActivity(costs_intent)
        }
    }

    override fun onPause() {
        super.onPause()

    }
}
