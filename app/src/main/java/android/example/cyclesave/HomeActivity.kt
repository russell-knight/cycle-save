package android.example.cyclesave

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

// import FloatingButton here?

class HomeActivity : AppCompatActivity() {

    private var moneySaved = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val costsButton = findViewById<Button>(R.id.button_view_costs)
        costsButton.setOnClickListener{
            val costsIntent = Intent(this, CostsActivity::class.java)
            startActivity(costsIntent)
        }
    }

    override fun onPause() {
        super.onPause()

    }
}
