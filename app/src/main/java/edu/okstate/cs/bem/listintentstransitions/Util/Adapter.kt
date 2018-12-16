package edu.okstate.cs.bem.listintentstransitions.Util

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import edu.okstate.cs.bem.listintentstransitions.MainActivity
import edu.okstate.cs.bem.listintentstransitions.Model.ListItem
import edu.okstate.cs.bem.listintentstransitions.Model.State
import edu.okstate.cs.bem.listintentstransitions.R
import kotlinx.android.synthetic.main.rv_row_separator.view.*
import kotlinx.android.synthetic.main.rv_row_state.view.*
import org.jetbrains.anko.imageResource

// recycler view adapter with two different view holders

class Adapter(val items: ArrayList<ListItem>, val context: MainActivity): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // state list item view holder
    class StateViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvStateName: TextView? = view.tvStateName
        val tvStateNickname: TextView? = view.tvStateNickname
        val containerView: LinearLayout = view.containerView
        val iv: ImageView = view.iv
    }

    // separator list item view holder
    class SeparatorViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvSeparatorLabel: TextView? = view.tvSeparatorLabel
    }

    // create and return the view corresponding the the type of ListItem
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == ListItem.TYPE_STATE) {

            return StateViewHolder( LayoutInflater.from(context).inflate(
                R.layout.rv_row_state,
                parent,
                false
            ))
        }

        // View is of type separator
        return SeparatorViewHolder(LayoutInflater.from(context).inflate(
            R.layout.rv_row_separator,
            parent,
            false
        ))
    }

    // define viewType parameter in OnCreateViewHolder
    override fun getItemViewType(position: Int): Int {

        if (items[position].type == ListItem.TYPE_SEPARATOR) {
            return ListItem.TYPE_SEPARATOR
        }

        return ListItem.TYPE_STATE
    }

    // Called when a view in memory is rebounded to the list
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder.itemViewType == ListItem.TYPE_STATE) {
            val stateVh = holder as StateViewHolder
            stateVh.tvStateName?.text = items[position].stateName
            stateVh.tvStateNickname?.text = items[position].stateNickname

            // get the resource file name from the state name
            val resourceFileName = items[position].stateName!!.toLowerCase().replace(" ", "_")
            stateVh.iv.imageResource = context.resources.getIdentifier(resourceFileName, "drawable", context.packageName)

            // Set the right color of the state view
            if (items[position].isScored) {
                stateVh.containerView.setBackgroundColor(ContextCompat.getColor(context, R.color.isScored))
            } else stateVh.containerView.setBackgroundColor(ContextCompat.getColor(context, R.color.isNotScored))

            stateVh.containerView.setOnClickListener {
                    context.handleStateClick(items[position] as State, stateVh.containerView)
            }
        }
        else  {
            val separatorVh = holder as SeparatorViewHolder
            separatorVh.tvSeparatorLabel?.text = items[position].separatorLabel
        }
    }

    // return the size of the list
    override fun getItemCount(): Int {
        return items.size
    }
}