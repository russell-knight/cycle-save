package android.example.cyclesave

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CostListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<CostListAdapter.CostViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var costs = emptyList<Cost>() // Cached copy of costs
    var totalCost = 0

    inner class CostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val costNameView: TextView = itemView.findViewById(R.id.cost_name_text)
        val costPriceView: TextView = itemView.findViewById(R.id.cost_price_text)
        val costDateView: TextView = itemView.findViewById(R.id.cost_date_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return CostViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CostViewHolder, position: Int) {
        val current = costs[position]
        holder.costNameView.text = current.name
        holder.costPriceView.text = "$" + current.price.toString()
        holder.costDateView.text = current.datePurchased

        totalCost += current.price
    }

    internal fun setCosts(costs: List<Cost>) {
        this.costs = costs
        notifyDataSetChanged()
    }

    override fun getItemCount() = costs.size

    fun getTotalCostPrice(costs: List<Cost>): Int {
        var total = 0
        for (c in costs) {
            total += c.price
        }
        return total
    }
}