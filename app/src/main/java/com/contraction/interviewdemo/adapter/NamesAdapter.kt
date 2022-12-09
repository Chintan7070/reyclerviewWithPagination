package com.contraction.interviewdemo.adapter

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.contraction.interviewdemo.MainActivity
import com.contraction.interviewdemo.R
import com.contraction.interviewdemo.modelclass.NameColorClass
import java.lang.Exception

class NamesAdapter(activity: MainActivity, nameValueList: List<NameColorClass>) :
    RecyclerView.Adapter<NamesAdapter.NameViewData?>() {
    private val activity: Activity
    private val nameValueList: List<NameColorClass>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewData {
        val view: View = LayoutInflater.from(activity).inflate(R.layout.name_list, parent, false)
        return NameViewData(view)
    }

    override fun onBindViewHolder(holder: NameViewData, position: Int) {
        try {
            val names: String = nameValueList[position].ncName.toString()
            holder.txtName.setText(names)
            holder.txtName.setTextColor(Color.parseColor(nameValueList[position].ncColor
                .toString()))
            //Log.e("TAG", "onBindViewHolder: Color for Text :cccccccccccccccccccccccc "+multiList.get(position).getTextColor().toString() );
        } catch (e: Exception) {
            Toast.makeText(activity, "null", Toast.LENGTH_SHORT).show()
            holder.txtName.setText("Api Null Fild")
            holder.txtName.setTextColor(Color.parseColor(nameValueList[position].ncColor
                .toString()))
            //Log.e("TAG", "onBindViewHolder: Color for Text :222222222222cccccccccccccccccccccccc "+multiList.get(position).getTextColor().toString() );
        }
    }

    override fun getItemCount(): Int {
        return nameValueList.size
    }

    inner class NameViewData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView

        init {
            txtName = itemView.findViewById<TextView>(R.id.txtName)
        }
    }

    init {
        this.activity = activity
        this.nameValueList = nameValueList
    }


}