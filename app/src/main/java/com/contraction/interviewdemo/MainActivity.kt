package com.contraction.interviewdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.AbsListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.contraction.interviewdemo.adapter.DateAdapter
import com.contraction.interviewdemo.apitools.ApiClient.Companion.getRetrofit
import com.contraction.interviewdemo.apitools.ApiInterface
import com.contraction.interviewdemo.modelclass.ApiDemoClass
import com.contraction.interviewdemo.modelclass.DateModelClass
import com.contraction.interviewdemo.modelclass.Datum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var lm: LinearLayoutManager
    private lateinit var dateAdapter: DateAdapter
    private lateinit var progress: ProgressBar
    private var page = 1;
    var list: List<Datum> = ArrayList<Datum>()
    var dateList: ArrayList<DateModelClass> = ArrayList<DateModelClass>()
    private var rvVirew: RecyclerView? = null

    var isLoading = false
    val limit = 10

    @SuppressLint("NewApi")
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        getWindow().setStatusBarColor(getColor(R.color.teal_200))
        initIdes()
        callApi()
        ///getpage();


        rvVirew!!.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                //if (dy > 0){
                    val visibleItemCount  = lm.childCount
                    val pastvisibleItem = lm.findLastCompletelyVisibleItemPosition()
                    val total = dateAdapter.itemCount;

                    if (isLoading){
                            if ((visibleItemCount + pastvisibleItem) >= total){
                                page++;
                                //getpage()
                            }
                  //  }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })



    }

    /*private fun getpage() {

        isLoading = true;
        progress.visibility = View.VISIBLE
        val start = (page - 1) * limit;
        val end = (page) * limit;
        for (i in start..end) {
           // callApi();
        }
        Handler().postDelayed({
            if (::dateAdapter.isInitialized){
                dateAdapter.notifyDataSetChanged();
            }else{
                dateAdapter = DateAdapter(this@MainActivity, list, dateList)
                rvVirew!!.adapter  = dateAdapter
            }
            isLoading = false
            progress.visibility = View.GONE
        },5000)

    }*/

    private fun initIdes() {
        rvVirew = findViewById<RecyclerView>(R.id.rvVirew)
        progress = findViewById<ProgressBar>(R.id.progress);
    }

    private fun callApi() {

        // For My Api It's Not Work
        /*http://www.remyndr.org/remyndr/services2.0/index.php?action=findvendorpickupdetail & user_address=100%20Afterglow%20Avenue,%20Verona,%20NJ,%20United%20States & vendor_id=1 &
         route_id=2 & udid=8F7CBFA0-0D93-418C-BA7E-6075A60B55C1*/
        var apiInterface = getRetrofit().create(ApiInterface::class.java)
        apiInterface.setdata("findvendorpickupdetail",
            "100%20Afterglow%20Avenue,%20Verona,%20NJ,%20United%20States",
            "1",
            "2",
            "8F7CBFA0-0D93-418C-BA7E-6075A60B55C1")
            .enqueue(object : Callback<ApiDemoClass> {
                override fun onResponse(
                    call: Call<ApiDemoClass>,
                    response: Response<ApiDemoClass>,
                ) {
                    progress.visibility = View.GONE
                    Toast.makeText(this@MainActivity, "Api Successfull call", Toast.LENGTH_SHORT)
                        .show()
                    Log.e("TAG", "onResponse: Api Successfull call ")
                    list = response.body()!!.data!!
                    var i = 0
                    while (i < list.size) {
                        val date: String = list[i].date.toString()
                        val dateModelClass = DateModelClass(i, date)
                        dateList.add(dateModelClass)
                        Log.e("TAG", "onResponse: Dates : ==== " + dateList[i].date)
                        i++
                    }
                    setAdapter(list, dateList)
                }

                override fun onFailure(call: Call<ApiDemoClass>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Api fail", Toast.LENGTH_SHORT).show()
                    Log.e("TAG", "onFailure: Api fail")
                }
            })


        // for News Api
//https://newsapi.org/v2/everything?q=tesla&from=2022-09-17&sortBy=publishedAt&apiKey=c690104f105e4be0b0c73f2b6ac2ff9d
        /*apiInterface = getRetrofit().create(ApiInterface.class);
        apiInterface.setdata("tesla","2022-09-17","publishedAt","c690104f105e4be0b0c73f2b6ac2ff9d").enqueue(new Callback<NewsModelClass>() {
            @Override
            public void onResponse(Call<NewsModelClass> call, Response<NewsModelClass> response) {
                Toast.makeText(MainActivity.this, "Api Successfull call", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onResponse: Api Successfull call " );
            }

            @Override
            public void onFailure(Call<NewsModelClass> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Api fail", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: Api fail" );
            }
        });
*/
    }

    private fun setAdapter(list: List<Datum>, dateList: ArrayList<DateModelClass>) {
         dateAdapter = DateAdapter(this@MainActivity, list, dateList)
         lm =
            LinearLayoutManager(this@MainActivity,
                RecyclerView.VERTICAL,
                false) as LinearLayoutManager
        rvVirew!!.setAdapter(dateAdapter)
        rvVirew!!.setLayoutManager(lm)


        rvVirew!!.addOnScrollListener(object : DateAdapter.PaginationScrollListener(lm) {
            override fun loadMoreItems() {
                progress.setVisibility(View.VISIBLE)

                page = page + 1
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed(Runnable {
                    progress.setVisibility(View.GONE)
                    dateAdapter.updateSize()
                }, 2000)
            }

            override val totalPageCount: Int
                get() = page
            override val isLastPage: Boolean
                get() = false
            override val isLoading: Boolean
                get() = false
        })
    }


}