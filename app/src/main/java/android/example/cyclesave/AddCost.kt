package android.example.cyclesave

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_add_cost.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class AddCost : AppCompatActivity() {

    private lateinit var editNameView: EditText
    private lateinit var editPriceView: EditText
    private lateinit var editDateView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cost)
        editNameView = findViewById(R.id.editText_name)
        editPriceView = findViewById(R.id.editText_price)
        editDateView = findViewById(R.id.editText_date)

        // Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Add Cost"
        setSupportActionBar(toolbar)

        // Database handling
        val fab = findViewById<FloatingActionButton>(R.id.button_add_cost)
        fab.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editNameView.text) || TextUtils.isEmpty(editPriceView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val name = editNameView.text.toString()
                val price = editText_price.text.toString().toInt()
                val date = editText_date.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, name)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

        // Date Dialog View
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val textView: TextView = findViewById(R.id.editText_date)
        textView.text = SimpleDateFormat(myFormat).format(System.currentTimeMillis())

        var cal = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val sdf = SimpleDateFormat(myFormat, Locale.US)
                textView.text = sdf.format(cal.time)
            }
        textView.setOnClickListener {
            DatePickerDialog(
                this@AddCost, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }
    companion object {
        const val EXTRA_REPLY = "com.example.android.costlistsql.REPLY"
    }
}
