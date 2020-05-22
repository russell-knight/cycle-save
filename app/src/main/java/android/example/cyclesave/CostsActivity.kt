package android.example.cyclesave;

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate

val costsArray = arrayOf(
    Cost("Lekker Bike", 599, LocalDate.of(2017,9,21)),
    Cost("Helmet", 40, LocalDate.of(2017,11,1)),
    Cost("Service", 75, LocalDate.of(2018, 2, 14)))

class CostsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_costs)

        val addCostButton = findViewById<Button>(R.id.button_add_cost)
        addCostButton.setOnClickListener{
            val costsIntent = Intent(this, AddCost::class.java)
            startActivity(costsIntent)
        }

        val arrayAdapter: ArrayAdapter<Cost>
        val derp = arrayOf("derp", "slurp", "herp")
        val myListView = findViewById<ListView>(R.id.costsListView)
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, costsArray)
        myListView.adapter = arrayAdapter
    }
}
