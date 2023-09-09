package com.farmani.xreminder.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.farmani.xreminder.R
import com.farmani.xreminder.model.Reminder

class ReminderAdapter(var reminderList: MutableList<Reminder>, var context: Context) :
    RecyclerView.Adapter<ReminderAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var card = itemView.findViewById<CardView>(R.id.cardView)
        var title = itemView.findViewById<TextView>(R.id.titleTV)
        var memo = itemView.findViewById<TextView>(R.id.memoTV)
        var date = itemView.findViewById<TextView>(R.id.dateTV)
        var time = itemView.findViewById<TextView>(R.id.timeTV)
        var completed = itemView.findViewById<CheckBox>(R.id.completedCB)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_reminder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            title.text = reminderList[position].title
            memo.text = reminderList[position].memo
            date.text = reminderList[position].date
            time.text = reminderList[position].time
            completed.isChecked = reminderList[position].completed
        }
    }

    override fun getItemCount(): Int {
        return reminderList.size
    }


}