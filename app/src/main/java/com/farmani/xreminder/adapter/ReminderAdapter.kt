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
import com.farmani.xreminder.fragment.dataStore
import com.farmani.xreminder.fragment.remindersListBinding
import com.farmani.xreminder.model.Reminder
import kotlinx.collections.immutable.mutate
import kotlinx.coroutines.runBlocking

class ReminderAdapter(var reminderList: MutableList<Reminder>, var context: Context) :
    RecyclerView.Adapter<ReminderAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var card = itemView.findViewById<CardView>(R.id.cardView)
        var title = itemView.findViewById<TextView>(R.id.titleTV)
        var memo = itemView.findViewById<TextView>(R.id.memoTV)
        var date = itemView.findViewById<TextView>(R.id.dateTV)
        var time = itemView.findViewById<TextView>(R.id.timeTV)
        var isCompleted = itemView.findViewById<CheckBox>(R.id.completedCB)

        init {
            isCompleted.setOnCheckedChangeListener { button, isChecked ->
                if (isChecked) {
                    runBlocking {
                        context.dataStore.updateData {
                            it.copy(
                                it.remindersList.mutate {
                                    it.removeAt(adapterPosition)
                                }
                            )
                        }
                    }
                    remindersListBinding.recyclerView.adapter!!.notifyItemRemoved(adapterPosition)
                }
            }
        }
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
            isCompleted.isChecked = reminderList[position].completed
        }
    }

    override fun getItemCount(): Int {
        return reminderList.size
    }


}