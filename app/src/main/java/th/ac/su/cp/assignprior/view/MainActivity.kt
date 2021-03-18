package th.ac.su.cp.assignprior.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import th.ac.su.cp.assignprior.model.ApiService
import th.ac.su.cp.assignprior.viewmodel.ItemAdapter
import th.ac.su.cp.assignprior.model.JobList
import th.ac.su.cp.assignprior.R

class MainActivity : AppCompatActivity() {
    //Create variable for RecyclerView
    var mRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Set id with RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView) as RecyclerView?
        //Create LinearLayout
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)
        //Build retrofit
        val ret = Retrofit.Builder()
            .baseUrl("https://dev.priorsolution.co.th/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = ret.create(ApiService::class.java)
        //Call ApiService.getJobList()
        val call = apiService.getJobList()
        call.enqueue(object : Callback<List<JobList>>{
            override fun onFailure(call: Call<List<JobList>>, t: Throwable) {
                //check error
                Log.e("Error",t.toString())
            }

            override fun onResponse(call: Call<List<JobList>>, response: Response<List<JobList>>) {
                if(response.isSuccessful){
                    //response
                    val list:List<JobList>? = response.body()
                    //send list to adapter
                    val adapter : ItemAdapter = ItemAdapter(this@MainActivity,list)
                    mRecyclerView!!.adapter = adapter

//                    val jobNoTV: TextView = findViewById(R.id.textView)
//                    jobNoTV.append(" ${list?.get(0)!!.jobNo}")
//                    Log.i("API", "${list!!.size}")
                }
            }
        })
    }
}