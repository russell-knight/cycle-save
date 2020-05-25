package android.example.cyclesave

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_add_cost.*
import java.text.SimpleDateFormat
import java.util.*

class AddCost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cost)

        // Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Add Cost"
        setSupportActionBar(toolbar)

        val textView: TextView = findViewById(R.id.editText_date)
        textView.text = SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis())

        var cal = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MM/yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                textView.text = sdf.format(cal.time)
            }
        textView.setOnClickListener {
            DatePickerDialog(this@AddCost, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }
}
