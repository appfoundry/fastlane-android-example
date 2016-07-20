package be.vergauwen.simon.androidretaindata.core.data

import be.vergauwen.simon.androidretaindata.core.model.GithubRepo
import be.vergauwen.simon.androidretaindata.core.service.GithubAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Simon Vergauwen. github.com/nomisRev
 */

class DataRepository(private val githubAPI: GithubAPI) {

  private var result: Response<List<GithubRepo>>? = null
  private var call: Call<List<GithubRepo>>? = null

  fun getData(reload: Boolean, callback: Callback<List<GithubRepo>>) {
    if (reload) {
      result = null
      call = null
    }

    if (result == null || call == null) {
      githubAPI.getReposCall().enqueue(object : Callback<List<GithubRepo>> {
        override fun onResponse(call: Call<List<GithubRepo>>?,
            response: Response<List<GithubRepo>>?) {
          this@DataRepository.call = call
          this@DataRepository.result = response
          callback.onResponse(call, result)
        }

        override fun onFailure(call: Call<List<GithubRepo>>?, t: Throwable?) {
          callback.onFailure(call, t)
        }
      })
    } else {
      callback.onResponse(call, result)
    }
  }
}