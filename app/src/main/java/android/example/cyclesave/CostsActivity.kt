package android.example.cyclesave;

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_add_cost.*
import java.time.LocalDate

class CostsActivity : AppCompatActivity() {

    private val newCostActivityRequestCode = 1
    private lateinit var costViewModel: CostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_costs)

        // Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Costs"
        setSupportActionBar(toolbar)

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

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@CostsActivity, AddCost::class.java)
            startActivityForResult(intent, newCostActivityRequestCode)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newCostActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(AddCost.EXTRA_REPLY)?.let {
                val cost = Cost(it, 455, "12/05/17")
                costViewModel.insert(cost)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}
