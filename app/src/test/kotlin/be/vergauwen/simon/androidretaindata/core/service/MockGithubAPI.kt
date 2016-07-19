package be.vergauwen.simon.androidretaindata.core.service

import be.vergauwen.simon.androidretaindata.core.model.GithubRepo
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rx.Observable

class MockGithubAPI : GithubAPI {

  var githubRepo = listOf(GithubRepo("test", "www.test.com", "test_desc"))
  var callSuccess = true
  val exception = RetrofitCallFailedException("Call failed")

  override fun getRepos(): Observable<List<GithubRepo>> = Observable.just(githubRepo)

  override fun getReposCall(): Call<List<GithubRepo>> {
    return object : retrofit2.Call<List<GithubRepo>>{
      override fun request(): Request? {
        throw UnsupportedOperationException("Not required for test")
      }

      override fun isExecuted(): Boolean {
        throw UnsupportedOperationException("Not required for test")
      }

      override fun execute(): Response<List<GithubRepo>>? {
        throw UnsupportedOperationException("Not required for test")
      }

      override fun cancel() {
        throw UnsupportedOperationException("Not required for test")
      }

      override fun isCanceled(): Boolean {
        throw UnsupportedOperationException("Not required for test")
      }

      override fun clone(): Call<List<GithubRepo>> {
        throw UnsupportedOperationException("Not required for test")
      }

      override fun enqueue(callback: Callback<List<GithubRepo>>?) {
        if(callSuccess){
          callback?.onResponse(this, Response.success(githubRepo))
        } else {
          callback?.onFailure(this, exception)
        }
      }
    }
  }
}

class RetrofitCallFailedException(message : String) : Exception(message)