package th.ac.su.cp.assignprior.viewmodel

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import th.ac.su.cp.assignprior.R
import th.ac.su.cp.assignprior.model.JobList
import th.ac.su.cp.assignprior.view.DetailActivity

class ItemAdapter( val mContext:Context, val jobList: List<JobList>?) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list,parent,false))
    }
    //set size of recycler view
    override fun getItemCount() :Int {return jobList!!.size}
    //holder item on recycler view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.jobNoTV.append(" "+jobList!!.get(position).jobNo)
        holder.truckLicenseTV.append(" "+jobList!!.get(position).truckLicense)
        //set onclick View
        holder.view.setOnClickListener(View.OnClickListener {
            val list : JobList = jobList.get(position)
            val itemJson : String = Gson().toJson(list)
            //intent to Detail
            val intent = Intent(mContext, DetailActivity::class.java).apply {
                putExtra("list",itemJson)
            }
            mContext.startActivity(intent)
        })
    }
    inner class ViewHolder(rootview : View) : RecyclerView.ViewHolder(rootview){
        //create variable with TextView
        val jobNoTV = rootview.findViewById<TextView>(R.id.jobNo_textView)
        val truckLicenseTV = rootview.findViewById<TextView>(R.id.truckLicense_textView)
        val view = rootview
    }
}