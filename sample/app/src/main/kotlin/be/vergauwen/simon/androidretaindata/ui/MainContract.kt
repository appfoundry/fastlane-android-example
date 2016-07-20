package be.vergauwen.simon.androidretaindata.ui

import be.vergauwen.simon.androidretaindata.core.model.GithubRepo
import be.vergauwen.simon.himurakotlin.MVPContract

/**
 * Created by Simon Vergauwen. github.com/nomisRev
 */

interface MainContract {
  interface View : MVPContract.View {
    fun showText(string : String)
    fun showError(t: Throwable)
  }

  interface Presenter<V : View> : MVPContract.Presenter<V> {
    fun loadRepos(reload: Boolean)
  }

  interface Component<V : View, P : Presenter<V>> : MVPContract.Component<V, P>
}