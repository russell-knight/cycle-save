package android.example.cyclesave

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

    inner class CostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val costItemView: TextView = itemView.findViewById(R.id.cost_name_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return CostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CostViewHolder, position: Int) {
        val current = costs[position]
        holder.costItemView.text = current.name
    }

    internal fun setCosts(costs: List<Cost>) {
        this.costs = costs
        notifyDataSetChanged()
    }

    override fun getItemCount() = costs.size
}