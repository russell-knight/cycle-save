package android.example.cyclesave

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_cost.*
import java.util.*

class AddCost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cost)

        // Prevent keyboard from appearing when date field is selected
        editText_date.showSoftInputOnFocus = false

        editText_date.setOnClickListener {
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                Toast.makeText(this, "Year : " + year
                        + "\nMonth :" + month
                + "\nDay : " + dayOfMonth,Toast.LENGTH_SHORT) .show()
            },

                    now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }
    }
}