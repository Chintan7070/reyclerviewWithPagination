package com.contraction.interviewdemo.adapter

import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.contraction.interviewdemo.MainActivity
import com.contraction.interviewdemo.R
import com.contraction.interviewdemo.modelclass.DateModelClass
import com.contraction.interviewdemo.modelclass.Datum
import com.contraction.interviewdemo.modelclass.NameColorClass
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateAdapter(
    mainActivity: MainActivity,
    list: List<Datum>,
    dateList: ArrayList<DateModelClass>,
) : RecyclerView.Adapter<DateAdapter.ViewData?>() {
    var size = 10

    private val activity: MainActivity
    private val list: List<Datum>
    private val dateList: ArrayList<DateModelClass>
    private val multiList: List<Datum> = ArrayList<Datum>()
    private var nameValueList: MutableList<NameColorClass> = ArrayList<NameColorClass>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        val view: View = LayoutInflater.from(activity).inflate(R.layout.date_list, parent, false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        val dateNumber: String = dateList[position].date.toString()
        val daynames = chnageDateFormater(dateNumber)
        holder.txtDate.text = daynames

        try {
            val names: String = list[position].name.toString()
            if (names.indexOf(',') >= 0) {
                val separated = names.split(",").toTypedArray()
                var i = 0
                nameValueList = ArrayList<NameColorClass>()
                nameValueList.clear()
                while (i < separated.size) {
                    val nameColorClass =
                        NameColorClass(separated[i], list[position].textColor.toString())
                    nameValueList.add(nameColorClass)
                    i++
                }
            } else {
                nameValueList = ArrayList<NameColorClass>()
                nameValueList.clear()
                val nameColorClass = NameColorClass(list[position].name.toString(),
                    list[position].textColor.toString())
                nameValueList.add(nameColorClass)
            }
        } catch (e: Exception) {
            nameValueList = ArrayList<NameColorClass>()
            nameValueList.clear()
            val nameColorClass =
                NameColorClass("Null Fild", list[position].textColor.toString())
            nameValueList.add(nameColorClass)
        }
        setSubAdapter(holder, nameValueList)
    }

    private fun chnageDateFormater(dateNumber: String): String {
        val inFormat = SimpleDateFormat("yyyy-MM-dd")
        var date: Date? = null
        try {
            date = inFormat.parse(dateNumber)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val daynames = DateFormat.format("EEEE, MMM dd", date) as String
        Log.e("TAG", "onBindViewHolder: Day Name With Date -- $date ----------  :$daynames")
        return daynames
    }

    private fun setSubAdapter(holder: ViewData, nameValueList: List<NameColorClass>) {
        val namesAdapter = NamesAdapter(activity, nameValueList)
        val lm: RecyclerView.LayoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        holder.subRvView.setLayoutManager(lm)
        holder.subRvView.setAdapter(namesAdapter)
    }

    override fun getItemCount(): Int {
        return size
    }

    fun updateSize() {
        if (size < list.size) {
            val i: Int = list.size - size
            if (i < 6) {
                this.size = this.size + 1
            } else {
                this.size = this.size + 6
            }
            notifyDataSetChanged()
        }
    }

    inner class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtDate: TextView
        val subRvView: RecyclerView

        init {
            txtDate = itemView.findViewById<TextView>(R.id.txtDate)
            subRvView = itemView.findViewById<RecyclerView>(R.id.subRvView)
        }
    }

    init {
        activity = mainActivity
        this.list = list
        this.dateList = dateList
    }

    abstract class PaginationScrollListener(private val layoutManager: LinearLayoutManager) :
        RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            if (!isLoading && !isLastPage) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                ) {
                    loadMoreItems()
                }
            }
        }

        protected abstract fun loadMoreItems()
        abstract val totalPageCount: Int
        abstract val isLastPage: Boolean
        abstract val isLoading: Boolean

    }

}