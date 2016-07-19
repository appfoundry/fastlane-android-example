package be.vergauwen.simon.androidretaindata.core.service

import be.vergauwen.simon.androidretaindata.core.model.GithubRepo
import retrofit2.Call
import retrofit2.http.GET
import rx.Observable

interface GithubAPI {

  @GET("/users/google/repos")
  fun getRepos(): Observable<List<GithubRepo>>


  @GET("/users/google/repos")
  fun getReposCall() : Call<List<GithubRepo>>
}