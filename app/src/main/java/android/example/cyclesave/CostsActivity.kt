package android.example.cyclesave;

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_costs.*

class CostsActivity : AppCompatActivity() {

    private val newCostActivityRequestCode = 1
    private lateinit var costViewModel: CostViewModel
    var totalCost = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_costs)

        // Retrieve global variables and update total cost
        totalCost = GlobalVariables.totalCost
        total_cost_value.text = "$" + totalCost.toString()

        // Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Costs"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(this@CostsActivity, HomeActivity::class.java))
        }


        // RecyclerView and Adapter setup
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = CostListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        costViewModel = ViewModelProvider(this).get(CostViewModel::class.java)
        costViewModel.allCosts.observe(this, Observer { costs ->
            // Update the cached copy of the costs in the adapter.
            costs?.let { adapter.setCosts(it) }
        })

        val fabDelete = findViewById<FloatingActionButton>(R.id.fabDeleteAll)
        fabDeleteAll.setOnClickListener {
            val intent = Intent()
        }

        // FAB Add Cost button functionality
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        fabAdd.setOnClickListener {
            val intent = Intent(this@CostsActivity, AddCost::class.java)
            startActivityForResult(intent, newCostActivityRequestCode)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newCostActivityRequestCode && resultCode == Activity.RESULT_OK) {

            // Retrieve values passed from AddCost
            val name = data!!.getStringExtra(AddCost.EXTRA_REPLY_NAME)
            val price = data.getIntExtra(AddCost.EXTRA_REPLY_PRICE, 0)
            val date = data.getStringExtra(AddCost.EXTRA_REPLY_DATE)

            val cost = Cost(name, price, date)
            costViewModel.insert(cost)

            GlobalVariables.totalCost += price // update global var total cost
            total_cost_value.text = "$" + GlobalVariables.totalCost.toString() // update local total cost

        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}
