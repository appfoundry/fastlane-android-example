package be.vergauwen.simon.androidretaindata.core.data

import be.vergauwen.simon.androidretaindata.core.model.GithubRepo
import be.vergauwen.simon.androidretaindata.core.service.GithubAPI
import rx.Observable
import rx.observables.ConnectableObservable
import rx.schedulers.Schedulers

/**
 * Created by Simon Vergauwen. github.com/nomisRev
 */

class RxDataRepository(private val githubAPI: GithubAPI) {

  private var request: ConnectableObservable<List<GithubRepo>>? = null

  fun getData(reload: Boolean): Observable<List<GithubRepo>> {
    if (reload) {
      clearData()
    }

    if (request == null) {
      request = githubAPI.getRepos().subscribeOn(Schedulers.io()).first().replay()
      request!!.connect() //start doing the background call
    }

    return request!!
  }

  private fun clearData() {
    request?.connect { it.unsubscribe() }
    request = null
  }
}