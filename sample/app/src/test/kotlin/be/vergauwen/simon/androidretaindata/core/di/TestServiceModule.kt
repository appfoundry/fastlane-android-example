package be.vergauwen.simon.androidretaindata.core.di

import be.vergauwen.simon.androidretaindata.core.data.RxDataRepository
import be.vergauwen.simon.androidretaindata.core.service.GithubAPI
import be.vergauwen.simon.androidretaindata.core.service.MockGithubAPI
import retrofit2.Retrofit

class TestServiceModule : ServiceModule(){
  override fun provideDataRepository(githubAPI: GithubAPI): RxDataRepository = RxDataRepository(githubAPI)

  override fun provideGithubAPI(retrofit: Retrofit): GithubAPI = MockGithubAPI()
}