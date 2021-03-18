package th.ac.su.cp.assignprior.model

import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("test/job_list.json")
    fun getJobList() : Call<List<JobList>>

}