package th.ac.su.cp.assignprior.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import th.ac.su.cp.assignprior.model.JobList
import th.ac.su.cp.assignprior.R
import java.text.SimpleDateFormat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //get Intent and get list fromJson
        val item = intent.getStringExtra("list")
        val jobList : JobList = Gson().fromJson(item, JobList::class.java)

        //set data to TextView
        val jobNoTV = findViewById<TextView>(R.id.jobNotextView)
        jobNoTV.append(" "+jobList.jobNo)

        //set data to TextView
        val truckLicense = findViewById<TextView>(R.id.trucklycense_textView)
        truckLicense.append(" "+jobList.truckLicense)

        //set data to TextView
        val province = findViewById<TextView>(R.id.province_textView)
        province.append(" "+jobList.province)

        //set data to TextView
        val truckType = findViewById<TextView>(R.id.trucktype_textView)
        truckType.append(" "+jobList.truckType)

        //set data to TextView
        val routeDt = findViewById<TextView>(R.id.routeDT_textView)
        routeDt.append(" "+getDateNewFormat(jobList.routeDt!!,"yyyy/MM/dd","dd MMM yyyy"))

        //set data to TextView
        val routeCd = findViewById<TextView>(R.id.routeCd_textView)
        routeCd.append(" "+jobList.routeCd)

        //set data to TextView
        val logisticPointCd = findViewById<TextView>(R.id.logisticPointCd_textView)
        logisticPointCd.append(" "+jobList.logisticPointCd)

        //set data to TextView
        val arrivalDt = findViewById<TextView>(R.id.arrivalDt_textView)
        arrivalDt.append(" "+getDateNewFormat(jobList.arrivalDt!!,"yyyy/MM/dd hh:mm","dd MMM yyyy hh:mm"))

        //set data to TextView
        val departureDt = findViewById<TextView>(R.id.departureDt_textView)
        departureDt.append(" "+getDateNewFormat(jobList.departureDt!!,"yyyy/MM/dd hh:mm","dd MMM yyyy hh:mm"))

        //set onclick on BACK button
        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
    }
    //fun to format date
    fun getDateNewFormat(date:String,beginFormat:String,endFormat:String):String{
        val formatter1 = SimpleDateFormat(beginFormat)
        val newDate = formatter1.parse(date)
        val formatter2 = SimpleDateFormat(endFormat)
        val date = formatter2.format(newDate)
        return date
    }
}