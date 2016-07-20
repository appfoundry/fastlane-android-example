package be.vergauwen.simon.androidretaindata.core.di

import be.vergauwen.simon.androidretaindata.BuildConfig
import be.vergauwen.simon.androidretaindata.core.data.RxDataRepository
import be.vergauwen.simon.androidretaindata.core.service.GithubAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class ServiceModule {

  val URI = "https://api.github.com"

  @ApplicationScope
  @Provides
  open fun provideRestAdapter(): Retrofit {
    val logging = HttpLoggingInterceptor();

    if (BuildConfig.DEBUG) {
      logging.level = HttpLoggingInterceptor.Level.BODY;
    } else {
      logging.level = HttpLoggingInterceptor.Level.NONE;
    }

    val httpClient = OkHttpClient.Builder();
    httpClient.interceptors().add(logging)

    return Retrofit.Builder().baseUrl(URI)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .client(httpClient.build())
        .build()
  }

  @ApplicationScope
  @Provides
  open fun provideGithubAPI(retrofit: Retrofit): GithubAPI = retrofit.create(GithubAPI::class.java)

  @ApplicationScope
  @Provides
  open fun provideDataRepository(githubAPI: GithubAPI): RxDataRepository = RxDataRepository(githubAPI)

}