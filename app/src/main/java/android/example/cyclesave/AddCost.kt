package android.example.cyclesave

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddCost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cost)

        val edtDate = findViewById<EditText>(R.id.editText_date) as EditText
        edtDate.setOnClickListener {
            Toast.makeText(this@AddCost, "EditText Click", Toast.LENGTH_SHORT).show()
        }
    }
}